/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CLASIFICACION_ADI_VALOR")
@Data
@EqualsAndHashCode(of="id")
public class ClasificacionAdiValor implements Serializable {
    @EmbeddedId
    private ClasificacionAdiValorPK id = new ClasificacionAdiValorPK();
    private String descripcion;
    @MapsId("clasificacion")
    @ManyToOne
    @JoinColumn(name="CLASIFICACION")
    private ClasificacionAdi clasificacion; 
    @OneToOne(mappedBy="valor")
    private MaquilaPrecio maquilaPrecio;
    @OneToMany(mappedBy = "clasificacionAdiValor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransformacionActividad> transformacionActividadList = new ArrayList<>();
}
