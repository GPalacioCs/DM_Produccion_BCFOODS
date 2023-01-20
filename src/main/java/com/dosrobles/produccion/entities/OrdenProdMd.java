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
 * @author DEV-PC
 */
@Entity
@Table(name = "ORDEN_PROD_MD")
@Data
@EqualsAndHashCode(of = "id")
public class OrdenProdMd implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private OrdenProdMdPK id;
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name = "ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @MapsId("articulo")
    @ManyToOne
    @JoinColumn(name = "COMPONENTE", referencedColumnName = "ARTICULO")
    private Articulo componente;    
    @ManyToOne
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    private Articulo producto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad = BigDecimal.ONE;
    @Column(name = "COSTO")
    private BigDecimal costo = BigDecimal.ZERO;
    @Column(name = "COSTO_DOLAR")
    private BigDecimal costoDolar = BigDecimal.ZERO;
    private String lote = "ND";

    public OrdenProdMd() {
        this.id = new OrdenProdMdPK();
    }

    public OrdenProdMd(OrdenProdMdPK id) {
        this.id = id;
    }

    public BigDecimal getCosto() {
        return componente.getCostoPromLoc();
    }

    public BigDecimal getCostoDolar() {
        return componente.getCostoPromDol();
    }
}
