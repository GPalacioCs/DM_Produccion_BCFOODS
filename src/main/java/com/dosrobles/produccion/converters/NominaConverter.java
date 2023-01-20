/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.Nomina;
import com.dosrobles.produccion.service.NominaService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pc
 */
@Named
@ApplicationScoped
public class NominaConverter implements Converter {
    
    @Inject
    private NominaService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null) return null;
        return service.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof Nomina) {
            return ((Nomina)value).getNomina();
        }
        return null;
    }
}
