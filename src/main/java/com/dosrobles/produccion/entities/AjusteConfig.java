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
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "AJUSTE_CONFIG")
@NamedQueries({
    @NamedQuery(name = "AjusteConfig.findAll", query = "SELECT a FROM AjusteConfig a")})
public class AjusteConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "AJUSTE_CONFIG")
    private String ajusteConfig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AJUSTE_BASE")
    private String ajusteBase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVA")
    private String activa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "INGRESO")
    private String ingreso;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajusteConfig", fetch = FetchType.LAZY)
    private List<LineaDocInv> lineaDocInvList;
    @OneToMany(mappedBy = "ajusteConfig", fetch = FetchType.LAZY)
    private List<TransaccionInv> transaccionInvList;

    public AjusteConfig() {
    }

    public AjusteConfig(String ajusteConfig) {
        this.ajusteConfig = ajusteConfig;
    }

    public AjusteConfig(String ajusteConfig, String descripcion, String ajusteBase, String activa, String ingreso, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.ajusteConfig = ajusteConfig;
        this.descripcion = descripcion;
        this.ajusteBase = ajusteBase;
        this.activa = activa;
        this.ingreso = ingreso;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getAjusteConfig() {
        return ajusteConfig;
    }

    public void setAjusteConfig(String ajusteConfig) {
        this.ajusteConfig = ajusteConfig;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAjusteBase() {
        return ajusteBase;
    }

    public void setAjusteBase(String ajusteBase) {
        this.ajusteBase = ajusteBase;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
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

    public List<LineaDocInv> getLineaDocInvList() {
        return lineaDocInvList;
    }

    public void setLineaDocInvList(List<LineaDocInv> lineaDocInvList) {
        this.lineaDocInvList = lineaDocInvList;
    }

    public List<TransaccionInv> getTransaccionInvList() {
        return transaccionInvList;
    }

    public void setTransaccionInvList(List<TransaccionInv> transaccionInvList) {
        this.transaccionInvList = transaccionInvList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ajusteConfig != null ? ajusteConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjusteConfig)) {
            return false;
        }
        AjusteConfig other = (AjusteConfig) object;
        if ((this.ajusteConfig == null && other.ajusteConfig != null) || (this.ajusteConfig != null && !this.ajusteConfig.equals(other.ajusteConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.AjusteConfig[ ajusteConfig=" + ajusteConfig + " ]";
    }

}
