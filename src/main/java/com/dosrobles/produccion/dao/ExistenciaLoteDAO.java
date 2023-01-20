/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.ExistenciaBodega;
import com.dosrobles.produccion.entities.ExistenciaLote;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Stateless
public class ExistenciaLoteDAO extends AbstractDAO<ExistenciaLote> {
    
    public ExistenciaLoteDAO() {
        super(ExistenciaLote.class);
    }
    
    public List<ExistenciaLote> getExistenciaLoteList(String articulo, String bodega) {
        ExistenciaBodega eb = new ExistenciaBodega(articulo, bodega);
        String jpql = "select el from ExistenciaLote el where el.existenciaBodega = :existenciaBodega and el.cantDisponible > 0";
        List<ExistenciaLote> existenciaLoteList = getEm().createQuery(jpql)
                .setParameter("existenciaBodega", eb)
                .getResultList();
        return existenciaLoteList.stream().filter(el -> !el.getLote().isVencido())
                .sorted((el1, el2) -> el1.getLote().getFechaEntrada().compareTo(el2.getLote().getFechaEntrada()))
                .collect(Collectors.toList());
    }
    
}
