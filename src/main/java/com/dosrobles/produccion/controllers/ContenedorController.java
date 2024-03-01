package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.IngresoProduccion;
import com.dosrobles.produccion.entities.contenedor.Contenedor;
import com.dosrobles.produccion.entities.contenedor.ContenedorDetalle;
import com.dosrobles.produccion.service.contenedor.ContenedorService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.viewModels.ContenidoSearchViewModel;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ContenedorController extends AbstractController<ContenedorService, Contenedor> {

    @Getter
    private List<ContenidoSearchViewModel> freeContenidosList = Collections.emptyList();
    @Setter
    @Getter
    private List<ContenidoSearchViewModel> selectedContenidosList = Collections.emptyList();
    @Getter
    private List<IngresoProduccion> detalleContenedorMinimo = new ArrayList<>();

    private static String getShortContenidoReference(ContenidoSearchViewModel c) {
        if (c.getTipo().equals("OP")) {
            return "OP" + c.getContenidoId();
        }
        return c.getContenidoId();
    }

    public String selectedContentText() {
        return selectedContenidosList.stream().map(ContenedorController::getShortContenidoReference).collect(Collectors.joining(","));
    }

    @PostConstruct
    private void init() {
        cargarLista();
    }

    @Override
    public void create() {
        try {
            freeContenidosList = service.getFreeContenidoList();
            entity = new Contenedor();
            entity.setContenedorId(service.getNextContenedor());
            create(entity);
        } catch (Exception e) {
            MessageUtils.showGrowlError(e.getLocalizedMessage());
        }
    }

    @Override
    public void edit() {
        try {
            freeContenidosList = service.getFreeContenidoList();
            detalleContenedorMinimo = service.getContenidoContenedor(selection);
        } catch (Exception e) {
            MessageUtils.showGrowlError(e.getLocalizedMessage());
        }
        entity = selection;
        edit(entity);
    }

    @Override
    public void save() {
        super.save();
        selectedContenidosList = Collections.emptyList();
    }

    @Override
    public void cancel() {
        super.cancel();
        selectedContenidosList = Collections.emptyList();
    }

    @Override
    public void cargarLista() {
        list = service.findAll();
    }

    public void agregarContenidoSeleccionadoAContenedor() {
        try {
            List<ContenedorDetalle> contDetalleList = entity.getContenedorDetalles();
            if (contDetalleList == null) {
                contDetalleList = new ArrayList<>();
            }

            for (ContenidoSearchViewModel c : selectedContenidosList) {
                //Validar si ya fue añadido
                if (entity.getContenedorDetalles().stream().anyMatch(cd -> cd.getContenido().equals(c.getContenidoId()) && cd.getTipo().equals(c.getTipo()))) {
                    MessageUtils.showGrowlError(String.format("El elemento %s ya está contenido en este contenedor", getShortContenidoReference(c)));
                    return;
                }
            }

            List<ContenedorDetalle> contentToAdd = selectedContenidosList.stream().map(m -> new ContenedorDetalle(entity, m.getContenidoId(), m.getTipo())).collect(Collectors.toList());
            contDetalleList.addAll(contentToAdd);
            entity.setContenedorDetalles(contDetalleList);
            selectedContenidosList = new ArrayList<>();
            freeContenidosList = service.getFreeContenidoList();
            freeContenidosList.removeAll(entity.getContenedorDetalles().stream().map(c -> new ContenidoSearchViewModel(c.getContenido(),c.getTipo())).collect(Collectors.toList()));
            detalleContenedorMinimo = service.getContenidoContenedor(entity);
        } catch (Exception e) {
            MessageUtils.showGrowlError(e.getLocalizedMessage());
        }
    }

    public void eliminarContenido(ContenedorDetalle contenido) {
        if (!Objects.equals(entity.getEstado(), "A"))
            return;

        entity.getContenedorDetalles().remove(contenido);
        detalleContenedorMinimo = service.getContenidoContenedor(entity);
    }
}
