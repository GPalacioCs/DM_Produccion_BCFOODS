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
@Table(name = "AUDIT_TRANS_INV")
@NamedQueries({
    @NamedQuery(name = "AuditTransInv.findAll", query = "SELECT a FROM AuditTransInv a")})
public class AuditTransInv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_TRANS_INV")
    private Integer auditTransInv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "MODULO_ORIGEN")
    private String moduloOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 249)
    @Column(name = "APLICACION")
    private String aplicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Size(max = 10)
    @Column(name = "ASIENTO")
    private String asiento;
    @Size(max = 25)
    @Column(name = "USUARIO_APRO")
    private String usuarioApro;
    @Column(name = "FECHA_HORA_APROB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAprob;
    @Size(max = 4)
    @Column(name = "PAQUETE_INVENTARIO")
    private String paqueteInventario;
    
    @Size(max = 10)
    @Column(name = "CONSECUTIVO")
    private String consecutivo;

    public AuditTransInv() {
    }

    public AuditTransInv(Integer auditTransInv) {
        this.auditTransInv = auditTransInv;
    }

    public Integer getAuditTransInv() {
        return auditTransInv;
    }

    public void setAuditTransInv(Integer auditTransInv) {
        this.auditTransInv = auditTransInv;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getModuloOrigen() {
        return moduloOrigen;
    }

    public void setModuloOrigen(String moduloOrigen) {
        this.moduloOrigen = moduloOrigen;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getUsuarioApro() {
        return usuarioApro;
    }

    public void setUsuarioApro(String usuarioApro) {
        this.usuarioApro = usuarioApro;
    }

    public Date getFechaHoraAprob() {
        return fechaHoraAprob;
    }

    public void setFechaHoraAprob(Date fechaHoraAprob) {
        this.fechaHoraAprob = fechaHoraAprob;
    }

    public String getPaqueteInventario() {
        return paqueteInventario;
    }

    public void setPaqueteInventario(String paqueteInventario) {
        this.paqueteInventario = paqueteInventario;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditTransInv != null ? auditTransInv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditTransInv)) {
            return false;
        }
        AuditTransInv other = (AuditTransInv) object;
        if ((this.auditTransInv == null && other.auditTransInv != null) || (this.auditTransInv != null && !this.auditTransInv.equals(other.auditTransInv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.AuditTransInv[ auditTransInv=" + auditTransInv + " ]";
    }

}
