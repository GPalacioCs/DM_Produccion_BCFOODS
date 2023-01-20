/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ClasificacionDAO;
import com.dosrobles.produccion.entities.Clasificacion;
import com.dosrobles.produccion.entities.GlobalesCi;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author abdallah
 */
@Stateless
public class ClasificacionService extends AbstractService<ClasificacionDAO, Clasificacion> {

    @Override
    public Clasificacion insert(Clasificacion clsf) throws BusinessValidationException {
        if(find(clsf.getClasificacion()) != null) {
            throw new BusinessValidationException("El código de la clasificación ya se encuentra en uso.");
        }        
        return dao.save(clsf);
    } 
    
    public String getTipoClasificacion(short agrupacion, GlobalesCi globalesCi) throws BusinessValidationException {
        switch (agrupacion) {
            case 1 : return globalesCi.getNombreClasif1();
            case 2 : return globalesCi.getNombreClasif2();
            case 3 : return globalesCi.getNombreClasif3();
            case 4 : return globalesCi.getNombreClasif4();
            case 5 : return globalesCi.getNombreClasif5();
            case 6 : return globalesCi.getNombreClasif6();
            default: return null;
        }        
    }
    
    public List<Clasificacion> getClasificacionesPorAgrupacion(short agrupacion) {
        return dao.getClasificacionesPorAgrupacion(agrupacion);
    }
}
