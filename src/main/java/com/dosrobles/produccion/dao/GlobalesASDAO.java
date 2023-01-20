package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.GlobalesAS;

import javax.ejb.Stateless;

@Stateless
public class GlobalesASDAO extends AbstractDAO<GlobalesAS> {

    public GlobalesASDAO() {
        super(GlobalesAS.class);
    }
    
}
