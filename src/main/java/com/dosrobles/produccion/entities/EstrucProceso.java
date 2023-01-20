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
@Table(name = "ESTRUC_PROCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstrucProceso.findAll", query = "SELECT e FROM EstrucProceso e")
    , @NamedQuery(name = "EstrucProceso.findByArticulo", query = "SELECT e FROM EstrucProceso e WHERE e.estrucProcesoPK.articulo = :articulo")
    , @NamedQuery(name = "EstrucProceso.findByVersion", query = "SELECT e FROM EstrucProceso e WHERE e.estrucProcesoPK.version = :version")
    , @NamedQuery(name = "EstrucProceso.findByOperacion", query = "SELECT e FROM EstrucProceso e WHERE e.estrucProcesoPK.operacion = :operacion")
    , @NamedQuery(name = "EstrucProceso.findByDescripcion", query = "SELECT e FROM EstrucProceso e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "EstrucProceso.findByEquipo", query = "SELECT e FROM EstrucProceso e WHERE e.equipo = :equipo")
    , @NamedQuery(name = "EstrucProceso.findByFactAprovecha", query = "SELECT e FROM EstrucProceso e WHERE e.factAprovecha = :factAprovecha")
    , @NamedQuery(name = "EstrucProceso.findByReportaProd", query = "SELECT e FROM EstrucProceso e WHERE e.reportaProd = :reportaProd")
    , @NamedQuery(name = "EstrucProceso.findByHorasStdMop", query = "SELECT e FROM EstrucProceso e WHERE e.horasStdMop = :horasStdMop")
    , @NamedQuery(name = "EstrucProceso.findByHorasStdMoe", query = "SELECT e FROM EstrucProceso e WHERE e.horasStdMoe = :horasStdMoe")
    , @NamedQuery(name = "EstrucProceso.findByHorasStdMaq", query = "SELECT e FROM EstrucProceso e WHERE e.horasStdMaq = :horasStdMaq")
    , @NamedQuery(name = "EstrucProceso.findByEmpMop", query = "SELECT e FROM EstrucProceso e WHERE e.empMop = :empMop")
    , @NamedQuery(name = "EstrucProceso.findByEmpMoe", query = "SELECT e FROM EstrucProceso e WHERE e.empMoe = :empMoe")
    , @NamedQuery(name = "EstrucProceso.findByUnidProduccionPp", query = "SELECT e FROM EstrucProceso e WHERE e.unidProduccionPp = :unidProduccionPp")
    , @NamedQuery(name = "EstrucProceso.findByCantProducidaPp", query = "SELECT e FROM EstrucProceso e WHERE e.cantProducidaPp = :cantProducidaPp")
    , @NamedQuery(name = "EstrucProceso.findByCantProducidaPt", query = "SELECT e FROM EstrucProceso e WHERE e.cantProducidaPt = :cantProducidaPt")
    , @NamedQuery(name = "EstrucProceso.findByCantCalidad", query = "SELECT e FROM EstrucProceso e WHERE e.cantCalidad = :cantCalidad")
    , @NamedQuery(name = "EstrucProceso.findByPoliticaKanBan", query = "SELECT e FROM EstrucProceso e WHERE e.politicaKanBan = :politicaKanBan")
    , @NamedQuery(name = "EstrucProceso.findByEquivalenciaKBan", query = "SELECT e FROM EstrucProceso e WHERE e.equivalenciaKBan = :equivalenciaKBan")
    , @NamedQuery(name = "EstrucProceso.findByCostoMopHora", query = "SELECT e FROM EstrucProceso e WHERE e.costoMopHora = :costoMopHora")
    , @NamedQuery(name = "EstrucProceso.findByCostoMoeHora", query = "SELECT e FROM EstrucProceso e WHERE e.costoMoeHora = :costoMoeHora")
    , @NamedQuery(name = "EstrucProceso.findByCostoGifHora", query = "SELECT e FROM EstrucProceso e WHERE e.costoGifHora = :costoGifHora")
    , @NamedQuery(name = "EstrucProceso.findByCostoMopUnidad", query = "SELECT e FROM EstrucProceso e WHERE e.costoMopUnidad = :costoMopUnidad")
    , @NamedQuery(name = "EstrucProceso.findByCostoMoeUnidad", query = "SELECT e FROM EstrucProceso e WHERE e.costoMoeUnidad = :costoMoeUnidad")
    , @NamedQuery(name = "EstrucProceso.findByCostoGifUnidad", query = "SELECT e FROM EstrucProceso e WHERE e.costoGifUnidad = :costoGifUnidad")
    , @NamedQuery(name = "EstrucProceso.findByCostoEstimMat", query = "SELECT e FROM EstrucProceso e WHERE e.costoEstimMat = :costoEstimMat")
    , @NamedQuery(name = "EstrucProceso.findByCostoEstimMop", query = "SELECT e FROM EstrucProceso e WHERE e.costoEstimMop = :costoEstimMop")
    , @NamedQuery(name = "EstrucProceso.findByCostoEstimMoe", query = "SELECT e FROM EstrucProceso e WHERE e.costoEstimMoe = :costoEstimMoe")
    , @NamedQuery(name = "EstrucProceso.findByCostoEstimGif", query = "SELECT e FROM EstrucProceso e WHERE e.costoEstimGif = :costoEstimGif")
    , @NamedQuery(name = "EstrucProceso.findByCostoTranMat", query = "SELECT e FROM EstrucProceso e WHERE e.costoTranMat = :costoTranMat")
    , @NamedQuery(name = "EstrucProceso.findByCostoTranMop", query = "SELECT e FROM EstrucProceso e WHERE e.costoTranMop = :costoTranMop")
    , @NamedQuery(name = "EstrucProceso.findByCostoTranMoe", query = "SELECT e FROM EstrucProceso e WHERE e.costoTranMoe = :costoTranMoe")
    , @NamedQuery(name = "EstrucProceso.findByCostoTranGif", query = "SELECT e FROM EstrucProceso e WHERE e.costoTranGif = :costoTranGif")
    , @NamedQuery(name = "EstrucProceso.findByCostoOprodMat", query = "SELECT e FROM EstrucProceso e WHERE e.costoOprodMat = :costoOprodMat")
    , @NamedQuery(name = "EstrucProceso.findByCostoOprodMop", query = "SELECT e FROM EstrucProceso e WHERE e.costoOprodMop = :costoOprodMop")
    , @NamedQuery(name = "EstrucProceso.findByCostoOprodMoe", query = "SELECT e FROM EstrucProceso e WHERE e.costoOprodMoe = :costoOprodMoe")
    , @NamedQuery(name = "EstrucProceso.findByCostoOprodGif", query = "SELECT e FROM EstrucProceso e WHERE e.costoOprodGif = :costoOprodGif")
    , @NamedQuery(name = "EstrucProceso.findByPorcMop", query = "SELECT e FROM EstrucProceso e WHERE e.porcMop = :porcMop")
    , @NamedQuery(name = "EstrucProceso.findByPorcMoe", query = "SELECT e FROM EstrucProceso e WHERE e.porcMoe = :porcMoe")
    , @NamedQuery(name = "EstrucProceso.findByPorcGif", query = "SELECT e FROM EstrucProceso e WHERE e.porcGif = :porcGif")
    , @NamedQuery(name = "EstrucProceso.findBySecuencia", query = "SELECT e FROM EstrucProceso e WHERE e.secuencia = :secuencia")
    , @NamedQuery(name = "EstrucProceso.findByHoraInicio", query = "SELECT e FROM EstrucProceso e WHERE e.horaInicio = :horaInicio")
    , @NamedQuery(name = "EstrucProceso.findByHoraFin", query = "SELECT e FROM EstrucProceso e WHERE e.horaFin = :horaFin")
    , @NamedQuery(name = "EstrucProceso.findByTiempoMaxEspera", query = "SELECT e FROM EstrucProceso e WHERE e.tiempoMaxEspera = :tiempoMaxEspera")
    , @NamedQuery(name = "EstrucProceso.findByTiempoMinEspera", query = "SELECT e FROM EstrucProceso e WHERE e.tiempoMinEspera = :tiempoMinEspera")
    , @NamedQuery(name = "EstrucProceso.findByReportaMo", query = "SELECT e FROM EstrucProceso e WHERE e.reportaMo = :reportaMo")
    , @NamedQuery(name = "EstrucProceso.findByReportaGif", query = "SELECT e FROM EstrucProceso e WHERE e.reportaGif = :reportaGif")
    , @NamedQuery(name = "EstrucProceso.findByReportaConsumo", query = "SELECT e FROM EstrucProceso e WHERE e.reportaConsumo = :reportaConsumo")
    , @NamedQuery(name = "EstrucProceso.findByNoteExistsFlag", query = "SELECT e FROM EstrucProceso e WHERE e.noteExistsFlag = :noteExistsFlag")
    , @NamedQuery(name = "EstrucProceso.findByRecordDate", query = "SELECT e FROM EstrucProceso e WHERE e.recordDate = :recordDate")
    , @NamedQuery(name = "EstrucProceso.findByRowPointer", query = "SELECT e FROM EstrucProceso e WHERE e.rowPointer = :rowPointer")
    , @NamedQuery(name = "EstrucProceso.findByCreatedBy", query = "SELECT e FROM EstrucProceso e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EstrucProceso.findByUpdatedBy", query = "SELECT e FROM EstrucProceso e WHERE e.updatedBy = :updatedBy")
    , @NamedQuery(name = "EstrucProceso.findByCreateDate", query = "SELECT e FROM EstrucProceso e WHERE e.createDate = :createDate")})
