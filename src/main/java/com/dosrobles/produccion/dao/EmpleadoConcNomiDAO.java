/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.EmpleadoConcNomi;
import com.dosrobles.produccion.entities.NominaHistorico;

import javax.ejb.Stateless;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author Administrador
 */
@Stateless
public class EmpleadoConcNomiDAO extends AbstractDAO<EmpleadoConcNomi> {
    
    public EmpleadoConcNomiDAO() {
        super(EmpleadoConcNomi.class);
    }
    
    public List<EmpleadoConcNomi> getEmpleadoConcNomi(String concepto, Collection<NominaHistorico> nominaHistoricoCol) {
        
        
        StringJoiner joiner = new StringJoiner(" OR ", "(", ")");
        for (NominaHistorico nh : nominaHistoricoCol) {
            joiner.add("ecn.nominaHistorico.id.consecutivo = "+nh.getId().getConsecutivo()+" and ecn.nominaHistorico.nomina.nomina = '"+nh.getNomina().getNomina()+"'");
        }
        
        String jpql = "select ecn from EmpleadoConcNomi ecn where ecn.concepto = :concepto and "+joiner;
        return getEm().createQuery(jpql, EmpleadoConcNomi.class)                
                .setParameter("concepto", concepto)                
                .getResultList();
    }
    
}
