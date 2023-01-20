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
 * @author DEV-PC
 */
@Embeddable
public class TipoCambioHistPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TIPO_CAMBIO")
    private String tipoCambio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public TipoCambioHistPK() {
    }

    public TipoCambioHistPK(String tipoCambio, Date fecha) {
        this.tipoCambio = tipoCambio;
        this.fecha = fecha;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCambio != null ? tipoCambio.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambioHistPK)) {
            return false;
        }
        TipoCambioHistPK other = (TipoCambioHistPK) object;
        if ((this.tipoCambio == null && other.tipoCambio != null) || (this.tipoCambio != null && !this.tipoCambio.equals(other.tipoCambio))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.TipoCambioHistPK[ tipoCambio=" + tipoCambio + ", fecha=" + fecha + " ]";
    }
    
}
