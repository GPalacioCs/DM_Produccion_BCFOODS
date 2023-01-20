/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import org.hibernate.validator.constraints.NotEmpty;

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
@Table(name="CUENTA_CONTABLE")
public class CuentaContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(min=1,max=25)
    @Column(name="CUENTA_CONTABLE")
    private String cuenta;
    @Size(max=120, message="La descripcion no puede tener mas de {max} careacteres")
    private String descripcion;
    @NotEmpty(message="El tipo es requerido")
    @Size(max=1, message="El tipo no puede tener mas de 1 caracter")
    private String tipo;
    @NotEmpty(message="El tipo detallado es requerido")
    @Size(max=1, message="El tipo detallado no puede tener mas de 1 caracter")
    @Column(name="tipo_detallado")
    private String tipoDetallado;
    @NotEmpty(message="El saldo normal es requerido")
    @Size(max=1, message="El saldo normal no puede tener mas de 1 caracter")
    @Column(name="saldo_normal")
    private String saldoNormal;
    @NotEmpty
    @Size(max=1)
    @Column(name="acepta_datos")
    private String aceptaDatos;
    private String notas;
    @NotEmpty
    @Size(max=1)
    @Column(name="TIPO_OAF")
    private String tipoOaf = "O";
    @NotEmpty
    @Size(max=1)
    private String conversion = "N";
    @NotEmpty
    @Size(max=4)
    @Column(name="tipo_cambio")
    private String tipoCambio = "OFIC";
    @NotEmpty
    @Size(max=1)
    private String consolida = "N";
    @NotEmpty
    @Size(max=1)
    @Column(name="usa_centro_costo")
    private String usaCentroCosto;
    @NotEmpty
    @Size(max=10)
    private String usuario;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_hora")
    private Date fechaHora;
    @NotEmpty
    @Size(max=10)
    @Column(name="usuario_ult_mod")
    private String usuarioUltMod="N";
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fch_hora_ult_mod")
    private Date fechaHoraUltMod;
    @NotEmpty
    @Size(max=1)
    @Column(name="acepta_unidades")
    private String aceptaUnidades;
    @NotEmpty
    @Size(max=1)
    @Column(name="uso_restringido")
    private String usoRestringido = "N";
    @NotEmpty
    @Size(max=1)
    @Column(name="origen_conversion")
    private String origenConversion = "N";
    @NotEmpty
    @Size(max=1)
    @Column(name="VALIDA_PRESUP_CR")
    private String validaPresupCr = "N";
    @Column(name="CreateDate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public CuentaContable() {
    }

    public CuentaContable(String cuenta) {
        this.cuenta = cuenta;
    }    
    
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
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

    public String getTipoDetallado() {
        return tipoDetallado;
    }

    public void setTipoDetallado(String tipoDetallado) {
        this.tipoDetallado = tipoDetallado;
    }

    public String getSaldoNormal() {
        return saldoNormal;
    }

    public void setSaldoNormal(String saldoNormal) {
        this.saldoNormal = saldoNormal;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getAceptaDatos() {
        return aceptaDatos;
    }

    public void setAceptaDatos(String aceptaDatos) {
        this.aceptaDatos = aceptaDatos;
    }

    public String getTipoOaf() {
        return tipoOaf;
    }

    public void setTipoOaf(String tipoOaf) {
        this.tipoOaf = tipoOaf;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getConsolida() {
        return consolida;
    }

    public void setConsolida(String consolida) {
        this.consolida = consolida;
    }

    public String getUsaCentroCosto() {
        return usaCentroCosto;
    }

    public void setUsaCentroCosto(String usaCentroCosto) {
        this.usaCentroCosto = usaCentroCosto;
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

    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    public Date getFechaHoraUltMod() {
        return fechaHoraUltMod;
    }

    public void setFechaHoraUltMod(Date fechaHoraUltMod) {
        this.fechaHoraUltMod = fechaHoraUltMod;
    }

    public String getAceptaUnidades() {
        return aceptaUnidades;
    }

    public void setAceptaUnidades(String aceptaUnidades) {
        this.aceptaUnidades = aceptaUnidades;
    }

    public String getUsoRestringido() {
        return usoRestringido;
    }

    public void setUsoRestringido(String usoRestringido) {
        this.usoRestringido = usoRestringido;
    }

    public String getOrigenConversion() {
        return origenConversion;
    }

    public void setOrigenConversion(String origenConversion) {
        this.origenConversion = origenConversion;
    }

    public String getValidaPresupCr() {
        return validaPresupCr;
    }

    public void setValidaPresupCr(String validaPresupCr) {
        this.validaPresupCr = validaPresupCr;
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
        hash += (cuenta != null ? cuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContable)) {
            return false;
        }
        CuentaContable other = (CuentaContable) object;
        if ((this.cuenta == null && other.cuenta != null) || (this.cuenta != null && !this.cuenta.equals(other.cuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.contabilidad.entities.CatalogoCuenta[ id=" + cuenta + " ]";
    }

}
