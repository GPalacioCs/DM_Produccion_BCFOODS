/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.ClasificacionAdiValor;
import com.dosrobles.produccion.entities.ClasificacionAdiValorPK;
import com.dosrobles.produccion.service.ClasificacionAdiValorService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ClasificacionAdiValorConverter implements Converter {
    
    @Inject
    private ClasificacionAdiValorService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(StringUtils.isBlank(value)) return null;
        String[] arr = value.split(",");
        if(arr.length != 2) return null;
        return service.find(new ClasificacionAdiValorPK(arr[0], arr[1]));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof ClasificacionAdiValor) {
            ClasificacionAdiValor valor = (ClasificacionAdiValor) value;
            return valor.getId().getClasificacion()+","+valor.getId().getValor();
        }
        return null;
    }
    
}
