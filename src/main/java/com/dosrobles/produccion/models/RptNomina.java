/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RptNomina {
    private String empleado;
    private String concepto;
    private BigDecimal monto = BigDecimal.ZERO;
}
