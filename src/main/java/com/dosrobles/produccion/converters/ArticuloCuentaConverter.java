package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.ArticuloCuenta;
import com.dosrobles.produccion.service.ArticuloCuentaService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ArticuloCuentaConverter implements Converter {
    
    @Inject
    private ArticuloCuentaService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null) return null;
        return service.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof ArticuloCuenta) {
            return ((ArticuloCuenta) value).getArticuloCuenta();
        }
        return null;
    }
    
}
