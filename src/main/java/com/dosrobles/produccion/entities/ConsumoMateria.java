/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CS_CONSUMO_MATERIA")
@NamedQueries({
    @NamedQuery(name = "ConsumoMateria.findAll", query = "SELECT c FROM ConsumoMateria c")})
public class ConsumoMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsumoMateriaPK consumoMateriaPK;    
    @Column(name = "CANTIDAD_CONSUMIDA")
    private BigDecimal cantidadConsumida;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="ETAPA", referencedColumnName = "ETAPA", insertable = false, updatable = false),
        @JoinColumn(name="ARTICULO_PADRE", referencedColumnName = "ARTICULO_PADRE", insertable = false, updatable = false),
        @JoinColumn(name="ARTICULO_HIJO", referencedColumnName = "ARTICULO_HIJO", insertable = false, updatable = false),
    })
    private MateriaPrima materiaPrima;    
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION", insertable = false, updatable = false)
    private CSOrdenProduccion ordenProduccion;
    @Column(name="TRANS_APLICADA")
    private String transAplicada = "N";
    @Column(name = "FECHA_APLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ARTICULO_HIJO", referencedColumnName = "ARTICULO", insertable = false, updatable = false),
        @JoinColumn(name = "BODEGA", referencedColumnName = "BODEGA")
    })
    private ExistenciaBodega existenciaBodega;
    
    public ConsumoMateria() {
    }

    public ConsumoMateria(ConsumoMateriaPK consumoMateriaPK) {
        this.consumoMateriaPK = consumoMateriaPK;
    }

    public ConsumoMateria(ConsumoMateriaPK consumoMateriaPK, BigDecimal cantidadConsumida, MateriaPrima materiaPrima) {
        this.consumoMateriaPK = consumoMateriaPK;
        this.cantidadConsumida = cantidadConsumida;
        this.materiaPrima = materiaPrima;
    }

    public ConsumoMateria(String ordenProduccion, int etapa, String articuloPadre, String articuloHijo) {
        this.consumoMateriaPK = new ConsumoMateriaPK(ordenProduccion, etapa, articuloPadre, articuloHijo);
    }

    public ConsumoMateriaPK getConsumoMateriaPK() {
        return consumoMateriaPK;
    }

    public void setConsumoMateriaPK(ConsumoMateriaPK consumoMateriaPK) {
        this.consumoMateriaPK = consumoMateriaPK;
    }

    public BigDecimal getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(BigDecimal cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public CSOrdenProduccion getOrdenProduccion() {
        return ordenProduccion;
    }

    public void setOrdenProduccion(CSOrdenProduccion ordenProduccion) {
        this.ordenProduccion = ordenProduccion;
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

    public ExistenciaBodega getExistenciaBodega() {
        return existenciaBodega;
    }

    public void setExistenciaBodega(ExistenciaBodega existenciaBodega) {
        this.existenciaBodega = existenciaBodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumoMateriaPK != null ? consumoMateriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoMateria)) {
            return false;
        }
        ConsumoMateria other = (ConsumoMateria) object;
        if ((this.consumoMateriaPK == null && other.consumoMateriaPK != null) || (this.consumoMateriaPK != null && !this.consumoMateriaPK.equals(other.consumoMateriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.ConsumoMateria[ consumoMateriaPK=" + consumoMateriaPK + " ]";
    }

}
