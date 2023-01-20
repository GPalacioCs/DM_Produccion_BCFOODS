/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ConsecutivoDAO;
import com.dosrobles.produccion.entities.Consecutivo;

import javax.ejb.Stateless;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ConsecutivoService extends AbstractService<ConsecutivoDAO, Consecutivo> {
    public BigInteger getUltimoValor(String consec) {
        return dao.getUltimoValor(consec);
    }
    
    public List<Consecutivo> getConsecutivoList(String entidad) {
        return dao.getConsecutivoList(entidad);
    }
}
