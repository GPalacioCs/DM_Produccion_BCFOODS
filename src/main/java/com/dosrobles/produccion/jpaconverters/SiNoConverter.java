/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.jpaconverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Administrador
 */
@Converter
public class SiNoConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "S".equals(dbData);
    }
    
}
