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
@Table(name = "LINEA_DOC_INV")
@NamedQueries({
    @NamedQuery(name = "LineaDocInv.findAll", query = "SELECT l FROM LineaDocInv l")})
public class LineaDocInv implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineaDocInvPK lineaDocInvPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SUBTIPO")
    private String subtipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SUBSUBTIPO")
    private String subsubtipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOTAL_LOCAL")
    private BigDecimal costoTotalLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOTAL_DOLAR")
    private BigDecimal costoTotalDolar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_TOTAL_LOCAL")
    private BigDecimal precioTotalLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_TOTAL_DOLAR")
    private BigDecimal precioTotalDolar;
    @Column(name = "SECUENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoteExistsFlag")
    private short noteExistsFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RecordDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "RowPointer")
    private String rowPointer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UpdatedBy")
    private String updatedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOTAL_LOCAL_COMP")
    private BigDecimal costoTotalLocalComp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TOTAL_DOLAR_COMP")
    private BigDecimal costoTotalDolarComp;
    @Size(max = 25)
    @Column(name = "CENTRO_COSTO")
    private String centroCosto;
    @Size(max = 25)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PAQUETE_INVENTARIO")
    private String paqueteInventario1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DOCUMENTO_INV")
    private String documentoInv1;
    @Size(max = 4)
    @Column(name = "BODEGA")
    private String bodega;
    @Size(max = 8)
    @Column(name = "LOCALIZACION")
    private String localizacion;
    @Size(max = 4)
    @Column(name = "BODEGA_DESTINO")
    private String bodegaDestino;
    @Size(max = 8)
    @Column(name = "LOCALIZACION_DEST")
    private String localizacionDest;
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
    @JoinColumn(name = "AJUSTE_CONFIG", referencedColumnName = "AJUSTE_CONFIG")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AjusteConfig ajusteConfig;
    @JoinColumn(name = "ARTICULO", referencedColumnName = "ARTICULO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;
    @JoinColumn(name = "UNIDAD_DISTRIBUCIO", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(fetch = FetchType.LAZY)
    private UnidadDeMedida unidadDeMedida;

    public LineaDocInv() {
    }

    public LineaDocInv(LineaDocInvPK lineaDocInvPK) {
        this.lineaDocInvPK = lineaDocInvPK;
    }

    public LineaDocInv(LineaDocInvPK lineaDocInvPK, String tipo, String subtipo, String subsubtipo, BigDecimal cantidad, BigDecimal costoTotalLocal, BigDecimal costoTotalDolar, BigDecimal precioTotalLocal, BigDecimal precioTotalDolar, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate, BigDecimal costoTotalLocalComp, BigDecimal costoTotalDolarComp, String paqueteInventario1, String documentoInv1, String articulo1) {
        this.lineaDocInvPK = lineaDocInvPK;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.subsubtipo = subsubtipo;
        this.cantidad = cantidad;
        this.costoTotalLocal = costoTotalLocal;
        this.costoTotalDolar = costoTotalDolar;
        this.precioTotalLocal = precioTotalLocal;
        this.precioTotalDolar = precioTotalDolar;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
        this.costoTotalLocalComp = costoTotalLocalComp;
        this.costoTotalDolarComp = costoTotalDolarComp;
        this.paqueteInventario1 = paqueteInventario1;
        this.documentoInv1 = documentoInv1;
        this.articulo1 = articulo1;
    }

    public LineaDocInv(String paqueteInventario, String documentoInv, int lineaDocInv) {
        this.lineaDocInvPK = new LineaDocInvPK(paqueteInventario, documentoInv, lineaDocInv);
    }

    public LineaDocInvPK getLineaDocInvPK() {
        return lineaDocInvPK;
    }

    public void setLineaDocInvPK(LineaDocInvPK lineaDocInvPK) {
        this.lineaDocInvPK = lineaDocInvPK;
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

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoTotalLocal() {
        return costoTotalLocal;
    }

    public void setCostoTotalLocal(BigDecimal costoTotalLocal) {
        this.costoTotalLocal = costoTotalLocal;
    }

    public BigDecimal getCostoTotalDolar() {
        return costoTotalDolar;
    }

    public void setCostoTotalDolar(BigDecimal costoTotalDolar) {
        this.costoTotalDolar = costoTotalDolar;
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

    public Date getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Date secuencia) {
        this.secuencia = secuencia;
    }

    public short getNoteExistsFlag() {
        return noteExistsFlag;
    }

    public void setNoteExistsFlag(short noteExistsFlag) {
        this.noteExistsFlag = noteExistsFlag;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRowPointer() {
        return rowPointer;
    }

    public void setRowPointer(String rowPointer) {
        this.rowPointer = rowPointer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getCostoTotalLocalComp() {
        return costoTotalLocalComp;
    }

    public void setCostoTotalLocalComp(BigDecimal costoTotalLocalComp) {
        this.costoTotalLocalComp = costoTotalLocalComp;
    }

    public BigDecimal getCostoTotalDolarComp() {
        return costoTotalDolarComp;
    }

    public void setCostoTotalDolarComp(BigDecimal costoTotalDolarComp) {
        this.costoTotalDolarComp = costoTotalDolarComp;
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

    public String getPaqueteInventario1() {
        return paqueteInventario1;
    }

    public void setPaqueteInventario1(String paqueteInventario1) {
        this.paqueteInventario1 = paqueteInventario1;
    }

    public String getDocumentoInv1() {
        return documentoInv1;
    }

    public void setDocumentoInv1(String documentoInv1) {
        this.documentoInv1 = documentoInv1;
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

    public String getBodegaDestino() {
        return bodegaDestino;
    }

    public void setBodegaDestino(String bodegaDestino) {
        this.bodegaDestino = bodegaDestino;
    }

    public String getLocalizacionDest() {
        return localizacionDest;
    }

    public void setLocalizacionDest(String localizacionDest) {
        this.localizacionDest = localizacionDest;
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
        hash += (lineaDocInvPK != null ? lineaDocInvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaDocInv)) {
            return false;
        }
        LineaDocInv other = (LineaDocInv) object;
        if ((this.lineaDocInvPK == null && other.lineaDocInvPK != null) || (this.lineaDocInvPK != null && !this.lineaDocInvPK.equals(other.lineaDocInvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.LineaDocInv[ lineaDocInvPK=" + lineaDocInvPK + " ]";
    }

}
