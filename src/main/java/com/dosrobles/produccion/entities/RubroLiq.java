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
@Table(name = "RUBRO_LIQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroLiq.findAll", query = "SELECT r FROM RubroLiq r")})
public class RubroLiq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUBRO")
    private String rubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIP_RUBRO")
    private String descripRubro;
    @Size(max = 4)
    @Column(name = "TIPO_GASTO")
    private String tipoGasto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MEDIR_AL_REPORTAR")
    private String medirAlReportar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETRO_PRORAT")
    private short parametroProrat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO_POR_UNIDAD_L")
    private BigDecimal costoPorUnidadL;
    @Column(name = "COSTO_POR_UNIDAD_D")
    private BigDecimal costoPorUnidadD;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "APLIC_CONT_CELULAS")
    private String aplicContCelulas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FCH_HORA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraCreacion;
    @Size(max = 25)
    @Column(name = "USUARIO_ULT_MODIF")
    private String usuarioUltModif;
    @Column(name = "FCH_HORA_ULT_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraUltModif;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ASOCIACION_CONTAB")
    private String asociacionContab;
    @Size(max = 1)
    @Column(name = "RUBRO_LIQUIDACION")
    private String rubroLiquidacion;
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

    public RubroLiq() {
    }

    public RubroLiq(String rubro) {
        this.rubro = rubro;
    }

    public RubroLiq(String rubro, String descripRubro, String medirAlReportar, short parametroProrat, String aplicContCelulas, String usuarioCreacion, Date fchHoraCreacion, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.rubro = rubro;
        this.descripRubro = descripRubro;
        this.medirAlReportar = medirAlReportar;
        this.parametroProrat = parametroProrat;
        this.aplicContCelulas = aplicContCelulas;
        this.usuarioCreacion = usuarioCreacion;
        this.fchHoraCreacion = fchHoraCreacion;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getDescripRubro() {
        return descripRubro;
    }

    public void setDescripRubro(String descripRubro) {
        this.descripRubro = descripRubro;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getMedirAlReportar() {
        return medirAlReportar;
    }

    public void setMedirAlReportar(String medirAlReportar) {
        this.medirAlReportar = medirAlReportar;
    }

    public short getParametroProrat() {
        return parametroProrat;
    }

    public void setParametroProrat(short parametroProrat) {
        this.parametroProrat = parametroProrat;
    }

    public BigDecimal getCostoPorUnidadL() {
        return costoPorUnidadL;
    }

    public void setCostoPorUnidadL(BigDecimal costoPorUnidadL) {
        this.costoPorUnidadL = costoPorUnidadL;
    }

    public BigDecimal getCostoPorUnidadD() {
        return costoPorUnidadD;
    }

    public void setCostoPorUnidadD(BigDecimal costoPorUnidadD) {
        this.costoPorUnidadD = costoPorUnidadD;
    }

    public String getAplicContCelulas() {
        return aplicContCelulas;
    }

    public void setAplicContCelulas(String aplicContCelulas) {
        this.aplicContCelulas = aplicContCelulas;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFchHoraCreacion() {
        return fchHoraCreacion;
    }

    public void setFchHoraCreacion(Date fchHoraCreacion) {
        this.fchHoraCreacion = fchHoraCreacion;
    }

    public String getUsuarioUltModif() {
        return usuarioUltModif;
    }

    public void setUsuarioUltModif(String usuarioUltModif) {
        this.usuarioUltModif = usuarioUltModif;
    }

    public Date getFchHoraUltModif() {
        return fchHoraUltModif;
    }

    public void setFchHoraUltModif(Date fchHoraUltModif) {
        this.fchHoraUltModif = fchHoraUltModif;
    }

    public String getAsociacionContab() {
        return asociacionContab;
    }

    public void setAsociacionContab(String asociacionContab) {
        this.asociacionContab = asociacionContab;
    }

    public String getRubroLiquidacion() {
        return rubroLiquidacion;
    }

    public void setRubroLiquidacion(String rubroLiquidacion) {
        this.rubroLiquidacion = rubroLiquidacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubro != null ? rubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroLiq)) {
            return false;
        }
        RubroLiq other = (RubroLiq) object;
        if ((this.rubro == null && other.rubro != null) || (this.rubro != null && !this.rubro.equals(other.rubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.RubroLiq[ rubro=" + rubro + " ]";
    }
    
}
