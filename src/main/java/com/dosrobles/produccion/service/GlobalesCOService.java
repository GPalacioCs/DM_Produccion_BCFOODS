package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.GlobalesCODAO;
import com.dosrobles.produccion.entities.GlobalesCO;

import javax.ejb.Stateless;

@Stateless
public class GlobalesCOService extends AbstractService<GlobalesCODAO, GlobalesCO> {
    
    public GlobalesCO getGlobalesCO() {
        return findAll().get(0);
    }
}
