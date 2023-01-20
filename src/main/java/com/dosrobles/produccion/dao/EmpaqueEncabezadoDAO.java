/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.entities.EmpaqueEncabezado;
import com.dosrobles.produccion.entities.OrdenProduccion;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

/**
 *
 * @author pc
 */
@Stateless
public class EmpaqueEncabezadoDAO extends AbstractDAO<EmpaqueEncabezado> {

    public EmpaqueEncabezadoDAO() {
        super(EmpaqueEncabezado.class);
    }
    
    public List<EmpaqueEncabezado> findByOrdenProduccion(OrdenProduccion ordenProduccion) {
        return getEm().createQuery("select ee from EmpaqueEncabezado ee where ee.ordenProduccion = :orden")
                .setParameter("orden", ordenProduccion).getResultList();
    }
    
    public List<EmpaqueEncabezado> findByOrdenProduccionAndCliente(OrdenProduccion ordenProduccion, Cliente cliente) {
        return getEm().createQuery("select ee from EmpaqueEncabezado ee where ee.ordenProduccion = :orden and ee.cliente = :cliente")
                .setParameter("orden", ordenProduccion)
                .setParameter("cliente", cliente)
                .getResultList();
    }
    
    public Integer getMaxEmpaque() {
        String jpql = "select max(e.id.empaque) from empaque e";
        try {
            return getEm().createQuery(jpql, Integer.class).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }
    
}
