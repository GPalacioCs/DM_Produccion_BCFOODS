/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.jpaconverters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Administrador
 */
@Converter
public class EstadoEmbarqueConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        String resultado = "";
        switch (attribute) {
            case "Planeado":
                resultado = "P";
                break;
            case "Recibido":
                resultado = "R";
                break;
            case "Cancelado":
                resultado = "C";
                break;
            case "Transito":
                resultado = "T";
                break;
            default:
                resultado = attribute;
                break;
        }
        return resultado;

    }

    @Override
    public String convertToEntityAttribute(String attribute) {
        String resultado = "";
        switch (attribute) {
            case "P":
                resultado = "Planeado";
                break;
            case "R":
                resultado = "Recibido";
                break;
            case "C":
                resultado = "Cancelado";
                break;
            case "T":
                resultado = "Transito";
                break;
            default:
                resultado = attribute;
                break;
        }
        return resultado;
    }

}
