/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class ArticuloEnsamblePK {
    @Column(name="ARTICULO_PADRE")
    private String articuloPadre;
    @Column(name="ARTICULO_HIJO")
    private String articuloHijo;    

    public ArticuloEnsamblePK() {
    }

    public ArticuloEnsamblePK(String articuloPadre, String articuloHijo) {
        this.articuloPadre = articuloPadre;
        this.articuloHijo = articuloHijo;
    }

    public String getArticuloPadre() {
        return articuloPadre;
    }

    public void setArticuloPadre(String articuloPadre) {
        this.articuloPadre = articuloPadre;
    }

    public String getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(String articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.articuloPadre);
        hash = 83 * hash + Objects.hashCode(this.articuloHijo);
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
        final ArticuloEnsamblePK other = (ArticuloEnsamblePK) obj;
        if (!Objects.equals(this.articuloPadre, other.articuloPadre)) {
            return false;
        }
        if (!Objects.equals(this.articuloHijo, other.articuloHijo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArticuloEnsamblePK{" + "articuloPadre=" + articuloPadre + ", articuloHijo=" + articuloHijo + '}';
    }
}
