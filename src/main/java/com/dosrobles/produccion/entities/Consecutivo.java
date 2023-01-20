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
 * @author KC
 */
@Entity
@Table(name = "CONSECUTIVO")
@NamedQueries({
    @NamedQuery(name = "Consecutivo.findAll", query = "SELECT c FROM Consecutivo c")})
public class Consecutivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONSECUTIVO")
    private String consecutivo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private String activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "LONGITUD")
    private BigDecimal longitud;
    @Basic(optional = false)
    @Column(name = "ENTIDAD")
    private String entidad;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "FORMATO_IMPRESION_DETALLADO")
    private String formatoImpresionDetallado;
    @Column(name = "FORMATO_IMPRESION_RESUMIDO")
    private String formatoImpresionResumido;
    @Basic(optional = false)
    @Column(name = "MASCARA")
    private String mascara;
    @Basic(optional = false)
    @Column(name = "VALOR_INICIAL")
    private String valorInicial;
    @Basic(optional = false)
    @Column(name = "VALOR_FINAL")
    private String valorFinal;
    @Basic(optional = false)
    @Column(name = "ULTIMO_VALOR")
    private String ultimoValor;
    @Basic(optional = false)
    @Column(name = "ULTIMO_USUARIO")
    private String ultimoUsuario;
    @Basic(optional = false)
    @Column(name = "FECHA_HORA_ULT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUlt;
    

    public Consecutivo() {
    }

    public Consecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFormatoImpresionDetallado() {
        return formatoImpresionDetallado;
    }

    public void setFormatoImpresionDetallado(String formatoImpresionDetallado) {
        this.formatoImpresionDetallado = formatoImpresionDetallado;
    }

    public String getFormatoImpresionResumido() {
        return formatoImpresionResumido;
    }

    public void setFormatoImpresionResumido(String formatoImpresionResumido) {
        this.formatoImpresionResumido = formatoImpresionResumido;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getUltimoValor() {
        return ultimoValor;
    }

    public void setUltimoValor(String ultimoValor) {
        this.ultimoValor = ultimoValor;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    public Date getFechaHoraUlt() {
        return fechaHoraUlt;
    }

    public void setFechaHoraUlt(Date fechaHoraUlt) {
        this.fechaHoraUlt = fechaHoraUlt;
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
        if (!(object instanceof Consecutivo)) {
            return false;
        }
        Consecutivo other = (Consecutivo) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Consecutivo[ consecutivo=" + consecutivo + " ]";
    }
    
}
