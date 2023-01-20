/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EstrucProcRubroDAO;
import com.dosrobles.produccion.entities.EstrucProcRubro;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucProcRubroService extends AbstractService<EstrucProcRubroDAO, EstrucProcRubro> {
    
    public List<EstrucProcRubro> getEstrucProcRubroList(String articulo, String version)
    {
        return this.dao.getEstrucProcRubroList(articulo, version);
    }
}
