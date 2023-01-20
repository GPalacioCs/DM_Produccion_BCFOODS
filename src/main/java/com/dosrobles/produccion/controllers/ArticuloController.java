/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.TiposArticulo;
import com.dosrobles.produccion.enums.TiposCostos;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.*;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MontoLocalDolar;
import com.dosrobles.produccion.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author KC
 */
@Named
@ViewScoped
public class ArticuloController extends AbstractController<ArticuloService, Articulo> {

    //<editor-fold defaultstate="collapsed" desc="Injections">
    @Inject
    private ConsecutivoService consecutivoService;
    @Inject
    private ClasificacionService clasificacionService;
    @Inject
    private UnidadDeMedidaService unidadDeMedidaService;
    @Inject
    private BodegaService bodegaService;
    @Inject
    private ImpuestoService impuestoService;
    @Inject
    private ArticuloCuentaService articuloCuentaService;

    @Inject
    private GlobalesController globalesController;

    @Inject
    private ExistenciaBodegaService existenciaBodegaService;
    
    @Inject
    private ParametrosPrService parametrosPrService;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    private List<Consecutivo> consecutivoList = new ArrayList<>();
    private List<UnidadDeMedida> unidadDeMedidaList = new ArrayList<>();

    private ExistenciaBodega existenciaBodega;

    private Bodega bodega;

    private Etapa etapa = new Etapa(new EtapaPK());
    private MateriaPrima materia = new MateriaPrima(new MateriaPrimaPK());

    private List<Articulo> MateriaPrimaList = new ArrayList<>();

    private List<Bodega> bodegasList = new ArrayList<>();

    private int accEtapasActiveIndex;

    private Etapa etapaSeleccionada;

    private List<ArticuloCuenta> articuloCuentaList = new ArrayList<>();

    private final String tipoCosto = TiposCostos.PROMEDIO.getTipo();
    
    private ParametrosPr parametro = new ParametrosPr();
    
    @Getter @Setter
    private UploadedFile uploadedFile;
    @Getter @Setter
    private UploadedFile uploadedFileRendimiento;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public boolean isActivo() {
        if (entity == null) {
            return false;
        }
        return "S".equals(entity.getActivo());
    }

    public void setActivo(boolean activo) {
        if (entity != null) {
            entity.setActivo(activo ? "S" : "N");
        }
    }

    public boolean isUsaLotes() {
        if (entity == null) {
            return false;
        }
        return "S".equals(entity.getUsaLotes());
    }

    public void setUsaLotes(boolean usaLotes) {
        if (entity != null) {
            entity.setUsaLotes(usaLotes ? "S" : "N");
        }
    }

    public List<UnidadDeMedida> getUnidadDeMedidaList() {
        return unidadDeMedidaList;
    }

    public List<SelectItem> getTiposArticulo() {
        List<SelectItem> items = new ArrayList<>();
        for (TiposArticulo tipo : TiposArticulo.values()) {
            items.add(new SelectItem(tipo.getTipo(), tipo.getDescripcion()));
        }
        return items;
    }

    public List<SelectItem> getTiposCostos() {
        List<SelectItem> items = new ArrayList<>();
        for (TiposCostos tipo : TiposCostos.values()) {
            items.add(new SelectItem(tipo.getTipo(), tipo.getDescripcion()));
        }
        return items;
    }

