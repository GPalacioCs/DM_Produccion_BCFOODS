package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.service.ClienteService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ClienteConverter implements Converter {
    
    @Inject
    private ClienteService service;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        return service.find(s);
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Cliente) {
            return ((Cliente) o).getCliente();
        }
        return null;
    }
}
