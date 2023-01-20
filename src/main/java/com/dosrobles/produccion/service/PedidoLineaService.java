/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.PedidoLineaDAO;
import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.PedidoLinea;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class PedidoLineaService extends AbstractService<PedidoLineaDAO, PedidoLinea> {
    public List<PedidoLinea> getPedidoLineaList(Articulo articulo) {
        return dao.getPedidoLineaList(articulo);
    }
}
