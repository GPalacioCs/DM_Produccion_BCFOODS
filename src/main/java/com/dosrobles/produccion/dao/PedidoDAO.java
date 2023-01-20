package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Pedido;

import javax.ejb.Stateless;

/**
 *
 * @author pc
 */
@Stateless
public class PedidoDAO extends AbstractDAO<Pedido> {

    public PedidoDAO() {
        super(Pedido.class);
    }
    
}
