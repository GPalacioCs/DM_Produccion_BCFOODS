/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NominaHistoricoPK {
    private String nomina;
    @Column(name="NUMERO_NOMINA")
    private Integer consecutivo;
}
