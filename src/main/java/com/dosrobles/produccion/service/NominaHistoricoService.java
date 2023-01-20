/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.NominaHistoricoDAO;
import com.dosrobles.produccion.entities.Nomina;
import com.dosrobles.produccion.entities.NominaHistorico;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class NominaHistoricoService extends AbstractService<NominaHistoricoDAO, NominaHistorico> {
    
    public NominaHistorico getNominaHistoricoActual(Nomina nomina) {
        return dao.getNominaHistoricoActual(nomina);
    }
}
