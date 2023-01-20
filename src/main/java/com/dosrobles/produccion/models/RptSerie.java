/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.models;

import lombok.Data;

/**
 *
 * @author Administrador
 */
@Data
public class RptSerie {
    private String serie;
    private String articulo;
    private String descArticulo;
    private String aliasArticulo;
    private String netWeight;
    private String lot;
    private String fechaVencimiento;
    private Long ordenProduccion;
    private String nutritionFacts;
}
