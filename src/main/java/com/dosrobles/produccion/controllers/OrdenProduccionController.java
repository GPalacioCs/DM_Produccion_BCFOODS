 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

 import com.dosrobles.produccion.entities.*;
 import com.dosrobles.produccion.enums.EstadosProd;
 import com.dosrobles.produccion.exceptions.BusinessValidationException;
 import com.dosrobles.produccion.service.*;
 import com.dosrobles.produccion.utils.MessageUtils;
 import com.dosrobles.produccion.utils.MimeUtils;
 import com.dosrobles.produccion.utils.MontoLocalDolar;
 import com.dosrobles.produccion.utils.Utils;

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
 * @author Corpsoft S.A.
 */
@Named
@ViewScoped
public class OrdenProduccionController extends AbstractController<CSOrdenProduccionService, CSOrdenProduccion> {

    //<editor-fold defaultstate="collapsed" desc="Injections">
    @Inject
    private PedidoLineaService pedidoLineaService;
    @Inject
    private ArticuloService articuloService;
    @Inject
    private EtapaService etapaService;
    @Inject
    private GlobalesController globalesController;
    @Inject
    private ExistenciaBodegaService existenciaBodegaService;
    @Inject
    private ConsumoMateriaService consumoMateriaService;    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    private List<PedidoOrdenProduccion> pedidoOrdenProduccionList = new ArrayList<>();
    private List<PedidoLinea> pedidoLineaList = new ArrayList<>();
    private List<PedidoLinea> selectedPedidoLineaList;
    private List<Articulo> articuloList = new ArrayList<>();
    private List<Etapa> etapaList = new ArrayList<>();
    private List<Etapa> selectedEtapasList = new ArrayList<>();
    private List<ExistenciaBodega> existenciaBodegaList = new ArrayList<>();
    private List<Bodega> bodegasList = new ArrayList<>();
    private Bodega bodegaMp;
    private Bodega bodegaPt;

    private Articulo articulo;

    private final String tipoCosto = "P";

    private PedidoLinea selectedPedidoLinea;

    private boolean general;
    private boolean consumo;
    private boolean aplicandoTransaccion;
    private boolean transEntrada;
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public List<PedidoOrdenProduccion> getPedidoOrdenProduccionList() {
        return pedidoOrdenProduccionList;
    }

    public List<PedidoLinea> getPedidoLineaList() {
        return pedidoLineaList;
    }

    public List<PedidoLinea> getSelectedPedidoLineaList() {
        List<PedidoLinea> list = new ArrayList<>();
        for (PedidoOrdenProduccion pop : entity.getPedidoOrdenProduccionList()) {
            PedidoLinea pl = new PedidoLinea(pop.getPedidoOrdenProduccionPK().getPedido(), (short) 0);
            list.add(pl);
        }
        return list;
    }

    public void setSelectedPedidoLineaList(List<PedidoLinea> selectedPedidoLineaList) {
        entity.getPedidoOrdenProduccionList().clear();
        if (selectedPedidoLineaList != null) {
            for (PedidoLinea pedidoLinea : selectedPedidoLineaList) {
                PedidoOrdenProduccion pop = new PedidoOrdenProduccion(new PedidoOrdenProduccionPK());
                pop.getPedidoOrdenProduccionPK().setPedido(pedidoLinea.getPedido().getPedido());
                pop.setCantidad(pedidoLinea.getCantidadPedida());
                pop.setOrdenProduccion(entity);
                entity.getPedidoOrdenProduccionList().add(pop);
            }
        }
    }

