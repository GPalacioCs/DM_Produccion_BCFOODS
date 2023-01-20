/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "DIARIO")
@NamedQueries({
    @NamedQuery(name = "Diario.findAll", query = "SELECT d FROM Diario d")})
public class Diario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiarioPK diarioPK;
    @Basic(optional = false)
    @Column(name = "FUENTE")
    private String fuente="-";
    @Basic(optional = false)
    @Column(name = "REFERENCIA")
    private String referencia="-";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEBITO_LOCAL")
    private BigDecimal debitoLocal;
    @Column(name = "DEBITO_DOLAR")
    private BigDecimal debitoDolar;
    @Column(name = "CREDITO_LOCAL")
    private BigDecimal creditoLocal;
    @Column(name = "CREDITO_DOLAR")
    private BigDecimal creditoDolar;
    @Column(name = "DEBITO_UNIDADES")
    private BigDecimal debitoUnidades;
    @Column(name = "CREDITO_UNIDADES")
    private BigDecimal creditoUnidades;
    @Column(name = "TIPO_CAMBIO")
    private BigDecimal tipoCambio;
    @Column(name = "BASE_LOCAL")
    private BigDecimal baseLocal;
    @Column(name = "BASE_DOLAR")
    private BigDecimal baseDolar;
    @Column(name = "PROYECTO")
    private String proyecto;
    @Column(name = "FASE")
    private String fase;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTRO_COSTO")
    private CentroCosto centroCosto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUENTA_CONTABLE")
    private CuentaContable cuentaContable;
    @Column(name = "NIT")
    private String nit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASIENTO", insertable = false, updatable = false)
    private AsientoDeDiario asiento;
    

    public Diario() {
    }

    public Diario(DiarioPK diarioPK) {
        this.diarioPK = diarioPK;
    }

    public Diario(DiarioPK diarioPK, String fuente, String referencia, CentroCosto centroCosto, CuentaContable cuentaContable) {
        this.diarioPK = diarioPK;
        this.fuente = fuente;
        this.referencia = referencia;
        this.centroCosto = centroCosto;
        this.cuentaContable = cuentaContable;
    }

    public Diario(String asiento, int consecutivo) {
        this.diarioPK = new DiarioPK(asiento, consecutivo);
    }

    public DiarioPK getDiarioPK() {
        return diarioPK;
    }

    public void setDiarioPK(DiarioPK diarioPK) {
        this.diarioPK = diarioPK;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getDebitoLocal() {
        return debitoLocal;
    }

    public void setDebitoLocal(BigDecimal debitoLocal) {
        this.debitoLocal = debitoLocal;
        if (debitoLocal != null) {
            creditoLocal = null;
        }
    }

    public BigDecimal getDebitoDolar() {
        return debitoDolar;
    }

    public void setDebitoDolar(BigDecimal debitoDolar) {
        this.debitoDolar = debitoDolar;
        if(debitoDolar != null)creditoDolar = null;
    }

    public BigDecimal getCreditoLocal() {
        return creditoLocal;
    }

    public void setCreditoLocal(BigDecimal creditoLocal) {
        this.creditoLocal = creditoLocal;
        if(creditoLocal != null)debitoLocal = null;
    }

    public BigDecimal getCreditoDolar() {
        return creditoDolar;
    }

    public void setCreditoDolar(BigDecimal creditoDolar) {
        this.creditoDolar = creditoDolar;
        if(creditoDolar != null)debitoDolar = null;
    }

    public BigDecimal getDebitoUnidades() {
        return debitoUnidades;
    }

    public void setDebitoUnidades(BigDecimal debitoUnidades) {
        this.debitoUnidades = debitoUnidades;
    }

    public BigDecimal getCreditoUnidades() {
        return creditoUnidades;
    }

    public void setCreditoUnidades(BigDecimal creditoUnidades) {
        this.creditoUnidades = creditoUnidades;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getBaseLocal() {
        return baseLocal;
    }

    public void setBaseLocal(BigDecimal baseLocal) {
        this.baseLocal = baseLocal;
    }

    public BigDecimal getBaseDolar() {
        return baseDolar;
    }

    public void setBaseDolar(BigDecimal baseDolar) {
        this.baseDolar = baseDolar;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }

    public CuentaContable getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(CuentaContable cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public AsientoDeDiario getAsiento() {
        return asiento;
    }

    public void setAsiento(AsientoDeDiario asiento) {
        this.asiento = asiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diarioPK != null ? diarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diario)) {
            return false;
        }
        Diario other = (Diario) object;
        if ((this.diarioPK == null && other.diarioPK != null) || (this.diarioPK != null && !this.diarioPK.equals(other.diarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Diario[ diarioPK=" + diarioPK + " ]";
    }

}
