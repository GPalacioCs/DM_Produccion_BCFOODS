package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.Lote;
import com.dosrobles.produccion.entities.LotePK;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.traslado.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.BodegaService;
import com.dosrobles.produccion.service.LoteService;
import com.dosrobles.produccion.service.TraspasoService;
import com.dosrobles.produccion.service.embarque.EmbarqueService;
import com.dosrobles.produccion.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class TraspasoController extends AbstractController<TraspasoService, Traspaso> {

    @Getter
    @Setter
    private Traspaso traspaso = new Traspaso();

    @Getter
    @Setter
    private List<Embarque> selectedEmbarques = new ArrayList<>();

    public String selectedEmbarquesText() {
        return selectedEmbarques.stream().map(Embarque::getEmbarque).collect(Collectors.joining(","));
    }

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
        selectedEmbarques = new ArrayList<>();
        this.traspaso = new Traspaso();
        create(traspaso);
    }

    @Override
    public void edit() {
        selectedEmbarques = new ArrayList<>();
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
        selectedEmbarques = new ArrayList<>();
    }

    public List<Bodega> getBodegas() {
        return bodegaService.findAll();
    }

    public List<Embarque> getEmbarques() {
        return embarqueService.getAllRecibidosSinEnviar();
    }

    public void agregarEmbarque() {
        addedEmbarques.addAll(selectedEmbarques);

        for (Embarque embarque : selectedEmbarques)
            for (EmbarqueLinea embarqueLinea :
                    embarque.getEmbarqueLineas()) {
                for (EmbarqueDesglose desglose : embarqueLinea.getEmbarqueLineaDesgloses()) {
                    Optional<TraspasoLineaEnvio> linea = entity.getLineasEnvio().stream().max(Comparator.comparing(le -> le.getId().getLinea()));
                    int maxLinea = linea.map(le -> le.getId().getLinea()).orElse(0);
                    maxLinea++;
                    TraspasoLineaEnvio newLinea = new TraspasoLineaEnvio();
                    newLinea.setId(new TraspasoLineaEnvioPK(traspaso.getIdTraspaso(), maxLinea));
                    newLinea.setArticulo(embarqueLinea.getArticulo());
                    newLinea.setCantidad(embarqueLinea.getCantidad_recibida());
                    Lote lote = loteService.find(new LotePK(desglose.getLote(), embarqueLinea.getArticulo().getArticulo()));
                    newLinea.setLote(lote.getLotePK().getLote());
                    newLinea.setPrecio_Unitario(embarqueLinea.getPrecio_unitario());
                    newLinea.setEmbarqueLinea(embarqueLinea);
                    entity.getLineasEnvio().add(newLinea);
                }
            }
        selectedEmbarques = new ArrayList<>();
    }

    private String createLote(TraspasoLineaRecepcion lr) {
        return Lote.CreateLoteStringForTraspaso(lr.getTraspaso().getN_Traspaso(),
                lr.getTraspasoLineaRecepcionPK().getLinea()
        );
    }

    public void enviarTraspaso() {
        try {
            Traspaso tras = selection;
            service.enviarTraspaso(tras, getUsername());
            selection = null;
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError("Error enviando traslado: " + ex.getLocalizedMessage());
        }
        cargarLista();
    }

    public void sugerirLineas() {
        Traspaso tras = entity;
        double totalLibras = tras.getLineasEnvio().stream().mapToDouble(TraspasoLineaEnvio::getCantidad).sum();
        double totalPrecio = tras.getLineasEnvio().stream().mapToDouble(l -> l.getPrecio_Unitario() * l.getCantidad()).sum();
        double precioUnitario = totalPrecio / totalLibras;
        int maxLinea = entity.getLineasRecepcion().stream().mapToInt(l -> l.getTraspasoLineaRecepcionPK().getLinea()).max().orElse(0);

        for (TraspasoLineaEnvio le :
                tras.getLineasEnvio()) {
            //Check if linea exists
            if (tras.getLineasRecepcion().stream().anyMatch(lr -> lr.getTraspasoLineaRecepcionPK().equals(new TraspasoLineaRecepcionPK(tras.getIdTraspaso(), le.getId().getLinea())))) {
                continue;
            }
            TraspasoLineaRecepcion lr = new TraspasoLineaRecepcion();
            lr.setArticulo(le.getArticulo());
            lr.setCantidad(le.getCantidad());
            lr.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(tras.getIdTraspaso(), le.getId().getLinea()));
            lr.setTraspaso(tras);
            lr.setLote(Lote.CreateLoteStringForTraspaso(lr.getTraspaso().getN_Traspaso(),
                    lr.getTraspasoLineaRecepcionPK().getLinea()
            ));
            lr.setPrecio_Unitario(precioUnitario);
            tras.getLineasRecepcion().add(lr);
        }
        entity = tras;
    }

    public void recibirTraspaso() {
        Traspaso tras = selection;
        service.recibirTraspaso(tras, getUsername());
        selection = null;
        cargarLista();
    }

    public void onArticuloMpRecepcionSelected() {
        int maxLinea = entity.getLineasRecepcion().stream().mapToInt(l -> l.getTraspasoLineaRecepcionPK().getLinea()).max().orElse(0);
        TraspasoLineaRecepcion lRecep = new TraspasoLineaRecepcion();
        lRecep.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(null, maxLinea + 1));
        entity.getLineasRecepcion().add(lRecep);
    }

    public void onBodegaOrigenChanged() {
        entity.setN_Traspaso(service.getNextTraspaso(entity.getBodegaOrigen().getBodega()));
    }

    public boolean areLineasRecepRendered() {
        return (entity.getEstado().equals("E") || entity.getEstado().equals("R"));
    }

    public void quitarLineaRecepcion(TraspasoLineaRecepcion linea) {
        entity.getLineasRecepcion().remove(linea);
    }

    public void agregarLineaRecepcion() {
        Traspaso tras = entity;
        double totalLibras = tras.getLineasEnvio().stream().mapToDouble(TraspasoLineaEnvio::getCantidad).sum();
        double totalPrecio = tras.getLineasEnvio().stream().mapToDouble(l -> l.getPrecio_Unitario() * l.getCantidad()).sum();
        double precioUnitario = totalPrecio / totalLibras;

        int maxLinea = entity.getLineasRecepcion().stream().mapToInt(l -> l.getTraspasoLineaRecepcionPK().getLinea()).max().orElse(0);
        TraspasoLineaRecepcion lineaRecepcion = new TraspasoLineaRecepcion();
        lineaRecepcion.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(entity.getIdTraspaso(), maxLinea + 1));
        lineaRecepcion.setTraspaso(entity);
        lineaRecepcion.setCantidad(cantidadArticuloRecepForm);
        lineaRecepcion.setPrecio_Unitario(precioArticuloRecepForm);
        lineaRecepcion.setArticulo(selectedArticuloRecepForm);
        lineaRecepcion.setLote(createLote(lineaRecepcion));
        lineaRecepcion.setPrecio_Unitario(precioUnitario);
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
        selectedEmbarques = new ArrayList<>();
        super.cancel();
    }
}
