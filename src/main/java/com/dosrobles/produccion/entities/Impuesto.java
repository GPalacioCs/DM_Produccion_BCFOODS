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
import java.math.BigDecimal;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "IMPUESTO")
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i")})
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "IMPUESTO")
    private String impuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO1")
    private BigDecimal impuesto1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO2")
    private BigDecimal impuesto2 = BigDecimal.ZERO;
    @Size(max = 1)
    @Column(name = "USA_IMPUESTO2_CANTIDAD")
    private String usaImpuesto2Cantidad = "N";
    @Column(name = "IMPUESTO2_CANTIDAD")
    private BigDecimal impuesto2Cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_CONTAB_IMP1")
    private String tipoContabImp1 = "S";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_CONTAB_IMP2")
    private String tipoContabImp2 = "U";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONTAB_DEV_IMP1")
    private String contabDevImp1 = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONTAB_DEV_IMP2")
    private String contabDevImp2 = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CALCULO_IMP2")
    private String calculoImp2 = "S";
    @Size(max = 25)
    @Column(name = "CTR_IMP1_GEN")
    private String ctrImp1Gen;
    @Size(max = 25)
    @Column(name = "CTA_IMP1_GEN")
    private String ctaImp1Gen;
    @Size(max = 25)
    @Column(name = "CTR_IMP2_GEN")
    private String ctrImp2Gen;
    @Size(max = 25)
    @Column(name = "CTA_IMP2_GEN")
    private String ctaImp2Gen;
    @Column(name = "CTR_IMP1_GEN_VTS")
    private String ctrImp1GenVts;
    @Column(name = "CTA_IMP1_GEN_VTS")
    private String ctaImp1GenVts;
    @Column(name = "CTR_IMP2_GEN_VTS")    
    private String ctrImp2GenVts;
    @Column(name = "CTA_IMP2_GEN_VTS")
    private String ctaImp2GenVts;
    @Column(name = "CTR_IMP1_DESC_CMPS")
    private String ctrImp1DescCmps;
    @Column(name = "CTA_IMP1_DESC_CMPS")
    private String ctaImp1DescCmps;
    @Size(max = 25)
    @Column(name = "CTR_IMP2_DESC_CMPS")
    private String ctrImp2DescCmps;
    @Size(max = 25)
    @Column(name = "CTA_IMP2_DESC_CMPS")
    private String ctaImp2DescCmps;
    @Size(max = 25)
    @Column(name = "CTR_IMP1_DEV_CMPS")
    private String ctrImp1DevCmps;
    @Size(max = 25)
    @Column(name = "CTA_IMP1_DEV_CMPS")
    private String ctaImp1DevCmps;
    @Size(max = 25)
    @Column(name = "CTR_IMP2_DEV_CMPS")
    private String ctrImp2DevCmps;
    @Size(max = 25)
    @Column(name = "CTA_IMP2_DEV_CMPS")
    private String ctaImp2DevCmps;
    @Size(max = 25)
    @Column(name = "CTR_IMP1_DEV_VTS")
    private String ctrImp1DevVts;
    @Size(max = 25)
    @Column(name = "CTA_IMP1_DEV_VTS")
    private String ctaImp1DevVts;
    @Size(max = 25)
    @Column(name = "CTR_IMP2_DEV_VTS")
    private String ctrImp2DevVts;
    @Size(max = 25)
    @Column(name = "CTA_IMP2_DEV_VTS")
    private String ctaImp2DevVts;

    public Impuesto() {
    }

    public Impuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImpuesto1() {
        return impuesto1;
    }

    public void setImpuesto1(BigDecimal impuesto1) {
        this.impuesto1 = impuesto1;
    }

    public BigDecimal getImpuesto2() {
        return impuesto2;
    }

    public void setImpuesto2(BigDecimal impuesto2) {
        this.impuesto2 = impuesto2;
    }

    public String getUsaImpuesto2Cantidad() {
        return usaImpuesto2Cantidad;
    }

    public void setUsaImpuesto2Cantidad(String usaImpuesto2Cantidad) {
        this.usaImpuesto2Cantidad = usaImpuesto2Cantidad;
    }

    public BigDecimal getImpuesto2Cantidad() {
        return impuesto2Cantidad;
    }

    public void setImpuesto2Cantidad(BigDecimal impuesto2Cantidad) {
        this.impuesto2Cantidad = impuesto2Cantidad;
    }

    public String getTipoContabImp1() {
        return tipoContabImp1;
    }

    public void setTipoContabImp1(String tipoContabImp1) {
        this.tipoContabImp1 = tipoContabImp1;
    }

    public String getTipoContabImp2() {
        return tipoContabImp2;
    }

    public void setTipoContabImp2(String tipoContabImp2) {
        this.tipoContabImp2 = tipoContabImp2;
    }

    public String getContabDevImp1() {
        return contabDevImp1;
    }

    public void setContabDevImp1(String contabDevImp1) {
        this.contabDevImp1 = contabDevImp1;
    }

    public String getContabDevImp2() {
        return contabDevImp2;
    }

    public void setContabDevImp2(String contabDevImp2) {
        this.contabDevImp2 = contabDevImp2;
    }

    public String getCalculoImp2() {
        return calculoImp2;
    }

    public void setCalculoImp2(String calculoImp2) {
        this.calculoImp2 = calculoImp2;
    }

    public String getCtrImp1Gen() {
        return ctrImp1Gen;
    }

    public void setCtrImp1Gen(String ctrImp1Gen) {
        this.ctrImp1Gen = ctrImp1Gen;
    }

    public String getCtaImp1Gen() {
        return ctaImp1Gen;
    }

    public void setCtaImp1Gen(String ctaImp1Gen) {
        this.ctaImp1Gen = ctaImp1Gen;
    }

    public String getCtrImp2Gen() {
        return ctrImp2Gen;
    }

    public void setCtrImp2Gen(String ctrImp2Gen) {
        this.ctrImp2Gen = ctrImp2Gen;
    }

    public String getCtaImp2Gen() {
        return ctaImp2Gen;
    }

    public void setCtaImp2Gen(String ctaImp2Gen) {
        this.ctaImp2Gen = ctaImp2Gen;
    }

    public String getCtrImp1GenVts() {
        return ctrImp1GenVts;
    }

    public void setCtrImp1GenVts(String ctrImp1GenVts) {
        this.ctrImp1GenVts = ctrImp1GenVts;
    }

    public String getCtaImp1GenVts() {
        return ctaImp1GenVts;
    }

    public void setCtaImp1GenVts(String ctaImp1GenVts) {
        this.ctaImp1GenVts = ctaImp1GenVts;
    }

    public String getCtrImp2GenVts() {
        return ctrImp2GenVts;
    }

    public void setCtrImp2GenVts(String ctrImp2GenVts) {
        this.ctrImp2GenVts = ctrImp2GenVts;
    }

    public String getCtaImp2GenVts() {
        return ctaImp2GenVts;
    }

    public void setCtaImp2GenVts(String ctaImp2GenVts) {
        this.ctaImp2GenVts = ctaImp2GenVts;
    }

    public String getCtrImp1DescCmps() {
        return ctrImp1DescCmps;
    }

    public void setCtrImp1DescCmps(String ctrImp1DescCmps) {
        this.ctrImp1DescCmps = ctrImp1DescCmps;
    }

    public String getCtaImp1DescCmps() {
        return ctaImp1DescCmps;
    }

    public void setCtaImp1DescCmps(String ctaImp1DescCmps) {
        this.ctaImp1DescCmps = ctaImp1DescCmps;
    }

    public String getCtrImp2DescCmps() {
        return ctrImp2DescCmps;
    }

    public void setCtrImp2DescCmps(String ctrImp2DescCmps) {
        this.ctrImp2DescCmps = ctrImp2DescCmps;
    }

    public String getCtaImp2DescCmps() {
        return ctaImp2DescCmps;
    }

    public void setCtaImp2DescCmps(String ctaImp2DescCmps) {
        this.ctaImp2DescCmps = ctaImp2DescCmps;
    }

    public String getCtrImp1DevCmps() {
        return ctrImp1DevCmps;
    }

    public void setCtrImp1DevCmps(String ctrImp1DevCmps) {
        this.ctrImp1DevCmps = ctrImp1DevCmps;
    }

    public String getCtaImp1DevCmps() {
        return ctaImp1DevCmps;
    }

    public void setCtaImp1DevCmps(String ctaImp1DevCmps) {
        this.ctaImp1DevCmps = ctaImp1DevCmps;
    }

    public String getCtrImp2DevCmps() {
        return ctrImp2DevCmps;
    }

    public void setCtrImp2DevCmps(String ctrImp2DevCmps) {
        this.ctrImp2DevCmps = ctrImp2DevCmps;
    }

    public String getCtaImp2DevCmps() {
        return ctaImp2DevCmps;
    }

    public void setCtaImp2DevCmps(String ctaImp2DevCmps) {
        this.ctaImp2DevCmps = ctaImp2DevCmps;
    }

    public String getCtrImp1DevVts() {
        return ctrImp1DevVts;
    }

    public void setCtrImp1DevVts(String ctrImp1DevVts) {
        this.ctrImp1DevVts = ctrImp1DevVts;
    }

    public String getCtaImp1DevVts() {
        return ctaImp1DevVts;
    }

    public void setCtaImp1DevVts(String ctaImp1DevVts) {
        this.ctaImp1DevVts = ctaImp1DevVts;
    }

    public String getCtrImp2DevVts() {
        return ctrImp2DevVts;
    }

    public void setCtrImp2DevVts(String ctrImp2DevVts) {
        this.ctrImp2DevVts = ctrImp2DevVts;
    }

    public String getCtaImp2DevVts() {
        return ctaImp2DevVts;
    }

    public void setCtaImp2DevVts(String ctaImp2DevVts) {
        this.ctaImp2DevVts = ctaImp2DevVts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (impuesto != null ? impuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.impuesto == null && other.impuesto != null) || (this.impuesto != null && !this.impuesto.equals(other.impuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Impuesto[ impuesto=" + impuesto + " ]";
    }

}
