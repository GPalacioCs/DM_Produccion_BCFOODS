/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.ClasificacionAdi;
import com.dosrobles.produccion.entities.ClasificacionAdiValor;
import com.dosrobles.produccion.entities.MaquilaPrecio;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Zeus
 */
@Stateless
public class MaquilaPrecioDAO extends AbstractDAO<MaquilaPrecio>{

    public MaquilaPrecioDAO() {
        super(MaquilaPrecio.class);
    }
    
    public MaquilaPrecio findByClasificacion(ClasificacionAdi clasificacion) {
        String jpql = "select m from MaquilaPrecio m where m.clasificacion = :clasificacion";
        
        try {
            return getEm().createQuery(jpql, MaquilaPrecio.class).setParameter("clasificacion", clasificacion)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public MaquilaPrecio findByValor(ClasificacionAdiValor valor) {
        String jpql = "select mp from MaquilaPrecio mp where mp.valor = :valor";
        try {
            return getEm().createQuery(jpql, MaquilaPrecio.class)
                    .setParameter("valor", valor)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
