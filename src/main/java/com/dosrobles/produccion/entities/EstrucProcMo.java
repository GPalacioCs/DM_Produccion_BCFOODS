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
@Table(name = "ESTRUC_PROC_MO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstrucProcMo.findAll", query = "SELECT e FROM EstrucProcMo e")
    , @NamedQuery(name = "EstrucProcMo.findByArticulo", query = "SELECT e FROM EstrucProcMo e WHERE e.estrucProcMoPK.articulo = :articulo")
    , @NamedQuery(name = "EstrucProcMo.findByVersion", query = "SELECT e FROM EstrucProcMo e WHERE e.estrucProcMoPK.version = :version")
    , @NamedQuery(name = "EstrucProcMo.findByOperacion", query = "SELECT e FROM EstrucProcMo e WHERE e.estrucProcMoPK.operacion = :operacion")
    , @NamedQuery(name = "EstrucProcMo.findBySecuencia", query = "SELECT e FROM EstrucProcMo e WHERE e.estrucProcMoPK.secuencia = :secuencia")
    , @NamedQuery(name = "EstrucProcMo.findByTipoManoObra", query = "SELECT e FROM EstrucProcMo e WHERE e.tipoManoObra = :tipoManoObra")
    , @NamedQuery(name = "EstrucProcMo.findByCantidadEmpleados", query = "SELECT e FROM EstrucProcMo e WHERE e.cantidadEmpleados = :cantidadEmpleados")
    , @NamedQuery(name = "EstrucProcMo.findByCantidadHoras", query = "SELECT e FROM EstrucProcMo e WHERE e.cantidadHoras = :cantidadHoras")
    , @NamedQuery(name = "EstrucProcMo.findByCostoPorHora", query = "SELECT e FROM EstrucProcMo e WHERE e.costoPorHora = :costoPorHora")
    , @NamedQuery(name = "EstrucProcMo.findByCostoPorHoraD", query = "SELECT e FROM EstrucProcMo e WHERE e.costoPorHoraD = :costoPorHoraD")
    , @NamedQuery(name = "EstrucProcMo.findByNoteExistsFlag", query = "SELECT e FROM EstrucProcMo e WHERE e.noteExistsFlag = :noteExistsFlag")
    , @NamedQuery(name = "EstrucProcMo.findByRecordDate", query = "SELECT e FROM EstrucProcMo e WHERE e.recordDate = :recordDate")
    , @NamedQuery(name = "EstrucProcMo.findByRowPointer", query = "SELECT e FROM EstrucProcMo e WHERE e.rowPointer = :rowPointer")
    , @NamedQuery(name = "EstrucProcMo.findByCreatedBy", query = "SELECT e FROM EstrucProcMo e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EstrucProcMo.findByUpdatedBy", query = "SELECT e FROM EstrucProcMo e WHERE e.updatedBy = :updatedBy")
    , @NamedQuery(name = "EstrucProcMo.findByCreateDate", query = "SELECT e FROM EstrucProcMo e WHERE e.createDate = :createDate")})
public class EstrucProcMo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstrucProcMoPK estrucProcMoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_MANO_OBRA")
    private String tipoManoObra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_EMPLEADOS")
    private short cantidadEmpleados;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_HORAS")
    private BigDecimal cantidadHoras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_POR_HORA")
    private BigDecimal costoPorHora;
    @Column(name = "COSTO_POR_HORA_D")
    private BigDecimal costoPorHoraD;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NOTAS")
    private String notas;
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

    public EstrucProcMo() {
    }

    public EstrucProcMo(EstrucProcMoPK estrucProcMoPK) {
        this.estrucProcMoPK = estrucProcMoPK;
    }

    public EstrucProcMo(EstrucProcMoPK estrucProcMoPK, String tipoManoObra, short cantidadEmpleados, BigDecimal cantidadHoras, BigDecimal costoPorHora, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.estrucProcMoPK = estrucProcMoPK;
        this.tipoManoObra = tipoManoObra;
        this.cantidadEmpleados = cantidadEmpleados;
        this.cantidadHoras = cantidadHoras;
        this.costoPorHora = costoPorHora;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public EstrucProcMo(String articulo, String version, String operacion, short secuencia) {
        this.estrucProcMoPK = new EstrucProcMoPK(articulo, version, operacion, secuencia);
    }

    public EstrucProcMoPK getEstrucProcMoPK() {
        return estrucProcMoPK;
    }

    public void setEstrucProcMoPK(EstrucProcMoPK estrucProcMoPK) {
        this.estrucProcMoPK = estrucProcMoPK;
    }

    public String getTipoManoObra() {
        return tipoManoObra;
    }

    public void setTipoManoObra(String tipoManoObra) {
        this.tipoManoObra = tipoManoObra;
    }

    public short getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(short cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public BigDecimal getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(BigDecimal cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public BigDecimal getCostoPorHora() {
        return costoPorHora;
    }

    public void setCostoPorHora(BigDecimal costoPorHora) {
        this.costoPorHora = costoPorHora;
    }

    public BigDecimal getCostoPorHoraD() {
        return costoPorHoraD;
    }

    public void setCostoPorHoraD(BigDecimal costoPorHoraD) {
        this.costoPorHoraD = costoPorHoraD;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
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
        hash += (estrucProcMoPK != null ? estrucProcMoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucProcMo)) {
            return false;
        }
        EstrucProcMo other = (EstrucProcMo) object;
        if ((this.estrucProcMoPK == null && other.estrucProcMoPK != null) || (this.estrucProcMoPK != null && !this.estrucProcMoPK.equals(other.estrucProcMoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucProcMo[ estrucProcMoPK=" + estrucProcMoPK + " ]";
    }
    
}
