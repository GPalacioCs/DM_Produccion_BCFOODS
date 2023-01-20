/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.beans;

import com.dosrobles.produccion.service.BodegaService;
import com.dosrobles.produccion.service.ClasificacionService;
import com.dosrobles.produccion.service.ParametrosPrService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pc
 */
@Named
@ApplicationScoped
public class ConfigBean {
    @Inject BodegaService bodegaService;
    @Inject ClasificacionService clasificacionService;
    @Inject ParametrosPrService parametrosPrService;
    
    
    
}
