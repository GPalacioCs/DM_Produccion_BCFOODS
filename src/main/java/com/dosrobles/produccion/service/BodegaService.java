/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.BodegaDAO;
import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
@Stateless
public class BodegaService extends AbstractService<BodegaDAO, Bodega> {

    @Override
    public Bodega insert(Bodega entity) throws BusinessValidationException {
        if(find(entity.getBodega()) != null) {
            throw new BusinessValidationException("Ya existe una bodega con el mismo código");
        }
        
        return dao.save(entity);
    }
    
    

    @Override
    public Bodega save(Bodega entity) throws BusinessValidationException {
        return dao.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Bodega entity) throws BusinessValidationException {
        dao.delete(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bodega> findAll() {
        return super.findAll().stream().sorted((b1,b2) ->b1.getBodega().compareTo(b2.getBodega()))
                .collect(Collectors.toList());
    }
    
    
    public List<Bodega> getBodegasPorArticulo(Articulo articulo) {
        return dao.getBodegasPorArticulo(articulo);
    }
    
}
