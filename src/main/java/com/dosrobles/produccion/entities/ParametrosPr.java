/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CS_PARAMETROS_PR")
@NamedQueries({
    @NamedQuery(name = "ParametrosPr.findAll", query = "SELECT c FROM ParametrosPr c")})
@Data
@EqualsAndHashCode(of="idParametro")
public class ParametrosPr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO")
    private Integer idParametro = 1;
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "BODEGA_MP")
    private Bodega bodegaMp;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "BODEGA_PT")
    private Bodega bodegaPt;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "BODEGA_MATERIALES_DIRECTOS")
    private Bodega bodegaMd;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "BODEGA_EXPORTACION")
    private Bodega bodegaExportacion;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "BODEGA_INVENTARIO")
    private Bodega bodegaInventario;
    @ManyToOne
    @JoinColumn(name="CLASIFICACION_PR")
    private Clasificacion clasificacionPr;
    @ManyToOne
    @JoinColumn(name="CLASIFICACION_MATERIA_PRIMA")
    private Clasificacion clasificacionMateriaPrima;
    @ManyToOne
    @JoinColumn(name="CLASIFICACION_MATERIALES_DIRECTOS")
    private Clasificacion clasificacionMaterialesDirectos;    
    @ManyToOne
    @JoinColumn(name="PAQUETE")
    private Paquete paquete;  
    @Column(name="CUENTA_CIF")
    private String cuentaCif;
    @Column(name="CENTRO_BCF")
    private String centroCif;
    @Column(name="CUENTA_CIF_DEBITO")
    private String cuentaCifDebito;
    @Column(name="CENTRO_BCF_DEBITO")
    private String centroCifDebito;
    @Column(name="CONSUMO_ESPECIFICO")
    private String consumoEspecifico;
    @ManyToOne
    @JoinColumn(name="CONSECUTIVO_TRASPASOS")
    private ConsecutivoCi consecutivoTraspasos;

    public ParametrosPr() {
    }

    public ParametrosPr(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public ParametrosPr(Integer idParametro, Bodega bodegaMp, Bodega bodegaPt) {
        this.idParametro = idParametro;
        this.bodegaMp = bodegaMp;
        this.bodegaPt = bodegaPt;
    }
    
    public String getConceptoProduccion() {
        return "BN018";
    }
    
    public Bodega getBodegaMp() {
        return this.bodegaMp;
    }
    
    public Bodega getBodegaMp(boolean fresco) {
        if(fresco) return bodegaExportacion;
        return bodegaMp;
    }

}
