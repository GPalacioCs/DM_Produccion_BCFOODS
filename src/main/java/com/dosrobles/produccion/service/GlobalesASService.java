package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.GlobalesASDAO;
import com.dosrobles.produccion.entities.GlobalesAS;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GlobalesASService extends AbstractService<GlobalesASDAO, GlobalesAS> {
    
    public GlobalesAS getGlobalesAS() throws BusinessValidationException {
        List<GlobalesAS> list = findAll();
        if (list.isEmpty()) {
            throw new BusinessValidationException("Debe configurar los parámetros de Globales AS");
        }
        else return list.get(0);
    }
    
    public String getMonedaLocal() throws BusinessValidationException {
        String monedaLocal = getGlobalesAS().getMonedaLocal();
        if (monedaLocal == null) {
            throw new BusinessValidationException("No se configuró el parametro de la moneda local");
        }
        return monedaLocal;
    }
}
