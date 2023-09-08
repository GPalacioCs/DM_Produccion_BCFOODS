package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.OrdenCompraDAO;
import com.dosrobles.produccion.dao.OrdenCompraLineaDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadoRecepcion;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.models.RptLineaOrdenRecepcion;
import com.dosrobles.produccion.models.RptOrdenRecepcion;
import com.dosrobles.produccion.models.RptVoucherRecepcion;
import com.dosrobles.produccion.utils.Pair;
import com.dosrobles.produccion.utils.Utils;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.Console;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

@Stateless
public class OrdenCompraService extends AbstractService<OrdenCompraDAO, OrdenCompra> {
    @Inject
    private OrdenCompraLineaDAO ordenCompraLineaDAO;
    @Inject
    private GlobalesCOService globalesCOService;
    @Inject
    private PrecioArtProvService precioArtProvService;
    @Inject
    private EmpaqueEncabezadoService empaqueEncabezadoService;
    @Inject
    private ParametrosPrService parametrosPrService;
    @Inject
    private OrdenProduccionService ordenProduccionService;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private TermoService termoService;
    @Inject
    private EmpaqueService empaqueService;
    @Inject
    private PesadaService pesadaService;
    
    public String getConsecutivo() {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        return Utils.generarConsecutivo(globales.getUltOrdenCompra());
    }
    
    @Override
    public OrdenCompra insert(OrdenCompra entity) throws BusinessValidationException {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        String consecOrden = getConsecutivo();
        globales.setUltOrdenCompra(consecOrden);
        entity.setOrdenCompra(consecOrden);
        entity.setMoneda(entity.getProveedor().getMoneda());
        entity.setCondicionPago(entity.getProveedor().getCondicionPago());
        return super.insert(entity);
    }
    
    @Override
    public OrdenCompra save(OrdenCompra orden, String schema) throws BusinessValidationException {
        orden.getLineas().clear();
        orden.getLineas().addAll(getLineasFromPesadas(orden));
        return super.save(orden, schema);
    }
    
    public List<OrdenCompra> findByModuloOrigen(String modulo) {
        return dao.findByModuloOrigen(modulo);
    }
    
    public List<OrdenCompraLinea> getLineasFromPesadas(OrdenCompra orden) {
        List<OrdenCompraLinea> lineas = new ArrayList<>();
        
        List<Pesada> pesadas = orden.getPesadas();
        if(pesadas.isEmpty()) return lineas;
    
        ParametrosPr params = parametrosPrService.getParametro();
        
        
        List<PrecioArtProv> precioArtProvList = precioArtProvService.findByProveedor(orden.getProveedor());
    
        Map<Pair<Articulo, Bodega>, BigDecimal> map = new HashMap<>();
        Map<Pair<Articulo, Bodega>, BigDecimal> mapRechazo = new HashMap<>();

        for (Pesada pesada : pesadas) {
            Bodega bodega = pesada.isExportacion() ? params.getBodegaExportacion() : params.getBodegaInventario();
            Pair<Articulo, Bodega> pair = new Pair(pesada.getArticulo(), bodega);
            map.put(pair, map.getOrDefault(pair, BigDecimal.ZERO).add(pesada.getPeso()));
            mapRechazo.put(pair, mapRechazo.getOrDefault(pair, BigDecimal.ZERO).add(pesada.getRechazo()));
        }
    
        for (Map.Entry<Pair<Articulo, Bodega>, BigDecimal> entry : map.entrySet()) {
//            OrdenCompraLinea linea = lineas.stream().filter(l -> l.getArticulo().equals(entry.getKey())).findFirst().orElse(new OrdenCompraLinea());
            OrdenCompraLinea linea = new OrdenCompraLinea();
            linea.setArticulo(entry.getKey().getFirst());
            linea.setCantidadOrdenada(entry.getValue());
            linea.setCantidadRecibida(BigDecimal.ZERO);
            linea.setCantidadRechazada(mapRechazo.get(entry.getKey()));
            linea.setEstado("A");
            linea.getOrdenCompraLineaId().setOrdenCompraLinea((short)(lineas.stream().mapToInt(l -> l.getOrdenCompraLineaId().getOrdenCompraLinea()).max().orElse(0) + 1));
            BigDecimal precio = precioArtProvList.stream().filter(p -> p.getCantidadHasta().compareTo(map.get(entry.getKey())) >= 0)
                    .sorted(Comparator.comparing(PrecioArtProv::getCantidadHasta))
                    .map(p -> p.getPrecio()).findFirst().orElse(BigDecimal.ZERO);
            
            linea.setPrecioUnitario(precio);
            linea.setPrecioArtProv(precio);
            linea.setBodega(entry.getKey().getSecond());
            linea.setOrdenCompra(orden);
            lineas.add(linea);
        }
        return lineas;
    }

