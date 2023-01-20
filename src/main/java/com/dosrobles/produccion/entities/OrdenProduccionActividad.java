/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="CS_ORDEN_PRODUCCION_ACTIVIDAD")
@Data
public class OrdenProduccionActividad implements Serializable {
    @EmbeddedId
    private OrdenProduccionActividadPK id = new OrdenProduccionActividadPK();
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @MapsId("actividad")
    @ManyToOne
    @JoinColumn(name="ACTIVIDAD")
    private ActividadProd actividad;
    private BigDecimal libras = BigDecimal.ZERO;
}
