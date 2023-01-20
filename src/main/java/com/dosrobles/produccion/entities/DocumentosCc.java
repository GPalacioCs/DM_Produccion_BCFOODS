/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "DOCUMENTOS_CC", schema = "BCFOODS")
@NamedQueries({
    @NamedQuery(name = "DocumentosCc.findAll", query = "SELECT d FROM DocumentosCc d")})
public class DocumentosCc implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentosCcPK documentosCcPK;
    @Column(name = "CONTRARECIBO")
    private String contrarecibo;
    @Basic(optional = false)
    @Column(name = "APLICACION")
    private String aplicacion;
    @Basic(optional = false)
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumento;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Basic(optional = false)
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @Column(name = "MONTO_LOCAL")
    private BigDecimal montoLocal;
    @Basic(optional = false)
    @Column(name = "SALDO_LOCAL")
    private BigDecimal saldoLocal;
    @Basic(optional = false)
    @Column(name = "MONTO_DOLAR")
    private BigDecimal montoDolar;
    @Basic(optional = false)
    @Column(name = "SALDO_DOLAR")
    private BigDecimal saldoDolar;
    @Basic(optional = false)
    @Column(name = "MONTO_CLIENTE")
    private BigDecimal montoCliente;
    @Basic(optional = false)
    @Column(name = "SALDO_CLIENTE")
    private BigDecimal saldoCliente;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMBIO_MONEDA")
    private BigDecimal tipoCambioMoneda;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMBIO_DOLAR")
    private BigDecimal tipoCambioDolar;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMBIO_CLIENT")
    private BigDecimal tipoCambioClient;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_LOC")
    private BigDecimal tipoCambActLoc;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_DOL")
    private BigDecimal tipoCambActDol;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_CLI")
    private BigDecimal tipoCambActCli;
    @Basic(optional = false)
    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;
    @Basic(optional = false)
    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    @Basic(optional = false)
    @Column(name = "IMPUESTO1")
    private BigDecimal impuesto1;
    @Basic(optional = false)
    @Column(name = "IMPUESTO2")
    private BigDecimal impuesto2;
    @Basic(optional = false)
    @Column(name = "RUBRO1")
    private BigDecimal rubro1;
    @Basic(optional = false)
    @Column(name = "RUBRO2")
    private BigDecimal rubro2;
    @Basic(optional = false)
    @Column(name = "MONTO_RETENCION")
    private BigDecimal montoRetencion;
    @Basic(optional = false)
    @Column(name = "SALDO_RETENCION")
    private BigDecimal saldoRetencion;
    @Basic(optional = false)
    @Column(name = "DEPENDIENTE")
    private String dependiente;
    @Basic(optional = false)
    @Column(name = "FECHA_ULT_CREDITO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltCredito;
    @Basic(optional = false)
    @Column(name = "CARGADO_DE_FACT")
    private String cargadoDeFact;
    @Basic(optional = false)
    @Column(name = "APROBADO")
    private String aprobado;
    @Column(name = "ASIENTO")
    private String asiento;
    @Basic(optional = false)
    @Column(name = "ASIENTO_PENDIENTE")
    private String asientoPendiente;
    @Basic(optional = false)
    @Column(name = "FECHA_ULT_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltMod;
    @Lob
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @Column(name = "CLASE_DOCUMENTO")
    private String claseDocumento;
    @Basic(optional = false)
    @Column(name = "FECHA_VENCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVence;
    @Basic(optional = false)
    @Column(name = "NUM_PARCIALIDADES")
    private short numParcialidades;
    @Column(name = "FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Basic(optional = false)
    @Column(name = "USUARIO_ULT_MOD")
    private String usuarioUltMod;
    @Basic(optional = false)
    @Column(name = "PORC_INTCTE")
    private BigDecimal porcIntcte;
    @Column(name = "CONTRATO")
    private String contrato;
    @Column(name = "TIPO_DOC_ORIGEN")
    private String tipoDocOrigen;
    @Column(name = "DOC_DOC_ORIGEN")
    private String docDocOrigen;
    @Column(name = "FECHA_ANUL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnul;
    @Column(name = "AUD_USUARIO_ANUL")
    private String audUsuarioAnul;
    @Column(name = "AUD_FECHA_ANUL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFechaAnul;
    @Column(name = "NUM_DOC_CB")
    private BigInteger numDocCb;
    @Column(name = "FECHA_COBRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobro;
    @Column(name = "AUDITORIA_COBRO")
    private String auditoriaCobro;
    @Column(name = "FECHA_SEGUIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSeguimiento;
    @Column(name = "OBSERVACIONES_COBRO")
    private String observacionesCobro;
    @Column(name = "USUARIO_APROBACION")
    private String usuarioAprobacion;
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @Column(name = "ANULADO")
    private String anulado;
    @Column(name = "BASE_IMPUESTO1")
    private BigDecimal baseImpuesto1;
    @Column(name = "BASE_IMPUESTO2")
    private BigDecimal baseImpuesto2;
    @Basic(optional = false)
    @Column(name = "DEPENDIENTE_GP")
    private String dependienteGp;
    @Basic(optional = false)
    @Column(name = "SALDO_TRANS")
    private BigDecimal saldoTrans;
    @Basic(optional = false)
    @Column(name = "SALDO_TRANS_LOCAL")
    private BigDecimal saldoTransLocal;
    @Basic(optional = false)
    @Column(name = "SALDO_TRANS_DOLAR")
    private BigDecimal saldoTransDolar;
    @Column(name = "FECHA_PROYECTADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProyectada;
    @Column(name = "PORC_RECUPERACION")
    private BigDecimal porcRecuperacion;
    @Basic(optional = false)
    @Column(name = "SALDO_TRANS_CLI")
    private BigDecimal saldoTransCli;
    @Column(name = "DOCUMENTO_FISCAL")
    private String documentoFiscal;
    @Basic(optional = false)
    @Column(name = "FACTURADO")
    private String facturado;
    @Basic(optional = false)
    @Column(name = "GENERA_DOC_FE")
    private String generaDocFe;
    @Column(name = "TASA_IMPOSITIVA")
    private String tasaImpositiva;
    @Column(name = "TASA_IMPOSITIVA_PORC")
    private BigDecimal tasaImpositivaPorc;
    @Column(name = "TASA_CREE1")
    private String tasaCree1;
    @Column(name = "TASA_CREE1_PORC")
    private BigDecimal tasaCree1Porc;
    @Column(name = "TASA_CREE2")
    private String tasaCree2;
    @Column(name = "TASA_CREE2_PORC")
    private BigDecimal tasaCree2Porc;
    @Column(name = "TASA_GAN_OCASIONAL_PORC")
    private BigDecimal tasaGanOcasionalPorc;
    @Basic(optional = false)
    @Column(name = "NoteExistsFlag", insertable = false, updatable = false)
    private short noteExistsFlag;
    @Basic(optional = false)
    @Column(name = "RecordDate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Basic(optional = false)
    @Column(name = "RowPointer", insertable = false, updatable = false)
    private String rowPointer;
    @Basic(optional = false)
    @Column(name = "CreatedBy", insertable = false, updatable = false)
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "UpdatedBy", insertable = false, updatable = false)
    private String updatedBy;
    @Basic(optional = false)
    @Column(name = "CreateDate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "ENTIDAD_FINANCIERA")
    private String entidadFinanciera;
    @Column(name = "LIQUIDACION")
    private String liquidacion;
    @Basic(optional = false)
    @Column(name = "CLIENTE_REPORTE")
    private String clienteReporte;
    @Basic(optional = false)
    @Column(name = "CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @Column(name = "CLIENTE_ORIGEN")
    private String clienteOrigen;
    @Column(name = "COBRADOR")
    private String cobrador;
    @Basic(optional = false)
    @Column(name = "CONDICION_PAGO")
    private String condicionPago;
    @Column(name = "CTA_BANCARIA")
    private String ctaBancaria;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "DIVISION_GEOGRAFICA1")
    private String divisionGeografica1;
    @Column(name = "DIVISION_GEOGRAFICA2")
    private String divisionGeografica2;
    @Column(name = "CODIGO_IMPUESTO")
    private String codigoImpuesto;
    @Basic(optional = false)
    @Column(name = "MONEDA")
    private String moneda;
    @Column(name = "PAQUETE")
    private String paquete;
    @Column(name = "TIPO_ASIENTO")
    private String tipoAsiento;
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Basic(optional = false)
    @Column(name = "TIPO", insertable = false, updatable = false)
    private String tipo1;
    @Column(name = "SUBTIPO")
    private Short subtipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentosCc", fetch = FetchType.LAZY)
    private List<DocumentoAnticipo> documentoAnticipoList;

    public DocumentosCc() {
    }

    public DocumentosCc(DocumentosCcPK documentosCcPK) {
        this.documentosCcPK = documentosCcPK;
    }

    public DocumentosCc(DocumentosCcPK documentosCcPK, String aplicacion, Date fechaDocumento, Date fecha, BigDecimal monto, BigDecimal saldo, BigDecimal montoLocal, BigDecimal saldoLocal, BigDecimal montoDolar, BigDecimal saldoDolar, BigDecimal montoCliente, BigDecimal saldoCliente, BigDecimal tipoCambioMoneda, BigDecimal tipoCambioDolar, BigDecimal tipoCambioClient, BigDecimal tipoCambActLoc, BigDecimal tipoCambActDol, BigDecimal tipoCambActCli, BigDecimal subtotal, BigDecimal descuento, BigDecimal impuesto1, BigDecimal impuesto2, BigDecimal rubro1, BigDecimal rubro2, BigDecimal montoRetencion, BigDecimal saldoRetencion, String dependiente, Date fechaUltCredito, String cargadoDeFact, String aprobado, String asientoPendiente, Date fechaUltMod, String claseDocumento, Date fechaVence, short numParcialidades, String usuarioUltMod, BigDecimal porcIntcte, String dependienteGp, BigDecimal saldoTrans, BigDecimal saldoTransLocal, BigDecimal saldoTransDolar, BigDecimal saldoTransCli, String facturado, String generaDocFe, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate, String clienteReporte, String cliente, String clienteOrigen, String condicionPago, String moneda, String tipo1) {
        this.documentosCcPK = documentosCcPK;
        this.aplicacion = aplicacion;
        this.fechaDocumento = fechaDocumento;
        this.fecha = fecha;
        this.monto = monto;
        this.saldo = saldo;
        this.montoLocal = montoLocal;
        this.saldoLocal = saldoLocal;
        this.montoDolar = montoDolar;
        this.saldoDolar = saldoDolar;
        this.montoCliente = montoCliente;
        this.saldoCliente = saldoCliente;
        this.tipoCambioMoneda = tipoCambioMoneda;
        this.tipoCambioDolar = tipoCambioDolar;
        this.tipoCambioClient = tipoCambioClient;
        this.tipoCambActLoc = tipoCambActLoc;
        this.tipoCambActDol = tipoCambActDol;
        this.tipoCambActCli = tipoCambActCli;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.impuesto1 = impuesto1;
        this.impuesto2 = impuesto2;
        this.rubro1 = rubro1;
        this.rubro2 = rubro2;
        this.montoRetencion = montoRetencion;
        this.saldoRetencion = saldoRetencion;
        this.dependiente = dependiente;
        this.fechaUltCredito = fechaUltCredito;
        this.cargadoDeFact = cargadoDeFact;
        this.aprobado = aprobado;
        this.asientoPendiente = asientoPendiente;
        this.fechaUltMod = fechaUltMod;
        this.claseDocumento = claseDocumento;
        this.fechaVence = fechaVence;
        this.numParcialidades = numParcialidades;
        this.usuarioUltMod = usuarioUltMod;
        this.porcIntcte = porcIntcte;
        this.dependienteGp = dependienteGp;
        this.saldoTrans = saldoTrans;
        this.saldoTransLocal = saldoTransLocal;
        this.saldoTransDolar = saldoTransDolar;
        this.saldoTransCli = saldoTransCli;
        this.facturado = facturado;
        this.generaDocFe = generaDocFe;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
        this.clienteReporte = clienteReporte;
        this.cliente = cliente;
        this.clienteOrigen = clienteOrigen;
        this.condicionPago = condicionPago;
        this.moneda = moneda;
        this.tipo1 = tipo1;
    }

    public DocumentosCc(String documento, String tipo) {
        this.documentosCcPK = new DocumentosCcPK(documento, tipo);
    }

    public DocumentosCcPK getDocumentosCcPK() {
        return documentosCcPK;
    }

    public void setDocumentosCcPK(DocumentosCcPK documentosCcPK) {
        this.documentosCcPK = documentosCcPK;
    }

    public String getContrarecibo() {
        return contrarecibo;
    }

    public void setContrarecibo(String contrarecibo) {
        this.contrarecibo = contrarecibo;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getMontoLocal() {
        return montoLocal;
    }

    public void setMontoLocal(BigDecimal montoLocal) {
        this.montoLocal = montoLocal;
    }

    public BigDecimal getSaldoLocal() {
        return saldoLocal;
    }

    public void setSaldoLocal(BigDecimal saldoLocal) {
        this.saldoLocal = saldoLocal;
    }

    public BigDecimal getMontoDolar() {
        return montoDolar;
    }

    public void setMontoDolar(BigDecimal montoDolar) {
        this.montoDolar = montoDolar;
    }

    public BigDecimal getSaldoDolar() {
        return saldoDolar;
    }

    public void setSaldoDolar(BigDecimal saldoDolar) {
        this.saldoDolar = saldoDolar;
    }

    public BigDecimal getMontoCliente() {
        return montoCliente;
    }

    public void setMontoCliente(BigDecimal montoCliente) {
        this.montoCliente = montoCliente;
    }

    public BigDecimal getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(BigDecimal saldoCliente) {
        this.saldoCliente = saldoCliente;
    }

    public BigDecimal getTipoCambioMoneda() {
        return tipoCambioMoneda;
    }

    public void setTipoCambioMoneda(BigDecimal tipoCambioMoneda) {
        this.tipoCambioMoneda = tipoCambioMoneda;
    }

    public BigDecimal getTipoCambioDolar() {
        return tipoCambioDolar;
    }

    public void setTipoCambioDolar(BigDecimal tipoCambioDolar) {
        this.tipoCambioDolar = tipoCambioDolar;
    }

    public BigDecimal getTipoCambioClient() {
        return tipoCambioClient;
    }

    public void setTipoCambioClient(BigDecimal tipoCambioClient) {
        this.tipoCambioClient = tipoCambioClient;
    }

    public BigDecimal getTipoCambActLoc() {
        return tipoCambActLoc;
    }

    public void setTipoCambActLoc(BigDecimal tipoCambActLoc) {
        this.tipoCambActLoc = tipoCambActLoc;
    }

    public BigDecimal getTipoCambActDol() {
        return tipoCambActDol;
    }

    public void setTipoCambActDol(BigDecimal tipoCambActDol) {
        this.tipoCambActDol = tipoCambActDol;
    }

    public BigDecimal getTipoCambActCli() {
        return tipoCambActCli;
    }

    public void setTipoCambActCli(BigDecimal tipoCambActCli) {
        this.tipoCambActCli = tipoCambActCli;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuesto1() {
        return impuesto1;
    }

    public void setImpuesto1(BigDecimal impuesto1) {
        this.impuesto1 = impuesto1;
    }

    public BigDecimal getImpuesto2() {
        return impuesto2;
    }

    public void setImpuesto2(BigDecimal impuesto2) {
        this.impuesto2 = impuesto2;
    }

    public BigDecimal getRubro1() {
        return rubro1;
    }

    public void setRubro1(BigDecimal rubro1) {
        this.rubro1 = rubro1;
    }

    public BigDecimal getRubro2() {
        return rubro2;
    }

    public void setRubro2(BigDecimal rubro2) {
        this.rubro2 = rubro2;
    }

    public BigDecimal getMontoRetencion() {
        return montoRetencion;
    }

    public void setMontoRetencion(BigDecimal montoRetencion) {
        this.montoRetencion = montoRetencion;
    }

    public BigDecimal getSaldoRetencion() {
        return saldoRetencion;
    }

    public void setSaldoRetencion(BigDecimal saldoRetencion) {
        this.saldoRetencion = saldoRetencion;
    }

    public String getDependiente() {
        return dependiente;
    }

    public void setDependiente(String dependiente) {
        this.dependiente = dependiente;
    }

    public Date getFechaUltCredito() {
        return fechaUltCredito;
    }

    public void setFechaUltCredito(Date fechaUltCredito) {
        this.fechaUltCredito = fechaUltCredito;
    }

    public String getCargadoDeFact() {
        return cargadoDeFact;
    }

    public void setCargadoDeFact(String cargadoDeFact) {
        this.cargadoDeFact = cargadoDeFact;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getAsientoPendiente() {
        return asientoPendiente;
    }

    public void setAsientoPendiente(String asientoPendiente) {
        this.asientoPendiente = asientoPendiente;
    }

    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public short getNumParcialidades() {
        return numParcialidades;
    }

    public void setNumParcialidades(short numParcialidades) {
        this.numParcialidades = numParcialidades;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    public BigDecimal getPorcIntcte() {
        return porcIntcte;
    }

    public void setPorcIntcte(BigDecimal porcIntcte) {
        this.porcIntcte = porcIntcte;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getTipoDocOrigen() {
        return tipoDocOrigen;
    }

    public void setTipoDocOrigen(String tipoDocOrigen) {
        this.tipoDocOrigen = tipoDocOrigen;
    }

    public String getDocDocOrigen() {
        return docDocOrigen;
    }

    public void setDocDocOrigen(String docDocOrigen) {
        this.docDocOrigen = docDocOrigen;
    }

    public Date getFechaAnul() {
        return fechaAnul;
    }

    public void setFechaAnul(Date fechaAnul) {
        this.fechaAnul = fechaAnul;
    }

    public String getAudUsuarioAnul() {
        return audUsuarioAnul;
    }

    public void setAudUsuarioAnul(String audUsuarioAnul) {
        this.audUsuarioAnul = audUsuarioAnul;
    }

    public Date getAudFechaAnul() {
        return audFechaAnul;
    }

    public void setAudFechaAnul(Date audFechaAnul) {
        this.audFechaAnul = audFechaAnul;
    }

    public BigInteger getNumDocCb() {
        return numDocCb;
    }

    public void setNumDocCb(BigInteger numDocCb) {
        this.numDocCb = numDocCb;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getAuditoriaCobro() {
        return auditoriaCobro;
    }

    public void setAuditoriaCobro(String auditoriaCobro) {
        this.auditoriaCobro = auditoriaCobro;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getObservacionesCobro() {
        return observacionesCobro;
    }

    public void setObservacionesCobro(String observacionesCobro) {
        this.observacionesCobro = observacionesCobro;
    }

    public String getUsuarioAprobacion() {
        return usuarioAprobacion;
    }

    public void setUsuarioAprobacion(String usuarioAprobacion) {
        this.usuarioAprobacion = usuarioAprobacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public BigDecimal getBaseImpuesto1() {
        return baseImpuesto1;
    }

    public void setBaseImpuesto1(BigDecimal baseImpuesto1) {
        this.baseImpuesto1 = baseImpuesto1;
    }

    public BigDecimal getBaseImpuesto2() {
        return baseImpuesto2;
    }

    public void setBaseImpuesto2(BigDecimal baseImpuesto2) {
        this.baseImpuesto2 = baseImpuesto2;
    }

    public String getDependienteGp() {
        return dependienteGp;
    }

    public void setDependienteGp(String dependienteGp) {
        this.dependienteGp = dependienteGp;
    }

    public BigDecimal getSaldoTrans() {
        return saldoTrans;
    }

    public void setSaldoTrans(BigDecimal saldoTrans) {
        this.saldoTrans = saldoTrans;
    }

    public BigDecimal getSaldoTransLocal() {
        return saldoTransLocal;
    }

    public void setSaldoTransLocal(BigDecimal saldoTransLocal) {
        this.saldoTransLocal = saldoTransLocal;
    }

    public BigDecimal getSaldoTransDolar() {
        return saldoTransDolar;
    }

    public void setSaldoTransDolar(BigDecimal saldoTransDolar) {
        this.saldoTransDolar = saldoTransDolar;
    }

    public Date getFechaProyectada() {
        return fechaProyectada;
    }

    public void setFechaProyectada(Date fechaProyectada) {
        this.fechaProyectada = fechaProyectada;
    }

    public BigDecimal getPorcRecuperacion() {
        return porcRecuperacion;
    }

    public void setPorcRecuperacion(BigDecimal porcRecuperacion) {
        this.porcRecuperacion = porcRecuperacion;
    }

    public BigDecimal getSaldoTransCli() {
        return saldoTransCli;
    }

    public void setSaldoTransCli(BigDecimal saldoTransCli) {
        this.saldoTransCli = saldoTransCli;
    }

    public String getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(String documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

    public String getFacturado() {
        return facturado;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public String getGeneraDocFe() {
        return generaDocFe;
    }

    public void setGeneraDocFe(String generaDocFe) {
        this.generaDocFe = generaDocFe;
    }

    public String getTasaImpositiva() {
        return tasaImpositiva;
    }

    public void setTasaImpositiva(String tasaImpositiva) {
        this.tasaImpositiva = tasaImpositiva;
    }

    public BigDecimal getTasaImpositivaPorc() {
        return tasaImpositivaPorc;
    }

    public void setTasaImpositivaPorc(BigDecimal tasaImpositivaPorc) {
        this.tasaImpositivaPorc = tasaImpositivaPorc;
    }

    public String getTasaCree1() {
        return tasaCree1;
    }

    public void setTasaCree1(String tasaCree1) {
        this.tasaCree1 = tasaCree1;
    }

    public BigDecimal getTasaCree1Porc() {
        return tasaCree1Porc;
    }

    public void setTasaCree1Porc(BigDecimal tasaCree1Porc) {
        this.tasaCree1Porc = tasaCree1Porc;
    }

    public String getTasaCree2() {
        return tasaCree2;
    }

    public void setTasaCree2(String tasaCree2) {
        this.tasaCree2 = tasaCree2;
    }

    public BigDecimal getTasaCree2Porc() {
        return tasaCree2Porc;
    }

    public void setTasaCree2Porc(BigDecimal tasaCree2Porc) {
        this.tasaCree2Porc = tasaCree2Porc;
    }

    public BigDecimal getTasaGanOcasionalPorc() {
        return tasaGanOcasionalPorc;
    }

    public void setTasaGanOcasionalPorc(BigDecimal tasaGanOcasionalPorc) {
        this.tasaGanOcasionalPorc = tasaGanOcasionalPorc;
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

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public String getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getClienteReporte() {
        return clienteReporte;
    }

    public void setClienteReporte(String clienteReporte) {
        this.clienteReporte = clienteReporte;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getClienteOrigen() {
        return clienteOrigen;
    }

    public void setClienteOrigen(String clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public String getCtaBancaria() {
        return ctaBancaria;
    }

    public void setCtaBancaria(String ctaBancaria) {
        this.ctaBancaria = ctaBancaria;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDivisionGeografica1() {
        return divisionGeografica1;
    }

    public void setDivisionGeografica1(String divisionGeografica1) {
        this.divisionGeografica1 = divisionGeografica1;
    }

    public String getDivisionGeografica2() {
        return divisionGeografica2;
    }

    public void setDivisionGeografica2(String divisionGeografica2) {
        this.divisionGeografica2 = divisionGeografica2;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    public Short getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(Short subtipo) {
        this.subtipo = subtipo;
    }

    public List<DocumentoAnticipo> getDocumentoAnticipoList() {
        return documentoAnticipoList;
    }

    public void setDocumentoAnticipoList(List<DocumentoAnticipo> documentoAnticipoList) {
        this.documentoAnticipoList = documentoAnticipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentosCcPK != null ? documentosCcPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosCc)) {
            return false;
        }
        DocumentosCc other = (DocumentosCc) object;
        if ((this.documentosCcPK == null && other.documentosCcPK != null) || (this.documentosCcPK != null && !this.documentosCcPK.equals(other.documentosCcPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentosCc[ documentosCcPK=" + documentosCcPK + " ]";
    }
}