package com.dosrobles.produccion.jpaconverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SiNoStringConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute.equals("SI"))
            return "S";

        return "N";
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData.equals("S"))
            return "SI";
        return "NO";
    }
}
