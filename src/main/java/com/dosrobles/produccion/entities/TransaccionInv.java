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
@Table(name = "TRANSACCION_INV")
@NamedQueries({
    @NamedQuery(name = "TransaccionInv.findAll", query = "SELECT t FROM TransaccionInv t")})
public class TransaccionInv implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransaccionInvPK transaccionInvPK;
    @Column(name = "FECHA_HORA_TRANSAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTransac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 1)
    @Column(name = "SUBTIPO")
    private String subtipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 0, max = 1)
    @Column(name = "SUBSUBTIPO")
    private String subsubtipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NATURALEZA")
    private String naturaleza;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOT_FISC_LOC")
    private BigDecimal costoTotFiscLoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOT_FISC_DOL")
    private BigDecimal costoTotFiscDol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOT_COMP_LOC")
    private BigDecimal costoTotCompLoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOT_COMP_DOL")
    private BigDecimal costoTotCompDol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_TOTAL_LOCAL")
    private BigDecimal precioTotalLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_TOTAL_DOLAR")
    private BigDecimal precioTotalDolar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONTABILIZADA")
    private String contabilizada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 10)
    @Column(name = "ASIENTO_CARDEX")
    private String asientoCardex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUDIT_TRANS_INV", insertable = false, updatable = false)
    private int auditTransInv1;
    @Size(max = 25)
    @Column(name = "CENTRO_COSTO")
    private String centroCosto;
    @Size(max = 25)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Size(max = 4)
    @Column(name = "BODEGA")
    private String bodega;
    @Size(max = 8)
    @Column(name = "LOCALIZACION")
    private String localizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO")
    private String articulo1;
    @Size(max = 15)
    @Column(name = "LOTE")
    private String lote;
    @Size(max = 20)
    @Column(name = "NIT")
    private String nit;
    @Column(name = "SERIE_CADENA")
    private Integer serieCadena;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transaccionInv", fetch = FetchType.LAZY)
    private TransInvAux transInvAux;
    @JoinColumn(name = "AJUSTE_CONFIG", referencedColumnName = "AJUSTE_CONFIG")
    @ManyToOne(fetch = FetchType.LAZY)
    private AjusteConfig ajusteConfig;
    @JoinColumn(name = "ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;
    @JoinColumn(name = "UNIDAD_DISTRIBUCIO", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadDeMedida unidadDeMedida;

    public TransaccionInv() {
    }

    public TransaccionInv(TransaccionInvPK transaccionInvPK) {
        this.transaccionInvPK = transaccionInvPK;
    }

    public TransaccionInv(int auditTransInv, int consecutivo) {
        this.transaccionInvPK = new TransaccionInvPK(auditTransInv, consecutivo);
    }

    public TransaccionInvPK getTransaccionInvPK() {
        return transaccionInvPK;
    }

    public void setTransaccionInvPK(TransaccionInvPK transaccionInvPK) {
        this.transaccionInvPK = transaccionInvPK;
    }

    public Date getFechaHoraTransac() {
        return fechaHoraTransac;
    }

    public void setFechaHoraTransac(Date fechaHoraTransac) {
        this.fechaHoraTransac = fechaHoraTransac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getSubsubtipo() {
        return subsubtipo;
    }

    public void setSubsubtipo(String subsubtipo) {
        this.subsubtipo = subsubtipo;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoTotFiscLoc() {
        return costoTotFiscLoc;
    }

    public void setCostoTotFiscLoc(BigDecimal costoTotFiscLoc) {
        this.costoTotFiscLoc = costoTotFiscLoc;
    }

    public BigDecimal getCostoTotFiscDol() {
        return costoTotFiscDol;
    }

    public void setCostoTotFiscDol(BigDecimal costoTotFiscDol) {
        this.costoTotFiscDol = costoTotFiscDol;
    }

    public BigDecimal getCostoTotCompLoc() {
        return costoTotCompLoc;
    }

    public void setCostoTotCompLoc(BigDecimal costoTotCompLoc) {
        this.costoTotCompLoc = costoTotCompLoc;
    }

    public BigDecimal getCostoTotCompDol() {
        return costoTotCompDol;
    }

    public void setCostoTotCompDol(BigDecimal costoTotCompDol) {
        this.costoTotCompDol = costoTotCompDol;
    }

    public BigDecimal getPrecioTotalLocal() {
        return precioTotalLocal;
    }

    public void setPrecioTotalLocal(BigDecimal precioTotalLocal) {
        this.precioTotalLocal = precioTotalLocal;
    }

    public BigDecimal getPrecioTotalDolar() {
        return precioTotalDolar;
    }

    public void setPrecioTotalDolar(BigDecimal precioTotalDolar) {
        this.precioTotalDolar = precioTotalDolar;
    }

    public String getContabilizada() {
        return contabilizada;
    }

    public void setContabilizada(String contabilizada) {
        this.contabilizada = contabilizada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsientoCardex() {
        return asientoCardex;
    }

    public void setAsientoCardex(String asientoCardex) {
        this.asientoCardex = asientoCardex;
    }

    public int getAuditTransInv1() {
        return auditTransInv1;
    }

    public void setAuditTransInv1(int auditTransInv1) {
        this.auditTransInv1 = auditTransInv1;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getArticulo1() {
        return articulo1;
    }

    public void setArticulo1(String articulo1) {
        this.articulo1 = articulo1;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Integer getSerieCadena() {
        return serieCadena;
    }

    public void setSerieCadena(Integer serieCadena) {
        this.serieCadena = serieCadena;
    }

    public TransInvAux getTransInvAux() {
        return transInvAux;
    }

    public void setTransInvAux(TransInvAux transInvAux) {
        this.transInvAux = transInvAux;
    }

    public AjusteConfig getAjusteConfig() {
        return ajusteConfig;
    }

    public void setAjusteConfig(AjusteConfig ajusteConfig) {
        this.ajusteConfig = ajusteConfig;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public UnidadDeMedida getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccionInvPK != null ? transaccionInvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionInv)) {
            return false;
        }
        TransaccionInv other = (TransaccionInv) object;
        if ((this.transaccionInvPK == null && other.transaccionInvPK != null) || (this.transaccionInvPK != null && !this.transaccionInvPK.equals(other.transaccionInvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.TransaccionInv[ transaccionInvPK=" + transaccionInvPK + " ]";
    }

}
