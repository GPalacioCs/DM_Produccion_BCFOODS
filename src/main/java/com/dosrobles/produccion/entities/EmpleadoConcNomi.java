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
import java.util.Date;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="EMPLEADO_CONC_NOMI", schema="BCFOODS")
@Data
@EqualsAndHashCode(of="consecutivo")
@NoArgsConstructor
public class EmpleadoConcNomi {
    @Id
    private Integer consecutivo;
    @JoinColumn(name="EMPLEADO", referencedColumnName = "EMPLEADO")
    @ManyToOne
    private Empleado empleado;
    private String concepto;
    @JoinColumns({
        @JoinColumn(name="NOMINA", referencedColumnName = "NOMINA"),
        @JoinColumn(name="NUMERO_NOMINA", referencedColumnName = "NUMERO_NOMINA"),
    })
    @ManyToOne
    private NominaHistorico nominaHistorico;
    @Column(name="CENTRO_COSTO")
    private String centroCosto;
    @Column(name="FORMA_APLICACION")
    private String formaAplicacion;
    @Column(name="CANTIDAD")
    private BigDecimal cantidad;
    @Column(name="MONTO")
    private String monto;
    private String total;
    @Column(name="FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    private String usuario;
    
    
     
}
