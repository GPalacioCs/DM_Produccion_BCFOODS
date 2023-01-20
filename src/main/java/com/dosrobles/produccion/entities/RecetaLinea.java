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
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CS_RECETA_LINEA")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RecetaLinea implements Serializable {
    @EmbeddedId
    private RecetaLineaPK id = new RecetaLineaPK(); 
    @MapsId("producto")
    @ManyToOne
    @JoinColumn(name="PRODUCTO", referencedColumnName = "ARTICULO")
    private Receta producto;    
    @MapsId("materiaPrima")
    @ManyToOne
    @JoinColumn(name="MATERIA_PRIMA", referencedColumnName = "ARTICULO")
    private Articulo materiaPrima;
    private BigDecimal cantidad;
    
    public RecetaLinea(RecetaLineaPK id) {
        this.id = id;
    }
    
    public BigDecimal getCostoLocal() {
        return materiaPrima.getCostoPromLoc();
    }
    
    public BigDecimal getCostoDolar() {
        return materiaPrima.getCostoPromDol();
    }
}
