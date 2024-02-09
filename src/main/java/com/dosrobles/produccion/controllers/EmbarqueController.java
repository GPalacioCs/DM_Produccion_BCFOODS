package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.*;
import com.dosrobles.produccion.service.embarque.EmbarqueService;
import com.dosrobles.produccion.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class EmbarqueController extends AbstractController<EmbarqueService, Embarque> {

    @Inject
    private LoteService loteService;
    @Inject
    private TipoCambioHistService tipoCambioHistService;
    @Inject
    private OrdenCompraService ordenCompraService;
    @Inject
    private ProveedorService proveedorService;

    @Inject
    private BodegaService bodegaService;

    private String GetNextConsec() {
        if (list.isEmpty())
            return "1";
        String result = service.getConsecutivoEmbarque();
        result = result.trim();
        return result;
    }

    @Getter
    @Setter
    private List<OrdenCompra> ordenesCompra;

    @Getter
    @Setter
    private List<OrdenCompraLinea> lineasOcPendientes;

    public List<Proveedor> getProveedores() {
        return proveedorService.findAll();
    }

    public List<Bodega> getBodegas() {
        return bodegaService.findAll();
    }

    @Getter
    @Setter
    private OrdenCompra selectedOrdenCompra;

    @Getter
    @Setter
    private Bodega selectedBodega;


    @Override
    public void create() {
        entity = new Embarque();
        entity.setEmbarque(GetNextConsec());
        create(entity);
    }

    @Override
    public void edit() {
        Embarque emb = service.findByEntity(selection);
        service.detach(emb);
        edit(emb);
    }

    @PostConstruct
    private void init() {
        entity = new Embarque();
        loadOrdenesCompra();
        cargarLista();
    }

    public void loadOrdenesCompra() {
        if (entity.getProveedor() == null) {
            ordenesCompra = ordenCompraService.findAllOnBackorderAndTransit();
            return;
        }
        ordenesCompra = ordenCompraService.findAllOnBackorderAndTransitByProveedor(entity.getProveedor().getProveedor());
    }

    public void getLineasPendientesOC() {
        if (selectedOrdenCompra != null) {
            lineasOcPendientes = ordenCompraService.findAllPendingByOc(selectedOrdenCompra.getOrdenCompra());
            return;
        }
        lineasOcPendientes = new ArrayList<>();
    }

    public void agregarOrden() {
        OrdenCompra oc = selectedOrdenCompra;
        for (OrdenCompraLinea ocl : oc.getLineas()) {
            addLineaEmbarque(ocl);
        }
    }

    public String getLoteLinea(EmbarqueLinea el) {
        if (el.getEmbarqueLineaDesgloses().size() > 0){
            return el.getEmbarqueLineaDesgloses().get(0).getLote();
        }

        else return Lote.CreateLoteStringForAcopio(el.getId().getEmbarque(),el.getId().getEmbarque_Linea());
    }

    public void addLineaEmbarque(OrdenCompraLinea ocl) {
        try {
            if (selectedBodega == null) {
                MessageUtils.showGrowlError("Debe seleccionar una bodega");
                return;
            }

            //Check if linea is already added
            Optional<EmbarqueLinea> ocla = entity.getEmbarqueLineas().stream().filter(el -> el.getOrden_Compra_Linea() == ocl.getOrdenCompraLineaId().getOrdenCompraLinea()
                            && Objects.equals(el.getOrden_Compra().getOrdenCompra(), ocl.getOrdenCompraLineaId().getOrdenCompra()))
                    .findAny();

            if (ocla.isPresent()) {
                MessageUtils.showGrowlError("La linea ya se encuentra en el embarque.");
                return;
            }

            Optional<EmbarqueLinea> oel = entity.getEmbarqueLineas().stream().max(Comparator.comparing(el -> el.getId().getEmbarque_Linea()));
            int maxLinea = oel.map(embarqueLinea -> embarqueLinea.getId().getEmbarque_Linea()).orElse(0);
            maxLinea++;
            String embarqueForLote = String.valueOf(Integer.parseInt(entity.getEmbarque().substring(2)));
            Double cantPendienteEmbarque = ocl.getCantidadOrdenada().subtract(ocl.getCantidadRecibida()).subtract(ocl.getCantidadEmbarcada()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

            EmbarqueLinea el = new EmbarqueLinea(ocl, entity, maxLinea, tipoCambioHistService.getTipoCambioActual(new Date()), selectedBodega);
            entity.getEmbarqueLineas().add(el);

        } catch (Exception e) {
            MessageUtils.showGrowlError(e.getMessage());
        }
    }

    @Override
    public void save() {
        String embarqueForLote = String.valueOf(Integer.parseInt(entity.getEmbarque().substring(2)));
        if (isEditing()) {
            for (EmbarqueLinea el : entity.getEmbarqueLineas().stream().filter(el -> el.getEmbarqueLineaDesgloses().isEmpty()).collect(Collectors.toList()))
                CreateLoteAndDesglose(embarqueForLote, el);
            super.save();
            cargarLista();
            return;
        }

        for (EmbarqueLinea el : entity.getEmbarqueLineas()) CreateLoteAndDesglose(embarqueForLote, el);
        super.save();
        cargarLista();
    }

    private void CreateLoteAndDesglose(String embarqueForLote, EmbarqueLinea el) {
        Lote newLote = Lote.CreateNewLoteForAcopio(embarqueForLote,
                entity.getProveedor().getProveedor(),
                el.getArticulo(),
                el.getOrden_Compra().getProveedor(),
                el.getCantidad_recibida(),
                el.getId().getEmbarque_Linea()
        );

        EmbarqueDesglose ed = new EmbarqueDesglose(el, newLote.getLotePK().getLote(), el.getBodega());
        List<EmbarqueDesglose> desgloses = new ArrayList<>();
        desgloses.add(ed);
        el.setEmbarqueLineaDesgloses(desgloses);
//        el.setEmbarque(entity);
    }

    @Override
    public void cargarLista() {
        list = service.getAllPlaneados();
    }

    public void aplicarEmbarque() {
        try {
            service.aplicarEmbarque(selection, getUsername());
            MessageUtils.showGrowlSuccess();
            cargarLista();
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showGrowlError();
            e.printStackTrace();
        }
    }
}
