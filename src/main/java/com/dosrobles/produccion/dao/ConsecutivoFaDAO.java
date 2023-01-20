/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.corpsoft.csvtigersoftland.entities.ConsecutivoFa;

import javax.ejb.Stateless;

/**
 *
 * @author pc
 */
@Stateless
public class ConsecutivoFaDAO extends AbstractDAO<ConsecutivoFa> {

    public ConsecutivoFaDAO() {
        super(ConsecutivoFa.class);
    }
    
}