    public void enviarAProduccion(OrdenCompra orden) {
        crearEmpaque(orden);
    }
    
    public void aprobarRecepcion(OrdenCompra orden) {
        if (orden.isAlinsa()){
            if (orden.isBarcoAlinsa()){
                crearEmpaque(orden);
                agregarMpOrdenProduccion(orden);
                actualizarTermos(orden);
            }
            ejecutarProcedimientoAlinsa(orden);
        }else{
            crearEmpaque(orden);
            agregarMpOrdenProduccion(orden);
            actualizarTermos(orden);
        }
        OrdenCompra dbOrden = find(orden.getOrdenCompra());
        dbOrden.setEstadoRecepcion(EstadoRecepcion.APROBADO);
    }
    
    public void crearEmpaque(OrdenCompra orden) {
        OrdenProduccion ordenProduccion = orden.getOrdenProduccion();
        if(ordenProduccion.getEstadoProd().equals(EstadosProd.LIBERADO)) {
            throw new BusinessValidationException("La orden ha sido liberada");
        }
    
        if(ordenProduccion.isEmpaqueAprobado()) {
            throw new BusinessValidationException("La lista de empaques ya ha sido aprobada");
        }
    
        Map<String, EmpaqueEncabezado> empaqueMap = new HashMap<>();
    
        List<Empaque> empaqueList = new ArrayList<>();
    
        for (Pesada pesada : orden.getPesadas()) {
            if(pesada.getCliente() == null || !pesada.isFresco() || pesada.isEnProduccion() ) continue;
            Cliente cliente = pesada.getCliente();
            if (!empaqueMap.containsKey(cliente.getCliente())) {
                EmpaqueEncabezado encabezado = empaqueEncabezadoService.findByOrdenProduccionAndCliente(ordenProduccion, cliente);
                if(encabezado == null){
                    encabezado = new EmpaqueEncabezado();
                    encabezado.setOrdenProduccion(ordenProduccion);
                    encabezado.setCliente(pesada.getCliente());
                }
                empaqueMap.put(cliente.getCliente(), encabezado);
            }
    
            EmpaqueEncabezado encabezado = empaqueMap.get(cliente.getCliente());
            Empaque empaque = new Empaque();
            int currentEmpaque = empaqueList.isEmpty() ? getCurrentEmpaque(encabezado) : getCurrentEmpaque(empaqueList);
//            empaque.getId().setEmpaque(currentEmpaque);
            empaque.getId().setEmpaque(pesada.getCaja());
            empaque.setArticulo(pesada.getProductoTerminado());
            if (pesada.getCaja() != null) {
                empaque.setCajas(BigDecimal.valueOf(pesada.getCaja()));
            }
            empaque.setEmpaqueEncabezado(encabezado);
            empaque.setCliente(cliente);
            empaque.setPesoNeto(pesada.getPeso());
            empaque.setPesoInyectado(pesada.getPeso());
            empaque.setPesoBruto(pesada.getPeso());
            empaque.setPescados(BigDecimal.ZERO);
            empaque.setEmpacador("00");
//            encabezado.getEmpaqueList().add(empaque);
            empaqueList.add(empaque);
            pesadaService.setEnProduccion(pesada);
        }
    
        for (EmpaqueEncabezado encabezado : empaqueMap.values()) {
            if (!ordenProduccion.getEmpaqueEncabezadoList().contains(encabezado)) {
                empaqueEncabezadoService.insert(encabezado);
            }
        }
    
        for (Empaque empaque : empaqueList) {
            empaqueService.insert(empaque);
        }
        
    }
    
