/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.SerieDAO;
import com.dosrobles.produccion.entities.Serie;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class SerieService extends AbstractService<SerieDAO, Serie> {
    public Serie getSerie(Serie serie) {
        return dao.findByEmpaque(serie);
    }
}
