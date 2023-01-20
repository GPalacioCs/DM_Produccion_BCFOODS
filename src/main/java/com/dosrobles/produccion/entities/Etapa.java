/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CS_ETAPA")
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e")})
public class Etapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EtapaPK etapaPK;
    @Size(max = 249)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;
    @NotNull
    @Column(name="ORDEN")
    private int orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etapa", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MateriaPrima> materiaPrimaList = new ArrayList<>();

    public Etapa() {
    }

    public Etapa(EtapaPK etapaPK) {
        this.etapaPK = etapaPK;
    }

    public Etapa(EtapaPK etapaPK, Articulo artiuclo) {
        this.etapaPK = etapaPK;
        this.articulo = artiuclo;
    }

    public Etapa(int etapa, String artiuclo) {
        this.etapaPK = new EtapaPK(etapa, artiuclo);
    }

    public EtapaPK getEtapaPK() {
        return etapaPK;
    }

    public void setEtapaPK(EtapaPK etapaPK) {
        this.etapaPK = etapaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public List<MateriaPrima> getMateriaPrimaList() {
        return materiaPrimaList;
    }

    public void setMateriaPrimaList(List<MateriaPrima> materiaPrimaList) {
        this.materiaPrimaList = materiaPrimaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etapaPK != null ? etapaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.etapaPK == null && other.etapaPK != null) || (this.etapaPK != null && !this.etapaPK.equals(other.etapaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.Etapa[ etapaPK=" + etapaPK + " ]";
    }

}
