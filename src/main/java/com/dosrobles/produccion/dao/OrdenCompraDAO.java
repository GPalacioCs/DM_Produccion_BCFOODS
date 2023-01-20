package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.OrdenCompra;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrdenCompraDAO extends AbstractDAO<OrdenCompra> {
    
    public OrdenCompraDAO() {
        super(OrdenCompra.class);
    }
    
    public List<OrdenCompra> findByModuloOrigen(String modulo) {
        String jpql = "select oc from OrdenCompra oc where oc.moduloOrigen = :mo";
        return getEm().createQuery(jpql)
                .setParameter("mo", modulo).getResultList();
    }
}
