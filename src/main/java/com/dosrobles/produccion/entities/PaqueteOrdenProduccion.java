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
 * @author Zeus
 */
@Entity
@Table(name = "CS_PAQUETE_ORDEN_PRODUCCION")
@NamedQueries({
    @NamedQuery(name = "PaqueteOrdenProduccion.findAll", query = "SELECT p FROM PaqueteOrdenProduccion p")})
public class PaqueteOrdenProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PaqueteOrdenProduccionPK paqueteOrdenProduccionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @JoinColumn(name = "ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;
    @JoinColumn(name = "ORDEN", referencedColumnName = "ORDEN_PRODUCCION", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CSOrdenProduccion ordenProduccion;
    @Transient
    private BigDecimal cantidadPedida;
    @Transient
    private ExistenciaBodega existenciaBodega;

    public PaqueteOrdenProduccion() {
    }

    public PaqueteOrdenProduccion(PaqueteOrdenProduccionPK paqueteOrdenProduccionPK) {
        this.paqueteOrdenProduccionPK = paqueteOrdenProduccionPK;
    }

    public PaqueteOrdenProduccion(PaqueteOrdenProduccionPK paqueteOrdenProduccionPK, BigDecimal cantidad) {
        this.paqueteOrdenProduccionPK = paqueteOrdenProduccionPK;
        this.cantidad = cantidad;
    }

    public PaqueteOrdenProduccion(String orden, String articulo, BigDecimal cantidad) {
        this.paqueteOrdenProduccionPK = new PaqueteOrdenProduccionPK(orden, articulo);
        this.cantidad = cantidad;
    }

    public PaqueteOrdenProduccion(String orden, String articulo) {
        this.paqueteOrdenProduccionPK = new PaqueteOrdenProduccionPK(orden, articulo);
    }

    public PaqueteOrdenProduccion(PaqueteOrdenProduccionPK paqueteOrdenProduccionPK, BigDecimal cantidad, Articulo articulo, CSOrdenProduccion ordenProduccion) {
        this.paqueteOrdenProduccionPK = paqueteOrdenProduccionPK;
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.ordenProduccion = ordenProduccion;
        this.cantidadPedida = cantidad;
    }

    public PaqueteOrdenProduccionPK getPaqueteOrdenProduccionPK() {
        return paqueteOrdenProduccionPK;
    }

    public void setPaqueteOrdenProduccionPK(PaqueteOrdenProduccionPK paqueteOrdenProduccionPK) {
        this.paqueteOrdenProduccionPK = paqueteOrdenProduccionPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public CSOrdenProduccion getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(CSOrdenProduccion ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public BigDecimal getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(BigDecimal cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public ExistenciaBodega getExistenciaBodega() {
        return existenciaBodega;
    }

    public void setExistenciaBodega(ExistenciaBodega existenciaBodega) {
        this.existenciaBodega = existenciaBodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paqueteOrdenProduccionPK != null ? paqueteOrdenProduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaqueteOrdenProduccion)) {
            return false;
        }
        PaqueteOrdenProduccion other = (PaqueteOrdenProduccion) object;
        if ((this.paqueteOrdenProduccionPK == null && other.paqueteOrdenProduccionPK != null) || (this.paqueteOrdenProduccionPK != null && !this.paqueteOrdenProduccionPK.equals(other.paqueteOrdenProduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaqueteOrdenProduccion[ paqueteOrdenProduccionPK=" + paqueteOrdenProduccionPK + " ]";
    }
    
}
