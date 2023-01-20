/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.PedidoOrdenProduccionDAO;
import com.dosrobles.produccion.entities.PedidoOrdenProduccion;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.LazyQueryObject;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class PedidoOrdenProduccionService extends AbstractService<PedidoOrdenProduccionDAO, PedidoOrdenProduccion> {

    @Override
    public PedidoOrdenProduccion save(PedidoOrdenProduccion entity) throws BusinessValidationException {
        throw new BusinessValidationException("Acción no soportada");
    }

    @Override
    public PedidoOrdenProduccion insert(PedidoOrdenProduccion entity) throws BusinessValidationException {
        throw new BusinessValidationException("Acción no soportada");
    }
    
    public List<PedidoOrdenProduccion> filtrar(LazyQueryObject<PedidoOrdenProduccion> lqo) {
        return dao.filtrar(lqo);
    }
    
    
    
}