    private void agregarMpOrdenProduccion(OrdenCompra orden) {
        OrdenProduccion op = ordenProduccionService.findByEntity(orden.getOrdenProduccion());
        ParametrosPr params = parametrosPrService.getParametro();
        for (OrdenCompraLinea linea : orden.getLineas()) {
            if (!params.getBodegaExportacion().getBodega().equals(params.getBodegaInventario())
                    && params.getBodegaInventario().getBodega().equals(linea.getBodega().getBodega())) {
                continue;
            }
            OrdenProdMp mp = new OrdenProdMp();
            mp.setOrdenProduccion(op);
            mp.setProducto(new Articulo("ND"));
            mp.getId().setProducto("ND");
            mp.setComponente(linea.getArticulo());
            int num = -1;
            String lote = new SimpleDateFormat("ddMMyyyy").format(new Date());
            for (int i = 0; i < orden.getOrdenCompra().length(); i++) {
                String substring = orden.getOrdenCompra().substring(i);
                if (StringUtils.isNumeric(substring)) {
                    BigInteger n = new BigInteger(substring);
                    lote += n.toString();
                    break;
                }
            }
            mp.setLote(lote);
            mp.setCosto(mp.getComponente().getCostoPromLoc());
            mp.setCostoDolar(mp.getComponente().getCostoPromDol());
            mp.setCantidad(linea.getCantidadOrdenada());
            op.getOrdenProdMpList().add(mp);
        }
        
    }
    
    private void actualizarTermos(OrdenCompra orden) {
        List<Termo> termoList = termoService.findAll();
        Map<Termo, Map<Articulo, ArticuloTermo>> termoMap = new HashMap<>();
        for (Termo termo : termoList) {
            Map<Articulo, ArticuloTermo> artMap = new HashMap<>();
            for (ArticuloTermo articuloTermo : termo.getArticuloTermoList()) {
                artMap.put(articuloTermo.getArticulo(), articuloTermo);
            }
            termoMap.put(termo, artMap);
        }
        for (Pesada pesada : orden.getPesadas()) {
            if (!pesada.isExportacion()) {
                Map<Articulo, ArticuloTermo> artMap = termoMap.get(pesada.getTermo());
                if (!artMap.containsKey(pesada.getArticulo())) {
                    ArticuloTermo articuloTermo = new ArticuloTermo();
                    articuloTermo.setArticulo(pesada.getArticulo());
                    articuloTermo.setTermo(pesada.getTermo());
                    articuloTermo.setPeso(BigDecimal.ZERO);
                    artMap.put(pesada.getArticulo(), articuloTermo);
                }
                ArticuloTermo articuloTermo = artMap.get(pesada.getArticulo());
                articuloTermo.setPeso(articuloTermo.getPeso().add(pesada.getPeso()));
            }
        }
        for (Termo termo : termoList) {
            termo.getArticuloTermoList().clear();
            termo.getArticuloTermoList().addAll(termoMap.get(termo).values());
        }
    }

    public void ejecutarProcedimientoAlinsa(OrdenCompra ordenCompra){
        Query q = dao.getEm().createNativeQuery("EXEC ALINSA.CS_SP_ORDEN_COMPRA_ALINSA ?1");
        q.setParameter(1, ordenCompra.getOrdenCompra());
        q.executeUpdate();
    }
    
