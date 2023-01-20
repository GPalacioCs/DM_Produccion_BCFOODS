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
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "UNIDAD_DE_MEDIDA")
@NamedQueries({
    @NamedQuery(name = "UnidadDeMedida.findAll", query = "SELECT u FROM UnidadDeMedida u")})
public class UnidadDeMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UNIDAD_MEDIDA")
    private String unidadMedida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "unidadDeMedida", fetch = FetchType.LAZY)
    private List<LineaDocInv> lineaDocInvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadDeMedida", fetch = FetchType.LAZY)
    private List<Clasificacion> clasificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadAlmacen", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadEmpaque", fetch = FetchType.LAZY)
    private List<Articulo> articuloList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadVenta", fetch = FetchType.LAZY)
    private List<Articulo> articuloList2;
    @OneToMany(mappedBy = "unidadDeMedida", fetch = FetchType.LAZY)
    private List<TransaccionInv> transaccionInvList;

    public UnidadDeMedida() {
    }

    public UnidadDeMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<LineaDocInv> getLineaDocInvList() {
        return lineaDocInvList;
    }

    public void setLineaDocInvList(List<LineaDocInv> lineaDocInvList) {
        this.lineaDocInvList = lineaDocInvList;
    }

    public List<Clasificacion> getClasificacionList() {
        return clasificacionList;
    }

    public void setClasificacionList(List<Clasificacion> clasificacionList) {
        this.clasificacionList = clasificacionList;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public List<Articulo> getArticuloList1() {
        return articuloList1;
    }

    public void setArticuloList1(List<Articulo> articuloList1) {
        this.articuloList1 = articuloList1;
    }

    public List<Articulo> getArticuloList2() {
        return articuloList2;
    }

    public void setArticuloList2(List<Articulo> articuloList2) {
        this.articuloList2 = articuloList2;
    }

    public List<TransaccionInv> getTransaccionInvList() {
        return transaccionInvList;
    }

    public void setTransaccionInvList(List<TransaccionInv> transaccionInvList) {
        this.transaccionInvList = transaccionInvList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadMedida != null ? unidadMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadDeMedida)) {
            return false;
        }
        UnidadDeMedida other = (UnidadDeMedida) object;
        if ((this.unidadMedida == null && other.unidadMedida != null) || (this.unidadMedida != null && !this.unidadMedida.equals(other.unidadMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.UnidadDeMedida[ unidadMedida=" + unidadMedida + " ]";
    }

}
