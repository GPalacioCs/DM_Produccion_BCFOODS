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
public enum EstadosProd {
    CANCELADA("C", "Cancelada"),
    PRECERRADA("D", "Pre-Cerrada"),
    CONFIRMADA("F", "Confirmada"), 
    LIBERADO("L", "Liberada"),
    PLANEADO("P", "Planeado"),
    TERMINADO("R","Cerrada");

    private String estado;
    private String descripcion;

    private EstadosProd(String estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static EstadosProd getEstadoProd(String estado) {
        for (EstadosProd est : EstadosProd.values()) {
            if(est.getEstado().equals(estado)) {
                return est;
            }
        }
        return null;
    }
}
