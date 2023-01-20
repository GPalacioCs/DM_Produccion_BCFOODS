/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "TIPO_CAMBIO_HIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCambioHist.findAll", query = "SELECT t FROM TipoCambioHist t")
    , @NamedQuery(name = "TipoCambioHist.findByTipoCambio", query = "SELECT t FROM TipoCambioHist t WHERE t.tipoCambioHistPK.tipoCambio = :tipoCambio")
    , @NamedQuery(name = "TipoCambioHist.findByFecha", query = "SELECT t FROM TipoCambioHist t WHERE t.tipoCambioHistPK.fecha = :fecha")
    , @NamedQuery(name = "TipoCambioHist.findByUsuario", query = "SELECT t FROM TipoCambioHist t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TipoCambioHist.findByMonto", query = "SELECT t FROM TipoCambioHist t WHERE t.monto = :monto")
    , @NamedQuery(name = "TipoCambioHist.findByNoteExistsFlag", query = "SELECT t FROM TipoCambioHist t WHERE t.noteExistsFlag = :noteExistsFlag")
    , @NamedQuery(name = "TipoCambioHist.findByRecordDate", query = "SELECT t FROM TipoCambioHist t WHERE t.recordDate = :recordDate")
    , @NamedQuery(name = "TipoCambioHist.findByRowPointer", query = "SELECT t FROM TipoCambioHist t WHERE t.rowPointer = :rowPointer")
    , @NamedQuery(name = "TipoCambioHist.findByCreatedBy", query = "SELECT t FROM TipoCambioHist t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TipoCambioHist.findByUpdatedBy", query = "SELECT t FROM TipoCambioHist t WHERE t.updatedBy = :updatedBy")
    , @NamedQuery(name = "TipoCambioHist.findByCreateDate", query = "SELECT t FROM TipoCambioHist t WHERE t.createDate = :createDate")})
public class TipoCambioHist implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoCambioHistPK tipoCambioHistPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO")
    private String usuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;
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
    @JoinColumn(name = "TIPO_CAMBIO", referencedColumnName = "TIPO_CAMBIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoCambio tipoCambio1;

    public TipoCambioHist() {
    }

    public TipoCambioHist(TipoCambioHistPK tipoCambioHistPK) {
        this.tipoCambioHistPK = tipoCambioHistPK;
    }

    public TipoCambioHist(TipoCambioHistPK tipoCambioHistPK, String usuario, BigDecimal monto, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.tipoCambioHistPK = tipoCambioHistPK;
        this.usuario = usuario;
        this.monto = monto;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public TipoCambioHist(String tipoCambio, Date fecha) {
        this.tipoCambioHistPK = new TipoCambioHistPK(tipoCambio, fecha);
    }

    public TipoCambioHistPK getTipoCambioHistPK() {
        return tipoCambioHistPK;
    }

    public void setTipoCambioHistPK(TipoCambioHistPK tipoCambioHistPK) {
        this.tipoCambioHistPK = tipoCambioHistPK;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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

    public TipoCambio getTipoCambio1() {
        return tipoCambio1;
    }

    public void setTipoCambio1(TipoCambio tipoCambio1) {
        this.tipoCambio1 = tipoCambio1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCambioHistPK != null ? tipoCambioHistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambioHist)) {
            return false;
        }
        TipoCambioHist other = (TipoCambioHist) object;
        if ((this.tipoCambioHistPK == null && other.tipoCambioHistPK != null) || (this.tipoCambioHistPK != null && !this.tipoCambioHistPK.equals(other.tipoCambioHistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.TipoCambioHist[ tipoCambioHistPK=" + tipoCambioHistPK + " ]";
    }
    
}
