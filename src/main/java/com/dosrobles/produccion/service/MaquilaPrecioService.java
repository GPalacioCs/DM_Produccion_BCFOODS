/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.MaquilaPrecioDAO;
import com.dosrobles.produccion.entities.ClasificacionAdi;
import com.dosrobles.produccion.entities.ClasificacionAdiValor;
import com.dosrobles.produccion.entities.MaquilaPrecio;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Zeus
 */
@Stateless
public class MaquilaPrecioService extends AbstractService<MaquilaPrecioDAO, MaquilaPrecio> {
    
    @Inject
    private ClasificacionAdiValorService clasificacionAdiValorService;
    
    public MaquilaPrecio findByClasificacion(ClasificacionAdi clasificacion) {
        return dao.findByClasificacion(clasificacion);
    }
    
    public MaquilaPrecio findByValor(ClasificacionAdiValor valor) {
        return dao.findByValor(valor);
    }

    @Override
    public MaquilaPrecio insert(MaquilaPrecio entity) throws BusinessValidationException {
        if(findByValor(entity.getValor()) != null) {
            throw new BusinessValidationException("La maquila ya existe para este valor");
        }        
        return super.insert(entity);
    }

    @Override
    public MaquilaPrecio save(MaquilaPrecio entity) throws BusinessValidationException {
        clasificacionAdiValorService.save(entity.getValor());
        
        return super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
