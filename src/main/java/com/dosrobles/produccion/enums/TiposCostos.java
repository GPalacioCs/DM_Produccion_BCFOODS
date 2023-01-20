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
public enum TiposCostos {
    ESTANDAR("S","Estándar"),
    PROMEDIO("P","Promedio"),
    PEPS("E", "PEPS"),
    ULTIMO("L", "Último"),
    UEPS("U", "UEPS");
    
    private String tipo;
    private String descripcion;

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    

    private TiposCostos(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public TiposCostos getTiposCostos(String tipo) {
        for (TiposCostos tc : TiposCostos.values()) {
            if(tc.getTipo().equals(tipo)) {
                return tc;
            }
        }
        return null;
    }
    
}
