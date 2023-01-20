/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author Zeus
 */
@Entity
public class MateriaPrimaConsumo implements java.io.Serializable {
    @Id
    @Column(name="ARTICULO_HIJO")
    private String articulo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ARTICULO_HIJO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    private Articulo articuloHijo;
    @Column(name="CANT_TOTAL")
    private BigDecimal cantTotal;

    public MateriaPrimaConsumo() {
    }

    public MateriaPrimaConsumo(String articulo) {
        this.articulo = articulo;
    }    

    public MateriaPrimaConsumo(String articulo, Articulo articuloHijo) {
        this.articulo = articulo;
        this.articuloHijo = articuloHijo;
    }

    public MateriaPrimaConsumo(String articulo, Articulo articuloHijo, BigDecimal cantTotal) {
        this.articulo = articulo;
        this.articuloHijo = articuloHijo;
        this.cantTotal = cantTotal;
    }

    public String getArticulo() {
        return articulo;
    }

    public Articulo getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(Articulo articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    public BigDecimal getCantTotal() {
        return cantTotal;
    }

    public void setCantTotal(BigDecimal cantTotal) {
        this.cantTotal = cantTotal;
    }
    
}
