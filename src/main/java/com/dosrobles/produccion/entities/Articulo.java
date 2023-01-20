/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "ARTICULO")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO")
    private String articulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FACTOR_CONVER_1")
    private BigDecimal factorConver1;
    @Column(name = "FACTOR_CONVER_2")
    private BigDecimal factorConver2;
    @Column(name = "FACTOR_CONVER_3")
    private BigDecimal factorConver3;
    @Column(name = "FACTOR_CONVER_4")
    private BigDecimal factorConver4;
    @Column(name = "FACTOR_CONVER_5")
    private BigDecimal factorConver5;
    @Column(name = "FACTOR_CONVER_6")
    private BigDecimal factorConver6;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORIGEN_CORP")
    private String origenCorp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO_NETO")
    private BigDecimal pesoNeto = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO_BRUTO")
    private BigDecimal pesoBruto = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VOLUMEN")
    private BigDecimal volumen = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BULTOS")
    private short bultos = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTOR_EMPAQUE")
    private BigDecimal factorEmpaque = BigDecimal.ONE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTOR_VENTA")
    private BigDecimal factorVenta = BigDecimal.ONE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXISTENCIA_MINIMA")
    private BigDecimal existenciaMinima = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXISTENCIA_MAXIMA")
    private BigDecimal existenciaMaxima = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUNTO_DE_REORDEN")
    private BigDecimal puntoDeReorden = BigDecimal.ZERO;
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
    @Column(name = "COSTO_PROM_LOC")
    private BigDecimal costoPromLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_DOL")
    private BigDecimal costoPromDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_STD_LOC")
    private BigDecimal costoStdLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_STD_DOL")
    private BigDecimal costoStdDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ULT_LOC")
    private BigDecimal costoUltLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ULT_DOL")
    private BigDecimal costoUltDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_BASE_LOCAL")
    private BigDecimal precioBaseLocal = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_BASE_DOLAR")
    private BigDecimal precioBaseDolar = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ULTIMA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaSalida = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "ULTIMO_MOVIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoMovimiento = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "ULTIMO_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoIngreso = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "ULTIMO_INVENTARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoInventario = new Date();
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CLASE_ABC")
    private String claseAbc = "A";
    @Basic(optional = false)
    @NotNull
    @Column(name = "FRECUENCIA_CONTEO")
    private short frecuenciaConteo = 0;
    @Size(max = 20)
    @Column(name = "CODIGO_BARRAS_VENT")
    private String codigoBarrasVent;
    @Size(max = 20)
    @Column(name = "CODIGO_BARRAS_INVT")
    private String codigoBarrasInvt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo = "S";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_LOTES")
    private String usaLotes = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "OBLIGA_CUARENTENA")
    private String obligaCuarentena = "N";
    @Basic(optional = false)
    @NotNull
    @Column(name = "MIN_VIDA_COMPRA")
    private short minVidaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MIN_VIDA_CONSUMO")
    private short minVidaConsumo = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MIN_VIDA_VENTA")
    private short minVidaVenta = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIDA_UTIL_PROM")
    private short vidaUtilProm = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIAS_CUARENTENA")
    private short diasCuarentena = 0;
    @Size(max = 30)
    @Column(name = "ARTICULO_DEL_PROV")
    private String articuloDelProv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN_MINIMA")
    private BigDecimal ordenMinima = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLAZO_REABAST")
    private short plazoReabast = 0;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOTE_MULTIPLO")
    private BigDecimal loteMultiplo = BigDecimal.ZERO;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "UTILIZADO_MANUFACT")
    private String utilizadoManufact = "N";
    @Size(max = 25)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FCH_HORA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraCreacion;
    @Size(max = 25)
    @Column(name = "USUARIO_ULT_MODIF")
    private String usuarioUltModif;
    @Column(name = "FCH_HORA_ULT_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraUltModif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_NUMEROS_SERIE")
    private String usaNumerosSerie = "N";
    @Size(max = 1)
    @Column(name = "MODALIDAD_INV_FIS")
    private String modalidadInvFis;
    @Size(max = 1)
    @Column(name = "TIPO_COD_BARRA_DET")
    private String tipoCodBarraDet;
    @Size(max = 1)
    @Column(name = "TIPO_COD_BARRA_ALM")
    private String tipoCodBarraAlm;
    @Size(max = 1)
    @Column(name = "USA_REGLAS_LOCALES")
    private String usaReglasLocales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PERECEDERO")
    private String perecedero = "N";
    @Size(max = 13)
    @Column(name = "GTIN")
    private String gtin;
    @Size(max = 35)
    @Column(name = "MANUFACTURADOR")
    private String manufacturador;
    @Size(max = 4)
    @Column(name = "RETENCION_VENTA")
    private String retencionVenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_COSTO")
    private String tipoCosto;
    @Size(max = 20)
    @Column(name = "ARTICULO_ENVASE")
    private String articuloEnvase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ES_ENVASE")
    private String esEnvase = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USA_CONTROL_ENVASE")
    private String usaControlEnvase = "N";    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_COMPARATIVO_LOC")
    private BigDecimal costoPromComparativoLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_COMPARATIVO_DOLAR")
    private BigDecimal costoPromComparativoDolar = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_ULTIMO_LOC")
    private BigDecimal costoPromUltimoLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_PROM_ULTIMO_DOL")
    private BigDecimal costoPromUltimoDol = BigDecimal.ZERO;
    @Size(max = 5)
    @Column(name = "ESTILO")
    private String estilo;
    @Size(max = 5)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 4)
    @Column(name = "RETENCION_COMPRA")
    private String retencionCompra;
    @Size(max = 5)
    @Column(name = "TALLA")
    private String talla;
    @Size(max = 4)
    @Column(name = "CODIGO_RETENCION")
    private String codigoRetencion;
    @Size(max = 4)
    @Column(name = "MODELO_RETENCION")
    private String modeloRetencion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMPUESTO")    
    private Impuesto impuesto;
    @Size(max = 20)
    @Column(name = "PROVEEDOR")
    private String proveedor;
    @Size(max = 4)
    @Column(name = "PLANTILLA_SERIE")
    private String plantillaSerie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<LineaDocInv> lineaDocInvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExistenciaBodega> existenciaBodegaList = new ArrayList<>();
    @JoinColumn(name = "ARTICULO_CUENTA", referencedColumnName = "ARTICULO_CUENTA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ArticuloCuenta articuloCuenta = new ArticuloCuenta("ND");
    @JoinColumn(name = "UNIDAD_ALMACEN", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadDeMedida unidadAlmacen;
    @JoinColumn(name = "UNIDAD_EMPAQUE", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadDeMedida unidadEmpaque;
    @JoinColumn(name = "CLASIFICACION_6", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion6;
    @JoinColumn(name = "CLASIFICACION_1", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion1;
    @JoinColumn(name = "CLASIFICACION_2", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion2;
    @JoinColumn(name = "CLASIFICACION_3", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion3;
    @JoinColumn(name = "CLASIFICACION_4", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion4;
    @JoinColumn(name = "CLASIFICACION_5", referencedColumnName = "CLASIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clasificacion clasificacion5;
    @JoinColumn(name = "UNIDAD_VENTA", referencedColumnName = "UNIDAD_MEDIDA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UnidadDeMedida unidadVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<TransaccionInv> transaccionInvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("orden ASC")
    private List<Etapa> etapaList = new ArrayList<>();
    @Column(name="CANTIDAD_PRODUCCION")
    private BigDecimal cantidadProduccion = BigDecimal.ZERO;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articuloPadre", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ArticuloEnsamble> articuloEnsambleList = new ArrayList<>();
    @OneToMany(mappedBy = "articulo")
    private List<EstrucManufactura> estrucManufacturaList = new ArrayList<>();
    @OneToMany(mappedBy = "articulo")
    private List<EstrucMaterial> estrucMaterialList = new ArrayList<>();
    @OneToMany(mappedBy = "articulo")
    private List<Lote> loteList = new ArrayList<>();
    @OneToMany(mappedBy="articulo")
    @Getter @Setter
    private List<ClasificAdiArticulo> clasificAdiArticuloList = new ArrayList<>();
    @Getter @Setter
    private BigDecimal rendimiento;
    
    @Transient
    private Consecutivo consecutivo;

    public Articulo() {
    }

    public Articulo(String articulo) {
        this.articulo = articulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getFactorConver1() {
        return factorConver1;
    }

    public void setFactorConver1(BigDecimal factorConver1) {
        this.factorConver1 = factorConver1;
    }

    public BigDecimal getFactorConver2() {
        return factorConver2;
    }

    public void setFactorConver2(BigDecimal factorConver2) {
        this.factorConver2 = factorConver2;
    }

    public BigDecimal getFactorConver3() {
        return factorConver3;
    }

    public void setFactorConver3(BigDecimal factorConver3) {
        this.factorConver3 = factorConver3;
    }

    public BigDecimal getFactorConver4() {
        return factorConver4;
    }

    public void setFactorConver4(BigDecimal factorConver4) {
        this.factorConver4 = factorConver4;
    }

    public BigDecimal getFactorConver5() {
        return factorConver5;
    }

    public void setFactorConver5(BigDecimal factorConver5) {
        this.factorConver5 = factorConver5;
    }

    public BigDecimal getFactorConver6() {
        return factorConver6;
    }

    public void setFactorConver6(BigDecimal factorConver6) {
        this.factorConver6 = factorConver6;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigenCorp() {
        return origenCorp;
    }

    public void setOrigenCorp(String origenCorp) {
        this.origenCorp = origenCorp;
    }

    public BigDecimal getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(BigDecimal pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public short getBultos() {
        return bultos;
    }

    public void setBultos(short bultos) {
        this.bultos = bultos;
    }

    public BigDecimal getFactorEmpaque() {
        return factorEmpaque;
    }

    public void setFactorEmpaque(BigDecimal factorEmpaque) {
        this.factorEmpaque = factorEmpaque;
    }

    public BigDecimal getFactorVenta() {
        return factorVenta;
    }

    public void setFactorVenta(BigDecimal factorVenta) {
        this.factorVenta = factorVenta;
    }

    public BigDecimal getExistenciaMinima() {
        return existenciaMinima;
    }

    public void setExistenciaMinima(BigDecimal existenciaMinima) {
        this.existenciaMinima = existenciaMinima;
    }

    public BigDecimal getExistenciaMaxima() {
        return existenciaMaxima;
    }

    public void setExistenciaMaxima(BigDecimal existenciaMaxima) {
        this.existenciaMaxima = existenciaMaxima;
    }

    public BigDecimal getPuntoDeReorden() {
        return puntoDeReorden;
    }

    public void setPuntoDeReorden(BigDecimal puntoDeReorden) {
        this.puntoDeReorden = puntoDeReorden;
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

    public BigDecimal getCostoPromLoc() {
        return costoPromLoc;
    }

    public void setCostoPromLoc(BigDecimal costoPromLoc) {
        this.costoPromLoc = costoPromLoc;
    }

    public BigDecimal getCostoPromDol() {
        return costoPromDol;
    }

    public void setCostoPromDol(BigDecimal costoPromDol) {
        this.costoPromDol = costoPromDol;
    }

    public BigDecimal getCostoStdLoc() {
        return costoStdLoc;
    }

    public void setCostoStdLoc(BigDecimal costoStdLoc) {
        this.costoStdLoc = costoStdLoc;
    }

    public BigDecimal getCostoStdDol() {
        return costoStdDol;
    }

    public void setCostoStdDol(BigDecimal costoStdDol) {
        this.costoStdDol = costoStdDol;
    }

    public BigDecimal getCostoUltLoc() {
        return costoUltLoc;
    }

    public void setCostoUltLoc(BigDecimal costoUltLoc) {
        this.costoUltLoc = costoUltLoc;
    }

    public BigDecimal getCostoUltDol() {
        return costoUltDol;
    }

    public void setCostoUltDol(BigDecimal costoUltDol) {
        this.costoUltDol = costoUltDol;
    }

    public BigDecimal getPrecioBaseLocal() {
        return precioBaseLocal;
    }

    public void setPrecioBaseLocal(BigDecimal precioBaseLocal) {
        this.precioBaseLocal = precioBaseLocal;
    }

    public BigDecimal getPrecioBaseDolar() {
        return precioBaseDolar;
    }

    public void setPrecioBaseDolar(BigDecimal precioBaseDolar) {
        this.precioBaseDolar = precioBaseDolar;
    }

    public Date getUltimaSalida() {
        return ultimaSalida;
    }

    public void setUltimaSalida(Date ultimaSalida) {
        this.ultimaSalida = ultimaSalida;
    }

    public Date getUltimoMovimiento() {
        return ultimoMovimiento;
    }

    public void setUltimoMovimiento(Date ultimoMovimiento) {
        this.ultimoMovimiento = ultimoMovimiento;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public Date getUltimoInventario() {
        return ultimoInventario;
    }

    public void setUltimoInventario(Date ultimoInventario) {
        this.ultimoInventario = ultimoInventario;
    }

    public String getClaseAbc() {
        return claseAbc;
    }

    public void setClaseAbc(String claseAbc) {
        this.claseAbc = claseAbc;
    }

    public short getFrecuenciaConteo() {
        return frecuenciaConteo;
    }

    public void setFrecuenciaConteo(short frecuenciaConteo) {
        this.frecuenciaConteo = frecuenciaConteo;
    }

    public String getCodigoBarrasVent() {
        return codigoBarrasVent;
    }

    public void setCodigoBarrasVent(String codigoBarrasVent) {
        this.codigoBarrasVent = codigoBarrasVent;
    }

    public String getCodigoBarrasInvt() {
        return codigoBarrasInvt;
    }

    public void setCodigoBarrasInvt(String codigoBarrasInvt) {
        this.codigoBarrasInvt = codigoBarrasInvt;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getUsaLotes() {
        return usaLotes;
    }

    public void setUsaLotes(String usaLotes) {
        this.usaLotes = usaLotes;
    }

    public String getObligaCuarentena() {
        return obligaCuarentena;
    }

    public void setObligaCuarentena(String obligaCuarentena) {
        this.obligaCuarentena = obligaCuarentena;
    }

    public short getMinVidaCompra() {
        return minVidaCompra;
    }

    public void setMinVidaCompra(short minVidaCompra) {
        this.minVidaCompra = minVidaCompra;
    }

    public short getMinVidaConsumo() {
        return minVidaConsumo;
    }

    public void setMinVidaConsumo(short minVidaConsumo) {
        this.minVidaConsumo = minVidaConsumo;
    }

    public short getMinVidaVenta() {
        return minVidaVenta;
    }

    public void setMinVidaVenta(short minVidaVenta) {
        this.minVidaVenta = minVidaVenta;
    }

    public short getVidaUtilProm() {
        return vidaUtilProm;
    }

    public void setVidaUtilProm(short vidaUtilProm) {
        this.vidaUtilProm = vidaUtilProm;
    }

    public short getDiasCuarentena() {
        return diasCuarentena;
    }

    public void setDiasCuarentena(short diasCuarentena) {
        this.diasCuarentena = diasCuarentena;
    }

    public String getArticuloDelProv() {
        return articuloDelProv;
    }

    public void setArticuloDelProv(String articuloDelProv) {
        this.articuloDelProv = articuloDelProv;
    }

    public BigDecimal getOrdenMinima() {
        return ordenMinima;
    }

    public void setOrdenMinima(BigDecimal ordenMinima) {
        this.ordenMinima = ordenMinima;
    }

    public short getPlazoReabast() {
        return plazoReabast;
    }

    public void setPlazoReabast(short plazoReabast) {
        this.plazoReabast = plazoReabast;
    }

    public BigDecimal getLoteMultiplo() {
        return loteMultiplo;
    }

    public void setLoteMultiplo(BigDecimal loteMultiplo) {
        this.loteMultiplo = loteMultiplo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getUtilizadoManufact() {
        return utilizadoManufact;
    }

    public void setUtilizadoManufact(String utilizadoManufact) {
        this.utilizadoManufact = utilizadoManufact;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFchHoraCreacion() {
        return fchHoraCreacion;
    }

    public void setFchHoraCreacion(Date fchHoraCreacion) {
        this.fchHoraCreacion = fchHoraCreacion;
    }

    public String getUsuarioUltModif() {
        return usuarioUltModif;
    }

    public void setUsuarioUltModif(String usuarioUltModif) {
        this.usuarioUltModif = usuarioUltModif;
    }

    public Date getFchHoraUltModif() {
        return fchHoraUltModif;
    }

    public void setFchHoraUltModif(Date fchHoraUltModif) {
        this.fchHoraUltModif = fchHoraUltModif;
    }

    public String getUsaNumerosSerie() {
        return usaNumerosSerie;
    }

    public void setUsaNumerosSerie(String usaNumerosSerie) {
        this.usaNumerosSerie = usaNumerosSerie;
    }

    public String getModalidadInvFis() {
        return modalidadInvFis;
    }

    public void setModalidadInvFis(String modalidadInvFis) {
        this.modalidadInvFis = modalidadInvFis;
    }

    public String getTipoCodBarraDet() {
        return tipoCodBarraDet;
    }

    public void setTipoCodBarraDet(String tipoCodBarraDet) {
        this.tipoCodBarraDet = tipoCodBarraDet;
    }

    public String getTipoCodBarraAlm() {
        return tipoCodBarraAlm;
    }

    public void setTipoCodBarraAlm(String tipoCodBarraAlm) {
        this.tipoCodBarraAlm = tipoCodBarraAlm;
    }

    public String getUsaReglasLocales() {
        return usaReglasLocales;
    }

    public void setUsaReglasLocales(String usaReglasLocales) {
        this.usaReglasLocales = usaReglasLocales;
    }

    public String getPerecedero() {
        return perecedero;
    }

    public void setPerecedero(String perecedero) {
        this.perecedero = perecedero;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getManufacturador() {
        return manufacturador;
    }

    public void setManufacturador(String manufacturador) {
        this.manufacturador = manufacturador;
    }

    public String getRetencionVenta() {
        return retencionVenta;
    }

    public void setRetencionVenta(String retencionVenta) {
        this.retencionVenta = retencionVenta;
    }

    public String getTipoCosto() {
        return tipoCosto;
    }

    public void setTipoCosto(String tipoCosto) {
        this.tipoCosto = tipoCosto;
    }

    public String getArticuloEnvase() {
        return articuloEnvase;
    }

    public void setArticuloEnvase(String articuloEnvase) {
        this.articuloEnvase = articuloEnvase;
    }

    public String getEsEnvase() {
        return esEnvase;
    }

    public void setEsEnvase(String esEnvase) {
        this.esEnvase = esEnvase;
    }

    public String getUsaControlEnvase() {
        return usaControlEnvase;
    }

    public void setUsaControlEnvase(String usaControlEnvase) {
        this.usaControlEnvase = usaControlEnvase;
    }

    public BigDecimal getCostoPromComparativoLoc() {
        return costoPromComparativoLoc;
    }

    public void setCostoPromComparativoLoc(BigDecimal costoPromComparativoLoc) {
        this.costoPromComparativoLoc = costoPromComparativoLoc;
    }

    public BigDecimal getCostoPromComparativoDolar() {
        return costoPromComparativoDolar;
    }

    public void setCostoPromComparativoDolar(BigDecimal costoPromComparativoDolar) {
        this.costoPromComparativoDolar = costoPromComparativoDolar;
    }

    public BigDecimal getCostoPromUltimoLoc() {
        return costoPromUltimoLoc;
    }

    public void setCostoPromUltimoLoc(BigDecimal costoPromUltimoLoc) {
        this.costoPromUltimoLoc = costoPromUltimoLoc;
    }

    public BigDecimal getCostoPromUltimoDol() {
        return costoPromUltimoDol;
    }

    public void setCostoPromUltimoDol(BigDecimal costoPromUltimoDol) {
        this.costoPromUltimoDol = costoPromUltimoDol;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRetencionCompra() {
        return retencionCompra;
    }

    public void setRetencionCompra(String retencionCompra) {
        this.retencionCompra = retencionCompra;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCodigoRetencion() {
        return codigoRetencion;
    }

    public void setCodigoRetencion(String codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    public String getModeloRetencion() {
        return modeloRetencion;
    }

    public void setModeloRetencion(String modeloRetencion) {
        this.modeloRetencion = modeloRetencion;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getPlantillaSerie() {
        return plantillaSerie;
    }

    public void setPlantillaSerie(String plantillaSerie) {
        this.plantillaSerie = plantillaSerie;
    }

    public List<LineaDocInv> getLineaDocInvList() {
        return lineaDocInvList;
    }

    public void setLineaDocInvList(List<LineaDocInv> lineaDocInvList) {
        this.lineaDocInvList = lineaDocInvList;
    }

    public List<ExistenciaBodega> getExistenciaBodegaList() {
        return existenciaBodegaList;
    }

    public void setExistenciaBodegaList(List<ExistenciaBodega> existenciaBodegaList) {
        this.existenciaBodegaList = existenciaBodegaList;
    }

    public ArticuloCuenta getArticuloCuenta() {
        return articuloCuenta;
    }

    public void setArticuloCuenta(ArticuloCuenta articuloCuenta) {
        this.articuloCuenta = articuloCuenta;
    }

    public UnidadDeMedida getUnidadAlmacen() {
        return unidadAlmacen;
    }

    public void setUnidadAlmacen(UnidadDeMedida unidadAlmacen) {
        this.unidadAlmacen = unidadAlmacen;
    }

    public UnidadDeMedida getUnidadEmpaque() {
        return unidadEmpaque;
    }

    public void setUnidadEmpaque(UnidadDeMedida unidadEmpaque) {
        this.unidadEmpaque = unidadEmpaque;
    }

    public Clasificacion getClasificacion6() {
        return clasificacion6;
    }

    public void setClasificacion6(Clasificacion clasificacion6) {
        this.clasificacion6 = clasificacion6;
    }

    public Clasificacion getClasificacion1() {
        return clasificacion1;
    }

    public void setClasificacion1(Clasificacion clasificacion1) {
        this.clasificacion1 = clasificacion1;
    }

    public Clasificacion getClasificacion2() {
        return clasificacion2;
    }

    public void setClasificacion2(Clasificacion clasificacion2) {
        this.clasificacion2 = clasificacion2;
    }

    public Clasificacion getClasificacion3() {
        return clasificacion3;
    }

    public void setClasificacion3(Clasificacion clasificacion3) {
        this.clasificacion3 = clasificacion3;
    }

    public Clasificacion getClasificacion4() {
        return clasificacion4;
    }

    public void setClasificacion4(Clasificacion clasificacion4) {
        this.clasificacion4 = clasificacion4;
    }

    public Clasificacion getClasificacion5() {
        return clasificacion5;
    }

    public void setClasificacion5(Clasificacion clasificacion5) {
        this.clasificacion5 = clasificacion5;
    }

    public UnidadDeMedida getUnidadVenta() {
        return unidadVenta;
    }

    public void setUnidadVenta(UnidadDeMedida unidadVenta) {
        this.unidadVenta = unidadVenta;
    }

    public List<TransaccionInv> getTransaccionInvList() {
        return transaccionInvList;
    }

    public void setTransaccionInvList(List<TransaccionInv> transaccionInvList) {
        this.transaccionInvList = transaccionInvList;
    }

    public List<Etapa> getEtapaList() {
        return etapaList;
    }

    public void setEtapaList(List<Etapa> etapaList) {
        this.etapaList = etapaList;
    }

    public BigDecimal getCantidadProduccion() {
        return cantidadProduccion;
    }

    public void setCantidadProduccion(BigDecimal cantidadProduccion) {
        this.cantidadProduccion = cantidadProduccion;
    }

    public List<ArticuloEnsamble> getArticuloEnsambleList() {
        return articuloEnsambleList;
    }

    public void setArticuloEnsambleList(List<ArticuloEnsamble> articuloEnsambleList) {
        this.articuloEnsambleList = articuloEnsambleList;
    }

    public Consecutivo getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Consecutivo consecutivo) {
        this.consecutivo = consecutivo;
    }

    public List<EstrucManufactura> getEstrucManufacturaList() {
        return estrucManufacturaList;
    }

    public void setEstrucManufacturaList(List<EstrucManufactura> estrucManufacturaList) {
        this.estrucManufacturaList = estrucManufacturaList;
    }

    public List<EstrucMaterial> getEstrucMaterialList() {
        return estrucMaterialList;
    }

    public void setEstrucMaterialList(List<EstrucMaterial> estrucMaterialList) {
        this.estrucMaterialList = estrucMaterialList;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }
    
    public boolean isFresco() {
        return StringUtils.trimToEmpty(descripcion).toUpperCase().startsWith("FRESH");
    }
    
    public boolean isMateriaPrima() {
        return StringUtils.trimToEmpty(descripcion).startsWith("MP");
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        return true;
    }
    
    public BigDecimal getPrecioMaquila() {
        ClasificAdiArticulo caa = clasificAdiArticuloList.stream().findFirst().orElse(null);
        if(caa==null || caa.getValor() == null || caa.getValor().getMaquilaPrecio() == null || caa.getValor().getMaquilaPrecio().getPrecio() == null) return BigDecimal.ZERO;
        return caa.getValor().getMaquilaPrecio().getPrecio();
    }
    
    public ClasificacionAdiValor getTransformacion() {
        ClasificAdiArticulo caa = clasificAdiArticuloList.stream().findFirst().orElse(null);
        if(caa != null) {
            return caa.getValor();
        }
        return null;
    }

    @Override
    public String toString() {
        return articulo;
    }
    
    
    public MateriaPrima getMateriaPrima() {
        MateriaPrima materiaPrima = null;
        Etapa etapa = getEtapaList().stream().findFirst().orElse(null);
        if (etapa != null) {
            materiaPrima = etapa.getMateriaPrimaList().stream().filter(mp -> mp.getArticuloHijo().isMateriaPrima()).findFirst().orElse(null);
        }
        if(materiaPrima == null) {
//            throw new BusinessValidationException(String.format("El producto %s no tiene asociada una materia prima", getArticulo()));
        }
        return materiaPrima;
    }

    public MateriaPrima getMateriaPrimaByArticulo(Articulo articulo) {
        if (!CollectionUtils.isEmpty(getEtapaList())) {
            return getEtapaList().stream().findFirst().get().getMateriaPrimaList()
                    .stream().filter(mp -> mp.getArticuloHijo().getArticulo().equals(articulo.getArticulo()))
                    .findFirst().orElse(null);
        }
        return null;
    }
    
    public String getArticuloAndDesc() {
        return String.format("%s - %s", articulo, descripcion);
    }
    
    public String getArticuloDesc() {
        return getArticuloAndDesc();
    }
    
}
