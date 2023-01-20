/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Zeus
 */
@Entity
public class ProductoPedido implements Serializable {
    
    @Id
    @Column(name="ARTICULO")
    private String id;
//    @ManyToOne
//    @JoinColumn(name="ARTICULO", insertable = false, updatable = false)
    @Transient
    private Articulo articulo;
    @Column(name="CANTIDAD")
    private BigDecimal cantidad;    

    public ProductoPedido() {
    }    

    public ProductoPedido(Articulo articulo, BigDecimal cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
