package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.GrupoManoObraDAO;
import com.dosrobles.produccion.entities.GrupoManoObra;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;

@Stateless
public class GrupoManoObraService extends AbstractService<GrupoManoObraDAO, GrupoManoObra> {
    
    @Override
    public void delete(GrupoManoObra entity) throws BusinessValidationException {
        dao.delete(entity);
    }
}
