/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.ClienteService;
import com.dosrobles.produccion.service.EmpaqueEncabezadoService;
import com.dosrobles.produccion.service.OrdenProduccionService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MimeUtils;
import com.dosrobles.produccion.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Named
@ViewScoped
@Getter @Setter
public class EmpaqueEncabezadoController extends AbstractController<EmpaqueEncabezadoService, EmpaqueEncabezado> {    
    
    @Inject
    private ClienteService clienteService;
    @Inject
    private ArticuloService articuloService;
    @Inject
    private OrdenProduccionService ordenProduccionService;
    
    private Integer maxEmpaque;
    
    private OrdenProduccion ordenProduccion;
    private List<Cliente> clienteList = new ArrayList<>();
    private List<Articulo> articuloList = new ArrayList<>();
    
    private Empaque empaque = new Empaque();
    
    private List<EmpaqueEncabezado> selectionList = new ArrayList<>();
    
    private Set<Integer> cajasImpresas = new HashSet<>();
    @Getter @Setter
    private boolean imprimir = false;
    
    
    @PostConstruct
    private void init() {
        ordenProduccion = (OrdenProduccion) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ordenProduccion");
        clienteList = clienteService.findAll();
        articuloList = articuloService.getArticulosProduccion();
        cargarLista();
//        this.maxEmpaque = service.getMaxEmpaque();
    }
    
    public Integer getCurrentEmpaque()  {
        Integer max = 0;
//        for (EmpaqueEncabezado empaqueEncabezado : ordenProduccion.getEmpaqueEncabezadoList()) {
//            for (Empaque empaque1 : empaqueEncabezado.getEmpaqueList()) {
//                Integer empaque = empaque1.getId().getEmpaque();
//                if(empaque > max) {
//                    max = empaque;
//                }
//            }            
//        }
        if(entity != null && CollectionUtils.isNotEmpty(entity.getEmpaqueList())) {
            max = entity.getEmpaqueList().stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
        }
        
        return max + 1;
    }

    @Override
    public void create() {
        if(EstadosProd.LIBERADO.equals(ordenProduccion.getEstadoProd())) {
            MessageUtils.showGrowlError("La orden ha sido liberada");
            return;            
        }
        
        if(ordenProduccion.isEmpaqueAprobado()) {
            MessageUtils.showGrowlError("La lista de empaques ya ha sido aprobada");
            return;            
        }
        empaque = new Empaque();
        empaque.getId().setEmpaque(getCurrentEmpaque());
        entity = new EmpaqueEncabezado();
        entity.setOrdenProduccion(ordenProduccion);
        reset();
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        if(EstadosProd.LIBERADO.equals(ordenProduccion.getEstadoProd())) {
            MessageUtils.showGrowlError("La orden ha sido liberada");
            return;            
        }
        if(ordenProduccion.isEmpaqueAprobado()) {
            MessageUtils.showGrowlError("La lista de empaques ya ha sido aprobada");
            return;            
        }
        empaque = new Empaque();
        entity = selection;
        empaque.getId().setEmpaque(getCurrentEmpaque());
        reset();
        setEditing(true);
        setFormView(true);
    }

    @Override
    protected void addDefaultColumns() {
        
    }

    @Override
    public void cargarLista() {
        this.list = ordenProduccion.getEmpaqueEncabezadoList();
    }
    
    public void agregarEmpaque() {
        agregarEmpaque(empaque);
    }

