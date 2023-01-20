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
@NoArgsConstructor
@AllArgsConstructor
public class IngresosLotePK {
    private LotePK lotePK;
    @Column(name="SECUENCIA_LOTE")
    private Integer secuenciaLote = 1;
}
