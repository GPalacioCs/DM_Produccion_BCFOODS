/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "ESTRUC_MANUFACTURA")
@XmlRootElement
public class EstrucManufactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstrucManufacturaPK estrucManufacturaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 10)
    @Column(name = "NUM_CAMBIO_ING")
    private String numCambioIng;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VERSION_ANTERIOR")
    private String versionAnterior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CLASE")
    private String clase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROX_REV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProxRev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "REQUERIDA_POR")
    private String requeridaPor;
    @Column(name = "FECHA_REQUERIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRequerida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ELABORADA_POR")
    private String elaboradaPor;
    @Column(name = "FECHA_ELABORACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaElaboracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CREADA_POR")
    private String creadaPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "APROBADA_POR")
    private String aprobadaPor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOTE_ESTANDAR")
    private BigDecimal loteEstandar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOTE_MULTIPLO")
    private BigDecimal loteMultiplo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOTE_MINIMO")
    private BigDecimal loteMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOTE_MAXIMO")
    private BigDecimal loteMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CICLO_PROD_INICIAL")
    private BigDecimal cicloProdInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_PRODUCCION")
    private BigDecimal cantProduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS_PRODUCCION")
    private BigDecimal horasProduccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACT_APROVECHA")
    private BigDecimal factAprovecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ULTIMO_NIVEL")
    private short ultimoNivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "METODO_COSTEO_MOP")
    private String metodoCosteoMop;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "METODO_COSTEO_MOE")
    private String metodoCosteoMoe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "METODO_COSTEO_GIF")
    private String metodoCosteoGif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MAT")
    private BigDecimal costoEstimMat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MOP")
    private BigDecimal costoEstimMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MOE")
    private BigDecimal costoEstimMoe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_GIF")
    private BigDecimal costoEstimGif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_OPROD_MAT")
    private BigDecimal costoOprodMat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_OPROD_MOP")
    private BigDecimal costoOprodMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_OPROD_MOE")
    private BigDecimal costoOprodMoe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_OPROD_GIF")
    private BigDecimal costoOprodGif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_DE_CAMBIO")
    private BigDecimal tipoDeCambio;
    @Size(max = 20)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SELECCIONADA")
    private String seleccionada;
    @Size(max = 8)
    @Column(name = "ARCHIVOVIEWIT")
    private String archivoviewit;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "VIEWIT")
    private String viewit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_AJUSTE_COSTO")
    private BigDecimal porcAjusteCosto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PERMITE_BACKFLUSH")
    private String permiteBackflush;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASIG_LOT_CREAR_OP")
    private String asigLotCrearOp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REP_PROD_SUG_LOT")
    private String repProdSugLot;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NO_SUG_LOTES")
    private String noSugLotes;
    @Size(max = 10)
    @Column(name = "TIP_CONSEC_LOTES")
    private String tipConsecLotes;
    @Size(max = 1)
    @Column(name = "PERMITE_COSTO_CONTA")
    private String permiteCostoConta;
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
    @ManyToOne
    @JoinColumn(name = "ARTICULO", insertable = false, updatable = false)
    private Articulo articulo;
   
    
    public EstrucManufactura() {
    }

    public EstrucManufactura(EstrucManufacturaPK estrucManufacturaPK) {
        this.estrucManufacturaPK = estrucManufacturaPK;
    }

    public EstrucManufactura(EstrucManufacturaPK estrucManufacturaPK, String descripcion, String versionAnterior, String estado, String clase, Date fechaDesde, Date fechaHasta, Date fechaProxRev, String requeridaPor, String elaboradaPor, String creadaPor, Date fechaCreacion, String aprobadaPor, Date fechaAprobacion, BigDecimal loteEstandar, BigDecimal loteMultiplo, BigDecimal loteMinimo, BigDecimal loteMaximo, BigDecimal cicloProdInicial, BigDecimal cantProduccion, BigDecimal horasProduccion, BigDecimal factAprovecha, short ultimoNivel, String metodoCosteoMop, String metodoCosteoMoe, String metodoCosteoGif, BigDecimal costoEstimMat, BigDecimal costoEstimMop, BigDecimal costoEstimMoe, BigDecimal costoEstimGif, BigDecimal costoOprodMat, BigDecimal costoOprodMop, BigDecimal costoOprodMoe, BigDecimal costoOprodGif, BigDecimal tipoDeCambio, String seleccionada, BigDecimal porcAjusteCosto, String permiteBackflush, String asigLotCrearOp, String repProdSugLot, String noSugLotes, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.estrucManufacturaPK = estrucManufacturaPK;
        this.descripcion = descripcion;
        this.versionAnterior = versionAnterior;
        this.estado = estado;
        this.clase = clase;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaProxRev = fechaProxRev;
        this.requeridaPor = requeridaPor;
        this.elaboradaPor = elaboradaPor;
        this.creadaPor = creadaPor;
        this.fechaCreacion = fechaCreacion;
        this.aprobadaPor = aprobadaPor;
        this.fechaAprobacion = fechaAprobacion;
        this.loteEstandar = loteEstandar;
        this.loteMultiplo = loteMultiplo;
        this.loteMinimo = loteMinimo;
        this.loteMaximo = loteMaximo;
        this.cicloProdInicial = cicloProdInicial;
        this.cantProduccion = cantProduccion;
        this.horasProduccion = horasProduccion;
        this.factAprovecha = factAprovecha;
        this.ultimoNivel = ultimoNivel;
        this.metodoCosteoMop = metodoCosteoMop;
        this.metodoCosteoMoe = metodoCosteoMoe;
        this.metodoCosteoGif = metodoCosteoGif;
        this.costoEstimMat = costoEstimMat;
        this.costoEstimMop = costoEstimMop;
        this.costoEstimMoe = costoEstimMoe;
        this.costoEstimGif = costoEstimGif;
        this.costoOprodMat = costoOprodMat;
        this.costoOprodMop = costoOprodMop;
        this.costoOprodMoe = costoOprodMoe;
        this.costoOprodGif = costoOprodGif;
        this.tipoDeCambio = tipoDeCambio;
        this.seleccionada = seleccionada;
        this.porcAjusteCosto = porcAjusteCosto;
        this.permiteBackflush = permiteBackflush;
        this.asigLotCrearOp = asigLotCrearOp;
        this.repProdSugLot = repProdSugLot;
        this.noSugLotes = noSugLotes;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public EstrucManufactura(String articulo, String version) {
        this.estrucManufacturaPK = new EstrucManufacturaPK(articulo, version);
    }

    public EstrucManufacturaPK getEstrucManufacturaPK() {
        return estrucManufacturaPK;
    }

    public void setEstrucManufacturaPK(EstrucManufacturaPK estrucManufacturaPK) {
        this.estrucManufacturaPK = estrucManufacturaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumCambioIng() {
        return numCambioIng;
    }

    public void setNumCambioIng(String numCambioIng) {
        this.numCambioIng = numCambioIng;
    }

    public String getVersionAnterior() {
        return versionAnterior;
    }

    public void setVersionAnterior(String versionAnterior) {
        this.versionAnterior = versionAnterior;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaProxRev() {
        return fechaProxRev;
    }

    public void setFechaProxRev(Date fechaProxRev) {
        this.fechaProxRev = fechaProxRev;
    }

    public String getRequeridaPor() {
        return requeridaPor;
    }

    public void setRequeridaPor(String requeridaPor) {
        this.requeridaPor = requeridaPor;
    }

    public Date getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(Date fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getElaboradaPor() {
        return elaboradaPor;
    }

    public void setElaboradaPor(String elaboradaPor) {
        this.elaboradaPor = elaboradaPor;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getCreadaPor() {
        return creadaPor;
    }

    public void setCreadaPor(String creadaPor) {
        this.creadaPor = creadaPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAprobadaPor() {
        return aprobadaPor;
    }

    public void setAprobadaPor(String aprobadaPor) {
        this.aprobadaPor = aprobadaPor;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public BigDecimal getLoteEstandar() {
        return loteEstandar;
    }

    public void setLoteEstandar(BigDecimal loteEstandar) {
        this.loteEstandar = loteEstandar;
    }

    public BigDecimal getLoteMultiplo() {
        return loteMultiplo;
    }

    public void setLoteMultiplo(BigDecimal loteMultiplo) {
        this.loteMultiplo = loteMultiplo;
    }

    public BigDecimal getLoteMinimo() {
        return loteMinimo;
    }

    public void setLoteMinimo(BigDecimal loteMinimo) {
        this.loteMinimo = loteMinimo;
    }

    public BigDecimal getLoteMaximo() {
        return loteMaximo;
    }

    public void setLoteMaximo(BigDecimal loteMaximo) {
        this.loteMaximo = loteMaximo;
    }

    public BigDecimal getCicloProdInicial() {
        return cicloProdInicial;
    }

    public void setCicloProdInicial(BigDecimal cicloProdInicial) {
        this.cicloProdInicial = cicloProdInicial;
    }

    public BigDecimal getCantProduccion() {
        return cantProduccion;
    }

    public void setCantProduccion(BigDecimal cantProduccion) {
        this.cantProduccion = cantProduccion;
    }

    public BigDecimal getHorasProduccion() {
        return horasProduccion;
    }

    public void setHorasProduccion(BigDecimal horasProduccion) {
        this.horasProduccion = horasProduccion;
    }

    public BigDecimal getFactAprovecha() {
        return factAprovecha;
    }

    public void setFactAprovecha(BigDecimal factAprovecha) {
        this.factAprovecha = factAprovecha;
    }

    public short getUltimoNivel() {
        return ultimoNivel;
    }

    public void setUltimoNivel(short ultimoNivel) {
        this.ultimoNivel = ultimoNivel;
    }

    public String getMetodoCosteoMop() {
        return metodoCosteoMop;
    }

    public void setMetodoCosteoMop(String metodoCosteoMop) {
        this.metodoCosteoMop = metodoCosteoMop;
    }

    public String getMetodoCosteoMoe() {
        return metodoCosteoMoe;
    }

    public void setMetodoCosteoMoe(String metodoCosteoMoe) {
        this.metodoCosteoMoe = metodoCosteoMoe;
    }

    public String getMetodoCosteoGif() {
        return metodoCosteoGif;
    }

    public void setMetodoCosteoGif(String metodoCosteoGif) {
        this.metodoCosteoGif = metodoCosteoGif;
    }

    public BigDecimal getCostoEstimMat() {
        return costoEstimMat;
    }

    public void setCostoEstimMat(BigDecimal costoEstimMat) {
        this.costoEstimMat = costoEstimMat;
    }

    public BigDecimal getCostoEstimMop() {
        return costoEstimMop;
    }

    public void setCostoEstimMop(BigDecimal costoEstimMop) {
        this.costoEstimMop = costoEstimMop;
    }

    public BigDecimal getCostoEstimMoe() {
        return costoEstimMoe;
    }

    public void setCostoEstimMoe(BigDecimal costoEstimMoe) {
        this.costoEstimMoe = costoEstimMoe;
    }

    public BigDecimal getCostoEstimGif() {
        return costoEstimGif;
    }

    public void setCostoEstimGif(BigDecimal costoEstimGif) {
        this.costoEstimGif = costoEstimGif;
    }

    public BigDecimal getCostoOprodMat() {
        return costoOprodMat;
    }

    public void setCostoOprodMat(BigDecimal costoOprodMat) {
        this.costoOprodMat = costoOprodMat;
    }

    public BigDecimal getCostoOprodMop() {
        return costoOprodMop;
    }

    public void setCostoOprodMop(BigDecimal costoOprodMop) {
        this.costoOprodMop = costoOprodMop;
    }

    public BigDecimal getCostoOprodMoe() {
        return costoOprodMoe;
    }

    public void setCostoOprodMoe(BigDecimal costoOprodMoe) {
        this.costoOprodMoe = costoOprodMoe;
    }

    public BigDecimal getCostoOprodGif() {
        return costoOprodGif;
    }

    public void setCostoOprodGif(BigDecimal costoOprodGif) {
        this.costoOprodGif = costoOprodGif;
    }

    public BigDecimal getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(BigDecimal tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(String seleccionada) {
        this.seleccionada = seleccionada;
    }

    public String getArchivoviewit() {
        return archivoviewit;
    }

    public void setArchivoviewit(String archivoviewit) {
        this.archivoviewit = archivoviewit;
    }

    public String getViewit() {
        return viewit;
    }

    public void setViewit(String viewit) {
        this.viewit = viewit;
    }

    public BigDecimal getPorcAjusteCosto() {
        return porcAjusteCosto;
    }

    public void setPorcAjusteCosto(BigDecimal porcAjusteCosto) {
        this.porcAjusteCosto = porcAjusteCosto;
    }

    public String getPermiteBackflush() {
        return permiteBackflush;
    }

    public void setPermiteBackflush(String permiteBackflush) {
        this.permiteBackflush = permiteBackflush;
    }

    public String getAsigLotCrearOp() {
        return asigLotCrearOp;
    }

    public void setAsigLotCrearOp(String asigLotCrearOp) {
        this.asigLotCrearOp = asigLotCrearOp;
    }

    public String getRepProdSugLot() {
        return repProdSugLot;
    }

    public void setRepProdSugLot(String repProdSugLot) {
        this.repProdSugLot = repProdSugLot;
    }

    public String getNoSugLotes() {
        return noSugLotes;
    }

    public void setNoSugLotes(String noSugLotes) {
        this.noSugLotes = noSugLotes;
    }

    public String getTipConsecLotes() {
        return tipConsecLotes;
    }

    public void setTipConsecLotes(String tipConsecLotes) {
        this.tipConsecLotes = tipConsecLotes;
    }

    public String getPermiteCostoConta() {
        return permiteCostoConta;
    }

    public void setPermiteCostoConta(String permiteCostoConta) {
        this.permiteCostoConta = permiteCostoConta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estrucManufacturaPK != null ? estrucManufacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucManufactura)) {
            return false;
        }
        EstrucManufactura other = (EstrucManufactura) object;
        if ((this.estrucManufacturaPK == null && other.estrucManufacturaPK != null) || (this.estrucManufacturaPK != null && !this.estrucManufacturaPK.equals(other.estrucManufacturaPK))) {
            return false;
        }
        return true;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucManufactura[ estrucManufacturaPK=" + estrucManufacturaPK + " ]";
    }
    
}
