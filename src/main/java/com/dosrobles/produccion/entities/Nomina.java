/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="NOMINA", schema = "ALINSA")
@Data
@EqualsAndHashCode(of="nomina")
@NoArgsConstructor
public class Nomina {
    @Id
    private String nomina;
    private String descripcion; 
    private Integer consecutivo;
    private String estado;

    public Nomina(String nomina) {
        this.nomina = nomina;
    }
    
    
}
