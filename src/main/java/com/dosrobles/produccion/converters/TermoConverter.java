package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.Termo;
import com.dosrobles.produccion.service.TermoService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TermoConverter implements Converter {
    
    @Inject
    private TermoService service;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        try {
            return service.find(Long.valueOf(s));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Termo) {
            return ((Termo) value).getId().toString();
        }
        return null;
    }
}
