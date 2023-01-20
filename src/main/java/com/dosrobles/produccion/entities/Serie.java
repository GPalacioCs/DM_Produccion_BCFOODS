/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="CS_SERIE")
@Data
public class Serie {
    @Id
    private String serie;
    private String origen;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String articulo;
    private String bodega;
    private Integer cajas;
    private BigDecimal lbs;
    private String estado;
    @Column(name="ES_FRESCO")
    private String esFresco;
    @Column(name="PESONETO")
    private BigDecimal pesoNeto;
    private BigDecimal pesoReal;
    private Integer noCaja;
    private Integer noPeces;
    private String cliente;
}
