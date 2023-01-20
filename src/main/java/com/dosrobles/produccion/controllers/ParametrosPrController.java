/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.Clasificacion;
import com.dosrobles.produccion.entities.Paquete;
import com.dosrobles.produccion.entities.ParametrosPr;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.*;
import com.dosrobles.produccion.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ViewScoped
public class ParametrosPrController implements Serializable {
    @Inject
    private ParametrosPrService service;
    @Inject
    private BodegaService bodegaService;
    @Inject
    private ClasificacionService clasificacionService;
    @Inject
    private PaqueteService paqueteService;
    @Inject
    private GlobalesCGService globalesCGService;
    @Inject
    private GlobalesASService globalesASService;
    
    private ParametrosPr parametro = new ParametrosPr();
    
    private List<Bodega> bodegaList = new ArrayList<>();
    
    private List<Clasificacion> clasificacionList = new ArrayList<>();
    @Getter @Setter
    private List<Paquete> paqueteList = new ArrayList<>();

    public List<Bodega> getBodegaList() {
        return bodegaList;
    }

    public ParametrosPr getParametro() {        
        return parametro;
    }

    public List<Clasificacion> getClasificacionList() {
        return clasificacionList;
    }    
    
    @PostConstruct
    private void init() {
        bodegaList = bodegaService.findAll();
        clasificacionList = clasificacionService.getClasificacionesPorAgrupacion((short)1);
        paqueteList = paqueteService.findAll();
        parametro = service.find(1);
        if(parametro == null) parametro = new ParametrosPr();        
    }
    
    public void save() {
        try {
            service.save(parametro);
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ParametrosPrController.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showGrowlError();
        }
    }
    
    public String getPatronCuenta() {
        return globalesCGService.getGlobalesCG().getPatron();
    }

    public String getPatronCosto() {
        return globalesASService.getGlobalesAS().getPatronCCosto();
    }
    
}
