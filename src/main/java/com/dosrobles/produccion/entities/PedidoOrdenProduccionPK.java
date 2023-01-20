/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class PedidoOrdenProduccionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEDIDO")
    private String pedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORDEN_PRODUCCION")
    private String ordenProduccion;

    public PedidoOrdenProduccionPK() {
    }

    public PedidoOrdenProduccionPK(String pedido, String ordenProduccion) {
        this.pedido = pedido;
        this.ordenProduccion = ordenProduccion;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedido != null ? pedido.hashCode() : 0);
        hash += (ordenProduccion != null ? ordenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoOrdenProduccionPK)) {
            return false;
        }
        PedidoOrdenProduccionPK other = (PedidoOrdenProduccionPK) object;
        if ((this.pedido == null && other.pedido != null) || (this.pedido != null && !this.pedido.equals(other.pedido))) {
            return false;
        }
        if ((this.ordenProduccion == null && other.ordenProduccion != null) || (this.ordenProduccion != null && !this.ordenProduccion.equals(other.ordenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.PedidoOrdenProduccionPK[ pedido=" + pedido + ", ordenProduccion=" + ordenProduccion + " ]";
    }

}
