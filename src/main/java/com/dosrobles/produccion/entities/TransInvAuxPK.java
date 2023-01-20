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
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class TransInvAuxPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "AUDIT_TRANS_INV")
    private int auditTransInv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSECUTIVO")
    private int consecutivo;

    public TransInvAuxPK() {
    }

    public TransInvAuxPK(int auditTransInv, int consecutivo) {
        this.auditTransInv = auditTransInv;
        this.consecutivo = consecutivo;
    }

    public int getAuditTransInv() {
        return auditTransInv;
    }

    public void setAuditTransInv(int auditTransInv) {
        this.auditTransInv = auditTransInv;
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
        hash += (int) auditTransInv;
        hash += (int) consecutivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransInvAuxPK)) {
            return false;
        }
        TransInvAuxPK other = (TransInvAuxPK) object;
        if (this.auditTransInv != other.auditTransInv) {
            return false;
        }
        if (this.consecutivo != other.consecutivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.TransInvAuxPK[ auditTransInv=" + auditTransInv + ", consecutivo=" + consecutivo + " ]";
    }

}
