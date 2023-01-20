/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Nomina;
import com.dosrobles.produccion.entities.NominaHistorico;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Administrador
 */
@Stateless
public class NominaHistoricoDAO extends AbstractDAO<NominaHistorico> {

    public NominaHistoricoDAO() {
        super(NominaHistorico.class);
    }
    
    public NominaHistorico getNominaHistoricoActual(Nomina nomina) {
        String jpql = "select nh from NominaHistorico nh where nh.fechaAplicacion is null and nh.nomina = :nomina and nh.nomina.estado in ('I','C','M')";
        
        try {
            NominaHistorico nh = getEm().createQuery(jpql, NominaHistorico.class)
                    .setParameter("nomina", nomina)
                    .getSingleResult();
            return nh;
        } catch (NoResultException e) {
            throw new BusinessValidationException(String.format("La nomina %s no existe o no se encuentra Inicializado, calculada ni modificada", nomina.getNomina()));
        }                 
    }
}
