package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.UnidadDeMedidaDAO;
import com.dosrobles.produccion.entities.UnidadDeMedida;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;

@Stateless
public class UnidadDeMedidaService extends AbstractService<UnidadDeMedidaDAO, UnidadDeMedida> {

    @Override
    public UnidadDeMedida insert(UnidadDeMedida entity) throws BusinessValidationException {
        if(find(entity.getUnidadMedida()) != null) {
            throw new BusinessValidationException("Ya existe una unidad con el mismo código");
        }
        
        return dao.save(entity);
    }
    
}
