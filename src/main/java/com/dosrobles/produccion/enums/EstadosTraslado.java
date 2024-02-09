package com.dosrobles.produccion.enums;

import lombok.Getter;

@Getter
public enum EstadosTraslado {
    PLANEADO("P", "Planeado"),
    ENVIADO("E", "Enviado"),
    RECIBIDO("R", "Recibido");

    private final String estado;
    private final String descripcion;

    private EstadosTraslado(String estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public static EstadosTraslado getEstadoTraslado(String estado) {
        for (EstadosTraslado est : EstadosTraslado.values()) {
            if (est.getEstado().equals(estado)) {
                return est;
            }
        }
        return null;
    }

}
