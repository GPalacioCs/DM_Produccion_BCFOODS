/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
@Table(name="EXISTENCIA_LOTE")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ExistenciaLote {
    @EmbeddedId
    private ExistenciaLotePK id = new ExistenciaLotePK();
    @Column(name="CANT_DISPONIBLE")
    private BigDecimal cantDisponible = BigDecimal.ZERO;
    @Column(name="CANT_RESERVADA")
    private BigDecimal cantReservada = BigDecimal.ZERO;
    @Column(name="CANT_NO_APROBADA")
    private BigDecimal cantNoAprobada = BigDecimal.ZERO;
    @Column(name="CANT_VENCIDA")
    private BigDecimal cantVencida = BigDecimal.ZERO;
    @Column(name="CANT_REMITIDA")
    private BigDecimal cantRemitida = BigDecimal.ZERO;
    @Column(name="COSTO_UNT_PROMEDIO_LOC")
    private BigDecimal cantUntPromedioLoc = BigDecimal.ZERO;
    @Column(name="COSTO_UNT_PROMEDIO_DOL")
    private BigDecimal costoUntPromedioDol = BigDecimal.ZERO;
    @Column(name="COSTO_UNT_ESTANDAR_LOC")
    private BigDecimal costoUntEstandarLoc = BigDecimal.ZERO;
    @Column(name="COSTO_UNT_ESTANDAR_DOL")
    private BigDecimal costoUntEstandarDol = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="LOTE", referencedColumnName = "LOTE", insertable = false, updatable = false),
        @JoinColumn(name="ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    })
    private Lote lote;
    @ManyToOne
    @JoinColumn(name="ARTICULO", insertable = false, updatable = false)
    private Articulo articulo;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false),
        @JoinColumn(name="BODEGA", referencedColumnName = "BODEGA", insertable = false, updatable = false)
    })
    private ExistenciaBodega existenciaBodega;

    public ExistenciaLote(ExistenciaLotePK id) {
        this.id = id;
    }
}
