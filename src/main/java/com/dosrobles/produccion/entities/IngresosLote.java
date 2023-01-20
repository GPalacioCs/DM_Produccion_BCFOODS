/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="INGRESOS_LOTE")
@Data
@EqualsAndHashCode(of="id")
public class IngresosLote {
    @EmbeddedId
    private IngresosLotePK id = new IngresosLotePK();
    @MapsId("lotePK")
    @JoinColumns({
        @JoinColumn(name="ARTICULO", referencedColumnName = "ARTICULO"),
        @JoinColumn(name="LOTE", referencedColumnName = "LOTE")
    })
    @ManyToOne
    private Lote lote;
    @Column(name="CANTIDAD_INGRESADA")
    private BigDecimal cantidadIngresada;
    @Column(name="FECHA_ENTRADA")
    private Date fechaEntrada;
}
