package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Proveedor;

import javax.ejb.Stateless;

@Stateless
public class ProveedorDAO extends AbstractDAO<Proveedor> {
    
    public ProveedorDAO() {
        super(Proveedor.class);
    }
}
