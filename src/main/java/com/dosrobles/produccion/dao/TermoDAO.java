package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Termo;

import javax.ejb.Stateless;

@Stateless
public class TermoDAO extends AbstractDAO<Termo> {
    public TermoDAO() {
        super(Termo.class);
    }
}
