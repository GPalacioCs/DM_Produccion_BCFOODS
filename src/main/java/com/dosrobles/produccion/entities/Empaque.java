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
@Table(name="CS_EMPAQUE_BCFOODS")
@Data
@EqualsAndHashCode(of="id")
public class Empaque {
    @EmbeddedId
    private EmpaquePK id = new EmpaquePK();
    @ManyToOne
    @JoinColumn(name="CLIENTE", insertable = false, updatable = false)
    private Cliente cliente;
    @MapsId("articulo")
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @MapsId("empaqueEncabezadoPK")
    @ManyToOne
    @JoinColumns ({
        @JoinColumn(name="ORDEN_PRODUCCION", referencedColumnName = "ORDEN_PRODUCCION"),
        @JoinColumn(name="CLIENTE", referencedColumnName = "CLIENTE")
    })
    private EmpaqueEncabezado empaqueEncabezado;    
    private String talla = "ND";
    @Column(name="PESO_NETO")
    private BigDecimal pesoNeto;
    @Column(name="PESO_INYECTADO")
    private BigDecimal pesoInyectado;
    @Column(name="PESO_BRUTO")
    private BigDecimal pesoBruto;
    private BigDecimal pescados;
    private String empacador = "00";
    private String pedido;
    private BigDecimal cajas = BigDecimal.ZERO;
    private boolean impreso;
    
    public Empaque() {        
    }
    
    public Empaque(EmpaquePK id) {
        this.id = id;
    }
    
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
