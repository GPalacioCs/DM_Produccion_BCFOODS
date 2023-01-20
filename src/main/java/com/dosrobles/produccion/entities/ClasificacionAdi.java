/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zeus
 */
@Entity
@Table(name="CLASIFICACION_ADI")
@Data
@EqualsAndHashCode(of="id")
public class ClasificacionAdi {
    @Id
    @Column(name="CLASIFICACION")
    private String id;
    private String descripcion;
    private Integer posicion;
//    @OneToOne(mappedBy = "clasificacion")
//    private MaquilaPrecio maquilaPrecio;
    @OneToMany(mappedBy = "clasificacion")
    private List<ClasificacionAdiValor> valorList = new ArrayList<>();    
}
