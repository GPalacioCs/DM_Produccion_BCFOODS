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

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "ESTRUC_PROC_RUBRO")
@XmlRootElement
public class EstrucProcRubro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstrucProcRubroPK estrucProcRubroPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD_ESTANDAR")
    private BigDecimal cantidadEstandar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNIT_ESTAND")
    private BigDecimal costoUnitEstand;
    @Column(name = "COSTO_UNIT_DOLAR")
    private BigDecimal costoUnitDolar;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTOR_CONVERSION")
    private BigDecimal factorConversion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RUBRO",referencedColumnName = "RUBRO",insertable = false,updatable = false)
    private RubroLiq rubro;

    public RubroLiq getRubro() {
        return rubro;
    }

    public void setRubro(RubroLiq rubro) {
        this.rubro = rubro;
    }

    public EstrucProcRubro() {
    }

    public EstrucProcRubro(EstrucProcRubroPK estrucProcRubroPK) {
        this.estrucProcRubroPK = estrucProcRubroPK;
    }

    public EstrucProcRubro(EstrucProcRubroPK estrucProcRubroPK, BigDecimal costoUnitEstand, BigDecimal factorConversion, short noteExistsFlag) {
        this.estrucProcRubroPK = estrucProcRubroPK;
        this.costoUnitEstand = costoUnitEstand;
        this.factorConversion = factorConversion;
    }

    public EstrucProcRubro(String articulo, String version, String operacion, String rubro) {
        this.estrucProcRubroPK = new EstrucProcRubroPK(articulo, version, operacion, rubro);
    }

    public EstrucProcRubroPK getEstrucProcRubroPK() {
        return estrucProcRubroPK;
    }

    public void setEstrucProcRubroPK(EstrucProcRubroPK estrucProcRubroPK) {
        this.estrucProcRubroPK = estrucProcRubroPK;
    }

    public BigDecimal getCantidadEstandar() {
        return cantidadEstandar;
    }

    public void setCantidadEstandar(BigDecimal cantidadEstandar) {
        this.cantidadEstandar = cantidadEstandar;
    }

    public BigDecimal getCostoUnitEstand() {
        return costoUnitEstand;
    }

    public void setCostoUnitEstand(BigDecimal costoUnitEstand) {
        this.costoUnitEstand = costoUnitEstand;
    }

    public BigDecimal getCostoUnitDolar() {
        return costoUnitDolar;
    }

    public void setCostoUnitDolar(BigDecimal costoUnitDolar) {
        this.costoUnitDolar = costoUnitDolar;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getFactorConversion() {
        return factorConversion;
    }

    public void setFactorConversion(BigDecimal factorConversion) {
        this.factorConversion = factorConversion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estrucProcRubroPK != null ? estrucProcRubroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucProcRubro)) {
            return false;
        }
        EstrucProcRubro other = (EstrucProcRubro) object;
        if ((this.estrucProcRubroPK == null && other.estrucProcRubroPK != null) || (this.estrucProcRubroPK != null && !this.estrucProcRubroPK.equals(other.estrucProcRubroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucProcRubro[ estrucProcRubroPK=" + estrucProcRubroPK + " ]";
    }
    
}
