/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "DOCUMENTO_ANTICIPO")
@NamedQueries({
    @NamedQuery(name = "DocumentoAnticipo.findAll", query = "SELECT d FROM DocumentoAnticipo d")})
public class DocumentoAnticipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentoAnticipoPK documentoAnticipoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @Column(name = "NoteExistsFlag")
    private short noteExistsFlag;
    @Basic(optional = false)
    @Column(name = "RecordDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Basic(optional = false)
    @Column(name = "RowPointer")
    private String rowPointer;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "UpdatedBy")
    private String updatedBy;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumns({
        @JoinColumn(name = "DOCUMENTO_CC", referencedColumnName = "DOCUMENTO", insertable = false, updatable = false),
        @JoinColumn(name = "TIPO_CC", referencedColumnName = "TIPO", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentosCc documentosCc;

    public DocumentoAnticipo() {
    }

    public DocumentoAnticipo(DocumentoAnticipoPK documentoAnticipoPK) {
        this.documentoAnticipoPK = documentoAnticipoPK;
    }

    public DocumentoAnticipo(DocumentoAnticipoPK documentoAnticipoPK, BigDecimal monto, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.documentoAnticipoPK = documentoAnticipoPK;
        this.monto = monto;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public DocumentoAnticipo(String tipoDocumento, String documento, String documentoCc, String tipoCc) {
        this.documentoAnticipoPK = new DocumentoAnticipoPK(tipoDocumento, documento, documentoCc, tipoCc);
    }

    public DocumentoAnticipoPK getDocumentoAnticipoPK() {
        return documentoAnticipoPK;
    }

    public void setDocumentoAnticipoPK(DocumentoAnticipoPK documentoAnticipoPK) {
        this.documentoAnticipoPK = documentoAnticipoPK;
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

    public DocumentosCc getDocumentosCc() {
        return documentosCc;
    }

    public void setDocumentosCc(DocumentosCc documentosCc) {
        this.documentosCc = documentosCc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoAnticipoPK != null ? documentoAnticipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoAnticipo)) {
            return false;
        }
        DocumentoAnticipo other = (DocumentoAnticipo) object;
        if ((this.documentoAnticipoPK == null && other.documentoAnticipoPK != null) || (this.documentoAnticipoPK != null && !this.documentoAnticipoPK.equals(other.documentoAnticipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentoAnticipo[ documentoAnticipoPK=" + documentoAnticipoPK + " ]";
    }

}
