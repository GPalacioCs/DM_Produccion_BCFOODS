package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.service.TraspasoService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TraspasoConverter implements Converter<Traspaso>{

    @Inject
    private TraspasoService service;

    @Override
    public Traspaso getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        return service.find(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Traspaso traspaso) {
        if (traspaso != null) {
            return traspaso.getIdTraspaso();
        }
        return null;
    }
}
