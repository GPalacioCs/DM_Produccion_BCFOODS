package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.ArticuloCuenta;

import javax.ejb.Stateless;

@Stateless
public class ArticuloCuentaDAO extends AbstractDAO<ArticuloCuenta> {

    public ArticuloCuentaDAO() {
        super(ArticuloCuenta.class);
    }
    
}
