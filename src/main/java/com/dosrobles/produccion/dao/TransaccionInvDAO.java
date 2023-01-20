/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.AuditTransInv;
import com.dosrobles.produccion.entities.TransaccionInv;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class TransaccionInvDAO extends AbstractDAO<TransaccionInv> {

    public TransaccionInvDAO() {
        super(TransaccionInv.class);
    }
    
    public List<TransaccionInv> findByAudit(AuditTransInv audit) {
        String jpql = "select trans from TransaccionInv trans where trans.transaccionInvPK.auditTransInv = :audit";
        return getEm().createQuery(jpql).setParameter("audit", audit.getAuditTransInv()).getResultList();
    }
    
}
