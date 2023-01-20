/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.enums.EstadosProd;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name = "CS_ORDEN_PRODUCCION")
@NamedQueries({
    @NamedQuery(name = "OrdenProduccion.findAll", query = "SELECT o FROM OrdenProduccion o")})
public class CSOrdenProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ORDEN_PRODUCCION")
    private String ordenProduccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "FECHA_LIBERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiberacion;
    @Column(name="TRANS_APLICADA")
    private String transAplicada;
    @Column(name = "FECHA_APLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICULO")
    private Articulo articulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenProduccion", orphanRemoval = true)
    private List<PedidoOrdenProduccion> pedidoOrdenProduccionList = new ArrayList<>();
    @Column(name="ESTADO")
    private String estado;
    @Column(name="COSTO_ESTIMADO_LOC")
    private BigDecimal costoEstimadoLoc = BigDecimal.ZERO;
    @Column(name="COSTO_ESTIMADO_DOL")
    private BigDecimal costoEstimadoDol = BigDecimal.ZERO;
    @Column(name="CANTIDAD_PRODUCIDA")
    private BigDecimal cantidadProducida = BigDecimal.ZERO;
    @Column(name="COSTO_REAL_LOC")
    private BigDecimal costoRealLoc;
    @Column(name="COSTO_REAL_DOL")
    private BigDecimal costoRealDol;
    private String segmento;
    private boolean consolidado;
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL)
    private List<ConsumoMateria> consumoMateriaList = new ArrayList<>();
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL)
    private List<PaqueteOrdenProduccion> paquetes = new ArrayList<>();
    @Transient
    private ExistenciaBodega existenciaBodega;

    public CSOrdenProduccion() {
    }

    public CSOrdenProduccion(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public CSOrdenProduccion(String ordenProduccion, String articulo) {
        this.ordenProduccion = ordenProduccion;        
    }

    public String getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(String ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Date fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    public String getTransAplicada() {
        return transAplicada;
    }

    public void setTransAplicada(String transAplicada) {
        this.transAplicada = transAplicada;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {        
        this.articulo = articulo;
    }

    public List<PedidoOrdenProduccion> getPedidoOrdenProduccionList() {
        return pedidoOrdenProduccionList;
    }

    public void setPedidoOrdenProduccionList(List<PedidoOrdenProduccion> pedidoOrdenProduccionList) {
        this.pedidoOrdenProduccionList = pedidoOrdenProduccionList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public EstadosProd getEstadoProd() {
        return EstadosProd.getEstadoProd(estado);
    }

    public BigDecimal getCostoEstimadoLoc() {
        return costoEstimadoLoc;
    }

    public void setCostoEstimadoLoc(BigDecimal costoEstimadoLoc) {
        this.costoEstimadoLoc = costoEstimadoLoc;
    }

    public BigDecimal getCostoEstimadoDol() {
        return costoEstimadoDol;
    }

    public void setCostoEstimadoDol(BigDecimal costoEstimadoDol) {
        this.costoEstimadoDol = costoEstimadoDol;
    }

    public BigDecimal getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(BigDecimal cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public BigDecimal getCostoRealLoc() {
        return costoRealLoc;
    }

    public void setCostoRealLoc(BigDecimal costoRealLoc) {
        this.costoRealLoc = costoRealLoc;
    }

    public BigDecimal getCostoRealDol() {
        return costoRealDol;
    }

    public void setCostoRealDol(BigDecimal costoRealDol) {
        this.costoRealDol = costoRealDol;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public boolean isConsolidado() {
        return consolidado;
    }

    public void setConsolidado(boolean consolidado) {
        this.consolidado = consolidado;
    }

    public List<ConsumoMateria> getConsumoMateriaList() {
        return consumoMateriaList;
    }

    public void setConsumoMateriaList(List<ConsumoMateria> consumoMateriaList) {
        this.consumoMateriaList = consumoMateriaList;
    }

    public ExistenciaBodega getExistenciaBodega() {
        return existenciaBodega;
    }

    public void setExistenciaBodega(ExistenciaBodega existenciaBodega) {
        this.existenciaBodega = existenciaBodega;
    }

    public List<PaqueteOrdenProduccion> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteOrdenProduccion> paquetes) {
        this.paquetes = paquetes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordenProduccion != null ? ordenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CSOrdenProduccion)) {
            return false;
        }
        CSOrdenProduccion other = (CSOrdenProduccion) object;
        if ((this.ordenProduccion == null && other.ordenProduccion != null) || (this.ordenProduccion != null && !this.ordenProduccion.equals(other.ordenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.OrdenProduccion[ ordenProduccion=" + ordenProduccion + " ]";
    }

}
