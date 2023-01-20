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
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "COSTO_UEPS_PEPS")
@NamedQueries({
    @NamedQuery(name = "CostoUepsPeps.findAll", query = "SELECT c FROM CostoUepsPeps c")})
public class CostoUepsPeps implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CostoUepsPepsPK costoUepsPepsPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_ORIGINAL")
    private BigDecimal cantidadOriginal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_RESTANTE")
    private BigDecimal cantidadRestante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_LOCAL")
    private BigDecimal costoLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_DOLAR")
    private BigDecimal costoDolar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO", insertable = false, updatable = false)
    private String articulo1;

    public CostoUepsPeps() {
    }

    public CostoUepsPeps(CostoUepsPepsPK costoUepsPepsPK) {
        this.costoUepsPepsPK = costoUepsPepsPK;
    }

    public CostoUepsPeps(String articulo, Date secuencia) {
        this.costoUepsPepsPK = new CostoUepsPepsPK(articulo, secuencia);
    }

    public CostoUepsPepsPK getCostoUepsPepsPK() {
        return costoUepsPepsPK;
    }

    public void setCostoUepsPepsPK(CostoUepsPepsPK costoUepsPepsPK) {
        this.costoUepsPepsPK = costoUepsPepsPK;
    }

    public BigDecimal getCantidadOriginal() {
        return cantidadOriginal;
    }

    public void setCantidadOriginal(BigDecimal cantidadOriginal) {
        this.cantidadOriginal = cantidadOriginal;
    }

    public BigDecimal getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(BigDecimal cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public BigDecimal getCostoLocal() {
        return costoLocal;
    }

    public void setCostoLocal(BigDecimal costoLocal) {
        this.costoLocal = costoLocal;
    }

    public BigDecimal getCostoDolar() {
        return costoDolar;
    }

    public void setCostoDolar(BigDecimal costoDolar) {
        this.costoDolar = costoDolar;
    }

    public String getArticulo1() {
        return articulo1;
    }

    public void setArticulo1(String articulo1) {
        this.articulo1 = articulo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (costoUepsPepsPK != null ? costoUepsPepsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoUepsPeps)) {
            return false;
        }
        CostoUepsPeps other = (CostoUepsPeps) object;
        if ((this.costoUepsPepsPK == null && other.costoUepsPepsPK != null) || (this.costoUepsPepsPK != null && !this.costoUepsPepsPK.equals(other.costoUepsPepsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.CostoUepsPeps[ costoUepsPepsPK=" + costoUepsPepsPK + " ]";
    }

}
