/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Impuesto;

import javax.ejb.Stateless;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ImpuestoDAO extends AbstractDAO<Impuesto>{

    public ImpuestoDAO() {
        super(Impuesto.class);
    }
    
}
