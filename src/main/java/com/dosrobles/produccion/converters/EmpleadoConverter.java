package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.Empleado;
import com.dosrobles.produccion.service.EmpleadoService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class EmpleadoConverter implements Converter {
    
    @Inject
    private EmpleadoService empleadoService;
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null) return null;
        return empleadoService.find(s);
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Empleado) {
            return ((Empleado) value).getEmpleado();
        }
        return null;
    }
}
