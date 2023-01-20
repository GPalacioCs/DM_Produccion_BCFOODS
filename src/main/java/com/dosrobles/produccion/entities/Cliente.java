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
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CLIENTE")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLIENTE")
    private String cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "ALIAS")
    private String alias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CONTACTO")
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CARGO")
    private String cargo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 8)
    @Column(name = "DIR_EMB_DEFAULT")
    private String dirEmbDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TELEFONO1")
    private String telefono1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TELEFONO2")
    private String telefono2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FAX")
    private String fax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MULTIMONEDA")
    private String multimoneda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_LOCAL")
    private BigDecimal saldoLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_DOLAR")
    private BigDecimal saldoDolar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_CREDITO")
    private BigDecimal saldoCredito;
    @Column(name = "SALDO_NOCARGOS")
    private BigDecimal saldoNocargos;
    @Column(name = "LIMITE_CREDITO")
    private BigDecimal limiteCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EXCEDER_LIMITE")
    private String excederLimite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_INTERES")
    private BigDecimal tasaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASA_INTERES_MORA")
    private BigDecimal tasaInteresMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ULT_MORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltMora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ULT_MOV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltMov;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO")
    private BigDecimal descuento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACEPTA_BACKORDER")
    private String aceptaBackorder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACEPTA_FRACCIONES")
    private String aceptaFracciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EXENTO_IMPUESTOS")
    private String exentoImpuestos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXENCION_IMP1")
    private BigDecimal exencionImp1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXENCION_IMP2")
    private BigDecimal exencionImp2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "COBRO_JUDICIAL")
    private String cobroJudicial;
    @Size(max = 1)
    @Column(name = "CLASE_ABC")
    private String claseAbc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_ABASTECIMIEN")
    private short diasAbastecimien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_TARJETA")
    private String usaTarjeta;
    @Size(max = 20)
    @Column(name = "TARJETA_CREDITO")
    private String tarjetaCredito;
    @Column(name = "FECHA_VENCE_TARJ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenceTarj;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 249)
    @Column(name = "E_MAIL")
    private String eMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REQUIERE_OC")
    private String requiereOc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ES_CORPORACION")
    private String esCorporacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "REGISTRARDOCSACORP")
    private String registrardocsacorp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USAR_DIREMB_CORP")
    private String usarDirembCorp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "APLICAC_ABIERTAS")
    private String aplicacAbiertas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VERIF_LIMCRED_CORP")
    private String verifLimcredCorp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USAR_DESC_CORP")
    private String usarDescCorp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DOC_A_GENERAR")
    private String docAGenerar;
    @Size(max = 40)
    @Column(name = "RUBRO1_CLIENTE")
    private String rubro1Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO2_CLIENTE")
    private String rubro2Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO3_CLIENTE")
    private String rubro3Cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIENE_CONVENIO")
    private String tieneConvenio;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_PROMED_ATRASO")
    private short diasPromedAtraso;
    @Size(max = 50)
    @Column(name = "RUBRO1_CLI")
    private String rubro1Cli;
    @Size(max = 50)
    @Column(name = "RUBRO2_CLI")
    private String rubro2Cli;
    @Size(max = 50)
    @Column(name = "RUBRO3_CLI")
    private String rubro3Cli;
    @Size(max = 50)
    @Column(name = "RUBRO4_CLI")
    private String rubro4Cli;
    @Size(max = 50)
    @Column(name = "RUBRO5_CLI")
    private String rubro5Cli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ASOCOBLIGCONTFACT")
    private String asocobligcontfact;
    @Size(max = 40)
    @Column(name = "RUBRO4_CLIENTE")
    private String rubro4Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO5_CLIENTE")
    private String rubro5Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO6_CLIENTE")
    private String rubro6Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO7_CLIENTE")
    private String rubro7Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO8_CLIENTE")
    private String rubro8Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO9_CLIENTE")
    private String rubro9Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO10_CLIENTE")
    private String rubro10Cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USAR_PRECIOS_CORP")
    private String usarPreciosCorp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USAR_EXENCIMP_CORP")
    private String usarExencimpCorp;
    @Size(max = 13)
    @Column(name = "DIAS_DE_COBRO")
    private String diasDeCobro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AJUSTE_FECHA_COBRO")
    private String ajusteFechaCobro;
    @Size(max = 13)
    @Column(name = "GLN")
    private String gln;
    @Size(max = 10)
    @Column(name = "UBICACION")
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CLASE_DOCUMENTO")
    private String claseDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LOCAL")
    private String local;
    @Size(max = 1)
    @Column(name = "TIPO_CONTRIBUYENTE")
    private String tipoContribuyente;
    @Size(max = 40)
    @Column(name = "RUBRO11_CLIENTE")
    private String rubro11Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO12_CLIENTE")
    private String rubro12Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO13_CLIENTE")
    private String rubro13Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO14_CLIENTE")
    private String rubro14Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO15_CLIENTE")
    private String rubro15Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO16_CLIENTE")
    private String rubro16Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO17_CLIENTE")
    private String rubro17Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO18_CLIENTE")
    private String rubro18Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO19_CLIENTE")
    private String rubro19Cliente;
    @Size(max = 40)
    @Column(name = "RUBRO20_CLIENTE")
    private String rubro20Cliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACEPTA_DOC_ELECTRONICO")
    private String aceptaDocElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONFIRMA_DOC_ELECTRONICO")
    private String confirmaDocElectronico;
    @Size(max = 249)
    @Column(name = "EMAIL_DOC_ELECTRONICO")
    private String emailDocElectronico;
    @Size(max = 249)
    @Column(name = "EMAIL_PED_EDI")
    private String emailPedEdi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACEPTA_DOC_EDI")
    private String aceptaDocEdi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NOTIFICAR_ERROR_EDI")
    private String notificarErrorEdi;
    @Size(max = 249)
    @Column(name = "EMAIL_ERROR_PED_EDI")
    private String emailErrorPedEdi;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA1")
    private String divisionGeografica1;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA2")
    private String divisionGeografica2;
    @Size(max = 12)
    @Column(name = "REGIMEN_TRIB")
    private String regimenTrib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MOROSO")
    private String moroso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MODIF_NOMB_EN_FAC")
    private String modifNombEnFac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TRANS")
    private BigDecimal saldoTrans;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TRANS_LOCAL")
    private BigDecimal saldoTransLocal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_TRANS_DOLAR")
    private BigDecimal saldoTransDolar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PERMITE_DOC_GP")
    private String permiteDocGp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PARTICIPA_FLUJOCAJA")
    private String participaFlujocaja;
    @Size(max = 18)
    @Column(name = "CURP")
    private String curp;
    @Size(max = 25)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_HORA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;
    @Size(max = 25)
    @Column(name = "USUARIO_ULT_MOD")
    private String usuarioUltMod;
    @Column(name = "FCH_HORA_ULT_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraUltMod;
    @Size(max = 249)
    @Column(name = "EMAIL_DOC_ELECTRONICO_COPIA")
    private String emailDocElectronicoCopia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DETALLAR_KITS")
    private String detallarKits;
    @Size(max = 20)
    @Column(name = "XSLT_PERSONALIZADO")
    private String xsltPersonalizado;
    @Size(max = 20)
    @Column(name = "NOMBRE_ADDENDA")
    private String nombreAddenda;
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
    @Size(min = 1, max = 4)
    @Column(name = "MONEDA")
    private String moneda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "CATEGORIA_CLIENTE")
    private String categoriaCliente;
    @Size(max = 12)
    @Column(name = "TIPO_TARJETA")
    private String tipoTarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COBRADOR")
    private String cobrador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CONDICION_PAGO")
    private String condicionPago;
    @Column(name = "DETALLE_DIRECCION")
    private Integer detalleDireccion;
    @Size(max = 4)
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Size(max = 4)
    @Column(name = "CODIGO_IMPUESTO")
    private String codigoImpuesto;
    @Size(max = 4)
    @Column(name = "MODELO_RETENCION")
    private String modeloRetencion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ZONA")
    private String zona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CONTRIBUYENTE")
    private String contribuyente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "NIVEL_PRECIO")
    private String nivelPrecio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MONEDA_NIVEL")
    private String monedaNivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PAIS")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "RUTA")
    private String ruta;
    @OneToMany(mappedBy = "cliente1")
    private Collection<Cliente> clienteCollection;
    @JoinColumn(name = "CLI_CORPORAC_ASOC", referencedColumnName = "CLIENTE")
    @ManyToOne
    private Cliente cliente1;

    public Cliente() {
    }

    public Cliente(String cliente) {
        this.cliente = cliente;
    }

    public Cliente(String cliente, String nombre, String contacto, String cargo, String telefono1, String telefono2, String fax, Date fechaIngreso, String multimoneda, BigDecimal saldo, BigDecimal saldoLocal, BigDecimal saldoDolar, BigDecimal saldoCredito, String excederLimite, BigDecimal tasaInteres, BigDecimal tasaInteresMora, Date fechaUltMora, Date fechaUltMov, BigDecimal descuento, String aceptaBackorder, String aceptaFracciones, String activo, String exentoImpuestos, BigDecimal exencionImp1, BigDecimal exencionImp2, String cobroJudicial, short diasAbastecimien, String usaTarjeta, String requiereOc, String esCorporacion, String registrardocsacorp, String usarDirembCorp, String aplicacAbiertas, String verifLimcredCorp, String usarDescCorp, String docAGenerar, String tieneConvenio, short diasPromedAtraso, String asocobligcontfact, String usarPreciosCorp, String usarExencimpCorp, String ajusteFechaCobro, String claseDocumento, String local, String aceptaDocElectronico, String confirmaDocElectronico, String aceptaDocEdi, String notificarErrorEdi, String moroso, String modifNombEnFac, BigDecimal saldoTrans, BigDecimal saldoTransLocal, BigDecimal saldoTransDolar, String permiteDocGp, String participaFlujocaja, String detallarKits, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate, String moneda, String categoriaCliente, String cobrador, String condicionPago, String zona, String contribuyente, String nivelPrecio, String monedaNivel, String pais, String ruta) {
        this.cliente = cliente;
        this.nombre = nombre;
        this.contacto = contacto;
        this.cargo = cargo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.fax = fax;
        this.fechaIngreso = fechaIngreso;
        this.multimoneda = multimoneda;
        this.saldo = saldo;
        this.saldoLocal = saldoLocal;
        this.saldoDolar = saldoDolar;
        this.saldoCredito = saldoCredito;
        this.excederLimite = excederLimite;
        this.tasaInteres = tasaInteres;
        this.tasaInteresMora = tasaInteresMora;
        this.fechaUltMora = fechaUltMora;
        this.fechaUltMov = fechaUltMov;
        this.descuento = descuento;
        this.aceptaBackorder = aceptaBackorder;
        this.aceptaFracciones = aceptaFracciones;
        this.activo = activo;
        this.exentoImpuestos = exentoImpuestos;
        this.exencionImp1 = exencionImp1;
        this.exencionImp2 = exencionImp2;
        this.cobroJudicial = cobroJudicial;
        this.diasAbastecimien = diasAbastecimien;
        this.usaTarjeta = usaTarjeta;
        this.requiereOc = requiereOc;
        this.esCorporacion = esCorporacion;
        this.registrardocsacorp = registrardocsacorp;
        this.usarDirembCorp = usarDirembCorp;
        this.aplicacAbiertas = aplicacAbiertas;
        this.verifLimcredCorp = verifLimcredCorp;
        this.usarDescCorp = usarDescCorp;
        this.docAGenerar = docAGenerar;
        this.tieneConvenio = tieneConvenio;
        this.diasPromedAtraso = diasPromedAtraso;
        this.asocobligcontfact = asocobligcontfact;
        this.usarPreciosCorp = usarPreciosCorp;
        this.usarExencimpCorp = usarExencimpCorp;
        this.ajusteFechaCobro = ajusteFechaCobro;
        this.claseDocumento = claseDocumento;
        this.local = local;
        this.aceptaDocElectronico = aceptaDocElectronico;
        this.confirmaDocElectronico = confirmaDocElectronico;
        this.aceptaDocEdi = aceptaDocEdi;
        this.notificarErrorEdi = notificarErrorEdi;
        this.moroso = moroso;
        this.modifNombEnFac = modifNombEnFac;
        this.saldoTrans = saldoTrans;
        this.saldoTransLocal = saldoTransLocal;
        this.saldoTransDolar = saldoTransDolar;
        this.permiteDocGp = permiteDocGp;
        this.participaFlujocaja = participaFlujocaja;
        this.detallarKits = detallarKits;
        this.noteExistsFlag = noteExistsFlag;
        this.recordDate = recordDate;
        this.rowPointer = rowPointer;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createDate = createDate;
        this.moneda = moneda;
        this.categoriaCliente = categoriaCliente;
        this.cobrador = cobrador;
        this.condicionPago = condicionPago;
        this.zona = zona;
        this.contribuyente = contribuyente;
        this.nivelPrecio = nivelPrecio;
        this.monedaNivel = monedaNivel;
        this.pais = pais;
        this.ruta = ruta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDirEmbDefault() {
        return dirEmbDefault;
    }

    public void setDirEmbDefault(String dirEmbDefault) {
        this.dirEmbDefault = dirEmbDefault;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getMultimoneda() {
        return multimoneda;
    }

    public void setMultimoneda(String multimoneda) {
        this.multimoneda = multimoneda;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldoLocal() {
        return saldoLocal;
    }

    public void setSaldoLocal(BigDecimal saldoLocal) {
        this.saldoLocal = saldoLocal;
    }

    public BigDecimal getSaldoDolar() {
        return saldoDolar;
    }

    public void setSaldoDolar(BigDecimal saldoDolar) {
        this.saldoDolar = saldoDolar;
    }

    public BigDecimal getSaldoCredito() {
        return saldoCredito;
    }

    public void setSaldoCredito(BigDecimal saldoCredito) {
        this.saldoCredito = saldoCredito;
    }

    public BigDecimal getSaldoNocargos() {
        return saldoNocargos;
    }

    public void setSaldoNocargos(BigDecimal saldoNocargos) {
        this.saldoNocargos = saldoNocargos;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getExcederLimite() {
        return excederLimite;
    }

    public void setExcederLimite(String excederLimite) {
        this.excederLimite = excederLimite;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getTasaInteresMora() {
        return tasaInteresMora;
    }

    public void setTasaInteresMora(BigDecimal tasaInteresMora) {
        this.tasaInteresMora = tasaInteresMora;
    }

    public Date getFechaUltMora() {
        return fechaUltMora;
    }

    public void setFechaUltMora(Date fechaUltMora) {
        this.fechaUltMora = fechaUltMora;
    }

    public Date getFechaUltMov() {
        return fechaUltMov;
    }

    public void setFechaUltMov(Date fechaUltMov) {
        this.fechaUltMov = fechaUltMov;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getAceptaBackorder() {
        return aceptaBackorder;
    }

    public void setAceptaBackorder(String aceptaBackorder) {
        this.aceptaBackorder = aceptaBackorder;
    }

    public String getAceptaFracciones() {
        return aceptaFracciones;
    }

    public void setAceptaFracciones(String aceptaFracciones) {
        this.aceptaFracciones = aceptaFracciones;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getExentoImpuestos() {
        return exentoImpuestos;
    }

    public void setExentoImpuestos(String exentoImpuestos) {
        this.exentoImpuestos = exentoImpuestos;
    }

    public BigDecimal getExencionImp1() {
        return exencionImp1;
    }

    public void setExencionImp1(BigDecimal exencionImp1) {
        this.exencionImp1 = exencionImp1;
    }

    public BigDecimal getExencionImp2() {
        return exencionImp2;
    }

    public void setExencionImp2(BigDecimal exencionImp2) {
        this.exencionImp2 = exencionImp2;
    }

    public String getCobroJudicial() {
        return cobroJudicial;
    }

    public void setCobroJudicial(String cobroJudicial) {
        this.cobroJudicial = cobroJudicial;
    }

    public String getClaseAbc() {
        return claseAbc;
    }

    public void setClaseAbc(String claseAbc) {
        this.claseAbc = claseAbc;
    }

    public short getDiasAbastecimien() {
        return diasAbastecimien;
    }

    public void setDiasAbastecimien(short diasAbastecimien) {
        this.diasAbastecimien = diasAbastecimien;
    }

    public String getUsaTarjeta() {
        return usaTarjeta;
    }

    public void setUsaTarjeta(String usaTarjeta) {
        this.usaTarjeta = usaTarjeta;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Date getFechaVenceTarj() {
        return fechaVenceTarj;
    }

    public void setFechaVenceTarj(Date fechaVenceTarj) {
        this.fechaVenceTarj = fechaVenceTarj;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRequiereOc() {
        return requiereOc;
    }

    public void setRequiereOc(String requiereOc) {
        this.requiereOc = requiereOc;
    }

    public String getEsCorporacion() {
        return esCorporacion;
    }

    public void setEsCorporacion(String esCorporacion) {
        this.esCorporacion = esCorporacion;
    }

    public String getRegistrardocsacorp() {
        return registrardocsacorp;
    }

    public void setRegistrardocsacorp(String registrardocsacorp) {
        this.registrardocsacorp = registrardocsacorp;
    }

    public String getUsarDirembCorp() {
        return usarDirembCorp;
    }

    public void setUsarDirembCorp(String usarDirembCorp) {
        this.usarDirembCorp = usarDirembCorp;
    }

    public String getAplicacAbiertas() {
        return aplicacAbiertas;
    }

    public void setAplicacAbiertas(String aplicacAbiertas) {
        this.aplicacAbiertas = aplicacAbiertas;
    }

    public String getVerifLimcredCorp() {
        return verifLimcredCorp;
    }

    public void setVerifLimcredCorp(String verifLimcredCorp) {
        this.verifLimcredCorp = verifLimcredCorp;
    }

    public String getUsarDescCorp() {
        return usarDescCorp;
    }

    public void setUsarDescCorp(String usarDescCorp) {
        this.usarDescCorp = usarDescCorp;
    }

    public String getDocAGenerar() {
        return docAGenerar;
    }

    public void setDocAGenerar(String docAGenerar) {
        this.docAGenerar = docAGenerar;
    }

    public String getRubro1Cliente() {
        return rubro1Cliente;
    }

    public void setRubro1Cliente(String rubro1Cliente) {
        this.rubro1Cliente = rubro1Cliente;
    }

    public String getRubro2Cliente() {
        return rubro2Cliente;
    }

    public void setRubro2Cliente(String rubro2Cliente) {
        this.rubro2Cliente = rubro2Cliente;
    }

    public String getRubro3Cliente() {
        return rubro3Cliente;
    }

    public void setRubro3Cliente(String rubro3Cliente) {
        this.rubro3Cliente = rubro3Cliente;
    }

    public String getTieneConvenio() {
        return tieneConvenio;
    }

    public void setTieneConvenio(String tieneConvenio) {
        this.tieneConvenio = tieneConvenio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public short getDiasPromedAtraso() {
        return diasPromedAtraso;
    }

    public void setDiasPromedAtraso(short diasPromedAtraso) {
        this.diasPromedAtraso = diasPromedAtraso;
    }

    public String getRubro1Cli() {
        return rubro1Cli;
    }

    public void setRubro1Cli(String rubro1Cli) {
        this.rubro1Cli = rubro1Cli;
    }

    public String getRubro2Cli() {
        return rubro2Cli;
    }

    public void setRubro2Cli(String rubro2Cli) {
        this.rubro2Cli = rubro2Cli;
    }

    public String getRubro3Cli() {
        return rubro3Cli;
    }

    public void setRubro3Cli(String rubro3Cli) {
        this.rubro3Cli = rubro3Cli;
    }

    public String getRubro4Cli() {
        return rubro4Cli;
    }

    public void setRubro4Cli(String rubro4Cli) {
        this.rubro4Cli = rubro4Cli;
    }

    public String getRubro5Cli() {
        return rubro5Cli;
    }

    public void setRubro5Cli(String rubro5Cli) {
        this.rubro5Cli = rubro5Cli;
    }

    public String getAsocobligcontfact() {
        return asocobligcontfact;
    }

    public void setAsocobligcontfact(String asocobligcontfact) {
        this.asocobligcontfact = asocobligcontfact;
    }

    public String getRubro4Cliente() {
        return rubro4Cliente;
    }

    public void setRubro4Cliente(String rubro4Cliente) {
        this.rubro4Cliente = rubro4Cliente;
    }

    public String getRubro5Cliente() {
        return rubro5Cliente;
    }

    public void setRubro5Cliente(String rubro5Cliente) {
        this.rubro5Cliente = rubro5Cliente;
    }

    public String getRubro6Cliente() {
        return rubro6Cliente;
    }

    public void setRubro6Cliente(String rubro6Cliente) {
        this.rubro6Cliente = rubro6Cliente;
    }

    public String getRubro7Cliente() {
        return rubro7Cliente;
    }

    public void setRubro7Cliente(String rubro7Cliente) {
        this.rubro7Cliente = rubro7Cliente;
    }

    public String getRubro8Cliente() {
        return rubro8Cliente;
    }

    public void setRubro8Cliente(String rubro8Cliente) {
        this.rubro8Cliente = rubro8Cliente;
    }

    public String getRubro9Cliente() {
        return rubro9Cliente;
    }

    public void setRubro9Cliente(String rubro9Cliente) {
        this.rubro9Cliente = rubro9Cliente;
    }

    public String getRubro10Cliente() {
        return rubro10Cliente;
    }

    public void setRubro10Cliente(String rubro10Cliente) {
        this.rubro10Cliente = rubro10Cliente;
    }

    public String getUsarPreciosCorp() {
        return usarPreciosCorp;
    }

    public void setUsarPreciosCorp(String usarPreciosCorp) {
        this.usarPreciosCorp = usarPreciosCorp;
    }

    public String getUsarExencimpCorp() {
        return usarExencimpCorp;
    }

    public void setUsarExencimpCorp(String usarExencimpCorp) {
        this.usarExencimpCorp = usarExencimpCorp;
    }

    public String getDiasDeCobro() {
        return diasDeCobro;
    }

    public void setDiasDeCobro(String diasDeCobro) {
        this.diasDeCobro = diasDeCobro;
    }

    public String getAjusteFechaCobro() {
        return ajusteFechaCobro;
    }

    public void setAjusteFechaCobro(String ajusteFechaCobro) {
        this.ajusteFechaCobro = ajusteFechaCobro;
    }

    public String getGln() {
        return gln;
    }

    public void setGln(String gln) {
        this.gln = gln;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    public String getRubro11Cliente() {
        return rubro11Cliente;
    }

    public void setRubro11Cliente(String rubro11Cliente) {
        this.rubro11Cliente = rubro11Cliente;
    }

    public String getRubro12Cliente() {
        return rubro12Cliente;
    }

    public void setRubro12Cliente(String rubro12Cliente) {
        this.rubro12Cliente = rubro12Cliente;
    }

    public String getRubro13Cliente() {
        return rubro13Cliente;
    }

    public void setRubro13Cliente(String rubro13Cliente) {
        this.rubro13Cliente = rubro13Cliente;
    }

    public String getRubro14Cliente() {
        return rubro14Cliente;
    }

    public void setRubro14Cliente(String rubro14Cliente) {
        this.rubro14Cliente = rubro14Cliente;
    }

    public String getRubro15Cliente() {
        return rubro15Cliente;
    }

    public void setRubro15Cliente(String rubro15Cliente) {
        this.rubro15Cliente = rubro15Cliente;
    }

    public String getRubro16Cliente() {
        return rubro16Cliente;
    }

    public void setRubro16Cliente(String rubro16Cliente) {
        this.rubro16Cliente = rubro16Cliente;
    }

    public String getRubro17Cliente() {
        return rubro17Cliente;
    }

    public void setRubro17Cliente(String rubro17Cliente) {
        this.rubro17Cliente = rubro17Cliente;
    }

    public String getRubro18Cliente() {
        return rubro18Cliente;
    }

    public void setRubro18Cliente(String rubro18Cliente) {
        this.rubro18Cliente = rubro18Cliente;
    }

    public String getRubro19Cliente() {
        return rubro19Cliente;
    }

    public void setRubro19Cliente(String rubro19Cliente) {
        this.rubro19Cliente = rubro19Cliente;
    }

    public String getRubro20Cliente() {
        return rubro20Cliente;
    }

    public void setRubro20Cliente(String rubro20Cliente) {
        this.rubro20Cliente = rubro20Cliente;
    }

    public String getAceptaDocElectronico() {
        return aceptaDocElectronico;
    }

    public void setAceptaDocElectronico(String aceptaDocElectronico) {
        this.aceptaDocElectronico = aceptaDocElectronico;
    }

    public String getConfirmaDocElectronico() {
        return confirmaDocElectronico;
    }

    public void setConfirmaDocElectronico(String confirmaDocElectronico) {
        this.confirmaDocElectronico = confirmaDocElectronico;
    }

    public String getEmailDocElectronico() {
        return emailDocElectronico;
    }

    public void setEmailDocElectronico(String emailDocElectronico) {
        this.emailDocElectronico = emailDocElectronico;
    }

    public String getEmailPedEdi() {
        return emailPedEdi;
    }

    public void setEmailPedEdi(String emailPedEdi) {
        this.emailPedEdi = emailPedEdi;
    }

    public String getAceptaDocEdi() {
        return aceptaDocEdi;
    }

    public void setAceptaDocEdi(String aceptaDocEdi) {
        this.aceptaDocEdi = aceptaDocEdi;
    }

    public String getNotificarErrorEdi() {
        return notificarErrorEdi;
    }

    public void setNotificarErrorEdi(String notificarErrorEdi) {
        this.notificarErrorEdi = notificarErrorEdi;
    }

    public String getEmailErrorPedEdi() {
        return emailErrorPedEdi;
    }

    public void setEmailErrorPedEdi(String emailErrorPedEdi) {
        this.emailErrorPedEdi = emailErrorPedEdi;
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

    public String getRegimenTrib() {
        return regimenTrib;
    }

    public void setRegimenTrib(String regimenTrib) {
        this.regimenTrib = regimenTrib;
    }

    public String getMoroso() {
        return moroso;
    }

    public void setMoroso(String moroso) {
        this.moroso = moroso;
    }

    public String getModifNombEnFac() {
        return modifNombEnFac;
    }

    public void setModifNombEnFac(String modifNombEnFac) {
        this.modifNombEnFac = modifNombEnFac;
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

    public String getPermiteDocGp() {
        return permiteDocGp;
    }

    public void setPermiteDocGp(String permiteDocGp) {
        this.permiteDocGp = permiteDocGp;
    }

    public String getParticipaFlujocaja() {
        return participaFlujocaja;
    }

    public void setParticipaFlujocaja(String participaFlujocaja) {
        this.participaFlujocaja = participaFlujocaja;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    public Date getFchHoraUltMod() {
        return fchHoraUltMod;
    }

    public void setFchHoraUltMod(Date fchHoraUltMod) {
        this.fchHoraUltMod = fchHoraUltMod;
    }

    public String getEmailDocElectronicoCopia() {
        return emailDocElectronicoCopia;
    }

    public void setEmailDocElectronicoCopia(String emailDocElectronicoCopia) {
        this.emailDocElectronicoCopia = emailDocElectronicoCopia;
    }

    public String getDetallarKits() {
        return detallarKits;
    }

    public void setDetallarKits(String detallarKits) {
        this.detallarKits = detallarKits;
    }

    public String getXsltPersonalizado() {
        return xsltPersonalizado;
    }

    public void setXsltPersonalizado(String xsltPersonalizado) {
        this.xsltPersonalizado = xsltPersonalizado;
    }

    public String getNombreAddenda() {
        return nombreAddenda;
    }

    public void setNombreAddenda(String nombreAddenda) {
        this.nombreAddenda = nombreAddenda;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(String categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
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

    public Integer getDetalleDireccion() {
        return detalleDireccion;
    }

    public void setDetalleDireccion(Integer detalleDireccion) {
        this.detalleDireccion = detalleDireccion;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public String getModeloRetencion() {
        return modeloRetencion;
    }

    public void setModeloRetencion(String modeloRetencion) {
        this.modeloRetencion = modeloRetencion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(String contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getNivelPrecio() {
        return nivelPrecio;
    }

    public void setNivelPrecio(String nivelPrecio) {
        this.nivelPrecio = nivelPrecio;
    }

    public String getMonedaNivel() {
        return monedaNivel;
    }

    public void setMonedaNivel(String monedaNivel) {
        this.monedaNivel = monedaNivel;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliente != null ? cliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cliente == null && other.cliente != null) || (this.cliente != null && !this.cliente.equals(other.cliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", cliente, nombre);
    }

}
