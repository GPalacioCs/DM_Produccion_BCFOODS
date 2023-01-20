/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.PedidoLinea;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class PedidoLineaDAO extends AbstractDAO<PedidoLinea> {

    public PedidoLineaDAO() {
        super(PedidoLinea.class);
    }
    
    public List<PedidoLinea> getPedidoLineaList(Articulo articulo) {
        String jpql = "select pl from PedidoLinea pl where pl.articulo = :articulo";
        
        return getEm().createQuery(jpql, PedidoLinea.class)
                .setParameter("articulo", articulo)
                .getResultList();
    }
}
