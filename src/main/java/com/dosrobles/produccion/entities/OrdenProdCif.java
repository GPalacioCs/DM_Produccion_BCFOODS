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
 * @author DEV-PC
 */
@Entity
@Table(name = "ORDEN_PROD_CIF")
@NamedQueries({
    @NamedQuery(name = "OrdenProdCif.findAll", query = "SELECT o FROM OrdenProdCif o")})
public class OrdenProdCif implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORDEN_PRODUCCION")
    private String ordenProduccion;
    @Size(max = 20)
    @Column(name = "RUBRO")
    private String rubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Column(name = "COSTO")
    private BigDecimal costo;

    public OrdenProdCif() {
    }

    public OrdenProdCif(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public String getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenProduccion != null ? ordenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenProdCif)) {
            return false;
        }
        OrdenProdCif other = (OrdenProdCif) object;
        if ((this.ordenProduccion == null && other.ordenProduccion != null) || (this.ordenProduccion != null && !this.ordenProduccion.equals(other.ordenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.OrdenProdCif[ ordenProduccion=" + ordenProduccion + " ]";
    }
    
}