    public void imprimirOrden(OrdenCompra orden, OutputStream outputStream, String sourceFileName) {
        Map<String, Object> params = new HashMap<>();
        params.put("p_numOrdenCompra", orden.getOrdenCompra());
        params.put("p_proveedor", orden.getProveedor().toString());
        params.put("p_ordenProduccion", orden.getOrdenProduccion().getOrdenProduccion());
    
        List<RptOrdenRecepcion> rptList = orden.getPesadas().stream().map(p -> RptOrdenRecepcion.from(p))
                .collect(Collectors.toList());

        String templatePath = sourceFileName + "/rpt_orden_recepcion.jrxml";
        /*File templateFile = new File(templatePath);
        if (!templateFile.exists()) {
            templatePath = getClass().getClassLoader().getResource("templates/ReportTemplates/pedido.jrxml").getFile();
        }*/

        try  {
        
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            JasperReportBuilder rpt = report()
                    .setParameters(params)
                    .setDataSource(rptList)
                    .setTemplateDesign(templatePath)
                    .summary(cmp.subreport(getLineasSubreprt(orden, sourceFileName)))
                    .toPdf(outputStream);
        
        
        } catch (DRException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inesperado");
        }
    }
    
    private JasperReportBuilder getLineasSubreprt(OrdenCompra orden, String folder) throws DRException {
        
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Usuario usuario = usuarioService.findUsuarioByUsername(username);
        
        List<RptLineaOrdenRecepcion> rptList = orden.getLineas().stream().map(l -> RptLineaOrdenRecepcion.from(l))
                .collect(Collectors.toList());
        
        JasperReportBuilder rpt = report()
                .setDataSource(rptList)
                .setParameter("p_entregadoPor", orden.getProveedor().getNombre())
                .setParameter("p_recibidoPor", usuario.getNombre())
                .setTemplateDesign(folder+"/rpt_linea_orden_recepcion.jrxml");
        return rpt;
    }

    public void imprimirVoucher(OrdenCompra orden, OutputStream outputStream, String sourceFileName){
        Map<String, Object> params = new HashMap<>();
        params.put("p_numOrdenCompra", orden.getOrdenCompra());
        params.put("p_proveedor", orden.getProveedor().getNombre());

        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        params.put("p_fecha", fecha.format(formatter));

        List<RptVoucherRecepcion> recepcionList = orden.getLineas().stream().map(RptVoucherRecepcion::from)
                .collect(Collectors.toList());

        String templatePath = sourceFileName + "/rpt_voucher_recepcion.jrxml";

        try {
            JasperReportBuilder rpt = report()
                    .setDataSource(recepcionList)
                    .setParameters(params)
                    .setTemplateDesign(templatePath)
                    .toPdf(outputStream);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

    private Integer getCurrentEmpaque(EmpaqueEncabezado entity)  {
        Integer max = 0;
        if(entity != null && CollectionUtils.isNotEmpty(entity.getEmpaqueList())) {
            max = entity.getEmpaqueList().stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
        }
        return max + 1;
    }
    
    private Integer getCurrentEmpaque(List<Empaque> empaques) {
        Integer max = 0;
        if(CollectionUtils.isNotEmpty(empaques)) {
            max = empaques.stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
        }
        return max + 1;
    }
    
    @Override
    public void delete(OrdenCompra entity) throws BusinessValidationException {
        if (entity.isAprobado()) {
            throw new BusinessValidationException("La orden ya está aprobada");
        }
        dao.delete(entity);
    }

    public List<OrdenCompra> findAllOnBackorderAndTransit() {
        return dao.findAllOnBackorderAndTransit();
    }

    public List<OrdenCompra> findAllOnBackorderAndTransitByProveedor(String proveedor) {
        return dao.findAllOnBackorderAndTransitByProveedor(proveedor);
    }

    public List<OrdenCompraLinea> findAllPendingByOc(String oc) {
        return ordenCompraLineaDAO.findAllPendingByOc(oc);
    }
}
