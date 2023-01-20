/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.ClasificacionAdi;

import javax.ejb.Stateless;

/**
 *
 * @author Zeus
 */
@Stateless
public class ClasificacionAdiDAO extends AbstractDAO<ClasificacionAdi> {

    public ClasificacionAdiDAO() {
        super(ClasificacionAdi.class);
    }
    
}
