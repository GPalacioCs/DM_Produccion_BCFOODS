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
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "ARTICULO_CUENTA")
@NamedQueries({
    @NamedQuery(name = "ArticuloCuenta.findAll", query = "SELECT a FROM ArticuloCuenta a")})
public class ArticuloCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ARTICULO_CUENTA")
    private String articuloCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 25)
    @Column(name = "CTR_CTO_CPGAR")
    private String ctrCtoCpgar;
    @Size(max = 25)
    @Column(name = "CTA_CTB_CPGAR")
    private String ctaCtbCpgar;
    @Size(max = 25)
    @Column(name = "CTR_CTO_PUGAR")
    private String ctrCtoPugar;
    @Size(max = 25)
    @Column(name = "CTA_CTB_PUGAR")
    private String ctaCtbPugar;
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
    @Size(max = 25)
    @Column(name = "CTR_CTO_ING_DEVOLUC")
    private String ctrCtoIngDevoluc;
    @Size(max = 25)
    @Column(name = "CTA_CTB_ING_DEVOLUC")
    private String ctaCtbIngDevoluc;
    @Size(max = 25)
    @Column(name = "CTR_CTO_PERD_DEVOLUC")
    private String ctrCtoPerdDevoluc;
    @Size(max = 25)
    @Column(name = "CTA_CTB_PERD_DEVOLUC")
    private String ctaCtbPerdDevoluc;
    @Size(max = 25)
    @Column(name = "CTR_CTO_AJU")
    private String ctrCtoAju;
    @Size(max = 25)
    @Column(name = "CTA_CTB_AJU")
    private String ctaCtbAju;
    @Size(max = 25)
    @Column(name = "CTR_CTO_AJU_CMV")
    private String ctrCtoAjuCmv;
    @Size(max = 25)
    @Column(name = "CTA_CTB_AJU_CMV")
    private String ctaCtbAjuCmv;
    @Size(max = 25)
    @Column(name = "CTR_COST_DESC_EXP")
    private String ctrCostDescExp;
    @Size(max = 25)
    @Column(name = "CTA_COST_DESC_EXP")
    private String ctaCostDescExp;
    @Size(max = 25)
    @Column(name = "CTR_FALTANTE_REMIS")
    private String ctrFaltanteRemis;
    @Size(max = 25)
    @Column(name = "CTA_FALTANTE_REMIS")
    private String ctaFaltanteRemis;
    @Size(max = 25)
    @Column(name = "CTR_SOBRANTE_REMIS")
    private String ctrSobranteRemis;
    @Size(max = 25)
    @Column(name = "CTA_SOBRANTE_REMIS")
    private String ctaSobranteRemis;
    @Size(max = 25)
    @Column(name = "CTR_VENCIMIENTO")
    private String ctrVencimiento;
    @Size(max = 25)
    @Column(name = "CTA_VENCIMIENTO")
    private String ctaVencimiento;
    @Size(max = 25)
    @Column(name = "CTR_COMPRA_LOC")
    private String ctrCompraLoc;
    @Size(max = 25)
    @Column(name = "CTA_COMPRA_LOC")
    private String ctaCompraLoc;
    @Size(max = 25)
    @Column(name = "CTR_COST_DESC_LOC")
    private String ctrCostDescLoc;
    @Size(max = 25)
    @Column(name = "CTA_COST_DESC_LOC")
    private String ctaCostDescLoc;
    @Size(max = 25)
    @Column(name = "CTR_COMS_COBRO_EXP")
    private String ctrComsCobroExp;
    @Size(max = 25)
    @Column(name = "CTA_COMS_COBRO_EXP")
    private String ctaComsCobroExp;
    @Size(max = 25)
    @Column(name = "CTR_DESC_VENTA_LOC")
    private String ctrDescVentaLoc;
    @Size(max = 25)
    @Column(name = "CTA_DESC_VENTA_LOC")
    private String ctaDescVentaLoc;
    @Size(max = 25)
    @Column(name = "CTR_COMS_VENTA_EXP")
    private String ctrComsVentaExp;
    @Size(max = 25)
    @Column(name = "CTA_COMS_VENTA_EXP")
    private String ctaComsVentaExp;
    @Size(max = 25)
    @Column(name = "CTR_CONS_GASTO")
    private String ctrConsGasto;
    @Size(max = 25)
    @Column(name = "CTA_CONS_GASTO")
    private String ctaConsGasto;
    @Size(max = 25)
    @Column(name = "CTR_CTO_RET_ASUM")
    private String ctrCtoRetAsum;
    @Size(max = 25)
    @Column(name = "CTA_CTB_RET_ASUM")
    private String ctaCtbRetAsum;
    @Size(max = 25)
    @Column(name = "CTR_COMPRA_IMP")
    private String ctrCompraImp;
    @Size(max = 25)
    @Column(name = "CTA_COMPRA_IMP")
    private String ctaCompraImp;
    @Size(max = 25)
    @Column(name = "CTR_COST_VENTA_LOC")
    private String ctrCostVentaLoc;
    @Size(max = 25)
    @Column(name = "CTA_COST_VENTA_LOC")
    private String ctaCostVentaLoc;
    @Size(max = 25)
    @Column(name = "CTR_CONS_NORMAL")
    private String ctrConsNormal;
    @Size(max = 25)
    @Column(name = "CTA_CONS_NORMAL")
    private String ctaConsNormal;
    @Size(max = 25)
    @Column(name = "CTR_DEV_VENTAS_EXP")
    private String ctrDevVentasExp;
    @Size(max = 25)
    @Column(name = "CTA_DEV_VENTAS_EXP")
    private String ctaDevVentasExp;
    @Size(max = 25)
    @Column(name = "CTR_INV_REMITIDO")
    private String ctrInvRemitido;
    @Size(max = 25)
    @Column(name = "CTA_INV_REMITIDO")
    private String ctaInvRemitido;
    @Size(max = 25)
    @Column(name = "CTR_DEV_VENTAS_LOC")
    private String ctrDevVentasLoc;
    @Size(max = 25)
    @Column(name = "CTA_DEV_VENTAS_LOC")
    private String ctaDevVentasLoc;
    @Size(max = 25)
    @Column(name = "CTR_DESC_BONIF_EXP")
    private String ctrDescBonifExp;
    @Size(max = 25)
    @Column(name = "CTA_DESC_BONIF_EXP")
    private String ctaDescBonifExp;
    @Size(max = 25)
    @Column(name = "CTR_SOBR_INVENTFIS")
    private String ctrSobrInventfis;
    @Size(max = 25)
    @Column(name = "CTA_SOBR_INVENTFIS")
    private String ctaSobrInventfis;
    @Size(max = 25)
    @Column(name = "CTR_COMS_VENTA_LOC")
    private String ctrComsVentaLoc;
    @Size(max = 25)
    @Column(name = "CTA_COMS_VENTA_LOC")
    private String ctaComsVentaLoc;
    @Size(max = 25)
    @Column(name = "CTR_COMS_COBRO_LOC")
    private String ctrComsCobroLoc;
    @Size(max = 25)
    @Column(name = "CTA_COMS_COBRO_LOC")
    private String ctaComsCobroLoc;
    @Size(max = 25)
    @Column(name = "CTR_CONS_RETRABAJO")
    private String ctrConsRetrabajo;
    @Size(max = 25)
    @Column(name = "CTA_CONS_RETRABAJO")
    private String ctaConsRetrabajo;
    @Size(max = 25)
    @Column(name = "CTR_CONS_DESPERDIC")
    private String ctrConsDesperdic;
    @Size(max = 25)
    @Column(name = "CTA_CONS_DESPERDIC")
    private String ctaConsDesperdic;
    @Size(max = 25)
    @Column(name = "CTR_MAT_PROCESO")
    private String ctrMatProceso;
    @Size(max = 25)
    @Column(name = "CTA_MAT_PROCESO")
    private String ctaMatProceso;
    @Size(max = 25)
    @Column(name = "CTR_VENTAS_EXP")
    private String ctrVentasExp;
    @Size(max = 25)
    @Column(name = "CTA_VENTAS_EXP")
    private String ctaVentasExp;
    @Size(max = 25)
    @Column(name = "CTR_DESC_VENTA_EXP")
    private String ctrDescVentaExp;
    @Size(max = 25)
    @Column(name = "CTA_DESC_VENTA_EXP")
    private String ctaDescVentaExp;    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTR_INVENTARIO")
    private CentroCosto ctrInventario;    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTA_INVENTARIO", referencedColumnName = "CUENTA_CONTABLE")
    private CuentaContable ctaInventario;
    @Size(max = 25)
    @Column(name = "CTR_COST_VENTA_EXP")
    private String ctrCostVentaExp;
    @Size(max = 25)
    @Column(name = "CTA_COST_VENTA_EXP")
    private String ctaCostVentaExp;
    @Size(max = 25)
    @Column(name = "CTR_FALT_INVENTFIS")
    private String ctrFaltInventfis;
    @Size(max = 25)
    @Column(name = "CTA_FALT_INVENTFIS")
    private String ctaFaltInventfis;
    @Size(max = 25)
    @Column(name = "CTR_VENTAS_LOC")
    private String ctrVentasLoc;
    @Size(max = 25)
    @Column(name = "CTA_VENTAS_LOC")
    private String ctaVentasLoc;
    @Size(max = 25)
    @Column(name = "CTR_DESC_LINEA_EXP")
    private String ctrDescLineaExp;
    @Size(max = 25)
    @Column(name = "CTA_DESC_LINEA_EXP")
    private String ctaDescLineaExp;
    @Size(max = 25)
    @Column(name = "CTR_VARIA_COSTO")
    private String ctrVariaCosto;
    @Size(max = 25)
    @Column(name = "CTA_VARIA_COSTO")
    private String ctaVariaCosto;
    @Size(max = 25)
    @Column(name = "CTR_DESC_LINEA_LOC")
    private String ctrDescLineaLoc;
    @Size(max = 25)
    @Column(name = "CTA_DESC_LINEA_LOC")
    private String ctaDescLineaLoc;
    @Size(max = 25)
    @Column(name = "CTR_DESC_BONIF_LOC")
    private String ctrDescBonifLoc;
    @Size(max = 25)
    @Column(name = "CTA_DESC_BONIF_LOC")
    private String ctaDescBonifLoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articuloCuenta", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;

    public ArticuloCuenta() {
    }

    public ArticuloCuenta(String articuloCuenta) {
        this.articuloCuenta = articuloCuenta;
    }

    public ArticuloCuenta(String articuloCuenta, String descripcion, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.articuloCuenta = articuloCuenta;
        this.descripcion = descripcion;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public String getArticuloCuenta() {
        return articuloCuenta;
    }

    public void setArticuloCuenta(String articuloCuenta) {
        this.articuloCuenta = articuloCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCtrCtoCpgar() {
        return ctrCtoCpgar;
    }

    public void setCtrCtoCpgar(String ctrCtoCpgar) {
        this.ctrCtoCpgar = ctrCtoCpgar;
    }

    public String getCtaCtbCpgar() {
        return ctaCtbCpgar;
    }

    public void setCtaCtbCpgar(String ctaCtbCpgar) {
        this.ctaCtbCpgar = ctaCtbCpgar;
    }

    public String getCtrCtoPugar() {
        return ctrCtoPugar;
    }

    public void setCtrCtoPugar(String ctrCtoPugar) {
        this.ctrCtoPugar = ctrCtoPugar;
    }

    public String getCtaCtbPugar() {
        return ctaCtbPugar;
    }

    public void setCtaCtbPugar(String ctaCtbPugar) {
        this.ctaCtbPugar = ctaCtbPugar;
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

    public String getCtrCtoIngDevoluc() {
        return ctrCtoIngDevoluc;
    }

    public void setCtrCtoIngDevoluc(String ctrCtoIngDevoluc) {
        this.ctrCtoIngDevoluc = ctrCtoIngDevoluc;
    }

    public String getCtaCtbIngDevoluc() {
        return ctaCtbIngDevoluc;
    }

    public void setCtaCtbIngDevoluc(String ctaCtbIngDevoluc) {
        this.ctaCtbIngDevoluc = ctaCtbIngDevoluc;
    }

    public String getCtrCtoPerdDevoluc() {
        return ctrCtoPerdDevoluc;
    }

    public void setCtrCtoPerdDevoluc(String ctrCtoPerdDevoluc) {
        this.ctrCtoPerdDevoluc = ctrCtoPerdDevoluc;
    }

    public String getCtaCtbPerdDevoluc() {
        return ctaCtbPerdDevoluc;
    }

    public void setCtaCtbPerdDevoluc(String ctaCtbPerdDevoluc) {
        this.ctaCtbPerdDevoluc = ctaCtbPerdDevoluc;
    }

    public String getCtrCtoAju() {
        return ctrCtoAju;
    }

    public void setCtrCtoAju(String ctrCtoAju) {
        this.ctrCtoAju = ctrCtoAju;
    }

    public String getCtaCtbAju() {
        return ctaCtbAju;
    }

    public void setCtaCtbAju(String ctaCtbAju) {
        this.ctaCtbAju = ctaCtbAju;
    }

    public String getCtrCtoAjuCmv() {
        return ctrCtoAjuCmv;
    }

    public void setCtrCtoAjuCmv(String ctrCtoAjuCmv) {
        this.ctrCtoAjuCmv = ctrCtoAjuCmv;
    }

    public String getCtaCtbAjuCmv() {
        return ctaCtbAjuCmv;
    }

    public void setCtaCtbAjuCmv(String ctaCtbAjuCmv) {
        this.ctaCtbAjuCmv = ctaCtbAjuCmv;
    }

    public String getCtrCostDescExp() {
        return ctrCostDescExp;
    }

    public void setCtrCostDescExp(String ctrCostDescExp) {
        this.ctrCostDescExp = ctrCostDescExp;
    }

    public String getCtaCostDescExp() {
        return ctaCostDescExp;
    }

    public void setCtaCostDescExp(String ctaCostDescExp) {
        this.ctaCostDescExp = ctaCostDescExp;
    }

    public String getCtrFaltanteRemis() {
        return ctrFaltanteRemis;
    }

    public void setCtrFaltanteRemis(String ctrFaltanteRemis) {
        this.ctrFaltanteRemis = ctrFaltanteRemis;
    }

    public String getCtaFaltanteRemis() {
        return ctaFaltanteRemis;
    }

    public void setCtaFaltanteRemis(String ctaFaltanteRemis) {
        this.ctaFaltanteRemis = ctaFaltanteRemis;
    }

    public String getCtrSobranteRemis() {
        return ctrSobranteRemis;
    }

    public void setCtrSobranteRemis(String ctrSobranteRemis) {
        this.ctrSobranteRemis = ctrSobranteRemis;
    }

    public String getCtaSobranteRemis() {
        return ctaSobranteRemis;
    }

    public void setCtaSobranteRemis(String ctaSobranteRemis) {
        this.ctaSobranteRemis = ctaSobranteRemis;
    }

    public String getCtrVencimiento() {
        return ctrVencimiento;
    }

    public void setCtrVencimiento(String ctrVencimiento) {
        this.ctrVencimiento = ctrVencimiento;
    }

    public String getCtaVencimiento() {
        return ctaVencimiento;
    }

    public void setCtaVencimiento(String ctaVencimiento) {
        this.ctaVencimiento = ctaVencimiento;
    }

    public String getCtrCompraLoc() {
        return ctrCompraLoc;
    }

    public void setCtrCompraLoc(String ctrCompraLoc) {
        this.ctrCompraLoc = ctrCompraLoc;
    }

    public String getCtaCompraLoc() {
        return ctaCompraLoc;
    }

    public void setCtaCompraLoc(String ctaCompraLoc) {
        this.ctaCompraLoc = ctaCompraLoc;
    }

    public String getCtrCostDescLoc() {
        return ctrCostDescLoc;
    }

    public void setCtrCostDescLoc(String ctrCostDescLoc) {
        this.ctrCostDescLoc = ctrCostDescLoc;
    }

    public String getCtaCostDescLoc() {
        return ctaCostDescLoc;
    }

    public void setCtaCostDescLoc(String ctaCostDescLoc) {
        this.ctaCostDescLoc = ctaCostDescLoc;
    }

    public String getCtrComsCobroExp() {
        return ctrComsCobroExp;
    }

    public void setCtrComsCobroExp(String ctrComsCobroExp) {
        this.ctrComsCobroExp = ctrComsCobroExp;
    }

    public String getCtaComsCobroExp() {
        return ctaComsCobroExp;
    }

    public void setCtaComsCobroExp(String ctaComsCobroExp) {
        this.ctaComsCobroExp = ctaComsCobroExp;
    }

    public String getCtrDescVentaLoc() {
        return ctrDescVentaLoc;
    }

    public void setCtrDescVentaLoc(String ctrDescVentaLoc) {
        this.ctrDescVentaLoc = ctrDescVentaLoc;
    }

    public String getCtaDescVentaLoc() {
        return ctaDescVentaLoc;
    }

    public void setCtaDescVentaLoc(String ctaDescVentaLoc) {
        this.ctaDescVentaLoc = ctaDescVentaLoc;
    }

    public String getCtrComsVentaExp() {
        return ctrComsVentaExp;
    }

    public void setCtrComsVentaExp(String ctrComsVentaExp) {
        this.ctrComsVentaExp = ctrComsVentaExp;
    }

    public String getCtaComsVentaExp() {
        return ctaComsVentaExp;
    }

    public void setCtaComsVentaExp(String ctaComsVentaExp) {
        this.ctaComsVentaExp = ctaComsVentaExp;
    }

    public String getCtrConsGasto() {
        return ctrConsGasto;
    }

    public void setCtrConsGasto(String ctrConsGasto) {
        this.ctrConsGasto = ctrConsGasto;
    }

    public String getCtaConsGasto() {
        return ctaConsGasto;
    }

    public void setCtaConsGasto(String ctaConsGasto) {
        this.ctaConsGasto = ctaConsGasto;
    }

    public String getCtrCtoRetAsum() {
        return ctrCtoRetAsum;
    }

    public void setCtrCtoRetAsum(String ctrCtoRetAsum) {
        this.ctrCtoRetAsum = ctrCtoRetAsum;
    }

    public String getCtaCtbRetAsum() {
        return ctaCtbRetAsum;
    }

    public void setCtaCtbRetAsum(String ctaCtbRetAsum) {
        this.ctaCtbRetAsum = ctaCtbRetAsum;
    }

    public String getCtrCompraImp() {
        return ctrCompraImp;
    }

    public void setCtrCompraImp(String ctrCompraImp) {
        this.ctrCompraImp = ctrCompraImp;
    }

    public String getCtaCompraImp() {
        return ctaCompraImp;
    }

    public void setCtaCompraImp(String ctaCompraImp) {
        this.ctaCompraImp = ctaCompraImp;
    }

    public String getCtrCostVentaLoc() {
        return ctrCostVentaLoc;
    }

    public void setCtrCostVentaLoc(String ctrCostVentaLoc) {
        this.ctrCostVentaLoc = ctrCostVentaLoc;
    }

    public String getCtaCostVentaLoc() {
        return ctaCostVentaLoc;
    }

    public void setCtaCostVentaLoc(String ctaCostVentaLoc) {
        this.ctaCostVentaLoc = ctaCostVentaLoc;
    }

    public String getCtrConsNormal() {
        return ctrConsNormal;
    }

    public void setCtrConsNormal(String ctrConsNormal) {
        this.ctrConsNormal = ctrConsNormal;
    }

    public String getCtaConsNormal() {
        return ctaConsNormal;
    }

    public void setCtaConsNormal(String ctaConsNormal) {
        this.ctaConsNormal = ctaConsNormal;
    }

    public String getCtrDevVentasExp() {
        return ctrDevVentasExp;
    }

    public void setCtrDevVentasExp(String ctrDevVentasExp) {
        this.ctrDevVentasExp = ctrDevVentasExp;
    }

    public String getCtaDevVentasExp() {
        return ctaDevVentasExp;
    }

    public void setCtaDevVentasExp(String ctaDevVentasExp) {
        this.ctaDevVentasExp = ctaDevVentasExp;
    }

    public String getCtrInvRemitido() {
        return ctrInvRemitido;
    }

    public void setCtrInvRemitido(String ctrInvRemitido) {
        this.ctrInvRemitido = ctrInvRemitido;
    }

    public String getCtaInvRemitido() {
        return ctaInvRemitido;
    }

    public void setCtaInvRemitido(String ctaInvRemitido) {
        this.ctaInvRemitido = ctaInvRemitido;
    }

    public String getCtrDevVentasLoc() {
        return ctrDevVentasLoc;
    }

    public void setCtrDevVentasLoc(String ctrDevVentasLoc) {
        this.ctrDevVentasLoc = ctrDevVentasLoc;
    }

    public String getCtaDevVentasLoc() {
        return ctaDevVentasLoc;
    }

    public void setCtaDevVentasLoc(String ctaDevVentasLoc) {
        this.ctaDevVentasLoc = ctaDevVentasLoc;
    }

    public String getCtrDescBonifExp() {
        return ctrDescBonifExp;
    }

    public void setCtrDescBonifExp(String ctrDescBonifExp) {
        this.ctrDescBonifExp = ctrDescBonifExp;
    }

    public String getCtaDescBonifExp() {
        return ctaDescBonifExp;
    }

    public void setCtaDescBonifExp(String ctaDescBonifExp) {
        this.ctaDescBonifExp = ctaDescBonifExp;
    }

    public String getCtrSobrInventfis() {
        return ctrSobrInventfis;
    }

    public void setCtrSobrInventfis(String ctrSobrInventfis) {
        this.ctrSobrInventfis = ctrSobrInventfis;
    }

    public String getCtaSobrInventfis() {
        return ctaSobrInventfis;
    }

    public void setCtaSobrInventfis(String ctaSobrInventfis) {
        this.ctaSobrInventfis = ctaSobrInventfis;
    }

    public String getCtrComsVentaLoc() {
        return ctrComsVentaLoc;
    }

    public void setCtrComsVentaLoc(String ctrComsVentaLoc) {
        this.ctrComsVentaLoc = ctrComsVentaLoc;
    }

    public String getCtaComsVentaLoc() {
        return ctaComsVentaLoc;
    }

    public void setCtaComsVentaLoc(String ctaComsVentaLoc) {
        this.ctaComsVentaLoc = ctaComsVentaLoc;
    }

    public String getCtrComsCobroLoc() {
        return ctrComsCobroLoc;
    }

    public void setCtrComsCobroLoc(String ctrComsCobroLoc) {
        this.ctrComsCobroLoc = ctrComsCobroLoc;
    }

    public String getCtaComsCobroLoc() {
        return ctaComsCobroLoc;
    }

    public void setCtaComsCobroLoc(String ctaComsCobroLoc) {
        this.ctaComsCobroLoc = ctaComsCobroLoc;
    }

    public String getCtrConsRetrabajo() {
        return ctrConsRetrabajo;
    }

    public void setCtrConsRetrabajo(String ctrConsRetrabajo) {
        this.ctrConsRetrabajo = ctrConsRetrabajo;
    }

    public String getCtaConsRetrabajo() {
        return ctaConsRetrabajo;
    }

    public void setCtaConsRetrabajo(String ctaConsRetrabajo) {
        this.ctaConsRetrabajo = ctaConsRetrabajo;
    }

    public String getCtrConsDesperdic() {
        return ctrConsDesperdic;
    }

    public void setCtrConsDesperdic(String ctrConsDesperdic) {
        this.ctrConsDesperdic = ctrConsDesperdic;
    }

    public String getCtaConsDesperdic() {
        return ctaConsDesperdic;
    }

    public void setCtaConsDesperdic(String ctaConsDesperdic) {
        this.ctaConsDesperdic = ctaConsDesperdic;
    }

    public String getCtrMatProceso() {
        return ctrMatProceso;
    }

    public void setCtrMatProceso(String ctrMatProceso) {
        this.ctrMatProceso = ctrMatProceso;
    }

    public String getCtaMatProceso() {
        return ctaMatProceso;
    }

    public void setCtaMatProceso(String ctaMatProceso) {
        this.ctaMatProceso = ctaMatProceso;
    }

    public String getCtrVentasExp() {
        return ctrVentasExp;
    }

    public void setCtrVentasExp(String ctrVentasExp) {
        this.ctrVentasExp = ctrVentasExp;
    }

    public String getCtaVentasExp() {
        return ctaVentasExp;
    }

    public void setCtaVentasExp(String ctaVentasExp) {
        this.ctaVentasExp = ctaVentasExp;
    }

    public String getCtrDescVentaExp() {
        return ctrDescVentaExp;
    }

    public void setCtrDescVentaExp(String ctrDescVentaExp) {
        this.ctrDescVentaExp = ctrDescVentaExp;
    }

    public String getCtaDescVentaExp() {
        return ctaDescVentaExp;
    }

    public void setCtaDescVentaExp(String ctaDescVentaExp) {
        this.ctaDescVentaExp = ctaDescVentaExp;
    }

    public CentroCosto getCtrInventario() {
        return ctrInventario;
    }

    public void setCtrInventario(CentroCosto ctrInventario) {
        this.ctrInventario = ctrInventario;
    }

    public CuentaContable getCtaInventario() {
        return ctaInventario;
    }

    public void setCtaInventario(CuentaContable ctaInventario) {
        this.ctaInventario = ctaInventario;
    }

    public String getCtrCostVentaExp() {
        return ctrCostVentaExp;
    }

    public void setCtrCostVentaExp(String ctrCostVentaExp) {
        this.ctrCostVentaExp = ctrCostVentaExp;
    }

    public String getCtaCostVentaExp() {
        return ctaCostVentaExp;
    }

    public void setCtaCostVentaExp(String ctaCostVentaExp) {
        this.ctaCostVentaExp = ctaCostVentaExp;
    }

    public String getCtrFaltInventfis() {
        return ctrFaltInventfis;
    }

    public void setCtrFaltInventfis(String ctrFaltInventfis) {
        this.ctrFaltInventfis = ctrFaltInventfis;
    }

    public String getCtaFaltInventfis() {
        return ctaFaltInventfis;
    }

    public void setCtaFaltInventfis(String ctaFaltInventfis) {
        this.ctaFaltInventfis = ctaFaltInventfis;
    }

    public String getCtrVentasLoc() {
        return ctrVentasLoc;
    }

    public void setCtrVentasLoc(String ctrVentasLoc) {
        this.ctrVentasLoc = ctrVentasLoc;
    }

    public String getCtaVentasLoc() {
        return ctaVentasLoc;
    }

    public void setCtaVentasLoc(String ctaVentasLoc) {
        this.ctaVentasLoc = ctaVentasLoc;
    }

    public String getCtrDescLineaExp() {
        return ctrDescLineaExp;
    }

    public void setCtrDescLineaExp(String ctrDescLineaExp) {
        this.ctrDescLineaExp = ctrDescLineaExp;
    }

    public String getCtaDescLineaExp() {
        return ctaDescLineaExp;
    }

    public void setCtaDescLineaExp(String ctaDescLineaExp) {
        this.ctaDescLineaExp = ctaDescLineaExp;
    }

    public String getCtrVariaCosto() {
        return ctrVariaCosto;
    }

    public void setCtrVariaCosto(String ctrVariaCosto) {
        this.ctrVariaCosto = ctrVariaCosto;
    }

    public String getCtaVariaCosto() {
        return ctaVariaCosto;
    }

    public void setCtaVariaCosto(String ctaVariaCosto) {
        this.ctaVariaCosto = ctaVariaCosto;
    }

    public String getCtrDescLineaLoc() {
        return ctrDescLineaLoc;
    }

    public void setCtrDescLineaLoc(String ctrDescLineaLoc) {
        this.ctrDescLineaLoc = ctrDescLineaLoc;
    }

    public String getCtaDescLineaLoc() {
        return ctaDescLineaLoc;
    }

    public void setCtaDescLineaLoc(String ctaDescLineaLoc) {
        this.ctaDescLineaLoc = ctaDescLineaLoc;
    }

    public String getCtrDescBonifLoc() {
        return ctrDescBonifLoc;
    }

    public void setCtrDescBonifLoc(String ctrDescBonifLoc) {
        this.ctrDescBonifLoc = ctrDescBonifLoc;
    }

    public String getCtaDescBonifLoc() {
        return ctaDescBonifLoc;
    }

    public void setCtaDescBonifLoc(String ctaDescBonifLoc) {
        this.ctaDescBonifLoc = ctaDescBonifLoc;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articuloCuenta != null ? articuloCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticuloCuenta)) {
            return false;
        }
        ArticuloCuenta other = (ArticuloCuenta) object;
        if ((this.articuloCuenta == null && other.articuloCuenta != null) || (this.articuloCuenta != null && !this.articuloCuenta.equals(other.articuloCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.ArticuloCuenta[ articuloCuenta=" + articuloCuenta + " ]";
    }

}
