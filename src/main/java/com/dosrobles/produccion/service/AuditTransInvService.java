/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.AuditTransInvDAO;
import com.dosrobles.produccion.entities.AuditTransInv;

import javax.ejb.Stateless;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class AuditTransInvService extends AbstractService<AuditTransInvDAO, AuditTransInv> {
    public AuditTransInv getAuditTransInvByAplicacion(String aplicacion) {
        return dao.getAuditTransInvByAplicacion(aplicacion);
    }    
}
