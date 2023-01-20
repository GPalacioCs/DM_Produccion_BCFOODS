/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.OrdenProduccionDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.Utils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class OrdenProduccionService extends AbstractService<OrdenProduccionDAO, OrdenProduccion> {
    
    @Inject
    private TipoCambioHistService tc;
    @Inject
    private TransaccionInvService transaccionInvService;
    @Inject
    private ParametrosPrService parametrosPrService;
    @Inject
    private OrdenProdEmpleadoService ordenProdEmpleadoService;
    @Inject
    private AsientoDeDiarioService asientoDeDiarioService;
    @Inject
    private EmpaqueEncabezadoService empaqueEncabezadoService;
    @Inject
    private SeriePlantillaService seriePlantillaService;
    @Inject 
    private SerieService serieService;
    @Inject
    private TipoCambioHistService tipoCambioHistService;
    
    @Override
    public void delete(OrdenProduccion orden)throws BusinessValidationException
    {
        if(orden.getEstadoProd() == EstadosProd.LIBERADO){
            throw new BusinessValidationException("La orden de producción ya fue liberada");
        }
        this.dao.delete(orden);
    }

    @Override
    public OrdenProduccion save(OrdenProduccion entity) throws BusinessValidationException {
        entity.getEmpleadoList().forEach(e -> ordenProdEmpleadoService.detach(e));
        if (entity != null && entity.getOrdenProduccion() != null) {
            OrdenProduccion ordendb = find(entity.getOrdenProduccion());
            if (ordendb != null && entity.getArticulo() == null) {
                entity.setArticulo(ordendb.getArticulo());
            }
        }
        for (IngresoProduccion ingreso : entity.getIngresoProduccoinList()) {
            if (StringUtils.isBlank(ingreso.getLote()) || "ND".equals(ingreso.getLote())) {
                ingreso.setLote(new SimpleDateFormat("yyyyMMdd").format(entity.getFechaRequerida()) + entity.getOrdenProduccion());
            }
        }
        if(entity.isTieneEmpaque()) {
            fillIngresoEmpaque(entity);
        }
        return super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public OrdenProduccion insert(OrdenProduccion orden)throws BusinessValidationException
    {
        if(orden== null)
            return null;        
        
        orden.setArticulo(new Articulo("ND"));
       
//        if(CollectionUtils.isEmpty(orden.getIngresoProduccoinList())) {
//            throw new BusinessValidationException("Debe seleccionar un artículo");  
//        }
        
//        if(!dao.findByLoteProd(orden.getLoteProd()).isEmpty()) {
//            throw new BusinessValidationException("El lote ya existe");
//        }
        //orden.setVersion("1.01");
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        orden.setTipo("C");
        orden.setEstado(EstadosProd.PLANEADO.getEstado());
        orden.setPrioridad((short)1);
        orden.setModuloOrigen("PC");
        orden.setBodegaMp(parametrosPrService.getParametro().getBodegaMp(orden.isFresco()).getBodega());
        orden.setBodegaPt(parametrosPrService.getParametro().getBodegaPt().getBodega());
        orden.setUsuarioCreacion(user);
        orden.setFechaCreacion(new  Date());
        orden.setFechaALiberar(orden.getFechaRequerida());
        orden.setVersion("1.01");
        //orden.setViewit(orden.getArticulo().getViewit());
        //orden.setArchivoviewit(orden.getArticulo().getArchivoviewit());
        
        BigDecimal tcz = tc.getTipoCambioActual();
        orden.setTipoDeCambio(tcz);
        //orden.setFechaLiberacion(orden.getFechaRequerida());
        
        List<OrdenProdEmpleado> empleadoList = new ArrayList<>(orden.getEmpleadoList());        
        List<OrdenProdMp> ordenProdMpList = new ArrayList<>(orden.getOrdenProdMpList());
        List<OrdenProdMd> ordenProdMdList = new ArrayList<>(orden.getOrdenProdMdList());        
        List<OrdenProdRubro> ordenProdRubroList = new ArrayList<>(orden.getOrdenProdRubroList());
        List<IngresoProduccion> ingresoProduccionList = new ArrayList<>(orden.getIngresoProduccoinList());
        List<EmpaqueEncabezado> empaqueEncabezadoList = new ArrayList<>(orden.getEmpaqueEncabezadoList());
        List<Maquila> maquilaList = new ArrayList<>(orden.getMaquilaList());        
        
        orden.getEmpleadoList().clear();
        orden.getOrdenProdMpList().clear();
        orden.getOrdenProdMdList().clear();
        orden.getOrdenProdRubroList().clear();
        orden.getIngresoProduccoinList().clear();
        orden.getEmpaqueEncabezadoList().clear();
        orden.getMaquilaList().clear();
        
        OrdenProduccion savedOrden = save(orden);
        super.dao.getEm().flush();
        
        empleadoList.forEach(e -> e.setOrdenProduccion(savedOrden));
        ordenProdMpList.forEach(mp -> mp.setOrdenProduccion(savedOrden));
        ordenProdMdList.forEach(md -> md.setOrdenProduccion(savedOrden));
        ordenProdRubroList.forEach(md -> md.getId().setOrdenProduccion(savedOrden.getOrdenProduccion()));
        ingresoProduccionList.forEach(md -> md.setOrdenProduccion(savedOrden));
        empaqueEncabezadoList.forEach(ee -> ee.setOrdenProduccion(savedOrden));
        maquilaList.forEach(m -> m.setOrdenProduccion(savedOrden));
        
        savedOrden.getEmpleadoList().addAll(empleadoList);
        savedOrden.getOrdenProdMpList().addAll(ordenProdMpList);
        savedOrden.getOrdenProdMdList().addAll(ordenProdMdList);
        savedOrden.getOrdenProdRubroList().addAll(ordenProdRubroList);
        savedOrden.getIngresoProduccoinList().addAll(ingresoProduccionList);
        savedOrden.getEmpaqueEncabezadoList().addAll(empaqueEncabezadoList);
        savedOrden.getMaquilaList().addAll(maquilaList);
        
        return savedOrden;
    }
    
    public void liberarOrden(OrdenProduccion orden, String usuario) throws BusinessValidationException {
        if(CollectionUtils.isEmpty(orden.getIngresoProduccoinList())) {
            throw new BusinessValidationException("No se ha seleccionado ningún producto");
        }
        if(CollectionUtils.isEmpty(orden.getOrdenProdMpList())) {
            throw new BusinessValidationException("No se ha seleccionado ninguna materia prima");
        }
        if(CollectionUtils.isEmpty(orden.getOrdenProdMpList())) {
            throw new BusinessValidationException("No se ha seleccionado ninguna mano de obra");
        }
//        if(CollectionUtils.isEmpty(orden.getOrdenProdRubroList())) {
//            throw new BusinessValidationException("No se ha seleccionado ningun rubro");
//        }
        if(CollectionUtils.isEmpty(orden.getOrdenProdMdList())) {
            throw new BusinessValidationException("No se ha seleccionado ningun material directo");
        }
        
        aplicarTransaccionInv(orden, parametrosPrService.getParametro().getBodegaMp(orden.isFresco()).getBodega(), parametrosPrService.getParametro().getBodegaMd().getBodega(), parametrosPrService.getParametro().getBodegaPt().getBodega(), usuario);
        OrdenProduccion ordendb = find(orden.getOrdenProduccion());
        ordendb.setEstado(EstadosProd.LIBERADO.getEstado()); 
        ordendb.setFechaLiberacion(new Date());
        orden.setEstado(EstadosProd.LIBERADO.getEstado());
        orden.setFechaLiberacion(new Date());
        for (EmpaqueEncabezado empaqueEncabezado : orden.getEmpaqueEncabezadoList()) {
            empaqueEncabezadoService.generarPedido(empaqueEncabezado, usuario);
        }
        generarAsiento(orden, usuario);
        ordendb.setAsientoAlinsa(orden.getAsientoAlinsa());
        ordendb.setAsientoBcfoods(orden.getAsientoBcfoods());
    }        
    
    public void aplicarTransaccionInv(OrdenProduccion orden, String bodegaMp, String bodegaMd, String bodegaPt, String usuario) throws BusinessValidationException {
        transaccionInvService.aplicarTransaccionInv(orden, bodegaMp, bodegaMd, bodegaPt, usuario);
//        transaccionInvService.aplicarTransaccionInv(orden, bodegaMp, bodegaMd, orden.getBodegaPt(), usuario);
    }
    
    public void fillIngresoEmpaque(OrdenProduccion entity) {
        List<IngresoProduccion> ipList = new ArrayList(entity.getIngresoProduccoinList());
        
        entity.getIngresoProduccoinList().clear();
        Map<Articulo, BigDecimal> mapCantInyectada = new HashMap<>();
        Map<Articulo, BigDecimal> mapCantInicial = new HashMap<>();
        for (EmpaqueEncabezado empaqueEncabezado : entity.getEmpaqueEncabezadoList()) {
            for (Empaque empaque : empaqueEncabezado.getEmpaqueList()) {
                if(!mapCantInyectada.containsKey(empaque.getArticulo())) {
                    mapCantInyectada.put(empaque.getArticulo(), empaque.getPesoInyectado());
                    mapCantInicial.put(empaque.getArticulo(), empaque.getPesoNeto());
                } else {
                    BigDecimal cantidadInyectada = mapCantInyectada.get(empaque.getArticulo());
                    BigDecimal cantidadInicial = mapCantInicial.get(empaque.getArticulo());
                    mapCantInyectada.put(empaque.getArticulo(), cantidadInyectada.add(empaque.getPesoInyectado()));
                    mapCantInicial.put(empaque.getArticulo(),cantidadInicial.add(empaque.getPesoNeto()));
                }
            }
        }
        mapCantInyectada.keySet().stream().sorted((a1, a2) -> a1.getArticulo().compareTo(a2.getArticulo())).forEach(a ->{
            IngresoProduccion ingreso = new IngresoProduccion();
            ingreso.setArticulo(a);
            ingreso.setCantidad(mapCantInyectada.get(a));
            ingreso.setCantidad_inicial(mapCantInicial.get(a));
            ingreso.setOrdenProduccion(entity);
            for (IngresoProduccion ip : ipList) {
                if(ip.getArticulo().equals(ingreso.getArticulo())){
                    ingreso.setLote(ip.getLote());
                }
            }
            entity.getIngresoProduccoinList().add(ingreso);
        });
    }    
    
    /**
     * Sugerir las materias primas o material directo
     * @param orden la orden de producción a procesar
     * @param tipoMaterial 1 = materia prima, 2 = material directo
     */
    public void sugerir(OrdenProduccion orden, int tipoMaterial) {
        ParametrosPr parametros = parametrosPrService.getParametro();
        Clasificacion clasifMateriaPrima = parametros.getClasificacionMateriaPrima();
        Clasificacion clasifMaterialesDirectos = parametros.getClasificacionMaterialesDirectos();
        Bodega bodegaMp = parametros.getBodegaMp(orden.isFresco());
        if(tipoMaterial == 1) {
            orden.getOrdenProdMpList().clear();
        } else if(tipoMaterial == 2) {
            orden.getOrdenProdMdList().clear();
        }
        if(true) {
            Map<OrdenProdMp, BigDecimal> mpMap = new HashMap<>();
            Map<Articulo, BigDecimal> mdMap = new HashMap<>();
            Map<Articulo, BigDecimal> materiaPrimaMap = new HashMap<>();
            for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {
                for (Etapa etapa : ingreso.getArticulo().getEtapaList()) {
                    for (MateriaPrima materiaPrima : etapa.getMateriaPrimaList()) {
                        if(tipoMaterial == 1) {
                            if(materiaPrima.getArticuloHijo().getClasificacion1().equals(clasifMateriaPrima)) {
                                try {
                                BigDecimal cantProduccion = ingreso.getArticulo().isFresco() ? ingreso.getArticulo().getCantidadProduccion() : ingreso.getArticulo().getVolumen();
                                BigDecimal cantTotalActual = materiaPrimaMap.getOrDefault(materiaPrima.getArticuloHijo(), BigDecimal.ZERO);
                                BigDecimal cantidadTotal = materiaPrima.getCantidad().multiply(ingreso.getCantidad()).divide(cantProduccion).add(cantTotalActual);
                                materiaPrimaMap.put(materiaPrima.getArticuloHijo(), cantidadTotal);
                                BigDecimal cantidadRecorrida = BigDecimal.ZERO;
                                
                                List<Lote> loteList = materiaPrima.getArticuloHijo().getLoteList().stream().sorted((l1, l2) -> l1.getFechaEntrada().compareTo(l2.getFechaEntrada())).collect(Collectors.toList());
                                for (Lote lote : loteList) {                                    
                                    if (!lote.isVencido()) {
                                        for (ExistenciaLote existenciaLote : lote.getExistenciaLoteList()) {
                                            BigDecimal cantidadFaltante = cantidadTotal.subtract(cantidadRecorrida);
                                            if (existenciaLote.getExistenciaBodega().getBodega().getBodega().equals(bodegaMp.getBodega())
                                                    && cantidadFaltante.compareTo(BigDecimal.ZERO) > 0
                                                    && existenciaLote.getCantDisponible().compareTo(BigDecimal.ZERO) > 0) {
                                                OrdenProdMp mp = new OrdenProdMp();
                                                mp.setOrdenProduccion(orden);
//                                                mp.setProducto(ingreso.getArticulo());                                                
                                                mp.setProducto(new Articulo("ND"));                                                
                                                mp.getId().setProducto("ND");
                                                mp.setComponente(materiaPrima.getArticuloHijo());
                                                mp.setLote(lote.getLotePK().getLote());
                                                mp.setCosto(mp.getComponente().getCostoPromLoc());
                                                mp.setCostoDolar(mp.getComponente().getCostoPromDol());
                                                BigDecimal cantActual = mpMap.getOrDefault(mp, BigDecimal.ZERO);
                                                if (cantidadFaltante.compareTo(existenciaLote.getCantDisponible()) >= 0) {
                                                    mp.setCantidad(existenciaLote.getCantDisponible());
                                                } else {
                                                    mp.setCantidad(cantidadFaltante);
                                                }
                                                mpMap.put(mp, cantActual.add(mp.getCantidad()));
                                                if (mpMap.get(mp).compareTo(existenciaLote.getCantDisponible())<0) {
                                                    mp.setCantidad(mpMap.get(mp));
                                                } else {
                                                    mp.setCantidad(existenciaLote.getCantDisponible());
                                                }
                                                
//                                                orden.getOrdenProdMpList().add(mp);
                                                cantidadRecorrida = cantidadRecorrida.add(mp.getCantidad());   
                                                Optional<OrdenProdMp> opt = orden.getOrdenProdMpList().stream().filter(m -> m.getComponente().equals(mp.getComponente()) && m.getLote().equals(mp.getLote())).findFirst();
                                                if(opt.isPresent()) {
                                                    opt.get().setCantidad(mp.getCantidad());
                                                } else {
                                                    orden.getOrdenProdMpList().add(mp);
                                                }
                                            }
                                        }
                                    }
                                }
                                
                                }catch(Exception ex){
                                    System.out.println("Articulo cantidad produccion y/o volumen 0" + ingreso.getArticulo().getArticulo() + ex.getMessage());
                                    Logger.getLogger(OrdenProduccionService.class.getName()).log(Level.SEVERE, "articulo con costo y/o volumen 0" + ingreso.getArticulo().getArticulo() , ex);
                                }
                                
                            }
                        } else if(tipoMaterial == 2) {
                            
                            if (materiaPrima.getArticuloHijo().getClasificacion1().equals(clasifMaterialesDirectos)) {
                                OrdenProdMd md = new OrdenProdMd();
                                md.getId().setProducto("ND");
                                md.setProducto(new Articulo("ND"));
                                md.setOrdenProduccion(orden);
                                md.setComponente(materiaPrima.getArticuloHijo());
                                BigDecimal cantActual = mdMap.get(md.getComponente()) == null ? BigDecimal.ZERO : mdMap.get(md.getComponente()); 
                                BigDecimal cantProduccion = ingreso.getArticulo().isFresco() ? ingreso.getArticulo().getCantidadProduccion() : ingreso.getArticulo().getVolumen();
                                md.setCantidad(materiaPrima.getCantidad().multiply(ingreso.getCantidad()).divide(cantProduccion, 8, RoundingMode.HALF_EVEN));
                                String consumoEspecifico = parametros.getConsumoEspecifico();
                                if(consumoEspecifico != null) {
                                    String[] consumoEspecificoArray = consumoEspecifico.split(",");
                                    if (!Arrays.asList(consumoEspecificoArray).contains(md.getComponente().getArticulo())) {                                    
                                        md.setCantidad(md.getCantidad().setScale(0, RoundingMode.CEILING));
                                    }
                                }
                                
                                mdMap.put(md.getComponente(), cantActual.add(md.getCantidad()));
                                md.setCantidad(mdMap.get(md.getComponente()));
                                Optional<OrdenProdMd> opt = orden.getOrdenProdMdList().stream().filter(m -> m.getComponente().equals(md.getComponente())).findFirst();
                                if(opt.isPresent()){                                    
                                    opt.get().setCantidad(mdMap.get(md.getComponente()));
                                }
                                else {
                                    orden.getOrdenProdMdList().add(md);                                    
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public List<OrdenProduccion> findByFecha(Date fechaInicio, Date fechaFin) {
        return dao.getOrdenesByFecha(fechaInicio, fechaFin);
    }
    
    public void generarAsiento(OrdenProduccion orden, String usuario) {
        asientoDeDiarioService.generarAsientoProduccion(orden, usuario);
    }
    
    public List<OrdenProduccion> findByEstados(String... estados) {
        return dao.findByEstados(Arrays.asList(estados));
    }
    
    public void revertirEstado(OrdenProduccion orden) {
        OrdenProduccion ordendb = find(orden.getOrdenProduccion());
        ordendb.setEstado(EstadosProd.PLANEADO.getEstado());
        orden.setEstado(EstadosProd.PLANEADO.getEstado());
    }
    
    public void generarEtiquetaCongelado(OrdenProduccion orden, OutputStream os, String sourceFileName, List<String> series) throws JRException {
        if (!orden.isFresco()) {
            /*if (orden.getEstadoProd() == EstadosProd.LIBERADO) {
                throw new BusinessValidationException("La orden ya fue liberada");
            }*/
            String bodega = parametrosPrService.getParametro().getBodegaPt().getBodega();
            String lote = "";
            for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {
                if(ingreso.getArticulo().getVolumen().compareTo(BigDecimal.ZERO) > 0 && isIntegerValue(ingreso.getArticulo().getVolumen())) {
                    lote = ingreso.getLote();
                    BigDecimal noCajas = ingreso.getCantidad().divide(ingreso.getArticulo().getVolumen(), 1, RoundingMode.HALF_EVEN);
                    if(!isIntegerValue(noCajas)) {
                        throw new BusinessValidationException("La cantidad producida debe ser multiplo del volumen");
                    }
                    for(int i = 0; i < noCajas.intValue(); i++) {
                        String numSerie = generarSerie();
                        series.add(numSerie);
                        Serie serie = new Serie();
                        serie.setSerie(numSerie);
                        serie.setOrigen(String.valueOf(orden.getOrdenProduccion()));
                        serie.setFecha(orden.getFechaRequerida());
                        serie.setArticulo(ingreso.getArticulo().getArticulo());
                        serie.setBodega(bodega);
                        serie.setCajas(1);
                        serie.setLbs(ingreso.getArticulo().getVolumen());
                        serie.setEstado("D");
                        serie.setEsFresco("N");
                        serie.setPesoNeto(ingreso.getArticulo().getVolumen());
                        serie.setPesoReal(BigDecimal.ZERO);
                        serie.setNoCaja(0);
                        serie.setNoPeces(0);
                        serieService.insert(serie);
                    }
                } else {
                    String numSerie = generarSerie();
                    series.add(numSerie);
                    Serie serie = new Serie();
                    serie.setSerie(numSerie);
                    serie.setOrigen(String.valueOf(orden.getOrdenProduccion()));
                    serie.setFecha(orden.getFechaRequerida());
                    serie.setArticulo(ingreso.getArticulo().getArticulo());
                    serie.setBodega(bodega);
                    serie.setCajas(1);
                    serie.setLbs(ingreso.getCantidad());
                    serie.setEstado("D");
                    serie.setEsFresco("N");
                    serie.setPesoNeto(ingreso.getCantidad());
                    serie.setPesoReal(BigDecimal.ZERO);
                    serie.setNoCaja(0);
                    serie.setNoPeces(0);
                    serieService.insert(serie);
                }
            }
//            imprimirEtiquetas(series, lote, Utils.date2ldt(orden.getFechaRequerida()).plusMonths(18).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), os, sourceFileName);
        }
    }
    
    private String generarSerie() {
        return seriePlantillaService.generarSerie();
    }
    
    private boolean isIntegerValue(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }
    
    public void imprimirEtiquetas(List<String> series, String lote, String fechaVencimiento, OutputStream os, String sourceFileName, Map<String, Object> params) throws JRException {
        imprimirEtiquetas(series, lote, fechaVencimiento, os, sourceFileName, false, params);
    }
    
    public void imprimirEtiquetas(List<String> series, String lote, String fechaVencimiento, OutputStream os, String sourceFileName, boolean fresco, Map<String, Object> params1) throws JRException {
        String inSeries = series.stream().collect(Collectors.joining("','","('","')"));
        Map<String, Object> params = new HashMap<>();
        params.putAll(params1);
        params.put("p_lote", lote);
        params.put("p_fecha_vencimiento", fechaVencimiento);
        params.put("p_series", inSeries);
        if(fresco) {
            
        }
        generarReporte(os, sourceFileName, params);
    }

    public String getSerieFresco(OrdenProduccion orden, Empaque empaque, String cliente){
        Serie serie = new Serie();
        serie.setOrigen(String.valueOf(orden.getOrdenProduccion()));
        serie.setFecha(orden.getFechaRequerida());
        serie.setArticulo(empaque.getArticulo().getArticulo());
        serie.setBodega(parametrosPrService.getParametro().getBodegaPt().getBodega());
        serie.setCajas(1);
        serie.setLbs(empaque.getPesoInyectado());
        serie.setEstado("D");
        serie.setEsFresco("S");
        serie.setPesoNeto(empaque.getPesoInyectado());
        serie.setPesoReal(empaque.getPesoBruto());
        serie.setNoCaja(empaque.getId().getEmpaque());
        serie.setNoPeces(empaque.getPescados().intValue());
        serie.setCliente(cliente);
        return serieService.getSerie(serie).getSerie();
    }
    
    public String generarSerieFresco(OrdenProduccion orden, Empaque empaque, String cliente) {
        String numSerie = generarSerie();
        Serie serie = new Serie();
        serie.setSerie(numSerie);
        serie.setOrigen(String.valueOf(orden.getOrdenProduccion()));
        serie.setFecha(orden.getFechaRequerida());
        serie.setArticulo(empaque.getArticulo().getArticulo());
        serie.setBodega(parametrosPrService.getParametro().getBodegaPt().getBodega());
        serie.setCajas(1);
        serie.setLbs(empaque.getPesoInyectado());
        serie.setEstado("D");
        serie.setEsFresco("S");
        serie.setPesoNeto(empaque.getPesoInyectado());
        serie.setPesoReal(empaque.getPesoBruto());
        serie.setNoCaja(empaque.getId().getEmpaque());
        serie.setNoPeces(empaque.getPescados().intValue());
        serie.setCliente(cliente);
        serieService.insert(serie);
        return numSerie;
    }
    
    public void imprimirEtiquetaFresco(List<String> series, String lote, OutputStream os, String sourceFileName, boolean mixto, Long orden, String logo, Map<String, Object> params1) throws JRException {
        String inSeries = series.stream().collect(Collectors.joining("','","('","')"));
        Map<String, Object> params = new HashMap<>(params1);
        params.put("p_lote", lote);
        params.put("p_mixto", mixto);
        params.put("p_series", inSeries);
        params.put("p_subreporte_etiqueta_congelado", "rpt_etiqueta_congelado.jasper");
        params.put("p_orden_produccion", orden);       
        params.put("p_logo",logo);
        generarReporte(os, sourceFileName, params);
    }
    
    public void generarReporte(OutputStream os, String sourceFileName, Map<String, Object> params) throws JRException {
        Connection conn = dao.getEm().unwrap(Connection.class);
        JasperPrint jp = JasperFillManager.fillReport(sourceFileName, params, conn);        
        JasperExportManager.exportReportToPdfStream(jp, os);
    }

    public void setImpreso(boolean impreso, Long codOrdenProduccion) {
        OrdenProduccion orden = find(codOrdenProduccion);
        if(orden != null) {
            orden.setImpreso(impreso);
        }
    }
    
    public void recalcularMaquila(OrdenProduccion orden) {        
        orden.getMaquilaList().clear();
        BigDecimal tc1 = tipoCambioHistService.getTipoCambioActual(orden.getFechaRequerida());
        for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {
            Maquila maquila = new Maquila();
            maquila.setArticulo(ingreso.getArticulo());
            maquila.setOrdenProduccion(orden);
            maquila.setCantidad(ingreso.getCantidad());
            maquila.setPrecioDolar(maquila.getArticulo().getPrecioMaquila());
            
            maquila.setPrecio(Utils.dol2loc(maquila.getPrecioDolar(), tc1));
            orden.getMaquilaList().add(maquila);
        }
    }
    
    public List<OrdenProduccion> findFrescoPlaneado() {
        return dao.findFrescoPlaneado();
    }
}
