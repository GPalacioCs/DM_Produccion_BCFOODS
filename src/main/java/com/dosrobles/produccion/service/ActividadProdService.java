/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ActividadProdDAO;
import com.dosrobles.produccion.entities.ActividadProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;

/**
 *
 * @author pc
 */
@Stateless
public class ActividadProdService extends AbstractService<ActividadProdDAO, ActividadProd> {

    @Override
    public void delete(ActividadProd entity) throws BusinessValidationException {
        dao.delete(entity);
    }
    
}
