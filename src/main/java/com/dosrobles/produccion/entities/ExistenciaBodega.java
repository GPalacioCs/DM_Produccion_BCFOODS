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
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "EXISTENCIA_BODEGA")
@NamedQueries({
    @NamedQuery(name = "ExistenciaBodega.findAll", query = "SELECT e FROM ExistenciaBodega e")})
public class ExistenciaBodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExistenciaBodegaPK existenciaBodegaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXISTENCIA_MINIMA")
    private BigDecimal existenciaMinima = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXISTENCIA_MAXIMA")
    private BigDecimal existenciaMaxima = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUNTO_DE_REORDEN")
    private BigDecimal puntoDeReorden = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_DISPONIBLE")
    private BigDecimal cantDisponible = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_RESERVADA")
    private BigDecimal cantReservada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_NO_APROBADA")
    private BigDecimal cantNoAprobada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_VENCIDA")
    private BigDecimal cantVencida = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_TRANSITO")
    private BigDecimal cantTransito = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_PRODUCCION")
    private BigDecimal cantProduccion = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_PEDIDA")
    private BigDecimal cantPedida  = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_REMITIDA")
    private BigDecimal cantRemitida  = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONGELADO")
    private String congelado = "N";
    @Column(name = "FECHA_CONG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCong;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "BLOQUEA_TRANS")
    private String bloqueaTrans = "N";
    @Column(name = "FECHA_DESCONG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNT_PROMEDIO_LOC")
    private BigDecimal costoUntPromedioLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNT_PROMEDIO_DOL")
    private BigDecimal costoUntPromedioDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNT_ESTANDAR_LOC")
    private BigDecimal costoUntEstandarLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNT_ESTANDAR_DOL")
    private BigDecimal costoUntEstandarDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_COMPARATIVO_LOC")
    private BigDecimal costoPromComparativoLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_COMPARATIVO_DOLAR")
    private BigDecimal costoPromComparativoDolar = BigDecimal.ZERO;
    @JoinColumn(name = "BODEGA", referencedColumnName = "BODEGA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bodega bodega;
    @JoinColumn(name = "ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;

    public ExistenciaBodega() {
        this.existenciaBodegaPK = new ExistenciaBodegaPK();
    }

    public ExistenciaBodega(ExistenciaBodegaPK existenciaBodegaPK) {
        this.existenciaBodegaPK = existenciaBodegaPK;
    }

    public ExistenciaBodega(String articulo, String bodega) {
        this.existenciaBodegaPK = new ExistenciaBodegaPK(articulo, bodega);
    }

    public ExistenciaBodegaPK getExistenciaBodegaPK() {
        return existenciaBodegaPK;
    }

    public void setExistenciaBodegaPK(ExistenciaBodegaPK existenciaBodegaPK) {
        this.existenciaBodegaPK = existenciaBodegaPK;
    }

    public BigDecimal getExistenciaMinima() {
        return existenciaMinima;
    }

    public void setExistenciaMinima(BigDecimal existenciaMinima) {
        this.existenciaMinima = existenciaMinima;
    }

    public BigDecimal getExistenciaMaxima() {
        return existenciaMaxima;
    }

    public void setExistenciaMaxima(BigDecimal existenciaMaxima) {
        this.existenciaMaxima = existenciaMaxima;
    }

    public BigDecimal getPuntoDeReorden() {
        return puntoDeReorden;
    }

    public void setPuntoDeReorden(BigDecimal puntoDeReorden) {
        this.puntoDeReorden = puntoDeReorden;
    }

    public BigDecimal getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(BigDecimal cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public BigDecimal getCantReservada() {
        return cantReservada;
    }

    public void setCantReservada(BigDecimal cantReservada) {
        this.cantReservada = cantReservada;
    }

    public BigDecimal getCantNoAprobada() {
        return cantNoAprobada;
    }

    public void setCantNoAprobada(BigDecimal cantNoAprobada) {
        this.cantNoAprobada = cantNoAprobada;
    }

    public BigDecimal getCantVencida() {
        return cantVencida;
    }

    public void setCantVencida(BigDecimal cantVencida) {
        this.cantVencida = cantVencida;
    }

    public BigDecimal getCantTransito() {
        return cantTransito;
    }

    public void setCantTransito(BigDecimal cantTransito) {
        this.cantTransito = cantTransito;
    }

    public BigDecimal getCantProduccion() {
        return cantProduccion;
    }

    public void setCantProduccion(BigDecimal cantProduccion) {
        this.cantProduccion = cantProduccion;
    }

    public BigDecimal getCantPedida() {
        return cantPedida;
    }

    public void setCantPedida(BigDecimal cantPedida) {
        this.cantPedida = cantPedida;
    }

    public BigDecimal getCantRemitida() {
        return cantRemitida;
    }

    public void setCantRemitida(BigDecimal cantRemitida) {
        this.cantRemitida = cantRemitida;
    }

    public String getCongelado() {
        return congelado;
    }

    public void setCongelado(String congelado) {
        this.congelado = congelado;
    }

    public Date getFechaCong() {
        return fechaCong;
    }

    public void setFechaCong(Date fechaCong) {
        this.fechaCong = fechaCong;
    }

    public String getBloqueaTrans() {
        return bloqueaTrans;
    }

    public void setBloqueaTrans(String bloqueaTrans) {
        this.bloqueaTrans = bloqueaTrans;
    }

    public Date getFechaDescong() {
        return fechaDescong;
    }

    public void setFechaDescong(Date fechaDescong) {
        this.fechaDescong = fechaDescong;
    }

    public BigDecimal getCostoUntPromedioLoc() {
        return costoUntPromedioLoc;
    }

    public void setCostoUntPromedioLoc(BigDecimal costoUntPromedioLoc) {
        this.costoUntPromedioLoc = costoUntPromedioLoc;
    }

    public BigDecimal getCostoUntPromedioDol() {
        return costoUntPromedioDol;
    }

    public void setCostoUntPromedioDol(BigDecimal costoUntPromedioDol) {
        this.costoUntPromedioDol = costoUntPromedioDol;
    }

    public BigDecimal getCostoUntEstandarLoc() {
        return costoUntEstandarLoc;
    }

    public void setCostoUntEstandarLoc(BigDecimal costoUntEstandarLoc) {
        this.costoUntEstandarLoc = costoUntEstandarLoc;
    }

    public BigDecimal getCostoUntEstandarDol() {
        return costoUntEstandarDol;
    }

    public void setCostoUntEstandarDol(BigDecimal costoUntEstandarDol) {
        this.costoUntEstandarDol = costoUntEstandarDol;
    }

    public BigDecimal getCostoPromComparativoLoc() {
        return costoPromComparativoLoc;
    }

    public void setCostoPromComparativoLoc(BigDecimal costoPromComparativoLoc) {
        this.costoPromComparativoLoc = costoPromComparativoLoc;
    }

    public BigDecimal getCostoPromComparativoDolar() {
        return costoPromComparativoDolar;
    }

    public void setCostoPromComparativoDolar(BigDecimal costoPromComparativoDolar) {
        this.costoPromComparativoDolar = costoPromComparativoDolar;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        if (bodega != null) {
            this.existenciaBodegaPK.setBodega(bodega.getBodega());
        } else {
            this.existenciaBodegaPK.setBodega(null);
        }
        this.bodega = bodega;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        if(articulo != null) {
            this.existenciaBodegaPK.setArticulo(articulo.getArticulo());
        } else {
            this.existenciaBodegaPK.setArticulo(null);
        }
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (existenciaBodegaPK != null ? existenciaBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaBodega)) {
            return false;
        }
        ExistenciaBodega other = (ExistenciaBodega) object;
        if ((this.existenciaBodegaPK == null && other.existenciaBodegaPK != null) || (this.existenciaBodegaPK != null && !this.existenciaBodegaPK.equals(other.existenciaBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.ExistenciaBodega[ existenciaBodegaPK=" + existenciaBodegaPK + " ]";
    }

}
