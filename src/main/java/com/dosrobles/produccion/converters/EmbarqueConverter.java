package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.service.TraspasoService;
import com.dosrobles.produccion.service.embarque.EmbarqueService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class EmbarqueConverter implements Converter<Embarque>{

    @Inject
    private EmbarqueService service;

    @Override
    public Embarque getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        return service.find(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Embarque embarque) {
        if (embarque != null) {
            return embarque.getEmbarque();
        }
        return null;
    }
}
