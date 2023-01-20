package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.ExistenciaBodega;
import com.dosrobles.produccion.entities.ExistenciaBodegaPK;
import com.dosrobles.produccion.service.ExistenciaBodegaService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ApplicationScoped
public class ExistenciaBodegaConverter implements Converter {
    
    @Inject
    private ExistenciaBodegaService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.split(",").length!=2) return null;
        ExistenciaBodega eb = service.find(new ExistenciaBodegaPK(value.split(",")[0], value.split(",")[1]));
        return eb;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof ExistenciaBodega) {
            ExistenciaBodega eb = (ExistenciaBodega) value;
            return eb.getExistenciaBodegaPK().getArticulo()+","+eb.getExistenciaBodegaPK().getBodega();
        }
        return null;
    }
}