    public List<Bodega> getBodegas() {
        return bodegasList;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Impuesto> getImpuestos() {
        return impuestoService.findAll();
    }

    public ExistenciaBodega getExistenciaBodega() {
        return existenciaBodega;
    }

    public void setExistenciaBodega(ExistenciaBodega existenciaBodega) {
        this.existenciaBodega = existenciaBodega;
    }

    public List<Consecutivo> getConsecutivoList() {
        return consecutivoService.getConsecutivoList("ART");
    }

    public List<ArticuloCuenta> getArticuloCuentaList() {
        return articuloCuentaList;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public MateriaPrima getMateria() {
        return materia;
    }

    public void setMateria(MateriaPrima materia) {
        this.materia = materia;
    }

    public List<Articulo> getMateriaPrimaList() {
        return MateriaPrimaList;
    }

    public int getAccEtapasActiveIndex() {
        return accEtapasActiveIndex;
    }

    public void setAccEtapasActiveIndex(int accEtapasActiveIndex) {
        this.accEtapasActiveIndex = accEtapasActiveIndex;
    }

    public Etapa getEtapaSeleccionada() {
        return etapaSeleccionada;
    }

    public void setEtapaSeleccionada(Etapa etapaSeleccionada) {
        this.etapaSeleccionada = etapaSeleccionada;
    }
//</editor-fold>

    @PostConstruct
    private void init() {
        try {
            parametro = parametrosPrService.getParametro();
        } catch (BusinessValidationException ex) {
            parametro = new ParametrosPr();
            MessageUtils.showGrowlError(ex.getMessage());
        }
        cargarLista();
        this.unidadDeMedidaList = unidadDeMedidaService.findAll();
        this.MateriaPrimaList = service.getMateriasPrimasAndMaterialesDirectors();
        addDefaultColumns();
        this.bodegasList = bodegaService.findAll();
        this.articuloCuentaList = articuloCuentaService.findAll();
        
    }

    @Override
    public void create() {
        entity = new Articulo();
        entity.setUsuarioCreacion(getUsername());
        entity.setOrigenCorp("C");
        entity.setTipo(TiposArticulo.KIT.getTipo());
        entity.setTipoCosto("A");
        entity.setClasificacion1(parametro.getClasificacionPr());
        //entity.setCantidadProduccion(BigDecimal.ONE);
        entity.setImpuesto(getImpuestos().stream().filter(c -> c.getImpuesto().equals("IVA")).findFirst().get());
        entity.setCostoFiscal(TiposCostos.PROMEDIO.getTipo());
        entity.setCostoComparativo(TiposCostos.ESTANDAR.getTipo());
        entity.setUnidadAlmacen(new UnidadDeMedida("UND"));
        entity.setUnidadVenta(new UnidadDeMedida("UND"));
        entity.setUnidadEmpaque(new UnidadDeMedida("UND"));
        if (parametro != null && parametro.getBodegaPt() != null) {
            ExistenciaBodega eb = new ExistenciaBodega();
            eb.setBodega(parametro.getBodegaPt());
            eb.setArticulo(entity);
            entity.getExistenciaBodegaList().add(eb);
        }
        reset();
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        entity = service.findByEntity(selection);
        entity.setUsuarioUltModif(getUsername());

        reset();
        setEditing(true);
        setFormView(true);
    }

    @Override
    protected void addDefaultColumns() {
        selectedColumns.add("articulo");
        selectedColumns.add("descripcion");
        selectedColumns.add("unidadAlmacen");
        selectedColumns.add("unidadDetalle");
        selectedColumns.add("unidadVenta");
        selectedColumns.add("clasif1");
        selectedColumns.add("clasif2");
        selectedColumns.add("clasif3");
        selectedColumns.add("clasif4");
        selectedColumns.add("clasif5");
        selectedColumns.add("clasif6");
    }

    @Override
    public void cargarLista() {
        setList(service.getArticulosProduccion());
    }

    @Override
    public void reset() {
        etapa = new Etapa(new EtapaPK());
        materia = new MateriaPrima(new MateriaPrimaPK());
        super.reset();
    }

    public String getTipoClasificacion(short agrupacion) throws BusinessValidationException {
        return clasificacionService.getTipoClasificacion(agrupacion, globalesController.getGlobalesCi());
    }

    public List<Clasificacion> getClasificacionesPorAgrupacion(short agrupacion) {
        return clasificacionService.getClasificacionesPorAgrupacion(agrupacion);
    }
    
    public List<Clasificacion> getClasifs2() {
        return new ArrayList<>();
    }

    public void onConsecutivoSelected() {
        entity.setArticulo(Utils.generarConsecutivo(entity.getConsecutivo().getUltimoValor()));
    }

    public void agregarBodega() {
        if (entity.getExistenciaBodegaList().stream().anyMatch(eb -> eb.getBodega().equals(bodega))) {
            MessageUtils.showMessage("El artículo ya fue asociado a esta bodega");
        } else if (bodega == null) {
            MessageUtils.showMessage("Debe seleccionar una bodega");
        } else {
            ExistenciaBodega existencia = new ExistenciaBodega();
            existencia.setBodega(bodega);
            existencia.setArticulo(entity);
            entity.getExistenciaBodegaList().add(existencia);
        }
    }

    public void quitarBodega(ExistenciaBodega eb) {
        if (eb.getCantDisponible().compareTo(BigDecimal.ZERO) > 0) {
            MessageUtils.showMessage("No se puede quitar bodegas con movimientos");
            return;
        }
        entity.getExistenciaBodegaList().remove(eb);
    }

    public void agregarEtapa() {
        List<Etapa> etapaList = entity.getEtapaList();
        
        try {

            if (etapa.getOrden() == 0 || etapa.getDescripcion() == null) {
                throw new BusinessValidationException("Debe especificar el número y la descripción de la etapa");
            }

            Etapa e = new Etapa(new EtapaPK(getEtapaId(etapaList), entity.getArticulo()));
            e.setArticulo(entity);
            e.setDescripcion(etapa.getDescripcion());
            e.setOrden(etapa.getOrden());
            etapaList.add(e);
            Collections.sort(etapaList, (et1, et2) -> Integer.valueOf(et1.getOrden()).compareTo(et2.getOrden()));
            reordenarEtapas(e);
            setAccEtapasActiveIndex(etapaList.indexOf(e));
//            service.calcularCostoArticulo(entity);
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        }
    }

    public void quitarEtapa(Etapa etapa) {
        List<Etapa> etapaList = entity.getEtapaList();
        etapaList.remove(etapa);
        for (Etapa e : etapaList) {
            e.setOrden(etapaList.indexOf(e) + 1);
        }
        service.calcularCostoArticulo(entity);
    }

    public void agregarMateriaPrima(Etapa etapa) {
        try {
            if (materia == null) {
                throw new BusinessValidationException("Debe seleccionar una materia");
            }

            if (materia.getCantidad() == null || materia.getCantidad().compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessValidationException("La cantidad de materia prima es requerida");
            }

            if (etapa.getMateriaPrimaList().stream().anyMatch(mp -> mp.getArticuloHijo().equals(materia.getArticuloHijo()))) {
                throw new BusinessValidationException("La materia ya fue agregada");
            }
            MateriaPrima mp = new MateriaPrima(etapa.getEtapaPK().getEtapa(), entity.getArticulo(), materia.getArticuloHijo().getArticulo());
            mp.setArticuloHijo(materia.getArticuloHijo());
            mp.setCantidad(materia.getCantidad());
            etapa.getMateriaPrimaList().add(mp);
            service.calcularCostoArticulo(entity);
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        }
    }

    public void quitarMateriaPrima(Etapa etapa, MateriaPrima materiaPrima) {
        etapa.getMateriaPrimaList().remove(materiaPrima);
        service.calcularCostoArticulo(entity);
    }

    public void onTabChange(Etapa etapa) {
        setEtapaSeleccionada(etapa);
    }

    public MontoLocalDolar getCostoArticulo(Articulo articulo) {
        return service.getCostoArticulo(articulo, tipoCosto);
    }

    public List<ExistenciaBodega> getExistenciaBodegaList() {
        return existenciaBodegaService.getExistenciaBodegaList(materia.getArticuloHijo());
    }

    public void onNumEtapaChanged(Etapa etapa) {
        reordenarEtapas(etapa);
        setAccEtapasActiveIndex(entity.getEtapaList().indexOf(etapa));
    }

    public void reordenarEtapas(Etapa etapa) {
        List<Etapa> etapaList = entity.getEtapaList();
        if (etapa.getOrden() < 1) {
            etapa.setOrden(1);
        } else if (etapa.getOrden() >= etapaList.size()) {
            etapa.setOrden(etapaList.size());
        }
        etapaList.remove(etapa);
        etapaList.add(etapa.getOrden() - 1, etapa);
        etapaList.removeIf(Objects::isNull);
        for (Etapa e : etapaList) {
            e.setOrden(etapaList.indexOf(e) + 1);
        }
    }

    private int getEtapaId(List<Etapa> etapaList) {
        OptionalInt opt = etapaList.stream().mapToInt(e -> e.getEtapaPK().getEtapa()).max();
        if (opt.isPresent()) {
            return opt.getAsInt() + 1;
        }
        return 1;
    }
    
    public boolean tieneOrdenes() {
        return service.tieneOrdenes(entity);
    }
    
    public boolean isModificarEtapas() {
        return true;
    }
    
    public void crearEtapa() {
        etapa.setOrden(1);
        etapa.setDescripcion("Etapa #1");
        etapa.setEtapaPK(new EtapaPK(1, entity.getArticulo()));
        
    }
    
    public void cargarReceta() {
        if(uploadedFile != null) {
            try {
                service.cargarReceta(uploadedFile.getInputstream());
            } catch (BusinessValidationException ex) {
                MessageUtils.showMessage(ex.getMessage());
                MessageUtils.showGrowlError(ex.getMessage());
            } catch (IOException ex) {                
                MessageUtils.showGrowlError("Error al subir el archivo");
                Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cargarRendimientos() {
        if(uploadedFileRendimiento != null) {
            try {
                Set<String> articulosNoExistentes = new HashSet<>();
                service.cargarRendimientos(uploadedFileRendimiento.getInputstream(), articulosNoExistentes);
                if(CollectionUtils.isNotEmpty(articulosNoExistentes)) {
                    MessageUtils.showGrowlSuccess(articulosNoExistentes.stream().collect(Collectors.joining(System.lineSeparator())));
                } else {                    
                    MessageUtils.showGrowlSuccess();
                }
            } catch (BusinessValidationException ex) {
                MessageUtils.showMessage(ex.getMessage());
                MessageUtils.showGrowlError(ex.getMessage());
            } catch (IOException ex) {                
                MessageUtils.showGrowlError("Error al subir el archivo");
                Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
