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
@Table(name = "CLASIFICACION")
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c")})
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CLASIFICACION")
    private String clasificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGRUPACION")
    private short agrupacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_NUMEROS_SERIE")
    private String usaNumerosSerie = "N";
    @Size(max = 5)
    @Column(name = "APORTE_CODIGO")
    private String aporteCodigo;
    @Size(max = 1)
    @Column(name = "TIPO_CODIGO_BARRAS")
    private String tipoCodigoBarras;
    @Size(max = 4)
    @Column(name = "PLANTILLA_SERIE")
    private String plantillaSerie;
    @JoinColumn(name = "UNIDAD_MEDIDA", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadDeMedida unidadDeMedida;
    @OneToMany(mappedBy = "clasificacion6", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;
    @OneToMany(mappedBy = "clasificacion1", fetch = FetchType.LAZY)
    private List<Articulo> articuloList1;
    @OneToMany(mappedBy = "clasificacion2", fetch = FetchType.LAZY)
    private List<Articulo> articuloList2;
    @OneToMany(mappedBy = "clasificacion3", fetch = FetchType.LAZY)
    private List<Articulo> articuloList3;
    @OneToMany(mappedBy = "clasificacion4", fetch = FetchType.LAZY)
    private List<Articulo> articuloList4;
    @OneToMany(mappedBy = "clasificacion5", fetch = FetchType.LAZY)
    private List<Articulo> articuloList5;

    public Clasificacion() {
    }

    public Clasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(short agrupacion) {
        this.agrupacion = agrupacion;
    }

    public String getUsaNumerosSerie() {
        return usaNumerosSerie;
    }

    public void setUsaNumerosSerie(String usaNumerosSerie) {
        this.usaNumerosSerie = usaNumerosSerie;
    }

    public String getAporteCodigo() {
        return aporteCodigo;
    }

    public void setAporteCodigo(String aporteCodigo) {
        this.aporteCodigo = aporteCodigo;
    }

    public String getTipoCodigoBarras() {
        return tipoCodigoBarras;
    }

    public void setTipoCodigoBarras(String tipoCodigoBarras) {
        this.tipoCodigoBarras = tipoCodigoBarras;
    }

    public String getPlantillaSerie() {
        return plantillaSerie;
    }

    public void setPlantillaSerie(String plantillaSerie) {
        this.plantillaSerie = plantillaSerie;
    }

    public UnidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
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

    public List<Articulo> getArticuloList3() {
        return articuloList3;
    }

    public void setArticuloList3(List<Articulo> articuloList3) {
        this.articuloList3 = articuloList3;
    }

    public List<Articulo> getArticuloList4() {
        return articuloList4;
    }

    public void setArticuloList4(List<Articulo> articuloList4) {
        this.articuloList4 = articuloList4;
    }

    public List<Articulo> getArticuloList5() {
        return articuloList5;
    }

    public void setArticuloList5(List<Articulo> articuloList5) {
        this.articuloList5 = articuloList5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clasificacion != null ? clasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.clasificacion == null && other.clasificacion != null) || (this.clasificacion != null && !this.clasificacion.equals(other.clasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.Clasificacion[ clasificacion=" + clasificacion + " ]";
    }

}
