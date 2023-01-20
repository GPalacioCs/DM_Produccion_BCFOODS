/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
@Table(name="actividades_prod", schema="BCFOODS")
@Data
@EqualsAndHashCode(of="codigo")
public class ActividadProd implements Serializable {
    @Id
    private String codigo;
    private String nombre;
    private String descripcion;
    @Column(name="COSTO_LOCAL")
    private BigDecimal costoLocal;
    @Column(name="COSTO_DOLAR")
    private BigDecimal costoDolar;
}
