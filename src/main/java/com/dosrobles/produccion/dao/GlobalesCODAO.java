package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.GlobalesCO;

import javax.ejb.Stateless;

@Stateless
public class GlobalesCODAO extends AbstractDAO<GlobalesCO> {
    public GlobalesCODAO() {
        super(GlobalesCO.class);
    }
}
