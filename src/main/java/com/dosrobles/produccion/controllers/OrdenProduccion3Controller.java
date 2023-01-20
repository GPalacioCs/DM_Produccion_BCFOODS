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
 import lombok.Getter;
 import lombok.Setter;
 import org.apache.commons.collections4.CollectionUtils;
 import org.primefaces.component.datatable.DataTable;
 import org.primefaces.context.RequestContext;
 import org.primefaces.event.CellEditEvent;

 import javax.annotation.PostConstruct;
 import javax.faces.context.FacesContext;
 import javax.faces.view.ViewScoped;
 import javax.inject.Inject;
 import javax.inject.Named;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.math.BigDecimal;
 import java.text.SimpleDateFormat;
 import java.time.format.DateTimeFormatter;
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
public class OrdenProduccion3Controller extends AbstractController<OrdenProduccionService, OrdenProduccion> {

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
    @Inject
    private EstrucManufacturaService estrucManufacturaService;
    @Inject
    private OrdenProduccionService ordenProduccionService;
    @Inject
    private EstrucMaterialService estrucMaterialService;
    @Inject
    private EstrucProcesoService estrucProcesoService;
    @Inject
    private EstrucProcRubroService estrucProcRubroService;
    @Inject
    private OrdenProdEmpleadoService ordenProdEmpleadoService;
    @Inject
    private EmpleadoService empleadoService;
    @Inject
    private RubroLiqService rubroLiqService;
    @Inject
    private ActividadProdService actividadProdService;
    @Inject
    private ClienteService clienteService;
    @Inject
    TipoCambioHistService tipoCambioHistService;
    @Inject
    LoteService loteService;
    @Inject
    ExistenciaLoteService existenciaLoteService;
    @Inject
    TransaccionInvService transaccionInvService;
    @Inject
    private GrupoManoObraService grupoManoObraService;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    private List<PedidoOrdenProduccion> pedidoOrdenProduccionList = new ArrayList<>();
    private List<PedidoLinea> pedidoLineaList = new ArrayList<>();
    private List<PedidoLinea> selectedPedidoLineaList;
    //private List<Articulo> articuloList = new ArrayList<>();
    private List<Etapa> etapaList = new ArrayList<>();
    private List<Etapa> selectedEtapasList = new ArrayList<>();
    private List<ExistenciaBodega> existenciaBodegaList = new ArrayList<>();
    private List<Bodega> bodegasList = new ArrayList<>();
    private List<EstrucManufactura> estrucManufacturaList = new ArrayList<>();
    private Bodega bodegaMp;
    private Bodega bodegaPt;
    private List<EstrucMaterial> materiaPrima= new ArrayList<>();
    private List<EstrucProceso> manoDeObra = new ArrayList<>();
    private List<EstrucProcRubro> cif = new ArrayList<>();
    private List<Articulo> articuloMPList = new ArrayList<>();
    private List<Articulo> articuloPTList = new ArrayList<>();
    
    private List<OrdenProdEmpleado> ordenProdEmpleadoList = new ArrayList();
    private List<Empleado> empleadoList = new ArrayList();
    private EstrucManufactura estrucManufactura;
    
    
    private EstrucMaterial MP;
    private Articulo articulo;
    
    private final String tipoCosto = "P";
    
    private OrdenProdEmpleado ordenProdEmpleado;
    private PedidoLinea selectedPedidoLinea;
    private Empleado empleado;
    private OrdenProdMp selectedOrdenProdMp;
    @Getter @Setter
    private OrdenProdMd selectedOrdenProdMd;
    private boolean general;
    private boolean consumo;
    private boolean aplicandoTransaccion;
    private boolean transEntrada;
    private Articulo selectedMateriaPrima;
    private Articulo selectedArticuloMaterial;
    private List<Articulo> materialList = new ArrayList<>();    
    private List<OrdenProdMp> ordenProdMpList;
    @Getter @Setter
    private RubroLiq selectedRubroLiq;
    @Getter @Setter
    private List<RubroLiq> rubroLiqList = new ArrayList<>();
//    @Getter @Setter
//    private List<ActividadProd> actividadProdList = new ArrayList<>();
    @Getter @Setter
    private List<Cliente> clienteList = new ArrayList<>();
    @Getter @Setter
    private Cliente selectedCliente;    
    @Getter @Setter
    private IngresoProduccion ingresoProduccion = new IngresoProduccion();    
    @Getter @Setter
    private OrdenProdEmpleado selectedOrdenProdEmpleado;
    @Getter @Setter
    private boolean mostrarLiberados;
    @Getter @Setter
    private GrupoManoObra selectedGrupoManoObra;
    @Getter @Setter
    private boolean mostrarSoloSugerido = true;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public EstrucMaterial getMP() {
        return MP;
    }

