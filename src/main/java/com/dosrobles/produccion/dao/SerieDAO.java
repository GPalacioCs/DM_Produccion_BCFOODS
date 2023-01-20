/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Serie;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class SerieDAO extends AbstractDAO<Serie> {

    public SerieDAO() {
        super(Serie.class);
    }

    public Serie findByEmpaque(Serie serie){
        String jpql = "SELECT s FROM Serie s where s.articulo = :articulo and s.origen = :origen and s.fecha = :fecha and s.bodega = :bodega and s.lbs = :lbs and s.estado = :estado" +
                " and s.esFresco = :fresco and s.pesoNeto = :pesoNeto and s.pesoReal = :pesoReal and s.noCaja = :noCaja and s.noPeces = :noPeces and s.cliente = :cliente";
        return getEm().createQuery(jpql, Serie.class)
                .setParameter("articulo", serie.getArticulo())
                .setParameter("origen", serie.getOrigen())
                .setParameter("fecha", serie.getFecha())
                .setParameter("bodega", serie.getBodega())
                .setParameter("lbs", serie.getLbs())
                .setParameter("estado", serie.getEstado())
                .setParameter("fresco", serie.getEsFresco())
                .setParameter("pesoNeto", serie.getPesoNeto())
                .setParameter("pesoReal", serie.getPesoReal())
                .setParameter("noCaja", serie.getNoCaja())
                .setParameter("noPeces", serie.getNoPeces())
                .setParameter("cliente", serie.getCliente())
                .getSingleResult();
    }
    
}
