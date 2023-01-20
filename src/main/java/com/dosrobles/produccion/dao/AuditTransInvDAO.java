/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.AuditTransInv;

import javax.ejb.Stateless;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class AuditTransInvDAO extends AbstractDAO<AuditTransInv> {

    public AuditTransInvDAO() {
        super(AuditTransInv.class);
    }


    public AuditTransInv getAuditTransInvByAplicacion(String aplicacion) {
        return getEm().createQuery("select a FROM AuditTransInv a where a.aplicacion = :aplicacion", AuditTransInv.class)
                .setParameter("aplicacion", aplicacion)
                .getSingleResult();
    }
}
