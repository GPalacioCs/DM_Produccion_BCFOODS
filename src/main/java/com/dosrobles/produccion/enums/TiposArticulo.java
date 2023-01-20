/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.enums;

/**
 *
 * @author abdallah
 */
public enum TiposArticulo {
    TERMINADO("T", "Terminado"),
    MATERIA_PRIMA("M", "Materia Prima"),
    SERVICIO("V", "Servicio"),
    SUMINISTRO("U", "Suministro"),
    KIT("K", "Kit");
    
    private String tipo;
    private String descripcion;

    private TiposArticulo(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }    

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static TiposArticulo getTipoArticulo(String tipo) {
        for (TiposArticulo ta : TiposArticulo.values()) {
            if(ta.getTipo().equals(tipo)) {
                return ta;
            }            
        }
        return null;
    }    
}
