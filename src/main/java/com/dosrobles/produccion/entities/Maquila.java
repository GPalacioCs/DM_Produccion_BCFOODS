/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CS_MAQUILA")
@Data
@EqualsAndHashCode(of="id")
public class Maquila {
    @EmbeddedId
    private MaquilaPK id = new MaquilaPK();
    @MapsId("articulo")
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    private BigDecimal cantidad;
    private BigDecimal precio;
    @Column(name="PRECIO_DOLAR")
    private BigDecimal precioDolar;
    
    public BigDecimal getMonto() {
        if(cantidad == null) return BigDecimal.ZERO;
        return cantidad.multiply(precio);
    }
    
    public BigDecimal getMontoDolar() {
        if(cantidad == null) return BigDecimal.ZERO;
        return cantidad.multiply(precioDolar);
    }
}
