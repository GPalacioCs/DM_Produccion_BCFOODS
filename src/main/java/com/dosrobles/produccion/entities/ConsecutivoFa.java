/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpsoft.csvtigersoftland.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author KC
 */
@Entity
@Table(name = "CONSECUTIVO_FA")
@NamedQueries({
    @NamedQuery(name = "ConsecutivoFa.findAll", query = "SELECT c FROM ConsecutivoFa c")})
public class ConsecutivoFa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_CONSECUTIVO")
    private String codigoConsecutivo;
    @Basic(optional = false)
    @Column(name = "USUARIO_ULT")
    private String usuarioUlt;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "LONGITUD")
    private short longitud;
    @Basic(optional = false)
    @Column(name = "VALOR_CONSECUTIVO")
    private String valorConsecutivo;
    @Column(name = "MASCARA")
    private String mascara;
    @Basic(optional = false)
    @Column(name = "FECHA_HORA_ULT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUlt;
    @Basic(optional = false)
    @Column(name = "FORMATO")
    private String formato;
    @Basic(optional = false)
    @Column(name = "USA_DESPACHOS")
    private String usaDespachos;
    @Basic(optional = false)
    @Column(name = "USA_ESQUEMA_CAJAS")
    private String usaEsquemaCajas;
    @Basic(optional = false)
    @Column(name = "VALOR_MAXIMO")
    private String valorMaximo;
    @Basic(optional = false)
    @Column(name = "NUMERO_COPIAS")
    private int numeroCopias;
    @Column(name = "ORIGINAL")
    private String original;
    @Column(name = "COPIA1")
    private String copia1;
    @Column(name = "COPIA2")
    private String copia2;
    @Column(name = "COPIA3")
    private String copia3;
    @Column(name = "COPIA4")
    private String copia4;
    @Column(name = "COPIA5")
    private String copia5;
    @Column(name = "REIMPRESION")
    private String reimpresion;
//    @Column(name = "SERIE")
//    private String serie;
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
    @Column(name = "RESOLUCION")
    private String resolucion;

    public ConsecutivoFa() {
    }

    public ConsecutivoFa(String codigoConsecutivo) {
        this.codigoConsecutivo = codigoConsecutivo;
    }

    public ConsecutivoFa(String codigoConsecutivo, String usuarioUlt, String descripcion, String tipo, short longitud, String valorConsecutivo, Date fechaHoraUlt, String formato, String usaDespachos, String usaEsquemaCajas, String valorMaximo, int numeroCopias, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.codigoConsecutivo = codigoConsecutivo;
        this.usuarioUlt = usuarioUlt;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.longitud = longitud;
        this.valorConsecutivo = valorConsecutivo;
        this.fechaHoraUlt = fechaHoraUlt;
        this.formato = formato;
        this.usaDespachos = usaDespachos;
        this.usaEsquemaCajas = usaEsquemaCajas;
        this.valorMaximo = valorMaximo;
        this.numeroCopias = numeroCopias;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getCodigoConsecutivo() {
        return codigoConsecutivo;
    }

    public void setCodigoConsecutivo(String codigoConsecutivo) {
        this.codigoConsecutivo = codigoConsecutivo;
    }

    public String getUsuarioUlt() {
        return usuarioUlt;
    }

    public void setUsuarioUlt(String usuarioUlt) {
        this.usuarioUlt = usuarioUlt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public short getLongitud() {
        return longitud;
    }

    public void setLongitud(short longitud) {
        this.longitud = longitud;
    }

    public String getValorConsecutivo() {
        return valorConsecutivo;
    }

    public void setValorConsecutivo(String valorConsecutivo) {
        this.valorConsecutivo = valorConsecutivo;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public Date getFechaHoraUlt() {
        return fechaHoraUlt;
    }

    public void setFechaHoraUlt(Date fechaHoraUlt) {
        this.fechaHoraUlt = fechaHoraUlt;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getUsaDespachos() {
        return usaDespachos;
    }

    public void setUsaDespachos(String usaDespachos) {
        this.usaDespachos = usaDespachos;
    }

    public String getUsaEsquemaCajas() {
        return usaEsquemaCajas;
    }

    public void setUsaEsquemaCajas(String usaEsquemaCajas) {
        this.usaEsquemaCajas = usaEsquemaCajas;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getCopia1() {
        return copia1;
    }

    public void setCopia1(String copia1) {
        this.copia1 = copia1;
    }

    public String getCopia2() {
        return copia2;
    }

    public void setCopia2(String copia2) {
        this.copia2 = copia2;
    }

    public String getCopia3() {
        return copia3;
    }

    public void setCopia3(String copia3) {
        this.copia3 = copia3;
    }

    public String getCopia4() {
        return copia4;
    }

    public void setCopia4(String copia4) {
        this.copia4 = copia4;
    }

    public String getCopia5() {
        return copia5;
    }

    public void setCopia5(String copia5) {
        this.copia5 = copia5;
    }

    public String getReimpresion() {
        return reimpresion;
    }

    public void setReimpresion(String reimpresion) {
        this.reimpresion = reimpresion;
    }

//    public String getSerie() {
//        return serie;
//    }
//
//    public void setSerie(String serie) {
//        this.serie = serie;
//    }

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

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoConsecutivo != null ? codigoConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsecutivoFa)) {
            return false;
        }
        ConsecutivoFa other = (ConsecutivoFa) object;
        if ((this.codigoConsecutivo == null && other.codigoConsecutivo != null) || (this.codigoConsecutivo != null && !this.codigoConsecutivo.equals(other.codigoConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication3.ConsecutivoFa[ codigoConsecutivo=" + codigoConsecutivo + " ]";
    }
    
}
