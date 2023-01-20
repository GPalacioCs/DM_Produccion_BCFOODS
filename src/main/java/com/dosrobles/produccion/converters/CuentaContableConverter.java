/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.converters;

import com.dosrobles.produccion.entities.CuentaContable;
import com.dosrobles.produccion.service.CuentaContableService;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ApplicationScoped
public class CuentaContableConverter implements Converter{

    @Inject
    CuentaContableService service;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        String id = StringUtils.trimToNull(value);
        return id == null ? null : service.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof CuentaContable) {
            CuentaContable cuenta = (CuentaContable) value;
            if(cuenta.getCuenta() != null) {
                return cuenta.getCuenta();
            }
        }
        return null;
    }
    
}
