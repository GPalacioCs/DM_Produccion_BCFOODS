/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "CONSECUTIVO_CI")
@NamedQueries({
    @NamedQuery(name = "ConsecutivoCi.findAll", query = "SELECT c FROM ConsecutivoCi c")})
public class ConsecutivoCi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONSECUTIVO")
    private String consecutivo;
    @Basic(optional = false)
    @Column(name = "ULTIMO_USUARIO")
    private String ultimoUsuario;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "MASCARA")
    private String mascara;
    @Basic(optional = false)
    @Column(name = "SIGUIENTE_CONSEC")
    private String siguienteConsec;
    @Basic(optional = false)
    @Column(name = "EDITABLE")
    private String editable;
    @Basic(optional = false)
    @Column(name = "MULTIPLES_TRANS")
    private String multiplesTrans;
    @Column(name = "FORMATO_IMP")
    private String formatoImp;
    @Basic(optional = false)
    @Column(name = "ULT_FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultFechaHora;
    @Basic(optional = false)
    @Column(name = "TODAS_TRANS")
    private String todasTrans;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
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
    @OneToMany(mappedBy = "consecutivoCi", fetch = FetchType.LAZY)
    private List<Bodega> bodegaList;

    public ConsecutivoCi() {
    }

    public ConsecutivoCi(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public ConsecutivoCi(String consecutivo, String ultimoUsuario, String descripcion, String mascara, String siguienteConsec, String editable, String multiplesTrans, Date ultFechaHora, String todasTrans, String tipo, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.consecutivo = consecutivo;
        this.ultimoUsuario = ultimoUsuario;
        this.descripcion = descripcion;
        this.mascara = mascara;
        this.siguienteConsec = siguienteConsec;
        this.editable = editable;
        this.multiplesTrans = multiplesTrans;
        this.ultFechaHora = ultFechaHora;
        this.todasTrans = todasTrans;
        this.tipo = tipo;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getSiguienteConsec() {
        return siguienteConsec;
    }

    public void setSiguienteConsec(String siguienteConsec) {
        this.siguienteConsec = siguienteConsec;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getMultiplesTrans() {
        return multiplesTrans;
    }

    public void setMultiplesTrans(String multiplesTrans) {
        this.multiplesTrans = multiplesTrans;
    }

    public String getFormatoImp() {
        return formatoImp;
    }

    public void setFormatoImp(String formatoImp) {
        this.formatoImp = formatoImp;
    }

    public Date getUltFechaHora() {
        return ultFechaHora;
    }

    public void setUltFechaHora(Date ultFechaHora) {
        this.ultFechaHora = ultFechaHora;
    }

    public String getTodasTrans() {
        return todasTrans;
    }

    public void setTodasTrans(String todasTrans) {
        this.todasTrans = todasTrans;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<Bodega> getBodegaList() {
        return bodegaList;
    }

    public void setBodegaList(List<Bodega> bodegaList) {
        this.bodegaList = bodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivo != null ? consecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsecutivoCi)) {
            return false;
        }
        ConsecutivoCi other = (ConsecutivoCi) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ConsecutivoCi[ consecutivo=" + consecutivo + " ]";
    }
    
}
