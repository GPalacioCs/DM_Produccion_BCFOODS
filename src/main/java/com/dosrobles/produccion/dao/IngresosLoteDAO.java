/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.IngresosLote;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class IngresosLoteDAO extends AbstractDAO<IngresosLote> {

    public IngresosLoteDAO() {
        super(IngresosLote.class);
    }
    
}
