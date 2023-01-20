/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.service.ActividadProdService;
import com.dosrobles.produccion.service.ClasificacionAdiService;
import com.dosrobles.produccion.service.ClasificacionAdiValorService;
import com.dosrobles.produccion.service.MaquilaPrecioService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Zeus
 */
@Named
@ViewScoped
@Getter @Setter
public class MaquilaPrecioController extends AbstractController<MaquilaPrecioService, MaquilaPrecio> {
    
    @Inject
    private ClasificacionAdiService clasificacionAdiService;
    @Inject
    private ClasificacionAdiValorService clasificacionAdiValorService;
    @Inject
    private ActividadProdService actividadProdService;
    
    private List<ClasificacionAdi> clasificacionAdiList = new ArrayList<>();
    private List<ClasificacionAdiValor> clasificacionAdiValorList = new ArrayList<>();
    private List<ActividadProd> actividadProdList = new ArrayList<>();
    
    private TransformacionActividad transformacionActividad = new TransformacionActividad();

    @PostConstruct
    private void init() {
        cargarLista();
//        clasificacionAdiList.addAll(clasificacionAdiService.findAll());
        clasificacionAdiValorList.addAll(clasificacionAdiValorService.findAll());
        actividadProdList.addAll(actividadProdService.findAll());
    }
    
    @Override
    public void create() {
        create(new MaquilaPrecio());
        transformacionActividad = new TransformacionActividad();
    }

    @Override
    public void edit() {
        edit(service.find(selection.getId()));
        transformacionActividad = new TransformacionActividad();
        transformacionActividad.setClasificacionAdiValor(entity.getValor());
    }

    @Override
    public void cargarLista() {
        this.list = service.findAll();
    }    
    
    public void agregarActividad() {
        List<TransformacionActividad> transformacionActividadList = entity.getValor().getTransformacionActividadList();
        if(transformacionActividadList.stream().noneMatch(a -> a.getActividad().equals(transformacionActividad.getActividad()))) {
            entity.getValor().getTransformacionActividadList().add(transformacionActividad);
            transformacionActividad = new TransformacionActividad();
            transformacionActividad.setClasificacionAdiValor(entity.getValor());
        }
    }
    
    public void quitarActividad(TransformacionActividad actividad) {
        List<TransformacionActividad> transformacionActividadList = entity.getValor().getTransformacionActividadList();
        transformacionActividadList.removeIf(a -> Objects.equals(a.getActividad(), actividad.getActividad()));
    }
}
