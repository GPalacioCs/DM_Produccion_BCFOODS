/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author DEV-PC
 */
@Embeddable
@Data
@AllArgsConstructor
public class OrdenProdEmpleadoPK implements Serializable {

    private Long ordenProduccion;    
    private String empleado;    
    private String actividad;

    public OrdenProdEmpleadoPK() {
    }

    

        
}
