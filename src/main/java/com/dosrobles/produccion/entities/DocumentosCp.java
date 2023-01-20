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
@Table(name = "DOCUMENTOS_CP")
@NamedQueries({
    @NamedQuery(name = "DocumentosCp.findAll", query = "SELECT d FROM DocumentosCp d")})
public class DocumentosCp implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentosCpPK documentosCpPK;
    @Basic(optional = false)
    @Column(name = "EMBARQUE_APROBADO")
    private String embarqueAprobado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIPO_CAMB_LIQ_LOC")
    private BigDecimal tipoCambLiqLoc;
    @Column(name = "TIPO_CAMB_LIQ_DOL")
    private BigDecimal tipoCambLiqDol;
    @Basic(optional = false)
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumento;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "TIPO_PRORRATEO")
    private String tipoProrrateo;
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Basic(optional = false)
    @Column(name = "TIPO_EMBARQUE")
    private String tipoEmbarque;
    @Basic(optional = false)
    @Column(name = "APLICACION")
    private String aplicacion;
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
    @Column(name = "TIPO_CAMBIO_MONEDA")
    private BigDecimal tipoCambioMoneda;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMBIO_DOLAR")
    private BigDecimal tipoCambioDolar;
    @Basic(optional = false)
    @Column(name = "FECHA_ULT_CREDITO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltCredito;
    @Basic(optional = false)
    @Column(name = "CHEQUE_IMPRESO")
    private String chequeImpreso;
    @Basic(optional = false)
    @Column(name = "APROBADO")
    private String aprobado;
    @Basic(optional = false)
    @Column(name = "SELECCIONADO")
    private String seleccionado;
    @Basic(optional = false)
    @Column(name = "CONGELADO")
    private String congelado;
    @Basic(optional = false)
    @Column(name = "MONTO_PROV")
    private BigDecimal montoProv;
    @Basic(optional = false)
    @Column(name = "SALDO_PROV")
    private BigDecimal saldoProv;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMBIO_PROV")
    private BigDecimal tipoCambioProv;
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
    @Column(name = "FECHA_ULT_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltMod;
    @Basic(optional = false)
    @Column(name = "MONTO_RETENCION")
    private BigDecimal montoRetencion;
    @Basic(optional = false)
    @Column(name = "SALDO_RETENCION")
    private BigDecimal saldoRetencion;
    @Basic(optional = false)
    @Column(name = "DEPENDIENTE")
    private String dependiente;
    @Column(name = "ASIENTO")
    private String asiento;
    @Basic(optional = false)
    @Column(name = "ASIENTO_PENDIENTE")
    private String asientoPendiente;
    @Lob
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_LOC")
    private BigDecimal tipoCambActLoc;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_DOL")
    private BigDecimal tipoCambActDol;
    @Basic(optional = false)
    @Column(name = "TIPO_CAMB_ACT_PROV")
    private BigDecimal tipoCambActProv;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO_EMBARQUE")
    private String documentoEmbarque;
    @Column(name = "FECHA_REVISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Basic(optional = false)
    @Column(name = "USUARIO_ULT_MOD")
    private String usuarioUltMod;
    @Basic(optional = false)
    @Column(name = "FECHA_VENCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVence;
    @Column(name = "FECHA_ANUL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnul;
    @Column(name = "AUD_USUARIO_ANUL")
    private String audUsuarioAnul;
    @Column(name = "AUD_FECHA_ANUL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFechaAnul;
    @Column(name = "USUARIO_APROBACION")
    private String usuarioAprobacion;
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @Column(name = "MONTO_PAGO")
    private BigDecimal montoPago;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "TIPO_CAMBIO")
    private BigDecimal tipoCambio;
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
    @Column(name = "IMP1_ASUMIDO_DESC")
    private BigDecimal imp1AsumidoDesc;
    @Column(name = "IMP1_ASUMIDO_NODESC")
    private BigDecimal imp1AsumidoNodesc;
    @Column(name = "IMP1_RETENIDO_DESC")
    private BigDecimal imp1RetenidoDesc;
    @Column(name = "IMP1_RETENIDO_NODESC")
    private BigDecimal imp1RetenidoNodesc;
    @Column(name = "DOCUMENTO_FISCAL")
    private String documentoFiscal;
    @Column(name = "ESTADO_ENVIO")
    private String estadoEnvio;
    @Column(name = "CONCEPTO_ME")
    private String conceptoMe;
    @Basic(optional = false)
    @Column(name = "PARTICIPA_IETU")
    private String participaIetu;
    @Basic(optional = false)
    @Column(name = "NoteExistsFlag")
    private short noteExistsFlag;
    @Basic(optional = false)
    @Column(name = "RecordDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Basic(optional = false)
    @Column(name = "RowPointer")
    private String rowPointer;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "UpdatedBy")
    private String updatedBy;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "CLASE_DOCUMENTO")
    private String claseDocumento;
    @Basic(optional = false)
    @Column(name = "PORC_INTCTE")
    private BigDecimal porcIntcte;
    @Column(name = "CONTRATO")
    private String contrato;
    @Basic(optional = false)
    @Column(name = "NUM_PARCIALIDADES")
    private short numParcialidades;
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
    @Column(name = "PROVEEDOR", insertable = false, updatable = false)
    private String proveedor1;
    @Basic(optional = false)
    @Column(name = "CONDICION_PAGO")
    private String condicionPago;
    @Column(name = "CONTRARECIBO")
    private String contrarecibo;
    @Column(name = "CUENTA_BANCO")
    private String cuentaBanco;
    @Column(name = "CHEQUE_CUENTA")
    private String chequeCuenta;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "DIVISION_GEOGRAFICA1")
    private String divisionGeografica1;
    @Column(name = "DIVISION_GEOGRAFICA2")
    private String divisionGeografica2;
    @Column(name = "CODIGO_IMPUESTO")
    private String codigoImpuesto;
    @Column(name = "LIQUIDAC_COMPRA")
    private String liquidacCompra;
    @Basic(optional = false)
    @Column(name = "MONEDA")
    private String moneda;
    @Column(name = "TIPO_ASIENTO")
    private String tipoAsiento;
    @Column(name = "PAQUETE")
    private String paquete;
    @Column(name = "SUBTIPO")
    private Short subtipo;
    @Basic(optional = false)
    @Column(name = "TIPO", updatable = false, insertable = false)
    private String tipo1;

    public DocumentosCp() {
    }

    public DocumentosCp(DocumentosCpPK documentosCpPK) {
        this.documentosCpPK = documentosCpPK;
    }

    public DocumentosCp(DocumentosCpPK documentosCpPK, String embarqueAprobado, Date fechaDocumento, Date fecha, String tipoEmbarque, String aplicacion, BigDecimal monto, BigDecimal saldo, BigDecimal montoLocal, BigDecimal saldoLocal, BigDecimal montoDolar, BigDecimal saldoDolar, BigDecimal tipoCambioMoneda, BigDecimal tipoCambioDolar, Date fechaUltCredito, String chequeImpreso, String aprobado, String seleccionado, String congelado, BigDecimal montoProv, BigDecimal saldoProv, BigDecimal tipoCambioProv, BigDecimal subtotal, BigDecimal descuento, BigDecimal impuesto1, BigDecimal impuesto2, BigDecimal rubro1, BigDecimal rubro2, Date fechaUltMod, BigDecimal montoRetencion, BigDecimal saldoRetencion, String dependiente, String asientoPendiente, BigDecimal tipoCambActLoc, BigDecimal tipoCambActDol, BigDecimal tipoCambActProv, String documentoEmbarque, String usuarioUltMod, Date fechaVence, String dependienteGp, BigDecimal saldoTrans, BigDecimal saldoTransLocal, BigDecimal saldoTransDolar, String participaIetu, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate, String claseDocumento, BigDecimal porcIntcte, short numParcialidades, String proveedor1, String condicionPago, String moneda, String tipo1) {
        this.documentosCpPK = documentosCpPK;
        this.embarqueAprobado = embarqueAprobado;
        this.fechaDocumento = fechaDocumento;
        this.fecha = fecha;
        this.tipoEmbarque = tipoEmbarque;
        this.aplicacion = aplicacion;
        this.monto = monto;
        this.saldo = saldo;
        this.montoLocal = montoLocal;
        this.saldoLocal = saldoLocal;
        this.montoDolar = montoDolar;
        this.saldoDolar = saldoDolar;
        this.tipoCambioMoneda = tipoCambioMoneda;
        this.tipoCambioDolar = tipoCambioDolar;
        this.fechaUltCredito = fechaUltCredito;
        this.chequeImpreso = chequeImpreso;
        this.aprobado = aprobado;
        this.seleccionado = seleccionado;
        this.congelado = congelado;
        this.montoProv = montoProv;
        this.saldoProv = saldoProv;
        this.tipoCambioProv = tipoCambioProv;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.impuesto1 = impuesto1;
        this.impuesto2 = impuesto2;
        this.rubro1 = rubro1;
        this.rubro2 = rubro2;
        this.fechaUltMod = fechaUltMod;
        this.montoRetencion = montoRetencion;
        this.saldoRetencion = saldoRetencion;
        this.dependiente = dependiente;
        this.asientoPendiente = asientoPendiente;
        this.tipoCambActLoc = tipoCambActLoc;
        this.tipoCambActDol = tipoCambActDol;
        this.tipoCambActProv = tipoCambActProv;
        this.documentoEmbarque = documentoEmbarque;
        this.usuarioUltMod = usuarioUltMod;
        this.fechaVence = fechaVence;
        this.dependienteGp = dependienteGp;
        this.saldoTrans = saldoTrans;
        this.saldoTransLocal = saldoTransLocal;
        this.saldoTransDolar = saldoTransDolar;
        this.participaIetu = participaIetu;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
        this.claseDocumento = claseDocumento;
        this.porcIntcte = porcIntcte;
        this.numParcialidades = numParcialidades;
        this.proveedor1 = proveedor1;
        this.condicionPago = condicionPago;
        this.moneda = moneda;
        this.tipo1 = tipo1;
    }

    public DocumentosCp(String proveedor, String documento, String tipo) {
        this.documentosCpPK = new DocumentosCpPK(proveedor, documento, tipo);
    }

    public DocumentosCpPK getDocumentosCpPK() {
        return documentosCpPK;
    }

    public void setDocumentosCpPK(DocumentosCpPK documentosCpPK) {
        this.documentosCpPK = documentosCpPK;
    }

    public String getEmbarqueAprobado() {
        return embarqueAprobado;
    }

    public void setEmbarqueAprobado(String embarqueAprobado) {
        this.embarqueAprobado = embarqueAprobado;
    }

    public BigDecimal getTipoCambLiqLoc() {
        return tipoCambLiqLoc;
    }

    public void setTipoCambLiqLoc(BigDecimal tipoCambLiqLoc) {
        this.tipoCambLiqLoc = tipoCambLiqLoc;
    }

    public BigDecimal getTipoCambLiqDol() {
        return tipoCambLiqDol;
    }

    public void setTipoCambLiqDol(BigDecimal tipoCambLiqDol) {
        this.tipoCambLiqDol = tipoCambLiqDol;
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

    public String getTipoProrrateo() {
        return tipoProrrateo;
    }

    public void setTipoProrrateo(String tipoProrrateo) {
        this.tipoProrrateo = tipoProrrateo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getTipoEmbarque() {
        return tipoEmbarque;
    }

    public void setTipoEmbarque(String tipoEmbarque) {
        this.tipoEmbarque = tipoEmbarque;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
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

    public Date getFechaUltCredito() {
        return fechaUltCredito;
    }

    public void setFechaUltCredito(Date fechaUltCredito) {
        this.fechaUltCredito = fechaUltCredito;
    }

    public String getChequeImpreso() {
        return chequeImpreso;
    }

    public void setChequeImpreso(String chequeImpreso) {
        this.chequeImpreso = chequeImpreso;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getCongelado() {
        return congelado;
    }

    public void setCongelado(String congelado) {
        this.congelado = congelado;
    }

    public BigDecimal getMontoProv() {
        return montoProv;
    }

    public void setMontoProv(BigDecimal montoProv) {
        this.montoProv = montoProv;
    }

    public BigDecimal getSaldoProv() {
        return saldoProv;
    }

    public void setSaldoProv(BigDecimal saldoProv) {
        this.saldoProv = saldoProv;
    }

    public BigDecimal getTipoCambioProv() {
        return tipoCambioProv;
    }

    public void setTipoCambioProv(BigDecimal tipoCambioProv) {
        this.tipoCambioProv = tipoCambioProv;
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

    public Date getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(Date fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
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

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
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

    public BigDecimal getTipoCambActProv() {
        return tipoCambActProv;
    }

    public void setTipoCambActProv(BigDecimal tipoCambActProv) {
        this.tipoCambActProv = tipoCambActProv;
    }

    public String getDocumentoEmbarque() {
        return documentoEmbarque;
    }

    public void setDocumentoEmbarque(String documentoEmbarque) {
        this.documentoEmbarque = documentoEmbarque;
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

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
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

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
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

    public BigDecimal getImp1AsumidoDesc() {
        return imp1AsumidoDesc;
    }

    public void setImp1AsumidoDesc(BigDecimal imp1AsumidoDesc) {
        this.imp1AsumidoDesc = imp1AsumidoDesc;
    }

    public BigDecimal getImp1AsumidoNodesc() {
        return imp1AsumidoNodesc;
    }

    public void setImp1AsumidoNodesc(BigDecimal imp1AsumidoNodesc) {
        this.imp1AsumidoNodesc = imp1AsumidoNodesc;
    }

    public BigDecimal getImp1RetenidoDesc() {
        return imp1RetenidoDesc;
    }

    public void setImp1RetenidoDesc(BigDecimal imp1RetenidoDesc) {
        this.imp1RetenidoDesc = imp1RetenidoDesc;
    }

    public BigDecimal getImp1RetenidoNodesc() {
        return imp1RetenidoNodesc;
    }

    public void setImp1RetenidoNodesc(BigDecimal imp1RetenidoNodesc) {
        this.imp1RetenidoNodesc = imp1RetenidoNodesc;
    }

    public String getDocumentoFiscal() {
        return documentoFiscal;
    }

    public void setDocumentoFiscal(String documentoFiscal) {
        this.documentoFiscal = documentoFiscal;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getConceptoMe() {
        return conceptoMe;
    }

    public void setConceptoMe(String conceptoMe) {
        this.conceptoMe = conceptoMe;
    }

    public String getParticipaIetu() {
        return participaIetu;
    }

    public void setParticipaIetu(String participaIetu) {
        this.participaIetu = participaIetu;
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

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
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

    public short getNumParcialidades() {
        return numParcialidades;
    }

    public void setNumParcialidades(short numParcialidades) {
        this.numParcialidades = numParcialidades;
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

    public String getProveedor1() {
        return proveedor1;
    }

    public void setProveedor1(String proveedor1) {
        this.proveedor1 = proveedor1;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public String getContrarecibo() {
        return contrarecibo;
    }

    public void setContrarecibo(String contrarecibo) {
        this.contrarecibo = contrarecibo;
    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getChequeCuenta() {
        return chequeCuenta;
    }

    public void setChequeCuenta(String chequeCuenta) {
        this.chequeCuenta = chequeCuenta;
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

    public String getLiquidacCompra() {
        return liquidacCompra;
    }

    public void setLiquidacCompra(String liquidacCompra) {
        this.liquidacCompra = liquidacCompra;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
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

    public Short getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(Short subtipo) {
        this.subtipo = subtipo;
    }

    public String getTipo1() {
        return tipo1;
    }

    public void setTipo1(String tipo1) {
        this.tipo1 = tipo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentosCpPK != null ? documentosCpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentosCp)) {
            return false;
        }
        DocumentosCp other = (DocumentosCp) object;
        if ((this.documentosCpPK == null && other.documentosCpPK != null) || (this.documentosCpPK != null && !this.documentosCpPK.equals(other.documentosCpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentosCp[ documentosCpPK=" + documentosCpPK + " ]";
    }

}
