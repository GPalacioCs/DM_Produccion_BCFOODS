/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

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
public class NominaBitacoraDAO extends AbstractDAO<NominaBitacora> {

    public NominaBitacoraDAO() {
        super(NominaBitacora.class);
    }
    
    
    public List<NominaBitacora> getNominaBitacoraList(Empleado empleado, Concepto concepto, NominaHistorico nominaHistorico) {
        String jpql="select b from NominaBitacora b where b.empleado = :empleado and b.concepto = :concepto and b.nominaHistorico = :nominaHistorico";
        return getEm().createQuery(jpql, NominaBitacora.class)
                .setParameter("empleado", empleado)
                .setParameter("concepto", concepto)
                .setParameter("nominaHistorico", nominaHistorico)
                .getResultList();
    }
}
