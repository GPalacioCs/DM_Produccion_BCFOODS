/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CS_MATERIA_PRIMA")
@NamedQueries({
    @NamedQuery(name = "MateriaPrima.findAll", query = "SELECT m FROM MateriaPrima m")})
public class MateriaPrima implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MateriaPrimaPK materiaPrimaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICULO_HIJO", insertable = false, updatable = false)
    private Articulo articuloHijo;
    @JoinColumns({
        @JoinColumn(name = "ETAPA", referencedColumnName = "ETAPA", insertable = false, updatable = false),
        @JoinColumn(name = "ARTICULO_PADRE", referencedColumnName = "ARTICULO", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Etapa etapa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICULO_PADRE", insertable = false, updatable = false)
    @Getter @Setter
    private Articulo articuloPadre;

    public MateriaPrima() {
    }

    public MateriaPrima(MateriaPrimaPK materiaPrimaPK) {
        this.materiaPrimaPK = materiaPrimaPK;
    }

    public MateriaPrima(MateriaPrimaPK materiaPrimaPK, BigDecimal cantidad, Articulo articuloHijo1) {
        this.materiaPrimaPK = materiaPrimaPK;
        this.cantidad = cantidad;
        this.articuloHijo = articuloHijo1;
    }

    public MateriaPrima(int etapa, String articuloPadre, String articuloHijo) {
        this.materiaPrimaPK = new MateriaPrimaPK(etapa, articuloPadre, articuloHijo);
    }

    public MateriaPrimaPK getMateriaPrimaPK() {
        return materiaPrimaPK;
    }

    public void setMateriaPrimaPK(MateriaPrimaPK materiaPrimaPK) {
        this.materiaPrimaPK = materiaPrimaPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(Articulo articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materiaPrimaPK != null ? materiaPrimaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((this.materiaPrimaPK == null && other.materiaPrimaPK != null) || (this.materiaPrimaPK != null && !this.materiaPrimaPK.equals(other.materiaPrimaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.MateriaPrima[ materiaPrimaPK=" + materiaPrimaPK + " ]";
    }

}
