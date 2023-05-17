/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "ORDEN_PROD_EMPLEADO", schema="ALINSA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenProdEmpleado.findAll", query = "SELECT o FROM OrdenProdEmpleado o")
    , @NamedQuery(name = "OrdenProdEmpleado.findByOrdenProduccion", query = "SELECT o FROM OrdenProdEmpleado o WHERE o.ordenProdEmpleadoPK.ordenProduccion = :ordenProduccion")
    , @NamedQuery(name = "OrdenProdEmpleado.findByEmpleado", query = "SELECT o FROM OrdenProdEmpleado o WHERE o.ordenProdEmpleadoPK.empleado = :empleado")
    , @NamedQuery(name = "OrdenProdEmpleado.findByRubro", query = "SELECT o FROM OrdenProdEmpleado o WHERE o.rubro = :rubro")
    , @NamedQuery(name = "OrdenProdEmpleado.findByCosto", query = "SELECT o FROM OrdenProdEmpleado o WHERE o.costo = :costo")})
@Data
@EqualsAndHashCode(of="ordenProdEmpleadoPK")
public class OrdenProdEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdenProdEmpleadoPK ordenProdEmpleadoPK = new OrdenProdEmpleadoPK();
    @Size(max = 20)
    @Column(name = "RUBRO")
    private String rubro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private BigDecimal costo = BigDecimal.ZERO;
    @Column(name = "COSTO_DOLAR")
    private BigDecimal costoDolar = BigDecimal.ZERO;
    @MapsId("empleado")
    @ManyToOne
    @JoinColumn(name = "EMPLEADO")
    private Empleado empleado;
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @MapsId("actividad")
    @ManyToOne
    @JoinColumn(name="ACTIVIDAD", referencedColumnName = "codigo")
    private ActividadProd actividad;
    
    public OrdenProdEmpleado() {
    }

    public OrdenProdEmpleado(OrdenProdEmpleadoPK ordenProdEmpleadoPK) {
        this.ordenProdEmpleadoPK = ordenProdEmpleadoPK;
    }

    public OrdenProdEmpleado(Long ordenProduccion, String empleado, String actividad) {
        this.ordenProdEmpleadoPK = new OrdenProdEmpleadoPK(ordenProduccion, empleado, actividad);
    }


    public BigDecimal getCostoActividadEmpleado() {
        try {
            OrdenProduccion op = getOrdenProduccion();
            OrdenProduccionActividad ordenProduccionActividad = op.getOrdenProduccionActividadList().stream()
                    .filter(opa -> Objects.equals(opa.getActividad().getCodigo(), this.actividad.getCodigo()))
                    .findFirst().get();

            long countEmpleadosActividad = op.getEmpleadoList().stream()
                    .filter(emp -> emp.getActividad().getCodigo().equals(this.actividad.getCodigo())).count();

            BigDecimal costo = ordenProduccionActividad.getLibras().multiply(this.actividad.getCostoLocal()).divide(BigDecimal.valueOf(countEmpleadosActividad), 2, RoundingMode.HALF_EVEN);
            return costo;
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return BigDecimal.ZERO;
        }
        
    }


}
