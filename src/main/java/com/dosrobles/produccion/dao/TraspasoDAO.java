package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.traslado.Traspaso;

import javax.ejb.Stateless;

@Stateless
public class TraspasoDAO extends AbstractDAO<Traspaso> {
    public TraspasoDAO() {
        super(Traspaso.class);
    }

}
