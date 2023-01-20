/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name="ARTICULO_ENSAMBLE")
public class ArticuloEnsamble implements java.io.Serializable {
    @EmbeddedId
    private ArticuloEnsamblePK articuloEnsamblePK;
    @Column(name="CANTIDAD")
    private BigDecimal cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ARTICULO_PADRE", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    private Articulo articuloPadre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ARTICULO_HIJO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    private Articulo articuloHijo;

    public ArticuloEnsamble() {
    }    

    public ArticuloEnsamble(ArticuloEnsamblePK articuloEnsamblePK, BigDecimal cantidad) {
        this.articuloEnsamblePK = articuloEnsamblePK;
        this.cantidad = cantidad;
    }
    
    public ArticuloEnsamble(String articuloPadre, String articuloHijo, BigDecimal cantidad) {
        this.articuloEnsamblePK = new ArticuloEnsamblePK(articuloPadre, articuloHijo);
        this.cantidad = cantidad;
    }

    public ArticuloEnsamblePK getArticuloEnsamblePK() {
        return articuloEnsamblePK;
    }

    public void setArticuloEnsamblePK(ArticuloEnsamblePK articuloEnsamblePK) {
        this.articuloEnsamblePK = articuloEnsamblePK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticuloPadre() {
        return articuloPadre;
    }

    public void setArticuloPadre(Articulo articuloPadre) {
        this.articuloPadre = articuloPadre;
    }

    public Articulo getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(Articulo articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.articuloEnsamblePK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticuloEnsamble other = (ArticuloEnsamble) obj;
        if (!Objects.equals(this.articuloEnsamblePK, other.articuloEnsamblePK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArticuloEnsamble{" + "articuloEnsamblePK=" + articuloEnsamblePK + '}';
    }
}
