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
public class PedidoLineaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEDIDO")
    private String pedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEDIDO_LINEA")
    private short pedidoLinea;

    public PedidoLineaPK() {
    }

    public PedidoLineaPK(String pedido, short pedidoLinea) {
        this.pedido = pedido;
        this.pedidoLinea = pedidoLinea;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public short getPedidoLinea() {
        return pedidoLinea;
    }

    public void setPedidoLinea(short pedidoLinea) {
        this.pedidoLinea = pedidoLinea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedido != null ? pedido.hashCode() : 0);
        hash += (int) pedidoLinea;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoLineaPK)) {
            return false;
        }
        PedidoLineaPK other = (PedidoLineaPK) object;
        if ((this.pedido == null && other.pedido != null) || (this.pedido != null && !this.pedido.equals(other.pedido))) {
            return false;
        }
        if (this.pedidoLinea != other.pedidoLinea) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.PedidoLineaPK[ pedido=" + pedido + ", pedidoLinea=" + pedidoLinea + " ]";
    }

}
