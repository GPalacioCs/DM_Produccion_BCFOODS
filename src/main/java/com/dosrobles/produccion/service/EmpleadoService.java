/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EmpleadoDAO;
import com.dosrobles.produccion.entities.Empleado;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EmpleadoService extends AbstractService <EmpleadoDAO,Empleado> {
    
    public List<Empleado> getEmpleadoList(List<String> codEmpleadoList) {
        return dao.getEmpleadoList(codEmpleadoList);
    }
}
