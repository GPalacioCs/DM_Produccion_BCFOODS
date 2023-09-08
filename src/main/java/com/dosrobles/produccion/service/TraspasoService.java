package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.TraspasoDAO;
import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;

@Stateless
public class TraspasoService extends AbstractService<TraspasoDAO, Traspaso> {

    @Override
    public void delete(Traspaso entity) throws BusinessValidationException {
        dao.delete(entity);
    }
}
