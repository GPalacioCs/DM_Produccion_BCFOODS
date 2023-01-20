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
@Entity
@Table(name = "TRANS_INV_AUX")
@NamedQueries({
    @NamedQuery(name = "TransInvAux.findAll", query = "SELECT t FROM TransInvAux t")})
public class TransInvAux implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransInvAuxPK transInvAuxPK;
    @Size(max = 10)
    @Column(name = "ORDEN")
    private String orden;
    @Size(max = 10)
    @Column(name = "OPERACION")
    private String operacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoteExistsFlag")
    private short noteExistsFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RecordDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "RowPointer")
    private String rowPointer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UpdatedBy")
    private String updatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumns({
        @JoinColumn(name = "AUDIT_TRANS_INV", referencedColumnName = "AUDIT_TRANS_INV", insertable = false, updatable = false),
        @JoinColumn(name = "CONSECUTIVO", referencedColumnName = "CONSECUTIVO", insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private TransaccionInv transaccionInv;

    public TransInvAux() {
    }

    public TransInvAux(TransInvAuxPK transInvAuxPK) {
        this.transInvAuxPK = transInvAuxPK;
    }

    public TransInvAux(TransInvAuxPK transInvAuxPK, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.transInvAuxPK = transInvAuxPK;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public TransInvAux(int auditTransInv, int consecutivo) {
        this.transInvAuxPK = new TransInvAuxPK(auditTransInv, consecutivo);
    }

    public TransInvAuxPK getTransInvAuxPK() {
        return transInvAuxPK;
    }

    public void setTransInvAuxPK(TransInvAuxPK transInvAuxPK) {
        this.transInvAuxPK = transInvAuxPK;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public short getNoteExistsFlag() {
        return noteExistsFlag;
    }

    public void setNoteExistsFlag(short noteExistsFlag) {
        this.noteExistsFlag = noteExistsFlag;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRowPointer() {
        return rowPointer;
    }

    public void setRowPointer(String rowPointer) {
        this.rowPointer = rowPointer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public TransaccionInv getTransaccionInv() {
        return transaccionInv;
    }

    public void setTransaccionInv(TransaccionInv transaccionInv) {
        this.transaccionInv = transaccionInv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transInvAuxPK != null ? transInvAuxPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransInvAux)) {
            return false;
        }
        TransInvAux other = (TransInvAux) object;
        if ((this.transInvAuxPK == null && other.transInvAuxPK != null) || (this.transInvAuxPK != null && !this.transInvAuxPK.equals(other.transInvAuxPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.TransInvAux[ transInvAuxPK=" + transInvAuxPK + " ]";
    }

}
