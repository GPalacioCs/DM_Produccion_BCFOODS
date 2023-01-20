/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Consecutivo;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author KC
 */

@Stateless
public class ConsecutivoDAO extends AbstractDAO<Consecutivo> {

public ConsecutivoDAO() { super(Consecutivo.class); }

    
    public BigInteger getUltimoValor(String consec) {
        try {
            String jpql = "select c.ultimoValor from Consecutivo c where c.consecutivo = :consec";
            
            String ultimo = getEm().createQuery(jpql, String.class)
                    .setParameter("consec", consec)
                    .getSingleResult();
            BigInteger result = new BigInteger(ultimo == null ? "0" : ultimo.trim());
            return result.add(BigInteger.ONE);
        } catch (NoResultException e) {
            return BigInteger.ONE;
        }
    }
    
    public List<Consecutivo> getConsecutivoList(String entidad) {
        String jpql = "select c from Consecutivo c where c.entidad = :entidad and c.activo = 'S'";
        
        return getEm().createQuery(jpql, Consecutivo.class)
                .setParameter("entidad", entidad)
                .getResultList();
    }
}
