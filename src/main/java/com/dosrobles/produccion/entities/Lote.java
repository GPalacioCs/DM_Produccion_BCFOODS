/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "LOTE")
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l")})
@Data
@EqualsAndHashCode(of="lotePK")
public class Lote implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LotePK lotePK;
    @Column(name = "LOTE_DEL_PROVEEDOR")
    private String loteDelProveedor = "ND";
    @Basic(optional = false)
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada = Utils.getDate(1980, 1, 1);;
    @Basic(optional = false)
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento = Utils.getDate(1980, 1, 1);
    @Basic(optional = false)
    @Column(name = "FECHA_CUARENTENA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCuarentena = Utils.getDate(1980, 1, 1);
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTIDAD_INGRESADA")
    private BigDecimal cantidadIngresada = BigDecimal.ZERO;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado = "A";
    @Basic(optional = false)
    @Column(name = "TIPO_INGRESO")
    private String tipoIngreso = "t";
    @Lob
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @Column(name = "ULTIMO_INGRESO")
    private int ultimoIngreso = 1;
    @Basic(optional = false)
    @Column(name = "ARTICULO", updatable = false, insertable = false)
    private String articulo1;
    @ManyToOne
    @JoinColumn(name = "ARTICULO", insertable = false, updatable = false)
    private Articulo articulo;
    @Column(name = "PROVEEDOR", updatable = false, insertable = false)
    private String proveedor;
    @OneToMany(mappedBy = "lote")
    private List<ExistenciaLote> existenciaLoteList = new ArrayList<>();

    public Lote() {        
    }

    public Lote(LotePK lotePK) {        
        this.lotePK = lotePK;
    }

    public Lote(String lote, String articulo) {        
        this.lotePK = new LotePK(lote, articulo);
    }

    public boolean isVencido() {
        Date fechaActual = new Date();
        return fechaVencimiento.compareTo(Utils.getDate(1980, 1, 1))>0 && fechaVencimiento.compareTo(Utils.stripTime(fechaActual))<0;
    }

    

}
