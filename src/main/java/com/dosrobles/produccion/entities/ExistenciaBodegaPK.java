/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class ExistenciaBodegaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO")
    private String articulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "BODEGA")
    private String bodega;

    public ExistenciaBodegaPK() {
    }

    public ExistenciaBodegaPK(String articulo, String bodega) {
        this.articulo = articulo;
        this.bodega = bodega;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (bodega != null ? bodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaBodegaPK)) {
            return false;
        }
        ExistenciaBodegaPK other = (ExistenciaBodegaPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.bodega == null && other.bodega != null) || (this.bodega != null && !this.bodega.equals(other.bodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.ExistenciaBodegaPK[ articulo=" + articulo + ", bodega=" + bodega + " ]";
    }

}
