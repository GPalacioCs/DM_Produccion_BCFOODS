/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.UnidadDeMedida;

import javax.ejb.Stateless;

/**
 *
 * @author abdallah
 */
@Stateless
public class UnidadDeMedidaDAO extends AbstractDAO<UnidadDeMedida> {

    public UnidadDeMedidaDAO() {
        super(UnidadDeMedida.class);
    }    
}