    public void agregarEmpaque(Empaque empaque) {
        List<Empaque> empaqueList = entity.getEmpaqueList();
        Cliente selectedCliente = entity.getCliente();
        
        empaque.setEmpaqueEncabezado(entity);
    
        if(empaque == null || empaque.getArticulo() == null) return;
        try {
            if(empaque.getArticulo().getMateriaPrima() == null) {
                throw new BusinessValidationException("El artículo no tiene una materia prima asociada");
            }
            
            if (selectedCliente == null) {
                throw new BusinessValidationException("Debe seleccionar un cliente");
            }
            
            if(empaque.getId().getEmpaque() == null) {
                throw new BusinessValidationException("Debe ingresar el número de la caja");
            }
            
            if(empaque.getArticulo() == null) {
                throw new BusinessValidationException("Debe ingresar el artículo");
            }
            
            if(empaque.getPesoBruto() == null) {
                throw new BusinessValidationException("Debe ingresar el peso bruto");
            }
            
            if(empaque.getPesoNeto() == null) {
                throw new BusinessValidationException("Debe ingresar el peso neto");
            }

            if(empaque.getPesoInyectado() == null){
                throw new BusinessValidationException("Debe ingresar el peso inyectado");
            }
            
            if(empaque.getPescados() == null) {
                throw new BusinessValidationException("Debe ingresar el número de pescados");
            }
            
            if(empaque.getTalla() == null) {
                throw new BusinessValidationException("Debe ingresar la talla");
            }
            boolean noExiste = empaqueList.stream().noneMatch(e -> e.getArticulo().equals(empaque.getArticulo())&&e.getId().getEmpaque().equals(empaque.getId().getEmpaque()));            
            if (noExiste) {                                
                boolean cajaExiste = empaqueList.stream().anyMatch(e -> e.getId().getEmpaque().equals(empaque.getId().getEmpaque()));
                if(!cajaExiste&&(empaque.getPesoNeto().compareTo(BigDecimal.ZERO)<=0
                        ||empaque.getPesoBruto().compareTo(BigDecimal.ZERO)<=0
                        ||empaque.getPesoInyectado().compareTo(BigDecimal.ZERO)<=0
                        ||empaque.getPescados().compareTo(BigDecimal.ZERO)<=0)){
                    throw new BusinessValidationException("Debe ingresar un valor mayor que 0");
                }
                empaqueList.add(empaque);
                Collections.sort(empaqueList, (e2,e1) -> e1.getId().getEmpaque().compareTo(e2.getId().getEmpaque()));
//                if (isImprimir()) {
//                    imprimirEtiqueta(empaque);
//                }
                this.empaque = new Empaque();
                this.empaque.setArticulo(empaque.getArticulo());
                this.empaque.getId().setEmpaque(getCurrentEmpaque());
                this.empaque.setTalla(empaque.getTalla());
            }
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        }
    }    

    public void quitarEmpaque(Empaque empaque) {
        entity.getEmpaqueList().remove(empaque);        
    }    
    
