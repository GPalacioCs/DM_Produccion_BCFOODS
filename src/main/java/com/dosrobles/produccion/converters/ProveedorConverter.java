package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.Proveedor;
import com.dosrobles.produccion.service.ProveedorService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ProveedorConverter implements Converter {
    
    @Inject
    private ProveedorService service;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if(value == null) return null;
        return service.find(value);
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value != null && value instanceof Proveedor) {
            return ((Proveedor) value).getProveedor();
        }
        return null;
    }
}
