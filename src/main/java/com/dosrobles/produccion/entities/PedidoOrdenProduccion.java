/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CS_PEDIDO_ORDEN_PRODUCCION")
@NamedQueries({
    @NamedQuery(name = "PedidoOrdenProduccion.findAll", query = "SELECT p FROM PedidoOrdenProduccion p")})
public class PedidoOrdenProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoOrdenProduccionPK pedidoOrdenProduccionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO", insertable = false, updatable = false)
    private Pedido pedido;
    @JoinColumn(name = "ORDEN_PRODUCCION", referencedColumnName = "ORDEN_PRODUCCION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CSOrdenProduccion ordenProduccion;

    public PedidoOrdenProduccion() {
    }

    public PedidoOrdenProduccion(PedidoOrdenProduccionPK pedidoOrdenProduccionPK) {
        this.pedidoOrdenProduccionPK = pedidoOrdenProduccionPK;
    }

    public PedidoOrdenProduccion(PedidoOrdenProduccionPK pedidoOrdenProduccionPK, BigDecimal cantidad, Pedido pedido1) {
        this.pedidoOrdenProduccionPK = pedidoOrdenProduccionPK;
        this.cantidad = cantidad;
        this.pedido = pedido1;
    }

    public PedidoOrdenProduccion(String pedido, String ordenProduccion) {
        this.pedidoOrdenProduccionPK = new PedidoOrdenProduccionPK(pedido, ordenProduccion);
        this.cantidad = BigDecimal.ZERO;
    }

    public PedidoOrdenProduccionPK getPedidoOrdenProduccionPK() {
        return pedidoOrdenProduccionPK;
    }

    public void setPedidoOrdenProduccionPK(PedidoOrdenProduccionPK pedidoOrdenProduccionPK) {
        this.pedidoOrdenProduccionPK = pedidoOrdenProduccionPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public CSOrdenProduccion getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(CSOrdenProduccion ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoOrdenProduccionPK != null ? pedidoOrdenProduccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoOrdenProduccion)) {
            return false;
        }
        PedidoOrdenProduccion other = (PedidoOrdenProduccion) object;
        if ((this.pedidoOrdenProduccionPK == null && other.pedidoOrdenProduccionPK != null) || (this.pedidoOrdenProduccionPK != null && !this.pedidoOrdenProduccionPK.equals(other.pedidoOrdenProduccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.PedidoOrdenProduccion[ pedidoOrdenProduccionPK=" + pedidoOrdenProduccionPK + " ]";
    }

}
