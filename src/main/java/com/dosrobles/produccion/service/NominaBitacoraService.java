/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.NominaBitacoraDAO;
import com.dosrobles.produccion.entities.Concepto;
import com.dosrobles.produccion.entities.Empleado;
import com.dosrobles.produccion.entities.NominaBitacora;
import com.dosrobles.produccion.entities.NominaHistorico;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author pc
 */
@Stateless
public class NominaBitacoraService extends AbstractService<NominaBitacoraDAO, NominaBitacora> {
    
    public List<NominaBitacora> getNominaBitacoraList(Empleado empleado, Concepto concepto, NominaHistorico nominaHistorico) {
        return dao.getNominaBitacoraList(empleado, concepto, nominaHistorico);
    }
    
}
