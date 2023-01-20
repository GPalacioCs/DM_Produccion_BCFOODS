/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransformacionActividadPK implements Serializable {
    private ClasificacionAdiValorPK clasificacionAdiValorPK;
    private String actividad;
}
