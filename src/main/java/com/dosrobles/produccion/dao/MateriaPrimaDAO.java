package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.MateriaPrima;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MateriaPrimaDAO extends AbstractDAO<MateriaPrima> {
    
    public MateriaPrimaDAO() {
        super(MateriaPrima.class);
    }
    
    public MateriaPrima findMateriaPrimaByArticuloHijo(Articulo articulo) {
        String jpql = "select mp from MateriaPrima mp where mp.articuloHijo = :articulo";
        List<MateriaPrima> list = getEm().createQuery(jpql, MateriaPrima.class).setParameter("articulo", articulo).getResultList();
        if(list.isEmpty()) return null;
        return list.get(0);
    }
}
