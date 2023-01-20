/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EstrucProcesoDAO;
import com.dosrobles.produccion.entities.EstrucProceso;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucProcesoService extends AbstractService<EstrucProcesoDAO,EstrucProceso> {
    
    public List<EstrucProceso> getEstrucProcesoList(String articulo,String version)
    {
        return this.dao.getEstrucProcesoList(articulo, version);
    }
}
