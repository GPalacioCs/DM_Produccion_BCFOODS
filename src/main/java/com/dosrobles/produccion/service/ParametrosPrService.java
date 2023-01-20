/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ParametrosPrDAO;
import com.dosrobles.produccion.entities.ParametrosPr;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ParametrosPrService extends AbstractService<ParametrosPrDAO, ParametrosPr> {
    public ParametrosPr getParametro() throws BusinessValidationException {
        List<ParametrosPr> list = findAll();
        if(list.isEmpty()) {
            throw new BusinessValidationException("Debe configurar los parámetros de producción");
        }
        return list.get(0);
    }
}
