/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.GlobalesCiDAO;
import com.dosrobles.produccion.entities.GlobalesCi;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class GlobalesCiService extends AbstractService<GlobalesCiDAO, GlobalesCi> {
    
    public GlobalesCi getGlobalesCi() throws BusinessValidationException {
        List<GlobalesCi> list = findAll();
        if(list.size() != 1) {
            throw new BusinessValidationException("Debe configurar los parámetros de Control de Inventario");
        }
        return list.get(0);
    }
}