    public void setMP(EstrucMaterial MP) {
        this.MP = MP;
    }
    
    public void setOrdenProdEmpleado(OrdenProdEmpleado ordenProdEmpleado) {
        this.ordenProdEmpleado = ordenProdEmpleado;
    }
    public OrdenProdEmpleado getOrdenProdEmpleado() {
        return ordenProdEmpleado;
    }

    public List<OrdenProdEmpleado> getOrdenProdEmpleadoList() {
        return ordenProdEmpleadoList;
    }

    public void setOrdenProdEmpleadoList(List<OrdenProdEmpleado> ordenProdEmpleado) {
        this.ordenProdEmpleadoList = ordenProdEmpleado;
    }


    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    public Articulo getArticulo() {
        return articulo;
    }
    public List<Articulo> getArticuloPTList() {
        return articuloPTList;
    }

    public void setArticuloPTList(List<Articulo> articuloPTList) {
        this.articuloPTList = articuloPTList;
    }
    
    public List<EstrucProcRubro> getCif() {
        return cif;
    }

    public void setCif(List<EstrucProcRubro> cif) {
        this.cif = cif;
    }

    public List<EstrucProceso> getManoDeObra() {
        return manoDeObra;
    }

    public void setManoDeObra(List<EstrucProceso> manoDeObra) {
        this.manoDeObra = manoDeObra;
    }
    
    public List<PedidoOrdenProduccion> getPedidoOrdenProduccionList() {
        return pedidoOrdenProduccionList;
    }

    public List<PedidoLinea> getPedidoLineaList() {
        return pedidoLineaList;
    }

    public List<EstrucMaterial> getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(List<EstrucMaterial> materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
    
    public void setPedidoLineaList(Articulo articulo) {
        this.pedidoLineaList = pedidoLineaService.getPedidoLineaList(articulo)
                .stream().filter(pl -> "N".equals(pl.getPedido().getEstado()))
                .collect(Collectors.toList());
    }

    public List<Etapa> getEtapaList() {
        return etapaList;
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

    public List<Articulo> getArticuloMPList() {
        return articuloMPList;
    }

    public void setArticuloMPList(List<Articulo> articuloMPList) {
        this.articuloMPList = articuloMPList;
    }

    public List<EstrucManufactura> getEstrucManufacturaList() {
        return estrucManufacturaList;
    }

    public void setEstrucManufacturaList(List<EstrucManufactura> estrucManufacturaList) {
        this.estrucManufacturaList = estrucManufacturaList;
    }

    public EstrucManufactura getEstrucManufactura() {
        return estrucManufactura;
    }

    public void setEstrucManufactura(EstrucManufactura estrucManufactura) {
        this.estrucManufactura = estrucManufactura;
    }

    public OrdenProdMp getSelectedOrdenProdMp() {
        return selectedOrdenProdMp;
    }

    public void setSelectedOrdenProdMp(OrdenProdMp selectedOrdenProdMp) {
        this.selectedOrdenProdMp = selectedOrdenProdMp;
    }
    
    public Articulo getSelectedMateriaPrima() {
        return selectedMateriaPrima;
    }

    public void setSelectedMateriaPrima(Articulo selectedMateriaPrima) {
        this.selectedMateriaPrima = selectedMateriaPrima;
    }

    public Articulo getSelectedArticuloMaterial() {
        return selectedArticuloMaterial;
    }

    public void setSelectedArticuloMaterial(Articulo selectedArticuloMaterial) {
        this.selectedArticuloMaterial = selectedArticuloMaterial;
    }

    public List<Articulo> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Articulo> materialList) {
        this.materialList = materialList;
    }
    
    
//</editor-fold>

    @Inject
    EmpaqueEncabezadoService empaqueEncabezadoService;

    public List<ActividadProd> getActividadProdList() {
        if(entity ==  null) return new ArrayList<>();
        return entity.getOrdenProduccionActividadList().stream()
                .map(opa -> opa.getActividad()).collect(Collectors.toList());
    }
    
    @PostConstruct
    private void init() {
        cargarLista();
        cargarMaterialList();
        cargarRubros();
        cargarActividades();
        cargarEmpleados();
        this.articuloPTList = this.articuloService.getArticulosProduccion();
        this.estrucManufacturaList = this.estrucManufacturaService.findAll();        
        this.articuloMPList = new ArrayList<>(this.articuloService.getArticulosMateriaPrima());
        this.clienteList = clienteService.findAll();
        OrdenProduccion orden = (OrdenProduccion) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("ordenProduccion");
        if(orden != null) {
            if(orden.getOrdenProduccion() != null) {
                edit(orden);                
            } else {
                create();
                entity = orden;
            }
            fillIngresoEmpaque();
        }
    }

    @Override
    public void create() {
        entity = new OrdenProduccion();
        entity.setFechaRequerida(Utils.stripTime(new Date()));
        selectedOrdenProdMp = new OrdenProdMp();
        selectedOrdenProdMd = new OrdenProdMd();
        selectedOrdenProdEmpleado = new OrdenProdEmpleado();
        //empleado = new Empleado();
        entity.setEstado(EstadosProd.PLANEADO.getEstado());
        entity.setFechaCreacion(Utils.stripTime(new Date()));
        entity.setFechaHoraUltAct(Utils.stripTime(new Date()));
        this.MP = new EstrucMaterial();
        pedidoLineaList = new ArrayList<>();
        
        
        
        //this.materiaPrima = this.estrucMaterialService.findAll();
        reset();
        setGeneral(true);
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
                        
        OrdenProduccion orden = service.findByEntity(selection);
//        if (service.findByEntity(selection).getEstado().equals(EstadosProd.LIBERADO.getEstado())) {
//            entity = orden;
//            reset();
//            setViewing(true);
//            setFormView(true);
//            return;
//        }
        service.detach(orden);
        edit(orden);
    }
    
    public void edit(OrdenProduccion orden) {
        try {
            selectedOrdenProdMp = new OrdenProdMp();
            selectedOrdenProdMd = new OrdenProdMd();
            selectedOrdenProdEmpleado = new OrdenProdEmpleado();
            entity = orden;
//        setPedidoLineaList(entity.getArticulo());
            if (orden.isTieneEmpaque()) {
                fillIngresoEmpaque();
            }
            IngresoProduccion ingresoSinMp = entity.getIngresoProduccoinList().stream().filter(ingreso -> ingreso.getMateriaPrima() == null)
                    .findFirst().orElse(null);
            if(entity.isFresco() && ingresoSinMp != null) {
                throw new BusinessValidationException(String.format("El artículo %s no tiene una materia prima asociada", ingresoSinMp.getArticulo().getArticulo()));
            }
            sugerirActividades();
            reset();
            setGeneral(true);
            setEditing(orden.getEstadoProd() != EstadosProd.LIBERADO);
            setViewing(orden.getEstadoProd() == EstadosProd.LIBERADO);
            setFormView(true);
        } catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        }
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
        if (!mostrarLiberados) {
            list = service.findByEstados("P");
        } else {
            list = service.findByEstados("P","L");
        }
        
    }
    
