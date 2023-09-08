package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.Lote;
import com.dosrobles.produccion.entities.LotePK;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.embarque.EmbarqueLineaPK;
import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaEnvio;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaRecepcion;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaRecepcionPK;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.BodegaService;
import com.dosrobles.produccion.service.LoteService;
import com.dosrobles.produccion.service.TraspasoService;
import com.dosrobles.produccion.service.embarque.EmbarqueService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class TraspasoController extends AbstractController<TraspasoService, Traspaso> {

    @Getter
    @Setter
    private Traspaso traspaso = new Traspaso();

    @Getter
    @Setter
    private Embarque embarque = new Embarque();

    @Getter
    private List<Embarque> addedEmbarques = new ArrayList<>();
    @Getter
    @Setter
    private Articulo selectedArticuloRecepForm;
    @Getter
    @Setter
    private double cantidadArticuloRecepForm;
    @Getter
    @Setter
    private double precioArticuloRecepForm;
    @Getter
    private List<Articulo> articulosMP = new ArrayList<>();
    @Inject
    private ArticuloService articuloService;
    @Inject
    private BodegaService bodegaService;

    @Inject
    private LoteService loteService;
    @Inject
    private EmbarqueService embarqueService;


    @Override
    public void create() {
        this.traspaso = new Traspaso();
        create(traspaso);
    }

    @Override
    public void edit() {
        Traspaso tras = service.findByEntity(selection);
        service.detach(tras);
        edit(tras);
    }

    @Override
    public void save() {
        if (!isEditing()) {
            entity.setIdTraspaso(UUID.randomUUID().toString());
        }
        for (TraspasoLineaEnvio tle :
                entity.getLineasEnvio()) {
            tle.setTraspaso(entity);
        }
        super.save();
    }

    @PostConstruct
    private void init() {
        cargarLista();
        articulosMP = articuloService.getArticulosMateriaPrima();
    }

    @Override
    public void cargarLista() {
        list = service.findAll();
    }

    public List<Bodega> getBodegas() {
        return bodegaService.findAll();
    }

    public List<Embarque> getEmbarques() {
        return embarqueService.getAllPlaneados();
    }

    public void agregarEmbarque() {
        addedEmbarques.add(embarque);
        for (EmbarqueLinea embarqueLinea :
                embarque.getEmbarqueLineas()) {
            for (EmbarqueDesglose desglose : embarqueLinea.getEmbarqueLineaDesgloses()) {
                TraspasoLineaEnvio newLinea = new TraspasoLineaEnvio();
                newLinea.setArticulo(embarqueLinea.getArticulo());
                newLinea.setCantidad(embarqueLinea.getCantidad_recibida());
                Lote lote = loteService.find(new LotePK(desglose.getLote(), embarqueLinea.getArticulo().getArticulo()));
                newLinea.setLote(lote.getLotePK().getLote());
                newLinea.setPrecio_Unitario(embarqueLinea.getPrecio_unitario());
                entity.getLineasEnvio().add(newLinea);
            }
        }
        embarque = new Embarque();
    }

    private String createLote(TraspasoLineaRecepcion lr) {
        return Lote.CreateNewLoteForTraspaso(lr.getTraspaso().getIdTraspaso(),
                lr.getArticulo(),
                lr.getCantidad()
        );
    }

    public void enviarTraspaso() {
        Traspaso tras = selection;
        tras.setEstado("E");
        for (TraspasoLineaEnvio le :
                tras.getLineasEnvio()) {
            TraspasoLineaRecepcion lr = new TraspasoLineaRecepcion();
            lr.setArticulo(le.getArticulo());
            lr.setCantidad(le.getCantidad());
            lr.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(selection.getIdTraspaso(), le.getId().getLinea()));
            lr.setLote(createLote(lr));
        }
        service.save(tras);
        selection = null;
        cargarLista();
    }

    public void onArticuloMpRecepcionSelected() {
        int maxLinea = entity.getLineasRecepcion().stream().mapToInt(l -> l.getTraspasoLineaRecepcionPK().getLinea()).max().orElse(0);
        TraspasoLineaRecepcion lRecep = new TraspasoLineaRecepcion();
        lRecep.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(null, maxLinea + 1));
        entity.getLineasRecepcion().add(lRecep);
    }

    public boolean areLineasRecepRendered() {
        return (entity.getEstado().equals("E") || entity.getEstado().equals("R"));
    }

    public void quitarLineaRecepcion(TraspasoLineaRecepcion linea) {
        entity.getLineasRecepcion().remove(linea);
    }

    public void agregarLineaRecepcion() {
        int maxLinea = entity.getLineasRecepcion().stream().mapToInt(l -> l.getTraspasoLineaRecepcionPK().getLinea()).max().orElse(0);
        TraspasoLineaRecepcion lineaRecepcion = new TraspasoLineaRecepcion();
        lineaRecepcion.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(entity.getIdTraspaso(), maxLinea + 1));
        lineaRecepcion.setTraspaso(entity);
        lineaRecepcion.setCantidad(cantidadArticuloRecepForm);
        lineaRecepcion.setPrecio_Unitario(precioArticuloRecepForm);
        lineaRecepcion.setArticulo(selectedArticuloRecepForm);
        lineaRecepcion.setLote(createLote(lineaRecepcion));
        entity.getLineasRecepcion().add(lineaRecepcion);
        selectedArticuloRecepForm = new Articulo();
        cantidadArticuloRecepForm = 0;
        precioArticuloRecepForm = 0;
    }

    @Override
    public void cancel() {
        selectedArticuloRecepForm = new Articulo();
        cantidadArticuloRecepForm = 0;
        precioArticuloRecepForm = 0;
        embarque = new Embarque();
        super.cancel();
    }
}
