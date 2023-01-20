/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.ActividadProd;
import com.dosrobles.produccion.service.ActividadProdService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.math.BigDecimal;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
@Getter @Setter
public class ActividadProdController extends AbstractController<ActividadProdService, ActividadProd> {
    
    @PostConstruct
    private void init(){
        cargarLista();
    }

    @Override
    public void create() {
        entity = new ActividadProd();
        entity.setCostoDolar(BigDecimal.ZERO);
        reset();
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        entity = service.find(selection.getCodigo());        
        reset();
        setEditing(true);
        setFormView(true);
    }

    @Override
    public void cargarLista() {
        this.list = service.findAll();
    }

    
    
}
