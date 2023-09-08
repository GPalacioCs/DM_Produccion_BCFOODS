package com.dosrobles.produccion.dao.embarque;

import com.dosrobles.produccion.dao.AbstractDAO;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;

import javax.ejb.Stateless;

@Stateless
public class EmbarqueLineaDao extends AbstractDAO<EmbarqueLinea> {
    public EmbarqueLineaDao() {
        super(EmbarqueLinea.class);
    }
}
