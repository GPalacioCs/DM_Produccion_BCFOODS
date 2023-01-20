/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ExistenciaBodegaDAO;
import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.ExistenciaBodega;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ExistenciaBodegaService extends AbstractService<ExistenciaBodegaDAO, ExistenciaBodega> {
    public List<ExistenciaBodega> getExistenciaBodegaList(Articulo articulo) {
        return dao.getExistenciaBodegaList(articulo);
    }
    
    public List<ExistenciaBodega> getExistenciaBodegaList(Bodega bodega) {
        return dao.getExistenciaBodegaList(bodega);
    }
}
