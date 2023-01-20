/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.utils;

import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Corpsoft S.A.
 */
@FacesConverter(forClass = String.class)
public class StringTrimmer implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return StringUtils.trimToNull(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null) return null;
        return String.valueOf(value);
    }
    
}
