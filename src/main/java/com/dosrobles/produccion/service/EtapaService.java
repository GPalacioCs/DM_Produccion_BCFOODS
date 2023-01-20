/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EtapaDAO;
import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Etapa;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class EtapaService extends AbstractService<EtapaDAO, Etapa> {
    public List<Etapa> getEtapasPorArticulo(Articulo articulo) {
        return dao.getEtapasPorArticulo(articulo);
    }

    @Override
    public void delete(Etapa entity) throws BusinessValidationException {
        dao.delete(entity);
    }
    
    
}
