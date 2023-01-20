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
import java.io.Serializable;

/**
 *
 * @author pc
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngresoProduccionPK implements Serializable {
    @Column(name="ORDEN_PRODUCCION")
    private Long ordenProduccion;
    @Column(name="ARTICULO")
    private String articulo;
}