    public void setPedidoLineaList(Articulo articulo) {
        this.pedidoLineaList = pedidoLineaService.getPedidoLineaList(articulo)
                .stream().filter(pl -> "N".equals(pl.getPedido().getEstado()))
                .collect(Collectors.toList());
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public List<Etapa> getEtapaList() {
        return etapaList;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public PedidoLinea getSelectedPedidoLinea() {
        return selectedPedidoLinea;
    }

    public void setSelectedPedidoLinea(PedidoLinea selectedPedidoLinea) {
        this.selectedPedidoLinea = selectedPedidoLinea;
    }

    public boolean isGeneral() {
        return general;
    }

    public void setGeneral(boolean general) {
        this.general = general;
    }

    public boolean isConsumo() {
        return consumo;
    }

    public void setConsumo(boolean consumo) {
        this.consumo = consumo;
    }

    public boolean isTransEntrada() {
        return transEntrada;
    }

    public void setTransEntrada(boolean transEntrada) {
        this.transEntrada = transEntrada;
    }

    public List<Bodega> getBodegasList() {
        return bodegasList;
    }

    public Bodega getBodegaMp() {
        return bodegaMp;
    }

    public void setBodegaMp(Bodega bodegaMp) {
        this.bodegaMp = bodegaMp;
    }

    public Bodega getBodegaPt() {
        return bodegaPt;
    }

    public void setBodegaPt(Bodega bodegaPt) {
        this.bodegaPt = bodegaPt;
    }

    public List<ConsumoMateria> getConsumoMateriaList(Etapa etapa) throws BusinessValidationException {
        List<ConsumoMateria> consumoList = new ArrayList<>();
        ParametrosPr par = globalesController.getParametrosPr();
        for (MateriaPrima materia : etapa.getMateriaPrimaList()) {
            Optional<ConsumoMateria> opt = entity.getConsumoMateriaList().stream().filter(cm -> cm.getMateriaPrima().equals(materia)).findFirst();
            if (!opt.isPresent()) {
                ConsumoMateria consumoMateria = new ConsumoMateria(new ConsumoMateriaPK(entity.getOrdenProduccion(), materia.getMateriaPrimaPK().getEtapa(), materia.getMateriaPrimaPK().getArticuloPadre(), materia.getMateriaPrimaPK().getArticuloHijo()));
                consumoMateria.setMateriaPrima(materia);
                consumoMateria.setOrdenProduccion(entity);
                //consumoMateria.setCantidadConsumida(materia.getCantidad().multiply(consumoMateria.getOrdenProduccion().getCantidad()).divide(consumoMateria.getMateriaPrima().getEtapa().getArticulo().getCantidadProduccion()));
                entity.getConsumoMateriaList().add(consumoMateria);
                consumoMateria.setExistenciaBodega(existenciaBodegaService.find(new ExistenciaBodegaPK(consumoMateria.getMateriaPrima().getArticuloHijo().getArticulo(), par.getBodegaMp(false).getBodega())));
                consumoList.add(consumoMateria);
            } else {
//                opt.get().setExistenciaBodega(existenciaBodegaService.find(new ExistenciaBodegaPK(opt.get().getMateriaPrima().getArticuloHijo().getArticulo(), par.getBodegaMp().getBodega())));
                consumoList.add(opt.get());
            }

        }
        return consumoList;
    }

    public List<Etapa> getSelectedEtapasList() {
        return selectedEtapasList;
    }

    public void setSelectedEtapasList(List<Etapa> selectedEtapasList) {
        this.selectedEtapasList = selectedEtapasList;
    }

    public boolean isAplicandoTransaccion() {
        return aplicandoTransaccion;
    }

    public void setAplicandoTransaccion(boolean aplicandoTransaccion) {
        this.aplicandoTransaccion = aplicandoTransaccion;
    }
//</editor-fold>    

    @PostConstruct
    private void init() {
        cargarLista();
        
        addDefaultColumns();
        try {
            this.articuloList = articuloService.getArticulosPorClasificacion(globalesController.getParametrosPr().getClasificacionPr());
            setBodegaPt(globalesController.getParametrosPr().getBodegaPt());
            setBodegaMp(globalesController.getParametrosPr().getBodegaMp(false));
            existenciaBodegaList = existenciaBodegaService.getExistenciaBodegaList(getBodegaMp());
        } catch (BusinessValidationException ex) {
            ex.printStackTrace();
            MessageUtils.showGrowlError(ex.getMessage());
        }
    }

    @Override
    public void create() {
        entity = new CSOrdenProduccion();
        entity.setEstado(EstadosProd.PLANEADO.getEstado());
        entity.setFechaInicio(Utils.stripTime(new Date()));
        entity.setFechaFin(Utils.stripTime(new Date()));
        pedidoLineaList = new ArrayList<>();
        reset();
        setGeneral(true);
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        if (service.findByEntity(selection).getEstado().equals(EstadosProd.LIBERADO.getEstado())) {
            MessageUtils.showGrowlError("La orden ya fue liberada");
            return;
        }
        entity = service.findByEntity(selection);
        setPedidoLineaList(entity.getArticulo());
        reset();
        setGeneral(true);
        setEditing(true);
        setFormView(true);
    }

    public void consumir() {
        if (!service.findByEntity(selection).getEstado().equals(EstadosProd.LIBERADO.getEstado())) {
            MessageUtils.showGrowlError("La orden no se ha liberado");
            return;
        }
        entity = service.findByEntity(selection);
        reset();
        setConsumo(true);
        setEditing(true);
        setFormView(true);
    }

    @Override
    protected void addDefaultColumns() {
        selectedColumns.add("orden");
        selectedColumns.add("articulo");
        selectedColumns.add("descripcion");
        selectedColumns.add("fechaInicio");
        selectedColumns.add("fechaFin");
        selectedColumns.add("estado");
    }

    @Override
    public void cargarLista() {
        list = service.getOrdenesProduccion(false);
    }

    @Override
    public void save() {
        try {
            if (general) {
                super.save();
            } else if (consumo) {

                service.guardarConsumo(entity, getUsername());
                cancel();
                MessageUtils.showGrowlSuccess();
                cargarLista();

            } else if (transEntrada) {
                String bodega = getBodegaPt().getBodega();
                entity.setExistenciaBodega(existenciaBodegaService.find(new ExistenciaBodegaPK(entity.getArticulo().getArticulo(), bodega)));
                service.aplicarTransEntrada(entity, getUsername());
                cancel();
                MessageUtils.showGrowlSuccess();
                cargarLista();
            }
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception ex) {
            MessageUtils.showMessage(MessageUtils.ERROR);
        }
    }

    public void onArticuloSelected() {
        setPedidoLineaList(entity.getArticulo());
    }

    public BigDecimal sumCantidadPedidos() {

        return Utils.sumBigDecimalList(entity.getPedidoOrdenProduccionList().stream()
                .map(pl -> pl.getCantidad()).collect(Collectors.toList()));

    }

    public void setCantidadOrden() {
        entity.setCantidad(sumCantidadPedidos());
    }

    public void calcularCostoOrden() {
        /*if (entity != null && entity.getArticulo() != null) {
            if (entity.getCantidad() == null) {
                MessageUtils.showMessage("Debe especificar la cantidad a producir");
            } else if (entity.getArticulo().getCantidadProduccion() == null || entity.getArticulo().getCantidadProduccion().compareTo(BigDecimal.ZERO) == 0) {
                MessageUtils.showMessage("No se especificó la cantidad de produccion del articulo");
            } else {
                BigDecimal cantidadArticulo = entity.getArticulo().getCantidadProduccion() == null ? BigDecimal.ONE : entity.getArticulo().getCantidadProduccion();
                entity.setCostoEstimadoLoc(entity.getCantidad().multiply(entity.getArticulo().getCostoPromLoc()).divide(cantidadArticulo, 2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN));
                entity.setCostoEstimadoDol(entity.getCantidad().multiply(entity.getArticulo().getCostoPromDol()).divide(cantidadArticulo, 2, RoundingMode.HALF_EVEN).setScale(2, RoundingMode.HALF_EVEN));
            }
        }*/
    }

    public void onPedidoSelected() {
        setCantidadOrden();
    }

    public MontoLocalDolar getCostoArticulo(Articulo articulo) {
        return articuloService.getCostoArticulo(articulo, tipoCosto);
    }

    public void agregarPedido() {
        try {
            if (selectedPedidoLinea == null) {
                throw new BusinessValidationException("Debe seleccionar un pedido");
            }

            if (entity.getPedidoOrdenProduccionList().stream().anyMatch(p -> p.getPedido().equals(selectedPedidoLinea.getPedido()))) {
                throw new BusinessValidationException("El pedido ya fue agregado");
            }

            PedidoOrdenProduccion pop = new PedidoOrdenProduccion(new PedidoOrdenProduccionPK(selectedPedidoLinea.getPedido().getPedido(), entity.getOrdenProduccion()));
            pop.setCantidad(selectedPedidoLinea.getCantidadPedida());
            pop.setPedido(selectedPedidoLinea.getPedido());
            pop.setOrdenProduccion(entity);
            entity.getPedidoOrdenProduccionList().add(pop);
            setCantidadOrden();
        } catch (BusinessValidationException e) {
            MessageUtils.showMessage(e.getMessage());
        }
    }

    public void quitarPedido(PedidoOrdenProduccion pedido) {
        entity.getPedidoOrdenProduccionList().remove(pedido);
        setCantidadOrden();
    }

    @Override
    public void reset() {
        setGeneral(false);
        selectedPedidoLinea = null;
        setConsumo(false);
        setAplicandoTransaccion(false);
        setTransEntrada(false);
        super.reset();
    }

    public void liberarOrden() {
        try {
            service.liberarOrden(selection);
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        }
    }

    public void aplicarTrans() {

        if (!service.findByEntity(selection).getEstado().equals(EstadosProd.LIBERADO.getEstado())) {
            MessageUtils.showGrowlError("La orden no se ha liberado");
            return;
        }

        if (!service.findByEntity(selection).getEstado().equals(EstadosProd.LIBERADO.getEstado())) {
            MessageUtils.showGrowlError("La orden no se ha liberado");
            return;
        }

        if (service.findByEntity(selection).getConsumoMateriaList().isEmpty()) {
            MessageUtils.showGrowlError("La orden no tiene consumo");
            return;
        }
        entity = service.findByEntity(selection);
        reset();
        setConsumo(true);
        setAplicandoTransaccion(true);
        setEditing(true);
        setFormView(true);
    }

    public void viewAplicarTransEntrada() {
        entity = service.findByEntity(selection);
        entity.setCantidadProducida(entity.getCantidad());
        calcularCostoTotalProduccion();
        reset();
        setTransEntrada(true);
        setEditing(true);
        setFormView(true);
    }

    public void aplicarTransEntrada() {
        try {
            service.aplicarTransEntrada(entity, getUsername());
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception ex) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            Logger.getLogger(OrdenProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void calcularCostoTotalProduccion() {
        CSOrdenProduccion orden = entity;
        MontoLocalDolar monto = service.getCostoTotalProduccion(orden);
        orden.setCostoRealLoc(monto.getMontoLocal());
        orden.setCostoRealDol(monto.getMontoDolar());
    }

    public void aplicarTransaccionesInventario() {
        List<Etapa> etapas = entity.getConsumoMateriaList().stream()
                .map(cm -> cm.getMateriaPrima().getEtapa())
                .collect(Collectors.toList());
        aplicarTransaccionesInventario(etapas);
    }

    public void aplicarTransaccionesInventario(List<Etapa> etapas) {
        try {
//            for (ConsumoMateria consumoMateria : selection.getConsumoMateriaList()) {
//                Optional<ExistenciaBodega> opt = existenciaBodegaList.stream().filter(eb -> eb.getArticulo().equals(consumoMateria.getMateriaPrima().getArticuloHijo())).findFirst();
//                if (opt.isPresent()) {
//                    consumoMateria.setExistenciaBodega(existenciaBodegaService.find(new ExistenciaBodegaPK(opt.get().getArticulo().getArticulo(), opt.get().getBodega().getBodega())));
//                }
//            }
            service.aplicarTransaccionInventario(selection, etapas, getUsername());
            cancel();
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception ex) {
            MessageUtils.showGrowlError();
            Logger.getLogger(OrdenProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aplicarTransaccionesInventarioEtapa(Etapa etapa) {
        if (isEtapaAplicada(etapa)) {
            MessageUtils.showMessage("La transacción para este proceso ya fue aplicada.");
            return;
        }
        List<Etapa> etapas = new ArrayList<>();
        etapas.add(etapa);
        aplicarTransaccionesInventario(etapas);
    }

    public boolean isEtapaAplicada(Etapa etapa) {
//        return entity.getConsumoMateriaList().stream()
//                .noneMatch(cm -> cm.getMateriaPrima().getEtapa().equals(etapa) 
//                        && cm.getTransAplicada().equals("N"));
        for (ConsumoMateria cm : entity.getConsumoMateriaList()) {
            ConsumoMateria cmdb = consumoMateriaService.find(cm.getConsumoMateriaPK());
            if (cmdb.getMateriaPrima().getEtapa().equals(etapa) && cmdb.getTransAplicada().equals("N")) {
                return false;
            }
        }
        return true;
    }
    
    public void consolidar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_consolidado_%s.pdf", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/consolidado_pedidos/consolidado_pedidos.jasper");
        String logoPath = request.getServletContext().getRealPath("/resources/images/pimgpsh_fullsize_distr.png");
        Map<String, Object> params = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        
        params.put("p_cantidad", selection.getCantidad());
        params.put("p_articulo_padre", selection.getArticulo().getArticulo());
        params.put("p_logo_path", logoPath);
        params.put("p_desc_producto", selection.getArticulo().getDescripcion());
        try {
            service.generarReporteConsolidado(response.getOutputStream(), sourceFileName, params, null);
            fc.responseComplete();
        } catch (Exception ex) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            Logger.getLogger(OrdenProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
