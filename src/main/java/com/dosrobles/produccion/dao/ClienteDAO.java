/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Cliente;

import javax.ejb.Stateless;

/**
 *
 * @author pc
 */
@Stateless
public class ClienteDAO extends AbstractDAO<Cliente> {
    
    public ClienteDAO() {
        super(Cliente.class);
    }
    
}
