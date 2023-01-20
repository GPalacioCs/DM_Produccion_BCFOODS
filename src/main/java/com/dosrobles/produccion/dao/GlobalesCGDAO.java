package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.GlobalesCG;

import javax.ejb.Stateless;

@Stateless
public class GlobalesCGDAO extends AbstractDAO<GlobalesCG> {

    public GlobalesCGDAO() {
        super(GlobalesCG.class);
    }
    
}
