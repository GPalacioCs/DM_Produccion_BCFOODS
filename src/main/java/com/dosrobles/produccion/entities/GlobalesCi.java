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
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "GLOBALES_CI")
@NamedQueries({
    @NamedQuery(name = "VwGlobalesCi.findAll", query = "SELECT gci FROM GlobalesCi gci")})
public class GlobalesCi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COSTOS_DEC")
    private short costosDec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXISTENCIAS_DEC")
    private short existenciasDec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESOS_DEC")
    private short pesosDec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COSTO_FISCAL")
    private String costoFiscal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COSTO_COMPARATIVO")
    private String costoComparativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COSTO_INGR_DEFAULT")
    private String costoIngrDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UNIDAD_PESO")
    private String unidadPeso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UNIDAD_VOLUMEN")
    private String unidadVolumen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_LOCALIZACION")
    private String usaLocalizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AJUSTAR_CONTEO")
    private String ajustarConteo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAX_AUDITORIA")
    private int maxAuditoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FCH_ULT_PROC_VCTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchUltProcVcto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FCH_ULT_PROC_APROB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchUltProcAprob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_TRANS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioTrans;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PURGAR_CAPAS_COSTO")
    private String purgarCapasCosto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NOMBRE_CLASIF_1")
    private String nombreClasif1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NOMBRE_CLASIF_2")
    private String nombreClasif2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NOMBRE_CLASIF_3")
    private String nombreClasif3;
    @Size(max = 10)
    @Column(name = "NOMBRE_CLASIF_4")
    private String nombreClasif4;
    @Size(max = 10)
    @Column(name = "NOMBRE_CLASIF_5")
    private String nombreClasif5;
    @Size(max = 10)
    @Column(name = "NOMBRE_CLASIF_6")
    private String nombreClasif6;
    @Size(max = 4)
    @Column(name = "TIPO_ASIENTO")
    private String tipoAsiento;
    @Size(max = 4)
    @Column(name = "PAQUETE")
    private String paquete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_VENTA")
    private String asntAjuVenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_CONSUMO")
    private String asntAjuConsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_COMPRA")
    private String asntAjuCompra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_PRODUC")
    private String asntAjuProduc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_MISCELAN")
    private String asntAjuMiscelan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_FISICO")
    private String asntAjuFisico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_VENCIM")
    private String asntAjuVencim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASNT_AJU_COSTO")
    private String asntAjuCosto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_FASB52")
    private String tipoFasb52;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOD_APLIC_ASIENTO")
    private short modAplicAsiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "INTEGRACION_CONTA")
    private String integracionConta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_CONTA_OMISION")
    private String tipoContaOmision;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CTR_EN_TRANSACCION")
    private String ctrEnTransaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EXIST_EN_TOTALES")
    private String existEnTotales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TRANSAC_X_USUARIO")
    private String transacXUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_CONSECUTIVOS")
    private String usaConsecutivos;
    @Size(max = 1)
    @Column(name = "MODALIDAD_USO")
    private String modalidadUso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USAR_NUMEROS_SERIE")
    private String usarNumerosSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CNTRL_SERIES_ENTR")
    private String cntrlSeriesEntr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_CODIGO_BARRAS")
    private String usaCodigoBarras;
    @Size(max = 1)
    @Column(name = "USA_UNIDADES_DIST")
    private String usaUnidadesDist;
    @Size(max = 1)
    @Column(name = "ASISTENCIA_AUTOMAT")
    private String asistenciaAutomat;
    @Size(max = 1)
    @Column(name = "USA_CODIGO_EAN13")
    private String usaCodigoEan13;
    @Size(max = 1)
    @Column(name = "USA_CODIGO_EAN8")
    private String usaCodigoEan8;
    @Size(max = 1)
    @Column(name = "USA_CODIGO_UCC12")
    private String usaCodigoUcc12;
    @Size(max = 1)
    @Column(name = "USA_CODIGO_UCC8")
    private String usaCodigoUcc8;
    @Size(max = 18)
    @Column(name = "EAN13_REGLA_LOCAL")
    private String ean13ReglaLocal;
    @Size(max = 3)
    @Column(name = "EAN8_REGLA_LOCAL")
    private String ean8ReglaLocal;
    @Size(max = 6)
    @Column(name = "UCC12_REGLA_LOCAL")
    private String ucc12ReglaLocal;
    @Size(max = 1)
    @Column(name = "PRIORIDAD_BUSQUEDA")
    private String prioridadBusqueda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_PEDIMENTOS")
    private String usaPedimentos;
    @Size(max = 1)
    @Column(name = "USA_CODIGO_GENERIC")
    private String usaCodigoGeneric;
    @Column(name = "LINEAS_MAX_TRANS")
    private Integer lineasMaxTrans;
    @Size(max = 1)
    @Column(name = "USAR_APROBACION")
    private String usarAprobacion;
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

    public GlobalesCi() {
    }

    public short getCostosDec() {
        return costosDec;
    }

    public void setCostosDec(short costosDec) {
        this.costosDec = costosDec;
    }

    public short getExistenciasDec() {
        return existenciasDec;
    }

    public void setExistenciasDec(short existenciasDec) {
        this.existenciasDec = existenciasDec;
    }

    public short getPesosDec() {
        return pesosDec;
    }

    public void setPesosDec(short pesosDec) {
        this.pesosDec = pesosDec;
    }

    public String getCostoFiscal() {
        return costoFiscal;
    }

    public void setCostoFiscal(String costoFiscal) {
        this.costoFiscal = costoFiscal;
    }

    public String getCostoComparativo() {
        return costoComparativo;
    }

    public void setCostoComparativo(String costoComparativo) {
        this.costoComparativo = costoComparativo;
    }

    public String getCostoIngrDefault() {
        return costoIngrDefault;
    }

    public void setCostoIngrDefault(String costoIngrDefault) {
        this.costoIngrDefault = costoIngrDefault;
    }

    public String getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(String unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public String getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(String unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
    }

    public String getUsaLocalizacion() {
        return usaLocalizacion;
    }

    public void setUsaLocalizacion(String usaLocalizacion) {
        this.usaLocalizacion = usaLocalizacion;
    }

    public String getAjustarConteo() {
        return ajustarConteo;
    }

    public void setAjustarConteo(String ajustarConteo) {
        this.ajustarConteo = ajustarConteo;
    }

    public int getMaxAuditoria() {
        return maxAuditoria;
    }

    public void setMaxAuditoria(int maxAuditoria) {
        this.maxAuditoria = maxAuditoria;
    }

    public Date getFchUltProcVcto() {
        return fchUltProcVcto;
    }

    public void setFchUltProcVcto(Date fchUltProcVcto) {
        this.fchUltProcVcto = fchUltProcVcto;
    }

    public Date getFchUltProcAprob() {
        return fchUltProcAprob;
    }

    public void setFchUltProcAprob(Date fchUltProcAprob) {
        this.fchUltProcAprob = fchUltProcAprob;
    }

    public Date getFechaInicioTrans() {
        return fechaInicioTrans;
    }

    public void setFechaInicioTrans(Date fechaInicioTrans) {
        this.fechaInicioTrans = fechaInicioTrans;
    }

    public String getPurgarCapasCosto() {
        return purgarCapasCosto;
    }

    public void setPurgarCapasCosto(String purgarCapasCosto) {
        this.purgarCapasCosto = purgarCapasCosto;
    }

    public String getNombreClasif1() {
        return nombreClasif1;
    }

    public void setNombreClasif1(String nombreClasif1) {
        this.nombreClasif1 = nombreClasif1;
    }

    public String getNombreClasif2() {
        return nombreClasif2;
    }

    public void setNombreClasif2(String nombreClasif2) {
        this.nombreClasif2 = nombreClasif2;
    }

    public String getNombreClasif3() {
        return nombreClasif3;
    }

    public void setNombreClasif3(String nombreClasif3) {
        this.nombreClasif3 = nombreClasif3;
    }

    public String getNombreClasif4() {
        return nombreClasif4;
    }

    public void setNombreClasif4(String nombreClasif4) {
        this.nombreClasif4 = nombreClasif4;
    }

    public String getNombreClasif5() {
        return nombreClasif5;
    }

    public void setNombreClasif5(String nombreClasif5) {
        this.nombreClasif5 = nombreClasif5;
    }

    public String getNombreClasif6() {
        return nombreClasif6;
    }

    public void setNombreClasif6(String nombreClasif6) {
        this.nombreClasif6 = nombreClasif6;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getAsntAjuVenta() {
        return asntAjuVenta;
    }

    public void setAsntAjuVenta(String asntAjuVenta) {
        this.asntAjuVenta = asntAjuVenta;
    }

    public String getAsntAjuConsumo() {
        return asntAjuConsumo;
    }

    public void setAsntAjuConsumo(String asntAjuConsumo) {
        this.asntAjuConsumo = asntAjuConsumo;
    }

    public String getAsntAjuCompra() {
        return asntAjuCompra;
    }

    public void setAsntAjuCompra(String asntAjuCompra) {
        this.asntAjuCompra = asntAjuCompra;
    }

    public String getAsntAjuProduc() {
        return asntAjuProduc;
    }

    public void setAsntAjuProduc(String asntAjuProduc) {
        this.asntAjuProduc = asntAjuProduc;
    }

    public String getAsntAjuMiscelan() {
        return asntAjuMiscelan;
    }

    public void setAsntAjuMiscelan(String asntAjuMiscelan) {
        this.asntAjuMiscelan = asntAjuMiscelan;
    }

    public String getAsntAjuFisico() {
        return asntAjuFisico;
    }

    public void setAsntAjuFisico(String asntAjuFisico) {
        this.asntAjuFisico = asntAjuFisico;
    }

    public String getAsntAjuVencim() {
        return asntAjuVencim;
    }

    public void setAsntAjuVencim(String asntAjuVencim) {
        this.asntAjuVencim = asntAjuVencim;
    }

    public String getAsntAjuCosto() {
        return asntAjuCosto;
    }

    public void setAsntAjuCosto(String asntAjuCosto) {
        this.asntAjuCosto = asntAjuCosto;
    }

    public String getTipoFasb52() {
        return tipoFasb52;
    }

    public void setTipoFasb52(String tipoFasb52) {
        this.tipoFasb52 = tipoFasb52;
    }

    public short getModAplicAsiento() {
        return modAplicAsiento;
    }

    public void setModAplicAsiento(short modAplicAsiento) {
        this.modAplicAsiento = modAplicAsiento;
    }

    public String getIntegracionConta() {
        return integracionConta;
    }

    public void setIntegracionConta(String integracionConta) {
        this.integracionConta = integracionConta;
    }

    public String getTipoContaOmision() {
        return tipoContaOmision;
    }

    public void setTipoContaOmision(String tipoContaOmision) {
        this.tipoContaOmision = tipoContaOmision;
    }

    public String getCtrEnTransaccion() {
        return ctrEnTransaccion;
    }

    public void setCtrEnTransaccion(String ctrEnTransaccion) {
        this.ctrEnTransaccion = ctrEnTransaccion;
    }

    public String getExistEnTotales() {
        return existEnTotales;
    }

    public void setExistEnTotales(String existEnTotales) {
        this.existEnTotales = existEnTotales;
    }

    public String getTransacXUsuario() {
        return transacXUsuario;
    }

    public void setTransacXUsuario(String transacXUsuario) {
        this.transacXUsuario = transacXUsuario;
    }

    public String getUsaConsecutivos() {
        return usaConsecutivos;
    }

    public void setUsaConsecutivos(String usaConsecutivos) {
        this.usaConsecutivos = usaConsecutivos;
    }

    public String getModalidadUso() {
        return modalidadUso;
    }

    public void setModalidadUso(String modalidadUso) {
        this.modalidadUso = modalidadUso;
    }

    public String getUsarNumerosSerie() {
        return usarNumerosSerie;
    }

    public void setUsarNumerosSerie(String usarNumerosSerie) {
        this.usarNumerosSerie = usarNumerosSerie;
    }

    public String getCntrlSeriesEntr() {
        return cntrlSeriesEntr;
    }

    public void setCntrlSeriesEntr(String cntrlSeriesEntr) {
        this.cntrlSeriesEntr = cntrlSeriesEntr;
    }

    public String getUsaCodigoBarras() {
        return usaCodigoBarras;
    }

    public void setUsaCodigoBarras(String usaCodigoBarras) {
        this.usaCodigoBarras = usaCodigoBarras;
    }

    public String getUsaUnidadesDist() {
        return usaUnidadesDist;
    }

    public void setUsaUnidadesDist(String usaUnidadesDist) {
        this.usaUnidadesDist = usaUnidadesDist;
    }

    public String getAsistenciaAutomat() {
        return asistenciaAutomat;
    }

    public void setAsistenciaAutomat(String asistenciaAutomat) {
        this.asistenciaAutomat = asistenciaAutomat;
    }

    public String getUsaCodigoEan13() {
        return usaCodigoEan13;
    }

    public void setUsaCodigoEan13(String usaCodigoEan13) {
        this.usaCodigoEan13 = usaCodigoEan13;
    }

    public String getUsaCodigoEan8() {
        return usaCodigoEan8;
    }

    public void setUsaCodigoEan8(String usaCodigoEan8) {
        this.usaCodigoEan8 = usaCodigoEan8;
    }

    public String getUsaCodigoUcc12() {
        return usaCodigoUcc12;
    }

    public void setUsaCodigoUcc12(String usaCodigoUcc12) {
        this.usaCodigoUcc12 = usaCodigoUcc12;
    }

    public String getUsaCodigoUcc8() {
        return usaCodigoUcc8;
    }

    public void setUsaCodigoUcc8(String usaCodigoUcc8) {
        this.usaCodigoUcc8 = usaCodigoUcc8;
    }

    public String getEan13ReglaLocal() {
        return ean13ReglaLocal;
    }

    public void setEan13ReglaLocal(String ean13ReglaLocal) {
        this.ean13ReglaLocal = ean13ReglaLocal;
    }

    public String getEan8ReglaLocal() {
        return ean8ReglaLocal;
    }

    public void setEan8ReglaLocal(String ean8ReglaLocal) {
        this.ean8ReglaLocal = ean8ReglaLocal;
    }

    public String getUcc12ReglaLocal() {
        return ucc12ReglaLocal;
    }

    public void setUcc12ReglaLocal(String ucc12ReglaLocal) {
        this.ucc12ReglaLocal = ucc12ReglaLocal;
    }

    public String getPrioridadBusqueda() {
        return prioridadBusqueda;
    }

    public void setPrioridadBusqueda(String prioridadBusqueda) {
        this.prioridadBusqueda = prioridadBusqueda;
    }

    public String getUsaPedimentos() {
        return usaPedimentos;
    }

    public void setUsaPedimentos(String usaPedimentos) {
        this.usaPedimentos = usaPedimentos;
    }

    public String getUsaCodigoGeneric() {
        return usaCodigoGeneric;
    }

    public void setUsaCodigoGeneric(String usaCodigoGeneric) {
        this.usaCodigoGeneric = usaCodigoGeneric;
    }

    public Integer getLineasMaxTrans() {
        return lineasMaxTrans;
    }

    public void setLineasMaxTrans(Integer lineasMaxTrans) {
        this.lineasMaxTrans = lineasMaxTrans;
    }

    public String getUsarAprobacion() {
        return usarAprobacion;
    }

    public void setUsarAprobacion(String usarAprobacion) {
        this.usarAprobacion = usarAprobacion;
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

}
