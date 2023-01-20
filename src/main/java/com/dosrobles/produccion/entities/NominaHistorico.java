/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="NOMINA_HISTORICO",schema="BCFOODS")
@Data
@EqualsAndHashCode(of="id")
public class NominaHistorico {
    @EmbeddedId
    private NominaHistoricoPK id = new NominaHistoricoPK();
    @MapsId("nomina")
    @JoinColumn(name="NOMINA", referencedColumnName = "NOMINA")
    @ManyToOne
    private Nomina nomina;
    @Column(name="FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name="FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name="PERIODO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodo;
    @Column(name="FECHA_APLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;    
    
}
