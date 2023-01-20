/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Zeus
 */
@Embeddable
public class PaqueteOrdenProduccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ORDEN")
    private String orden;
    @Basic(optional = false)
    @Column(name = "ARTICULO")
    private String articulo;

    public PaqueteOrdenProduccionPK() {
    }

    public PaqueteOrdenProduccionPK(String orden, String articulo) {
        this.orden = orden;
        this.articulo = articulo;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orden != null ? orden.hashCode() : 0);
        hash += (articulo != null ? articulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaqueteOrdenProduccionPK)) {
            return false;
        }
        PaqueteOrdenProduccionPK other = (PaqueteOrdenProduccionPK) object;
        if ((this.orden == null && other.orden != null) || (this.orden != null && !this.orden.equals(other.orden))) {
            return false;
        }
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaqueteOrdenProduccionPK[ orden=" + orden + ", articulo=" + articulo + " ]";
    }
    
}
