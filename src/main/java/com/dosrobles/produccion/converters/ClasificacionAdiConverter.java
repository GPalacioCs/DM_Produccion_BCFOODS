/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.ClasificacionAdi;
import com.dosrobles.produccion.service.ClasificacionAdiService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Zeus
 */
@Named
@ApplicationScoped
public class ClasificacionAdiConverter implements Converter {

    @Inject
    private ClasificacionAdiService service;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null) return null;
        return service.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value != null && value instanceof ClasificacionAdi) {
            ClasificacionAdi clasif = (ClasificacionAdi) value;
            return clasif.getId();
        }
        
        return null;
    }
    
}
