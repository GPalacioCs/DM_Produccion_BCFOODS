/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CLASIFIC_ADI_ARTICULO")
@Data
@EqualsAndHashCode(of={"articulo","clasificacion"})
public class ClasificAdiArticulo {
    @EmbeddedId
    private ClasificAdiArticuloPK id = new ClasificAdiArticuloPK();
    @MapsId("articulo")
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @MapsId("clasificacion")
    @ManyToOne
    @JoinColumn(name="CLASIFICACION")
    private ClasificacionAdi clasificacion;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="VALOR", referencedColumnName = "VALOR"),
        @JoinColumn(name="CLASIFICACION", referencedColumnName = "CLASIFICACION", insertable = false, updatable = false),
    })
    
    private ClasificacionAdiValor valor;
    
}
