package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.GlobalesCGDAO;
import com.dosrobles.produccion.entities.GlobalesCG;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GlobalesCGService extends AbstractService<GlobalesCGDAO, GlobalesCG> {
    
    
    
    public GlobalesCG getGlobalesCG() throws BusinessValidationException {
        List<GlobalesCG> list = findAll();
        if(list.size() != 1) {
            throw new BusinessValidationException("Debe configurar los parámetros de contabilidad general");
        }
        return list.get(0);
    }
}
