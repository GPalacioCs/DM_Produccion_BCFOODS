package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.MateriaPrimaDAO;
import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.MateriaPrima;

import javax.ejb.Stateless;

@Stateless
public class MateriaPrimaService extends AbstractService<MateriaPrimaDAO, MateriaPrima> {
    
    public MateriaPrima findMateriaPrimaByArticuloHijo(Articulo articulo) {
        return dao.findMateriaPrimaByArticuloHijo(articulo);
    }
    
}
