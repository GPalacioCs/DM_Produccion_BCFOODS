/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class CostoUepsPepsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO")
    private String articulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date secuencia;

    public CostoUepsPepsPK() {
    }

    public CostoUepsPepsPK(String articulo, Date secuencia) {
        this.articulo = articulo;
        this.secuencia = secuencia;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Date getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Date secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (secuencia != null ? secuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoUepsPepsPK)) {
            return false;
        }
        CostoUepsPepsPK other = (CostoUepsPepsPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.secuencia == null && other.secuencia != null) || (this.secuencia != null && !this.secuencia.equals(other.secuencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.CostoUepsPepsPK[ articulo=" + articulo + ", secuencia=" + secuencia + " ]";
    }

}