    public String regresar() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("ordenProduccion", ordenProduccion);
        return "/produccion/prod_a/orden-produccion3.xhtml?faces-redirect=true";
    }
    
    public void aprobar() {
        ordenProduccion.setEmpaqueAprobado(true);
    }
    
    public void generarReporte(boolean consolidado) {
        generarReporte(consolidado, false);
    }
    
    public void generarReporte(boolean consolidado, boolean excel) {
        if(ordenProduccion.getOrdenProduccion() == null) {
            MessageUtils.showGrowlError("Debe guardar la orden de producción");
            return;
        }
        if(selection == null) {
            MessageUtils.showGrowlError("Debe seleccionar un empaque");
            return;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        if (!excel) {
            response.setContentType(MimeUtils.getMimeType("pdf"));
        } else {
            response.setContentType(MimeUtils.getMimeType("xlsx"));
        }
        String filename = String.format("rpt_%s_%s.%s", consolidado ? "consolidado": "packing_list", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), excel?"xlsx":"pdf");
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFile = consolidado ? "/reportes/rpt_invoice/rpt_factura_provisional.jasper" : "/reportes/rpt_packing_list/rpt_packing_list.jasper";
        String sourceFileName = request.getServletContext().getRealPath(sourceFile);
        String logoPath = request.getServletContext().getRealPath("/resources/images/pimgpsh_fullsize_distr.png");
        Map<String, Object> params = new HashMap<>();
        params.put("p_cliente",selection.getCliente().getCliente());
        params.put("p_orden_produccion",ordenProduccion.getOrdenProduccion());
        params.put("p_consolidado",consolidado);
        params.put("REPORT_LOCALE",new Locale("es", "NI"));
        if(consolidado) {
            int totalCajas = (int)selection.getEmpaqueList().stream().map(e -> e.getId().getEmpaque()).distinct().count();
            params.put("p_total_cajas", totalCajas);
            params.put("p_ruc", "J0310000106916");
        }
        
        try {
            if (!excel) {
                service.generarReporte(response.getOutputStream(), sourceFileName, params);
            } else {
                service.generarReporteExcel(response.getOutputStream(), sourceFileName, params);
            }
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarEtiquetasEmpaque(){
        String cliente = selection.getCliente().getCliente();
    }
    
    public void generarPedido() {
        try {
            service.generarPedido(selection, getUsername());
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch(Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showGrowlError();
        }
    }

    public void imprimirEtiquetasForEmpaquesByCliente(){
        if(selection == null) {
            MessageUtils.showGrowlError("Debe seleccionar un empaque");
            return;
        }

        try {
            List<String> series = new ArrayList<>();
            for (Empaque empaque: selection.getEmpaqueList()) {
                if (empaque.isImpreso()) {
                    String serie = ordenProduccionService.getSerieFresco(selection.getOrdenProduccion(), empaque, selection.getCliente().getCliente());
                    series.add(serie);
                    continue;
                }
                String serie = ordenProduccionService.generarSerieFresco(selection.getOrdenProduccion(), empaque, selection.getCliente().getCliente());
                series.add(serie);
                empaque.setImpreso(true);
            }
            service.save(selection);

            if (series.isEmpty()){
                MessageUtils.showGrowlError("Todas las etiquetas ya han sido impresas.");
                return;
            }

            FacesContext fc = FacesContext.getCurrentInstance();
            fc.responseComplete();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType(MimeUtils.getMimeType("pdf"));
            String filename = String.format("rpt_%s_%s.pdf", "etiqueta_fresco", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
            response.setHeader("Content-disposition","attachment; filename="+filename);
            String sourceFile = "/reportes/rpt_etiquetas/rpt_etiqueta_fresco_orden.jasper";
            String sourceFileName = request.getServletContext().getRealPath(sourceFile);
            String logo = request.getServletContext().getRealPath("/resources/images/bcfoods_logo.jpg");
            String logoHaccp = request.getServletContext().getRealPath("/resources/images/haccp.png");
            String logoIso = request.getServletContext().getRealPath("/resources/images/client_logo_ISO_22000.jpg");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String lote = new SimpleDateFormat("yyyyMMdd").format(selection.getOrdenProduccion().getFechaRequerida())+selection.getOrdenProduccion().getOrdenProduccion();
            Map<String, Object> params = new HashMap<>();
            params.put("p_logo_haccp", logoHaccp);
            params.put("p_logo_iso", logoIso);
            params.put("p_fecha_produccion", sdf.format(selection.getOrdenProduccion().getFechaRequerida()));
            //Actualizacion
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ordenProduccion.getFechaRequerida());
            calendar.add(Calendar.DAY_OF_YEAR,10);
            params.put("p_fecha_produccion_Vence", sdf.format(calendar.getTime()));
            ordenProduccionService.imprimirEtiquetaFresco(series, lote, response.getOutputStream(), sourceFileName, false, selection.getOrdenProduccion().getOrdenProduccion(), logo, params);
            selection = entity;
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void imprimirEtiqueta(Empaque empaque) {
        Integer caja = empaque.getId().getEmpaque();
        List<Empaque> empaqueList = entity.getEmpaqueList().stream().filter(e -> Objects.equals(e.getId().getEmpaque(), empaque.getId().getEmpaque()))
                .collect(Collectors.toList());
        boolean mixto = empaqueList.size() > 1;
        String descArticulo = empaque.getArticulo().getDescripcion();
        if(mixto) {
            StringJoiner joiner = new StringJoiner(System.lineSeparator());
            for (Empaque emp : empaqueList) {
                joiner.add(emp.getArticulo().getDescripcion());
            }
            descArticulo = joiner.toString();
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_%s_%s.pdf", "etiqueta_fresco", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","inline; filename="+filename);
        String sourceFile = "/reportes/rpt_etiquetas/rpt_etiqueta_fresco.jasper";                
        String sourceFileName = request.getServletContext().getRealPath(sourceFile); 
        String logo = request.getServletContext().getRealPath("/resources/images/bcfoods_logo.jpg");
        String logoHaccp = request.getServletContext().getRealPath("/resources/images/haccp.png");
        String logoIso = request.getServletContext().getRealPath("/resources/images/client_logo_ISO_22000.jpg");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            List<String> series = new ArrayList<>();
            if (empaque.isImpreso()){
                String serie = ordenProduccionService.getSerieFresco(ordenProduccion, empaque, entity.getCliente().getCliente());
                series.add(serie);
            }else{
                String serie = ordenProduccionService.generarSerieFresco(ordenProduccion, empaque, entity.getCliente().getCliente());
                empaqueList.forEach(e -> e.setImpreso(true));
                series.add(serie);
            }
            String lote = new SimpleDateFormat("yyyyMMdd").format(ordenProduccion.getFechaRequerida())+ordenProduccion.getOrdenProduccion();
            Map<String, Object> params = new HashMap<>();
            params.put("p_logo_haccp", logoHaccp);
            params.put("p_logo_iso", logoIso);
            params.put("p_desc_articulo", descArticulo);
            params.put("p_peso_neto", Utils.sumBigDecimals(empaqueList, Empaque::getPesoInyectado));
            params.put("p_peso_bruto", Utils.sumBigDecimals(empaqueList, Empaque::getPesoBruto));
            params.put("p_fecha_produccion", sdf.format(ordenProduccion.getFechaRequerida()));
            params.put("p_empacador", empaque.getEmpacador());
            //Actualizacion
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ordenProduccion.getFechaRequerida());
            calendar.add(Calendar.DAY_OF_YEAR,10);
            params.put("p_fecha_produccion_Vence", sdf.format(calendar.getTime()));
            ordenProduccionService.imprimirEtiquetaFresco(series, lote, response.getOutputStream(), sourceFileName, mixto, ordenProduccion.getOrdenProduccion(), logo, params);
            selection = entity;
            save();
            edit();
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