    public void cargarMaterialList() {
        setMaterialList(new ArrayList<>(articuloService.getMaterialesDirectos()));
    }

    public void onArticuloSelected() {
        //setPedidoLineaList(entity.getArticulo());
        //Articulo a = articuloService.find(entity.getArticulo().getArticulo().getArticulo());
        //setArticulo(a);
    }

    public void onEmpleadoSelected()
    {
        
        //setEmpleado(empleado);
        //MessageUtils.showMessage(empleado.toString());
//        Empleado e = empleadoService.find(empleado.getEmpleado());
//        empleadoList.add(e);
    }
    
    public void onEstrucManufacturaSelected()
    {
        entity.setArticulo(ingresoProduccion.getArticulo());
        
        String Articulo = ingresoProduccion.getArticulo().getArticulo();//entity.getArticulo().getEstrucManufacturaPK().getArticulo();
        String version = "1.01";
        Articulo a = articuloService.find(ingresoProduccion.getArticulo().getArticulo());
        setArticulo(a);
        //String version = estrucManufacturaService.getLastVersion(entity.getArticulo().getEstrucManufacturaPK().getArticulo());
        if(estrucManufacturaService.existEstrucManufactura(Articulo))
        {
            this.estrucManufactura = estrucManufacturaService.findByArticuloString(Articulo);
            this.materiaPrima = estrucMaterialService.getListMaterial(Articulo, version);
            this.manoDeObra = estrucProcesoService.getEstrucProcesoList(Articulo, version);
            this.cif = estrucProcRubroService.getEstrucProcRubroList(Articulo, version);
        }        
        entity.setOrdenProdMpList(new ArrayList<>(getMateriaPrimaList(a)));
        
    }
    
    public BigDecimal sumCantidadPedidos() {

        /*return Utils.sumBigDecimalList(entity.getPedidoOrdenProduccionList().stream()
                .map(pl -> pl.getCantidad()).collect(Collectors.toList()));
*/
        return null;
    }

