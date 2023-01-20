/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CONCEPTO", schema = "BCFOODS")
@Data
@EqualsAndHashCode(of = "concepto")
public class Concepto {
    @Id
    private String concepto;
    private String descripcion;       

    public Concepto() {
    }
    
    

    public Concepto(String concepto) {
        this.concepto = concepto;
    }
}
