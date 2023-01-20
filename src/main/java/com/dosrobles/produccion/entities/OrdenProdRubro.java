/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
@Table(name="ORDEN_PROD_RUBRO")
@Data
@EqualsAndHashCode(of = "id")
public class OrdenProdRubro implements Serializable {
    @EmbeddedId
    private OrdenProdRubroPK id;
    private BigDecimal cantidad = BigDecimal.ONE;    
    private BigDecimal costo = BigDecimal.ZERO;
    @Column(name = "COSTO_DOLAR")
    private BigDecimal costoDolar = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION", insertable = false, updatable = false)
    private OrdenProduccion ordenProduccion;
    @ManyToOne
    @JoinColumn(name="RUBRO", insertable = false, updatable = false)
    private RubroLiq rubroLiq;
}