public class EstrucProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstrucProcesoPK estrucProcesoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "EQUIPO")
    private String equipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACT_APROVECHA")
    private BigDecimal factAprovecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REPORTA_PROD")
    private String reportaProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS_STD_MOP")
    private BigDecimal horasStdMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS_STD_MOE")
    private BigDecimal horasStdMoe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS_STD_MAQ")
    private BigDecimal horasStdMaq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_MOP")
    private short empMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_MOE")
    private short empMoe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "UNID_PRODUCCION_PP")
    private String unidProduccionPp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_PRODUCIDA_PP")
    private BigDecimal cantProducidaPp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_PRODUCIDA_PT")
    private BigDecimal cantProducidaPt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANT_CALIDAD")
    private BigDecimal cantCalidad;
    @Size(max = 20)
    @Column(name = "POLITICA_KAN_BAN")
    private String politicaKanBan;
    @Column(name = "EQUIVALENCIA_K_BAN")
    private BigDecimal equivalenciaKBan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_MOP_HORA")
    private BigDecimal costoMopHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_MOE_HORA")
    private BigDecimal costoMoeHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_GIF_HORA")
    private BigDecimal costoGifHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_MOP_UNIDAD")
    private BigDecimal costoMopUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_MOE_UNIDAD")
    private BigDecimal costoMoeUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_GIF_UNIDAD")
    private BigDecimal costoGifUnidad;
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
    @Column(name = "COSTO_TRAN_MAT")
    private BigDecimal costoTranMat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TRAN_MOP")
    private BigDecimal costoTranMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TRAN_MOE")
    private BigDecimal costoTranMoe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_TRAN_GIF")
    private BigDecimal costoTranGif;
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
    @Column(name = "PORC_MOP")
    private BigDecimal porcMop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_MOE")
    private BigDecimal porcMoe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_GIF")
    private BigDecimal porcGif;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NOTAS")
    private String notas;
    @Column(name = "SECUENCIA")
    private Short secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_INICIO")
    private BigDecimal horaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA_FIN")
    private BigDecimal horaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_MAX_ESPERA")
    private BigDecimal tiempoMaxEspera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_MIN_ESPERA")
    private BigDecimal tiempoMinEspera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REPORTA_MO")
    private String reportaMo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REPORTA_GIF")
    private String reportaGif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REPORTA_CONSUMO")
    private String reportaConsumo;
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

    public EstrucProceso() {
    }

    public EstrucProceso(EstrucProcesoPK estrucProcesoPK) {
        this.estrucProcesoPK = estrucProcesoPK;
    }

    public EstrucProceso(EstrucProcesoPK estrucProcesoPK, String descripcion, BigDecimal factAprovecha, String reportaProd, BigDecimal horasStdMop, BigDecimal horasStdMoe, BigDecimal horasStdMaq, short empMop, short empMoe, String unidProduccionPp, BigDecimal cantProducidaPp, BigDecimal cantProducidaPt, BigDecimal cantCalidad, BigDecimal costoMopHora, BigDecimal costoMoeHora, BigDecimal costoGifHora, BigDecimal costoMopUnidad, BigDecimal costoMoeUnidad, BigDecimal costoGifUnidad, BigDecimal costoEstimMat, BigDecimal costoEstimMop, BigDecimal costoEstimMoe, BigDecimal costoEstimGif, BigDecimal costoTranMat, BigDecimal costoTranMop, BigDecimal costoTranMoe, BigDecimal costoTranGif, BigDecimal costoOprodMat, BigDecimal costoOprodMop, BigDecimal costoOprodMoe, BigDecimal costoOprodGif, BigDecimal porcMop, BigDecimal porcMoe, BigDecimal porcGif, BigDecimal horaInicio, BigDecimal horaFin, BigDecimal tiempoMaxEspera, BigDecimal tiempoMinEspera, String reportaMo, String reportaGif, String reportaConsumo, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.estrucProcesoPK = estrucProcesoPK;
        this.descripcion = descripcion;
        this.factAprovecha = factAprovecha;
        this.reportaProd = reportaProd;
        this.horasStdMop = horasStdMop;
        this.horasStdMoe = horasStdMoe;
        this.horasStdMaq = horasStdMaq;
        this.empMop = empMop;
        this.empMoe = empMoe;
        this.unidProduccionPp = unidProduccionPp;
        this.cantProducidaPp = cantProducidaPp;
        this.cantProducidaPt = cantProducidaPt;
        this.cantCalidad = cantCalidad;
        this.costoMopHora = costoMopHora;
        this.costoMoeHora = costoMoeHora;
        this.costoGifHora = costoGifHora;
        this.costoMopUnidad = costoMopUnidad;
        this.costoMoeUnidad = costoMoeUnidad;
        this.costoGifUnidad = costoGifUnidad;
        this.costoEstimMat = costoEstimMat;
        this.costoEstimMop = costoEstimMop;
        this.costoEstimMoe = costoEstimMoe;
        this.costoEstimGif = costoEstimGif;
        this.costoTranMat = costoTranMat;
        this.costoTranMop = costoTranMop;
        this.costoTranMoe = costoTranMoe;
        this.costoTranGif = costoTranGif;
        this.costoOprodMat = costoOprodMat;
        this.costoOprodMop = costoOprodMop;
        this.costoOprodMoe = costoOprodMoe;
        this.costoOprodGif = costoOprodGif;
        this.porcMop = porcMop;
        this.porcMoe = porcMoe;
        this.porcGif = porcGif;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tiempoMaxEspera = tiempoMaxEspera;
        this.tiempoMinEspera = tiempoMinEspera;
        this.reportaMo = reportaMo;
        this.reportaGif = reportaGif;
        this.reportaConsumo = reportaConsumo;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
    }

    public EstrucProceso(String articulo, String version, String operacion) {
        this.estrucProcesoPK = new EstrucProcesoPK(articulo, version, operacion);
    }

    public EstrucProcesoPK getEstrucProcesoPK() {
        return estrucProcesoPK;
    }

    public void setEstrucProcesoPK(EstrucProcesoPK estrucProcesoPK) {
        this.estrucProcesoPK = estrucProcesoPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public BigDecimal getFactAprovecha() {
        return factAprovecha;
    }

    public void setFactAprovecha(BigDecimal factAprovecha) {
        this.factAprovecha = factAprovecha;
    }

    public String getReportaProd() {
        return reportaProd;
    }

    public void setReportaProd(String reportaProd) {
        this.reportaProd = reportaProd;
    }

    public BigDecimal getHorasStdMop() {
        return horasStdMop;
    }

    public void setHorasStdMop(BigDecimal horasStdMop) {
        this.horasStdMop = horasStdMop;
    }

    public BigDecimal getHorasStdMoe() {
        return horasStdMoe;
    }

    public void setHorasStdMoe(BigDecimal horasStdMoe) {
        this.horasStdMoe = horasStdMoe;
    }

    public BigDecimal getHorasStdMaq() {
        return horasStdMaq;
    }

    public void setHorasStdMaq(BigDecimal horasStdMaq) {
        this.horasStdMaq = horasStdMaq;
    }

    public short getEmpMop() {
        return empMop;
    }

    public void setEmpMop(short empMop) {
        this.empMop = empMop;
    }

    public short getEmpMoe() {
        return empMoe;
    }

    public void setEmpMoe(short empMoe) {
        this.empMoe = empMoe;
    }

    public String getUnidProduccionPp() {
        return unidProduccionPp;
    }

    public void setUnidProduccionPp(String unidProduccionPp) {
        this.unidProduccionPp = unidProduccionPp;
    }

    public BigDecimal getCantProducidaPp() {
        return cantProducidaPp;
    }

    public void setCantProducidaPp(BigDecimal cantProducidaPp) {
        this.cantProducidaPp = cantProducidaPp;
    }

    public BigDecimal getCantProducidaPt() {
        return cantProducidaPt;
    }

    public void setCantProducidaPt(BigDecimal cantProducidaPt) {
        this.cantProducidaPt = cantProducidaPt;
    }

    public BigDecimal getCantCalidad() {
        return cantCalidad;
    }

    public void setCantCalidad(BigDecimal cantCalidad) {
        this.cantCalidad = cantCalidad;
    }

    public String getPoliticaKanBan() {
        return politicaKanBan;
    }

    public void setPoliticaKanBan(String politicaKanBan) {
        this.politicaKanBan = politicaKanBan;
    }

    public BigDecimal getEquivalenciaKBan() {
        return equivalenciaKBan;
    }

    public void setEquivalenciaKBan(BigDecimal equivalenciaKBan) {
        this.equivalenciaKBan = equivalenciaKBan;
    }

    public BigDecimal getCostoMopHora() {
        return costoMopHora;
    }

    public void setCostoMopHora(BigDecimal costoMopHora) {
        this.costoMopHora = costoMopHora;
    }

    public BigDecimal getCostoMoeHora() {
        return costoMoeHora;
    }

    public void setCostoMoeHora(BigDecimal costoMoeHora) {
        this.costoMoeHora = costoMoeHora;
    }

    public BigDecimal getCostoGifHora() {
        return costoGifHora;
    }

    public void setCostoGifHora(BigDecimal costoGifHora) {
        this.costoGifHora = costoGifHora;
    }

    public BigDecimal getCostoMopUnidad() {
        return costoMopUnidad;
    }

    public void setCostoMopUnidad(BigDecimal costoMopUnidad) {
        this.costoMopUnidad = costoMopUnidad;
    }

    public BigDecimal getCostoMoeUnidad() {
        return costoMoeUnidad;
    }

    public void setCostoMoeUnidad(BigDecimal costoMoeUnidad) {
        this.costoMoeUnidad = costoMoeUnidad;
    }

    public BigDecimal getCostoGifUnidad() {
        return costoGifUnidad;
    }

    public void setCostoGifUnidad(BigDecimal costoGifUnidad) {
        this.costoGifUnidad = costoGifUnidad;
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

    public BigDecimal getCostoTranMat() {
        return costoTranMat;
    }

    public void setCostoTranMat(BigDecimal costoTranMat) {
        this.costoTranMat = costoTranMat;
    }

    public BigDecimal getCostoTranMop() {
        return costoTranMop;
    }

    public void setCostoTranMop(BigDecimal costoTranMop) {
        this.costoTranMop = costoTranMop;
    }

    public BigDecimal getCostoTranMoe() {
        return costoTranMoe;
    }

    public void setCostoTranMoe(BigDecimal costoTranMoe) {
        this.costoTranMoe = costoTranMoe;
    }

    public BigDecimal getCostoTranGif() {
        return costoTranGif;
    }

    public void setCostoTranGif(BigDecimal costoTranGif) {
        this.costoTranGif = costoTranGif;
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

    public BigDecimal getPorcMop() {
        return porcMop;
    }

    public void setPorcMop(BigDecimal porcMop) {
        this.porcMop = porcMop;
    }

    public BigDecimal getPorcMoe() {
        return porcMoe;
    }

    public void setPorcMoe(BigDecimal porcMoe) {
        this.porcMoe = porcMoe;
    }

    public BigDecimal getPorcGif() {
        return porcGif;
    }

    public void setPorcGif(BigDecimal porcGif) {
        this.porcGif = porcGif;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Short getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Short secuencia) {
        this.secuencia = secuencia;
    }

    public BigDecimal getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(BigDecimal horaInicio) {
        this.horaInicio = horaInicio;
    }

    public BigDecimal getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(BigDecimal horaFin) {
        this.horaFin = horaFin;
    }

    public BigDecimal getTiempoMaxEspera() {
        return tiempoMaxEspera;
    }

    public void setTiempoMaxEspera(BigDecimal tiempoMaxEspera) {
        this.tiempoMaxEspera = tiempoMaxEspera;
    }

    public BigDecimal getTiempoMinEspera() {
        return tiempoMinEspera;
    }

    public void setTiempoMinEspera(BigDecimal tiempoMinEspera) {
        this.tiempoMinEspera = tiempoMinEspera;
    }

    public String getReportaMo() {
        return reportaMo;
    }

    public void setReportaMo(String reportaMo) {
        this.reportaMo = reportaMo;
    }

    public String getReportaGif() {
        return reportaGif;
    }

    public void setReportaGif(String reportaGif) {
        this.reportaGif = reportaGif;
    }

    public String getReportaConsumo() {
        return reportaConsumo;
    }

    public void setReportaConsumo(String reportaConsumo) {
        this.reportaConsumo = reportaConsumo;
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
        hash += (estrucProcesoPK != null ? estrucProcesoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucProceso)) {
            return false;
        }
        EstrucProceso other = (EstrucProceso) object;
        if ((this.estrucProcesoPK == null && other.estrucProcesoPK != null) || (this.estrucProcesoPK != null && !this.estrucProcesoPK.equals(other.estrucProcesoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucProceso[ estrucProcesoPK=" + estrucProcesoPK + " ]";
    }
    
}
