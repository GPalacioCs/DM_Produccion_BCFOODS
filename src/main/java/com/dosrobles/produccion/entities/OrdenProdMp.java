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
import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "ORDEN_PROD_MP")
@NamedQueries({
    @NamedQuery(name = "OrdenProdMp.findAll", query = "SELECT o FROM OrdenProdMp o")})
@Data
@EqualsAndHashCode(of = {"id","componente"})
public class OrdenProdMp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private OrdenProdMpPK id = new OrdenProdMpPK();
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name = "ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @MapsId("articulo")
    @ManyToOne
    @JoinColumn(name = "COMPONENTE", referencedColumnName = "ARTICULO")
    private Articulo componente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad = BigDecimal.ONE;
    @Column(name = "COSTO")
    private BigDecimal costo = BigDecimal.ZERO;
    @Column(name = "COSTO_DOLAR")
    private BigDecimal costoDolar = BigDecimal.ZERO;    
    @ManyToOne
    @JoinColumn(name="PRODUCTO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    private Articulo producto;

    public OrdenProdMp() {
        this.id = new OrdenProdMpPK();
    }

    public OrdenProdMp(OrdenProdMpPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("--");
        joiner.add(componente.getArticulo());
        joiner.add(id.getLote());
        joiner.add(id.getProducto());
        joiner.add(String.valueOf(ordenProduccion));        
        return joiner.toString();
    }
    
    public String getLote() {
        return id.getLote();
    }
    
    public void setLote(String lote) {
        id.setLote(lote);
    }

    public BigDecimal getCostoTotal() {
        return getComponente().getCostoPromLoc().multiply(getCantidad());
    }

    public BigDecimal getCostoTotalDolar() {
        return getComponente().getCostoPromDol().multiply(getCantidad());
    }
}