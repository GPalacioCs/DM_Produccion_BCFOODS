/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.NominaBitacora;
import com.dosrobles.produccion.service.NominaBitacoraService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author pc
 */
@Named
@ViewScoped
public class NominaBitacoraController extends AbstractController<NominaBitacoraService, NominaBitacora> {
    
    @PostConstruct
    private void init() {
        cargarLista();
    }

    @Override
    public void create() {
    }

    @Override
    public void edit() {
    }
    
}
