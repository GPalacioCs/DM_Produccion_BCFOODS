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
 * @author Corpsoft S.A.
 */
@Embeddable
public class DiarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ASIENTO")
    private String asiento;
    @Basic(optional = false)
    @Column(name = "CONSECUTIVO")
    private int consecutivo;

    public DiarioPK() {
    }

    public DiarioPK(String asiento, int consecutivo) {
        this.asiento = asiento;
        this.consecutivo = consecutivo;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiento != null ? asiento.hashCode() : 0);
        hash += (int) consecutivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiarioPK)) {
            return false;
        }
        DiarioPK other = (DiarioPK) object;
        if ((this.asiento == null && other.asiento != null) || (this.asiento != null && !this.asiento.equals(other.asiento))) {
            return false;
        }
        if (this.consecutivo != other.consecutivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DiarioPK[ asiento=" + asiento + ", consecutivo=" + consecutivo + " ]";
    }

}
