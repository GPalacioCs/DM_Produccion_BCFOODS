/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.EstrucProcRubro;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucProcRubroDAO extends AbstractDAO<EstrucProcRubro> {
    
    public EstrucProcRubroDAO() {
        super(EstrucProcRubro.class);
    }
    
    public List<EstrucProcRubro> getEstrucProcRubroList(String articulo, String version)       
    {
        return this.getEm().createQuery("select e from EstrucProcRubro e WHERE e.estrucProcRubroPK.articulo=:articulo and e.estrucProcRubroPK.version = :version",EstrucProcRubro.class)
                .setParameter("articulo", articulo)
                .setParameter("version", version)
                .getResultList();
    
    }
    
}
