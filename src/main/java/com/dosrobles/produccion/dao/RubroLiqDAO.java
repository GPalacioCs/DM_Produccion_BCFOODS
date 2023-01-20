/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.RubroLiq;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author pc
 */
@Stateless
public class RubroLiqDAO extends AbstractDAO<RubroLiq> {
    
    public RubroLiqDAO() {
        super(RubroLiq.class);
    }
    
    public List<RubroLiq> findByCodigos(List<String> codigos) {
        String jpql = "select r from RubroLiq r where r.rubro in :codigos";        
        return getEm().createQuery(jpql)
                .setParameter("codigos", codigos)
                .getResultList();
    }
    
}
