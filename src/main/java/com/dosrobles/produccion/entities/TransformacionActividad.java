/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="CS_TRANSFORMACION_ACTIVIDAD")
@Data
public class TransformacionActividad implements Serializable {
    @EmbeddedId
    private TransformacionActividadPK id = new TransformacionActividadPK();
    @MapsId("clasificacionAdiValorPK")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="CLASIFICACION", referencedColumnName = "CLASIFICACION"),
        @JoinColumn(name="VALOR", referencedColumnName = "VALOR")
    })    
    private ClasificacionAdiValor clasificacionAdiValor;
    @MapsId("actividad")
    @ManyToOne
    @JoinColumn(name="ACTIVIDAD", referencedColumnName = "codigo")
    private ActividadProd actividad;        
}
