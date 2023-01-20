/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ImpuestoDAO;
import com.dosrobles.produccion.entities.Impuesto;

import javax.ejb.Stateless;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class ImpuestoService extends AbstractService<ImpuestoDAO, Impuesto> {
    
//    @Inject
//    private CentroCuentaService centroCuentaService;
//
//    @Override
//    public Impuesto insert(Impuesto entity) throws BusinessValidationException {
//        return save(entity);
//    }
//
//    @Override
//    public Impuesto save(Impuesto entity) throws BusinessValidationException {
//        
//        centroCuentaService.validarCentroCuenta(entity.getCtrImp1GenVts(), entity.getCtaImp1GenVts());        
//        centroCuentaService.validarCentroCuenta(entity.getCtrImp1DescCmps(), entity.getCtaImp1DescCmps());
//        return dao.save(entity);
//    }
    
    
    
}
