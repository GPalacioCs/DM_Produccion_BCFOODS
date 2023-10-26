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
 * @author pc
 */
@Entity
@Table(name="CS_NOMINA_BITACORA", schema = "ALINSA")
@Data
@EqualsAndHashCode(of="id")
public class NominaBitacora {
    @EmbeddedId
    private NominaBitacoraPK id;
    @MapsId("empleado")    
    @JoinColumn(name="EMPLEADO", referencedColumnName = "EMPLEADO")    
    @ManyToOne
    private Empleado empleado;
    @MapsId("concepto")
    @JoinColumn(name="CONCEPTO")
    @ManyToOne
    private Concepto concepto;
    @MapsId("nominaHistoricoPK")
    @JoinColumns({
        @JoinColumn(name="NOMINA", referencedColumnName = "NOMINA"),
        @JoinColumn(name="NUMERO_NOMINA", referencedColumnName = "NUMERO_NOMINA"),
    })
    @ManyToOne
    private NominaHistorico nominaHistorico;
    private BigDecimal monto;
    
    public static NominaBitacora of(Empleado empleado, Concepto concepto, NominaHistorico nominaHistorico, BigDecimal monto) {
        NominaBitacora nb = new NominaBitacora();
        nb.setEmpleado(empleado);
        nb.setConcepto(concepto);
        nb.setNominaHistorico(nominaHistorico);
        nb.setMonto(monto);
        return nb;
    }
}