    public void setCantidadOrden() {
        //entity.setCantidad(sumCantidadPedidos());
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

    public void onBtnAddEmpleado(String orden){
        agregarEmpleado(selectedOrdenProdEmpleado);
    }
//    
//    public void agregarEmpleado(Empleado empleado) {
//        if(empleado!= null && entity.getEmpleadoList().stream().noneMatch(e -> e.getOrdenProdEmpleadoPK().getEmpleado().equals(empleado.getEmpleado()))) {
//            OrdenProdEmpleado o = new OrdenProdEmpleado();
//            o.setEmpleado(empleado);
//            OrdenProdEmpleadoPK pk = new OrdenProdEmpleadoPK();
//            pk.setEmpleado(empleado.getEmpleado());
//            pk.setOrdenProduccion(entity.getOrdenProduccion());
//            o.setOrdenProdEmpleadoPK(pk);
//            o.setActividad(actividadProdList.stream().findFirst().orElse(null));
//            entity.getEmpleadoList().add(o);            
//        }
//    }
    
    
    
    public void agregarEmpleado(OrdenProdEmpleado ope) {
        try {
            if (ope == null || ope.getEmpleado() == null) {
                throw new BusinessValidationException("Debe seleccionar un empleado");
            }
            
            if(ope.getActividad() == null) {
                throw new BusinessValidationException("Debe seleccionar la actividad");
            }
            
            ope.setOrdenProduccion(entity);
            if(entity.getEmpleadoList().stream().noneMatch(e -> 
                    e.getEmpleado().equals(ope.getEmpleado())
            && e.getActividad().equals(ope.getActividad()))) {
                ope.setCostoDolar(ope.getActividad().getCostoDolar());
                ope.setCosto(Utils.dol2loc(ope.getCostoDolar(), tipoCambioHistService.getTipoCambioActual(entity.getFechaRequerida())));
                entity.getEmpleadoList().add(ope);
            }
            
            this.selectedOrdenProdEmpleado = new OrdenProdEmpleado();
            
        } catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        }
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

            /*if (entity.getPedidoOrdenProduccionList().stream().anyMatch(p -> p.getPedido().equals(selectedPedidoLinea.getPedido()))) {
                throw new BusinessValidationException("El pedido ya fue agregado");
            }*/

            /*PedidoOrdenProduccion pop = new PedidoOrdenProduccion(new PedidoOrdenProduccionPK(selectedPedidoLinea.getPedido().getPedido(), entity.getOrdenProduccion()));
            pop.setCantidad(selectedPedidoLinea.getCantidadPedida());
            pop.setPedido(selectedPedidoLinea.getPedido());
            pop.setOrdenProduccion(entity);*/
            //entity.getPedidoOrdenProduccionList().add(pop);
            setCantidadOrden();
        } catch (BusinessValidationException e) {
            MessageUtils.showMessage(e.getMessage());
        }
    }

    public void quitarPedido(PedidoOrdenProduccion pedido) {
        /*entity.getPedidoOrdenProduccionList().remove(pedido);
        setCantidadOrden();*/
    }
    
    public void agregarMateriaPrima() {
        agregarMateriaPrima(selectedOrdenProdMp);
    }
    
    public void agregarMateriaPrima(OrdenProdMp materiaPrima) {
        
        try {
            if(materiaPrima == null || materiaPrima.getComponente()==null) throw new BusinessValidationException("Debe seleccionar la materia prima");
            boolean componenteNoAgregado = entity.getOrdenProdMpList().stream().noneMatch(mp -> mp.getComponente().getArticulo().equals(materiaPrima.getComponente().getArticulo()));
            boolean productoNoAgregado = entity.getOrdenProdMpList().stream().noneMatch(mp -> mp.getId().getProducto().equals(materiaPrima.getId().getProducto()));
            
            if ((componenteNoAgregado || productoNoAgregado)) {
                if (materiaPrima.getId().getProducto() == null) {
//                    throw new BusinessValidationException("Debe seleccionar el producto");
                    materiaPrima.setProducto(new Articulo("ND"));
                    materiaPrima.getId().setProducto("ND");
                }
                materiaPrima.setOrdenProduccion(entity);                
                Map<String, BigDecimal> elCantMap = existenciaLoteService.getExistenciasPorArticulo(materiaPrima.getComponente(), entity, materiaPrima.getCantidad());
                for (Map.Entry<String, BigDecimal> entry : elCantMap.entrySet()) {
                    OrdenProdMp mp = new OrdenProdMp();
                    mp.setLote(entry.getKey());
                    mp.setCantidad(entry.getValue());
                    mp.setComponente(materiaPrima.getComponente());
                    mp.setCosto(mp.getComponente().getCostoPromLoc());
                    mp.setCostoDolar(mp.getComponente().getCostoPromDol());
                    mp.setOrdenProduccion(materiaPrima.getOrdenProduccion());
                    mp.setProducto(new Articulo("ND"));
                    mp.getId().setProducto("ND");
                    entity.getOrdenProdMpList().add(mp);
                }
                
            }
            selectedOrdenProdMp = new OrdenProdMp();
            selectedOrdenProdMp.setComponente(materiaPrima.getComponente());
            selectedOrdenProdMp.setProducto(materiaPrima.getProducto());
        } catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        }
    }
    
    private OrdenProdMp getMpUltimoLote() {        
        List<ExistenciaLote> elList = existenciaLoteService.getExistenciaLoteList(selectedOrdenProdMp.getComponente(), null);
        if(CollectionUtils.isEmpty(elList)) return null;        
        Map<String, ExistenciaLote> elMap = elList.stream().collect(Collectors.toMap(el -> el.getId().getLote(), el -> el));
        
        OrdenProdMp mp = entity.getOrdenProdMpList().stream()
                .filter(materia -> materia.getComponente().equals(selectedOrdenProdMp.getComponente()))
                .sorted((mp2, mp1) -> elMap.get(mp1.getLote()).getLote().getFechaEntrada().compareTo(elMap.get(mp2.getLote()).getLote().getFechaEntrada()))
                .findFirst().orElse(null);         
        
        return mp;
    }
    
    public void agregarMaterial() {
        agregarMaterial(selectedOrdenProdMd);
    }
    
    public void agregarMaterial(OrdenProdMd material) {
        try {
            if(material == null || material.getComponente() == null) {
                throw new BusinessValidationException("Debe seleccionar el material");
            }
            boolean componenteNoAgregado = entity.getOrdenProdMdList().stream().noneMatch(mp -> mp.getComponente().getArticulo().equals(material.getComponente().getArticulo()));
            boolean productoNoAgregado = entity.getOrdenProdMdList().stream().noneMatch(mp -> mp.getId().getProducto().equals(material.getId().getProducto()));
            if ((componenteNoAgregado || productoNoAgregado)) {
                if (material.getId().getProducto() == null) {
//                    throw new BusinessValidationException("Debe seleccionar el producto");
                    material.setProducto(new Articulo("ND"));
                    material.getId().setProducto("ND");
                }
                material.setOrdenProduccion(entity);
                entity.getOrdenProdMdList().add(material);
            }
            selectedOrdenProdMd = new OrdenProdMd();
            selectedOrdenProdMd.setComponente(material.getComponente());
            selectedOrdenProdMd.setProducto(material.getProducto());
        } catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        }
    }
    
    public void agregarRubro() {
        agregarRubro(selectedRubroLiq);
    }
    
    public void agregarRubro(RubroLiq rubro) {
        if(rubro != null && entity.getOrdenProdRubroList().stream().noneMatch(r -> r.getId().getRubro().equals(rubro.getRubro()))) {
            OrdenProdRubro opr = new OrdenProdRubro();
            opr.setId(new OrdenProdRubroPK(entity.getOrdenProduccion(), rubro.getRubro()));
            opr.setRubroLiq(rubro);
            opr.setCosto(rubro.getCostoPorUnidadL());
            opr.setCostoDolar(rubro.getCostoPorUnidadD());
            opr.setCantidad(Utils.sumBigDecimals(entity.getIngresoProduccoinList(), ip -> ip.getCantidad()));
            entity.getOrdenProdRubroList().add(opr);            
        }
    }
    
    public void quitarMateriaPrima(OrdenProdMp materia) {
        entity.getOrdenProdMpList().remove(materia);
    }
    
    public void quitarMaterial(OrdenProdMd material) {
        entity.getOrdenProdMdList().remove(material);
    }
    
    public void quitarEmpleado(OrdenProdEmpleado empleado) {
        this.entity.getEmpleadoList().removeIf(e-> e.getEmpleado().equals(empleado.getEmpleado()) && e.getActividad().equals(empleado.getActividad()));
    }
    
    public void quitarRubro(OrdenProdRubro rubro) {
        this.entity.getOrdenProdRubroList().remove(rubro);
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
            service.liberarOrden(selection, getUsername());
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showGrowlError();
            e.printStackTrace();
        }
    }

    public void aprobarOrden() {
       this.selection.setEstado(EstadosProd.CONFIRMADA.getEstado());
       
//       service.saveOrden(selection);
    }

    private List<OrdenProdMp> getMateriaPrimaList(Articulo articulo) {
        return articulo.getEstrucMaterialList().stream()
                .map(em -> {
                  OrdenProdMp orden = new OrdenProdMp(new OrdenProdMpPK(entity.getOrdenProduccion(), em.getEstrucMaterialPK().getComponente(), articulo.getArticulo(), "ND"));                  
                  orden.setCantidad(em.getCantidad());
                  orden.setComponente(em.getComponente());
                  orden.setCosto(em.getCostoUnitario());                  
                  return orden;
                }).collect(Collectors.toList());
    }
    
    public BigDecimal getCostoMateriaPrima() {
        return Utils.sumBigDecimals(entity.getOrdenProdMpList(), mp -> mp.getCosto().multiply(mp.getCantidad()) );
    }
    
    public BigDecimal getCostoManoDeObra() {
        return Utils.sumBigDecimals(entity.getEmpleadoList(), mo -> mo.getCosto() );
    }
    
    public BigDecimal getCostoMateriales() {
        return Utils.sumBigDecimals(entity.getOrdenProdMdList(), md -> md.getCosto().multiply(md.getCantidad()));
    }
    
    public BigDecimal getCostoCif() {
        return Utils.sumBigDecimals(entity.getOrdenProdRubroList(), r -> r.getCosto().multiply(r.getCantidad()));
    }
    
    public BigDecimal getCostoTotal() {
        return getCostoMateriaPrima()
                .add(getCostoManoDeObra())
                .add(getCostoMateriales())
                .add(getCostoCif());
    }
    
    public List<GrupoManoObra> getGruposManoObra() {
        return grupoManoObraService.findAll().stream().filter(g -> !mostrarSoloSugerido
                || entity.getOrdenProduccionActividadList().stream().anyMatch(a -> a.getActividad().getCodigo().equals(g.getActividad().getCodigo())))
                .collect(Collectors.toList());
    }

    private void cargarRubros() {
        this.rubroLiqList = this.rubroLiqService.findAll();
    }

    private void cargarActividades() {
//        setActividadProdList(actividadProdService.findAll());
    }

    private void cargarEmpleados() {
        this.empleadoList = empleadoService.findAll("schema02").stream().filter(e -> "02.01.00".equals(e.getCentroCosto())).collect(Collectors.toList());
    }
    
    public void agregarIngresoProduccion() {
        if(entity.getOrdenProduccion() == null) {
            MessageUtils.showGrowlError("Debe guardar la orden de produccion antes de proceder");
            return;
        }
        if (ingresoProduccion != null && ingresoProduccion.getArticulo() != null && this.entity.getIngresoProduccoinList().stream().noneMatch(ip -> ip.getArticulo().equals(ingresoProduccion.getArticulo()))) {
            ingresoProduccion.setOrdenProduccion(entity);
            if (entity.getFechaRequerida() != null) {                
                ingresoProduccion.setFechaVencimiento(Utils.ldt2date(Utils.date2ldt(entity.getFechaRequerida()).plusMonths(24)));
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String lote = formatter.format(entity.getFechaRequerida());

            ingresoProduccion.setLote(lote+ingresoProduccion.getOrdenProduccion().getOrdenProduccion().toString());
            this.entity.getIngresoProduccoinList().add(ingresoProduccion);            
//            agregarMaquila(ingresoProduccion);
            recalcularMaquila();
            ingresoProduccion = new IngresoProduccion();
            sugerirActividades();
        }
    }
    
    public void removeIngresoProduccion() {
        
    }
    
    public String showEmpaques() {
        if(entity.getOrdenProduccion() == null) {
            MessageUtils.showGrowlError("Debe guardar la orden de produccion antes de proceder");
            return null;
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("ordenProduccion", entity);
        return "/produccion/empaque/empaque2.xhtml";
    }
    
    public void onFrescoChanged() {
        if(entity.isFresco()) {
            entity.getIngresoProduccoinList().clear();
        }
    }
    
    public void fillIngresoEmpaque() {
        service.fillIngresoEmpaque(entity);
        entity.getMaquilaList().clear();
        for (IngresoProduccion ingreso : entity.getIngresoProduccoinList()) {
            ingreso.setFechaVencimiento(Utils.ldt2date(Utils.date2ldt(entity.getFechaRequerida()).plusDays(15)));
//            agregarMaquila(ingreso);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String lote = formatter.format(entity.getFechaRequerida())+entity.getOrdenProduccion();
            ingreso.setLote(lote);
        }        
        recalcularMaquila();
    }
    
    public void sugerir(int tipoMaterial) {
        service.sugerir(entity, tipoMaterial);
    }
    
    public List<Articulo> getProductosIngresados() {
        if(entity == null) return new ArrayList<>();
        return entity.getIngresoProduccoinList().stream().map(ingreso -> ingreso.getArticulo())
                .collect(Collectors.toList());
    }
    
    public void quitarIngresoProduccion(IngresoProduccion ingreso) {
        entity.getIngresoProduccoinList().removeIf(i -> ingreso.getArticulo().equals(i.getArticulo()));
        quitarMaquila(ingreso);
        sugerirActividades();
    }
    
    public void agregarMaquila(IngresoProduccion ingreso) {
        Maquila maquila = new Maquila();
        maquila.setArticulo(ingreso.getArticulo());
        maquila.setOrdenProduccion(ingreso.getOrdenProduccion());
        maquila.setCantidad(ingreso.getCantidad());
        maquila.setPrecio(Utils.dol2loc(ingreso.getArticulo().getPrecioMaquila(), tipoCambioHistService.getTipoCambioActual(entity.getFechaRequerida())));        
        maquila.setPrecioDolar(ingreso.getArticulo().getPrecioMaquila());
        entity.getMaquilaList().add(maquila);
    }
    
    public void quitarMaquila(IngresoProduccion ingreso) {
        entity.getMaquilaList().removeIf(m -> m.getArticulo().getArticulo().equals(ingreso.getArticulo().getArticulo()));
    }
    
    public void updateMaquilaRow(CellEditEvent event) {
        DataTable s = (DataTable) event.getSource();
        RequestContext.getCurrentInstance().update(s.getClientId(FacesContext.getCurrentInstance())
                    +
             ":" + event.getRowIndex() +
             ":otMonto"
        );
    }
    
    public void generarAsiento() {
        
        try{
            if(selection == null){
                throw new BusinessValidationException("Debe seleccionar una orden");
            }
            service.generarAsiento(selection, getUsername());
            MessageUtils.showGrowlSuccess();
        } catch(BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        } catch(Exception e) {
            MessageUtils.showGrowlError();
            e.printStackTrace();
        }
    }
 
    public void revertirEstado() {
        service.revertirEstado(selection);
        cargarLista();
    }
    
    public void generarEtiquetas() {
        String sourceFile;
        String sourceFileName;
        String logo;
        String logoHaccp;
        String logoIso;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (selection.isFresco()) {
            try {
                List<String> series = new ArrayList<>();

                for (EmpaqueEncabezado empaqueEncabezado: selection.getEmpaqueEncabezadoList()) {
                    for (Empaque empaque: empaqueEncabezado.getEmpaqueList()) {
                        if (empaque.isImpreso()) {
                            String serie = service.getSerieFresco(selection, empaque, empaqueEncabezado.getCliente().getCliente());
                            series.add(serie);
                            continue;
                        }
                        String serie = service.generarSerieFresco(selection, empaque, empaqueEncabezado.getCliente().getCliente());
                        series.add(serie);
                        empaque.setImpreso(true);
                    }
                    empaqueEncabezadoService.save(empaqueEncabezado);
                }

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
                sourceFile = "/reportes/rpt_etiquetas/rpt_etiqueta_fresco_orden.jasper";
                sourceFileName = request.getServletContext().getRealPath(sourceFile);
                logo = request.getServletContext().getRealPath("/resources/images/bcfoods_logo.jpg");
                logoHaccp = request.getServletContext().getRealPath("/resources/images/haccp.png");
                logoIso = request.getServletContext().getRealPath("/resources/images/client_logo_ISO_22000.jpg");

                String lote = new SimpleDateFormat("yyyyMMdd").format(selection.getFechaRequerida())+selection.getOrdenProduccion();
                Map<String, Object> params = new HashMap<>();
                params.put("p_logo_haccp", logoHaccp);
                params.put("p_logo_iso", logoIso);
                params.put("p_fecha_produccion", sdf.format(selection.getFechaRequerida()));
                //Actualizacion
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selection.getFechaRequerida());
                calendar.add(Calendar.DAY_OF_YEAR,10);
                params.put("p_fecha_produccion_Vence", sdf.format(calendar.getTime()));

                ordenProduccionService.imprimirEtiquetaFresco(series, lote, response.getOutputStream(), sourceFileName, false, selection.getOrdenProduccion(), logo, params);
                save();
                fc.responseComplete();
            }catch (Exception ex){
                Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if(selection.isImpreso()) {
                MessageUtils.showGrowlError("La etiqueta ya fue impresa");
                return;
            }

            FacesContext fc = FacesContext.getCurrentInstance();
            fc.responseComplete();
            HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType(MimeUtils.getMimeType("pdf"));

            String filename = String.format("rpt_%s_%s.pdf", "etiqueta_congelado", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
            response.setHeader("Content-disposition","attachment; filename="+filename);
            sourceFile = "/reportes/rpt_etiquetas/rpt_etiqueta_congelado.jasper";
            sourceFileName = request.getServletContext().getRealPath(sourceFile);
            logoHaccp = request.getServletContext().getRealPath("/resources/images/haccp.png");
            logoIso = request.getServletContext().getRealPath("/resources/images/client_logo_ISO_22000.jpg");

            try {
                List<String> series = new ArrayList<>();
                service.generarEtiquetaCongelado(selection, response.getOutputStream(), sourceFileName, series);
                service.setImpreso(true, selection.getOrdenProduccion());
                selection.setImpreso(true);
                String fechaVencimientoStr = Utils.date2ldt(selection.getFechaRequerida()).plusMonths(24).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Map<String, Object> params = new HashMap<>();
                params.put("p_logo_haccp", logoHaccp);
                params.put("p_logo_iso", logoIso);
                params.put("p_orden_produccion", selection.getOrdenProduccion());
                service.imprimirEtiquetas(series, selection.getIngresoProduccoinList().get(0).getLote(), fechaVencimientoStr, response.getOutputStream(), sourceFileName, params);
                fc.responseComplete();
            } catch (Exception ex) {
                Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sugerirActividades() {
        List<OrdenProduccionActividad> opaList = entity.getOrdenProduccionActividadList();
        List<ActividadProd> actividadesDisponibles = new ArrayList<>();
        List<ActividadProd> actividadesAgregadas = opaList.stream().map(opa -> opa.getActividad()).collect(Collectors.toList());
        for (IngresoProduccion ingreso : entity.getIngresoProduccoinList()) {
            ClasificAdiArticulo caa = ingreso.getArticulo().getClasificAdiArticuloList().stream().findFirst().orElse(null);
            if(caa!=null) {
                for (TransformacionActividad transformacionActividad : caa.getValor().getTransformacionActividadList()) {
                    if(actividadesDisponibles.stream().noneMatch(ad -> Objects.equals(ad, transformacionActividad.getActividad()))) {
                        actividadesDisponibles.add(transformacionActividad.getActividad());
                    }                    
                }
            }            
        }
        actividadesAgregadas.retainAll(actividadesDisponibles);
        for (ActividadProd actividad : actividadesDisponibles) {
            if(!actividadesAgregadas.contains(actividad)) {
                actividadesAgregadas.add(actividad);
            }
        }
        opaList.removeIf(opa -> !actividadesAgregadas.contains(opa.getActividad()));
        actividadesAgregadas.stream().filter(a -> opaList.stream().noneMatch(opa -> Objects.equals(a, opa.getActividad())))
                .forEach(actividad -> {
                    OrdenProduccionActividad opa = new OrdenProduccionActividad();
                    opa.setActividad(actividad);
                    opa.setOrdenProduccion(entity);
                    if(opa.getLibras() == null || opa.getLibras().compareTo(BigDecimal.ZERO) == 0) {
                        opa.setLibras(Utils.sumBigDecimals(entity.getIngresoProduccoinList(), ip -> ip.getCantidad()));
                    }
                    opaList.add(opa);
                });
    }
    
    public List<OrdenProdMp> getOrdenProdMpConsolidado() {
        List<OrdenProdMp> list = new ArrayList<>();
        Map<Articulo, BigDecimal> articuloMap = new HashMap<>();
        for (OrdenProdMp mp : entity.getOrdenProdMpList()) {
            BigDecimal cantActual = articuloMap.getOrDefault(mp.getComponente(), BigDecimal.ZERO);
            articuloMap.put(mp.getComponente(), cantActual.add(mp.getCantidad()));
        }
        for (Map.Entry<Articulo, BigDecimal> entry : articuloMap.entrySet()) {
            Articulo articulo = entry.getKey();
            BigDecimal cantidad = entry.getValue();
            OrdenProdMp mp = new OrdenProdMp();
            mp.setComponente(articulo);
            mp.setCantidad(cantidad);
            list.add(mp);
        }
        Collections.sort(list, (mp1, mp2) -> mp1.getComponente().getArticulo().compareTo(mp2.getComponente().getArticulo()));
        return list;
    }
    
    public void sugerirCif() {
        List<RubroLiq> rubroList = new ArrayList<>();
        rubroList.addAll(rubroLiqService.findByCodigos(entity.getIngresoProduccoinList().stream()
            .filter(ip->ip.getArticulo().getTransformacion()!= null)
            .map(ip -> ip.getArticulo().getTransformacion().getId().getValor())
            .collect(Collectors.toList())));
        
        Map<String, BigDecimal> rubroMap = rubroList.stream().collect(Collectors.toMap(r -> r.getRubro(), r-> BigDecimal.ZERO));
        
        for (IngresoProduccion ingreso : entity.getIngresoProduccoinList()) {            
            if(ingreso.getArticulo().getTransformacion() != null) {
                
            }
        }
        
    }
    
    public void fixTransacciones() {
        OrdenProduccion orden = selection;
        transaccionInvService.fixTransacciones(orden);        
    }
    
    public void recalcularMaquila() {
        service.recalcularMaquila(entity);
    }
    
    public void agregarEmpleadosGrupoManoObra() {
        GrupoManoObra grupo = selectedGrupoManoObra;
//        entity.getEmpleadoList().clear();
        for (EmpleadoGrupoManoObra egmo: grupo.getEmpleados()) {
            OrdenProdEmpleado ope = new OrdenProdEmpleado();
            ope.setEmpleado(egmo.getEmpleado());
            ope.setActividad(egmo.getGrupo().getActividad());
            agregarEmpleado(ope);
        }
    }
    
    public void clearManoObra() {
        entity.getEmpleadoList().clear();
    }
    
}
