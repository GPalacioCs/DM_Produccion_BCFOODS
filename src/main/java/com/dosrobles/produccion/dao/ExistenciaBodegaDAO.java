/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.ExistenciaBodega;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ExistenciaBodegaDAO extends AbstractDAO<ExistenciaBodega> {

    public ExistenciaBodegaDAO() {
        super(ExistenciaBodega.class);
    }
    
    public List<ExistenciaBodega> getExistenciaBodegaList(Articulo articulo) {
        String jpql = "select eb from ExistenciaBodega eb where eb.articulo = :articulo";
        
        return getEm().createQuery(jpql, ExistenciaBodega.class)
                .setParameter("articulo", articulo)
                .getResultList();
    }
    
    public List<ExistenciaBodega> getExistenciaBodegaList(Bodega bodega) {
        String jpql = "select eb from ExistenciaBodega eb where eb.bodega = :bodega";
                return getEm().createQuery(jpql, ExistenciaBodega.class)
                .setParameter("bodega", bodega)
                .getResultList();
    }
    
}
