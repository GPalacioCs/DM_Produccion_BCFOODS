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
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "TIPO_CAMBIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCambio.findAll", query = "SELECT t FROM TipoCambio t")
    , @NamedQuery(name = "TipoCambio.findByTipoCambio", query = "SELECT t FROM TipoCambio t WHERE t.tipoCambio = :tipoCambio")
    , @NamedQuery(name = "TipoCambio.findByDescripcion", query = "SELECT t FROM TipoCambio t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TipoCambio.findByNoteExistsFlag", query = "SELECT t FROM TipoCambio t WHERE t.noteExistsFlag = :noteExistsFlag")
    , @NamedQuery(name = "TipoCambio.findByRecordDate", query = "SELECT t FROM TipoCambio t WHERE t.recordDate = :recordDate")
    , @NamedQuery(name = "TipoCambio.findByRowPointer", query = "SELECT t FROM TipoCambio t WHERE t.rowPointer = :rowPointer")
    , @NamedQuery(name = "TipoCambio.findByCreatedBy", query = "SELECT t FROM TipoCambio t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TipoCambio.findByUpdatedBy", query = "SELECT t FROM TipoCambio t WHERE t.updatedBy = :updatedBy")
    , @NamedQuery(name = "TipoCambio.findByCreateDate", query = "SELECT t FROM TipoCambio t WHERE t.createDate = :createDate")})
public class TipoCambio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TIPO_CAMBIO")
    private String tipoCambio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCambio1")
    private List<TipoCambioHist> tipoCambioHistList;

    public TipoCambio() {
    }

    public TipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public TipoCambio(String tipoCambio, String descripcion, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.tipoCambio = tipoCambio;
        this.descripcion = descripcion;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<TipoCambioHist> getTipoCambioHistList() {
        return tipoCambioHistList;
    }

    public void setTipoCambioHistList(List<TipoCambioHist> tipoCambioHistList) {
        this.tipoCambioHistList = tipoCambioHistList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCambio != null ? tipoCambio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambio)) {
            return false;
        }
        TipoCambio other = (TipoCambio) object;
        if ((this.tipoCambio == null && other.tipoCambio != null) || (this.tipoCambio != null && !this.tipoCambio.equals(other.tipoCambio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.TipoCambio[ tipoCambio=" + tipoCambio + " ]";
    }
    
}
