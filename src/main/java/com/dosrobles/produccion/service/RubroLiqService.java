/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.RubroLiqDAO;
import com.dosrobles.produccion.entities.RubroLiq;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author pc
 */
@Stateless
public class RubroLiqService extends AbstractService<RubroLiqDAO, RubroLiq> {
    
    public List<RubroLiq> findByCodigos(List<String> codigos) {
        return dao.findByCodigos(codigos);
    }    
}
