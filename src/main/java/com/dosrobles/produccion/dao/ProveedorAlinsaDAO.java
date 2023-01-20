package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Proveedor;
import com.dosrobles.produccion.entities.ProveedorAlinsa;

import javax.ejb.Stateless;

@Stateless
public class ProveedorAlinsaDAO extends AbstractDAO<ProveedorAlinsa> {

    public ProveedorAlinsaDAO() {
        super(ProveedorAlinsa.class);
    }
}
