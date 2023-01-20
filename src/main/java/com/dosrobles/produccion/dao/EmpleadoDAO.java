/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Empleado;

import javax.ejb.Stateless;
import java.util.List;


/**
 *
 * @author DEV-PC
 */
@Stateless
public class EmpleadoDAO  extends AbstractDAO<Empleado> {
        public EmpleadoDAO() {
        super(Empleado.class);
    }
        
    public List<Empleado> getEmpleadoList(List<String> codEmpleadoList) {
        return getEm().createQuery("select e from Empleado e where e.empleado in :empleadoList", Empleado.class)
                .setParameter("empleadoList",codEmpleadoList)
                .getResultList();
    }
}
