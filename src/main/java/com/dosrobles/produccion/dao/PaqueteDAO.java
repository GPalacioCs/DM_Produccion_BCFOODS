/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Paquete;

import javax.ejb.Stateless;

/**
 *
 * @author pc
 */
@Stateless
public class PaqueteDAO extends AbstractDAO<Paquete>{

    public PaqueteDAO() {
        super(Paquete.class);
    }
    
}
