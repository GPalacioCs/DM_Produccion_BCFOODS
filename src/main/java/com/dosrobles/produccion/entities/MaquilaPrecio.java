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

/**
 *
 * @author Zeus
 */
@Entity
@Table(name="CS_MAQUILA_PRECIO")
@Data
@EqualsAndHashCode(of = "id")
public class MaquilaPrecio {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_MAQUILA")
    private Long id;
    @OneToOne
    @JoinColumns({
        @JoinColumn(name="CLASIFICACION", referencedColumnName = "CLASIFICACION"),
        @JoinColumn(name="VALOR", referencedColumnName = "VALOR"),
    })
    
    private ClasificacionAdiValor valor;
    private BigDecimal precio;    
}
