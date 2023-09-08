package com.dosrobles.produccion.converters;


import com.dosrobles.produccion.entities.OrdenCompra;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.service.OrdenCompraService;
import com.dosrobles.produccion.service.OrdenProduccionService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class OrdenCompraConverter implements Converter<OrdenCompra> {
    
    @Inject
    private OrdenCompraService service;
    
    @Override
    public OrdenCompra getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) return null;
        try {
            return service.find(Long.valueOf(s));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, OrdenCompra value) {
        if (value != null) {
            return (value).getOrdenCompra();
        }
        return null;
    }
}
