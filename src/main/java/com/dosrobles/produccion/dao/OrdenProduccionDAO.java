/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.OrdenProduccion;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class OrdenProduccionDAO extends AbstractDAO<OrdenProduccion>{
    
    public OrdenProduccionDAO() {
            super(OrdenProduccion.class);
    }
    
    public List<OrdenProduccion> findByLoteProd(String lote) {
        String jpql = "select op from OrdenProduccion op where op.loteProd = :lote";
        return getEm().createQuery(jpql).setParameter("lote", lote).getResultList();
    }
    
    public List<OrdenProduccion> getOrdenesByFecha(Date fechaInicio, Date fechaFin) {
        String jpql = "select op from OrdenProduccion op where op.fechaRequerida between :fechaInicio and :fechaFin";
        return getEm().createQuery(jpql)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }
    
    public List<OrdenProduccion> findByEstados(List<String> estadoList) {
        String jpql = "select e from OrdenProduccion e where e.estado in :estadoList";
        
        return getEm().createQuery(jpql).setParameter("estadoList", estadoList)
                .getResultList();
    }
    
    public List<OrdenProduccion> findFrescoPlaneado() {
        String jpql = "select e from OrdenProduccion e where e.estado = :estado and e.fresco = TRUE";
        
        return getEm().createQuery(jpql)
                .setParameter("estado", "P")
                .getResultList();
    }
    
}
