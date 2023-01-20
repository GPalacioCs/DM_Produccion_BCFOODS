/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "CS_ORDEN_PRODUCCION_BCFOODS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenProduccion.findAll", query = "SELECT o FROM OrdenProduccion o")})
@Data
@EqualsAndHashCode(of = "ordenProduccion")
@ToString(of = "ordenProduccion")
public class OrdenProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "ORDEN_PRODUCCION")
    private Long ordenProduccion;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "ARTICULO",name = "ARTICULO")
    private Articulo articulo;    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_ARTICULO")
    private BigDecimal cantidadArticulo = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORIDAD")
    private short prioridad=1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "MODULO_ORIGEN")
    private String moduloOrigen="PC";
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_A_LIBERAR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaALiberar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REQUERIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaRequerida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CONTABILIZADA")
    private String contabilizada = "N";
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MAT")
    private BigDecimal costoEstimMat=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MOP")
    private BigDecimal costoEstimMop=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_MOE")
    private BigDecimal costoEstimMoe=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_GIF")
    private BigDecimal costoEstimGif=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_OP_MAT")
    private BigDecimal costoEstimOpMat=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_OP_MOP")
    private BigDecimal costoEstimOpMop=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_OP_MOE")
    private BigDecimal costoEstimOpMoe=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_ESTIM_OP_GIF")
    private BigDecimal costoEstimOpGif=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_DE_CAMBIO")
    private BigDecimal tipoDeCambio=BigDecimal.ONE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MAT_L")
    private BigDecimal costoRealMatL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MOP_L")
    private BigDecimal costoRealMopL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MOE_L")
    private BigDecimal costoRealMoeL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_GIF_L")
    private BigDecimal costoRealGifL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMAT_L")
    private BigDecimal costoRealOpmatL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMOP_L")
    private BigDecimal costoRealOpmopL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMOE_L")
    private BigDecimal costoRealOpmoeL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPGIF_L")
    private BigDecimal costoRealOpgifL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MAT_D")
    private BigDecimal costoRealMatD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MOP_D")
    private BigDecimal costoRealMopD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_MOE_D")
    private BigDecimal costoRealMoeD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_GIF_D")
    private BigDecimal costoRealGifD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMAT_D")
    private BigDecimal costoRealOpmatD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMOP_D")
    private BigDecimal costoRealOpmopD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPMOE_D")
    private BigDecimal costoRealOpmoeD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_REAL_OPGIF_D")
    private BigDecimal costoRealOpgifD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_INVENTARIO_L")
    private BigDecimal costoInventarioL=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_INVENTARIO_D")
    private BigDecimal costoInventarioD=BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VARIACION_COSTO")
    private BigDecimal variacionCosto=BigDecimal.ZERO;
    @Size(max = 8)
    @Column(name = "ARCHIVOVIEWIT")
    private String archivoviewit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_LIBERACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiberacion;
    @Size(max = 25)
    @Column(name = "USUARIO_LIBERACION")
    private String usuarioLiberacion;
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @Size(max = 25)
    @Column(name = "USUARIO_CIERRE")
    private String usuarioCierre;
    @Column(name = "FECHA_HORA_COSTEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCosteo;
    @Column(name = "FECHA_HORA_ULT_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUltAct;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "VIEWIT")
    private String viewit;
    @Size(max = 1)
    @Column(name = "SELECCIONADA")
    private String seleccionada="N";
    @Size(max = 1)
    @Column(name = "ESTRUCTURA_MODIFIC")
    private String estructuraModific="N";
    @Column(name = "PORC_AJUSTE_COSTO")
    private BigDecimal porcAjusteCosto=BigDecimal.ZERO;
    @Size(max = 10)
    @Column(name = "ASIENTO_LIQUIDA")
    private String asientoLiquida;
    @Column(name = "DURACION")
    private BigDecimal duracion=BigDecimal.ZERO;
    @Column(name = "FECHA_TERMINADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTerminada;
    @Size(max = 1)
    @Column(name = "COSTO_INGRESO_PT")
    private String costoIngresoPt="O";
    @Size(max = 25)
    @Column(name = "USUARIO_PRECIERRE")
    private String usuarioPrecierre;
    @Column(name = "FECHA_HORA_PRECIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPrecierre;
    @Size(max = 25)
    @Column(name = "USUARIO_CANCELA")
    private String usuarioCancela;
    @Column(name = "FECHA_HORA_CANCELA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCancela;
    @Column(name = "FECHA_CIERRE_CONTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierreConta;
    @Size(max = 50)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Size(max = 15)
    @Column(name = "LOTE_PROD")
    private String loteProd = "ND";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PERMITIR_MOVIMIENTO_ORDEN")
    private String permitirMovimientoOrden="S";
    @Size(max = 1)
    @Column(name = "PERMITE_COSTO_CONTA")
    private String permiteCostoConta="N";

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "BODEGA_MP")
    private String bodegaMp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "BODEGA_PT")
    private String bodegaPt;
    @Size(max = 25)
    @Column(name = "CENTRO_COSTO")
    private String centroCosto;
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrdenProdEmpleado> empleadoList = new ArrayList<>();
    @NotNull
    private String version;
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false)
    List<OrdenProdMp> ordenProdMpList = new ArrayList<>();
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenProdMd> ordenProdMdList = new ArrayList<>();
    @OneToMany(mappedBy="ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)    
    private List<OrdenProdRubro> ordenProdRubroList = new ArrayList<>();    
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_VENCIMIENTO_LOTE")
    private Date fechaVencimientoLote = Utils.getDate(2017, 1, 1);    
    @Column(name="ASIENTO_ALINSA")
    private String asientoAlinsa;
    @Column(name="ASIENTO_BCFOODS")
    private String asientoBcfoods;
     @Column(name="CANTIDAD_MESA")
    private String Cantidad_Mesa;
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngresoProduccion> ingresoProduccoinList = new ArrayList<>();
    @OneToMany(mappedBy="ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpaqueEncabezado> empaqueEncabezadoList = new ArrayList<>();    
    private boolean fresco;
    @Column(name="EMPAQUE_APROBADO")
    private boolean empaqueAprobado;
    @OneToMany(mappedBy="ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Maquila> maquilaList = new ArrayList<>();    
    @OneToMany(mappedBy = "ordenProduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenProduccionActividad> ordenProduccionActividadList = new ArrayList<>();
    private boolean impreso;
    @Column(name = "APLICAR_MANO_DE_OBRA")
    private boolean aplicarManoDeObra = true;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    public EstadosProd getEstadoProd() {
        return EstadosProd.getEstadoProd(estado);
    }
    
    public BigDecimal getCostoUnitario() {
        BigDecimal costo = Utils.sumBigDecimals(empleadoList, e -> e.getCosto())
                .add(Utils.sumBigDecimals(ordenProdMpList, mp -> mp.getCosto().multiply(mp.getCantidad())))
                .add(Utils.sumBigDecimals(ordenProdMdList, md -> md.getCosto().multiply(md.getCantidad())))
                .add(Utils.sumBigDecimals(ordenProdRubroList, r -> r.getCosto().multiply(r.getCantidad())));
        return costo.divide(this.getCantidadArticulo(), 2, RoundingMode.HALF_EVEN);
    }    
    
    public BigDecimal getCostoUnitarioDolar() {
        BigDecimal costo = Utils.sumBigDecimals(empleadoList, e -> e.getCosto())
                .add(Utils.sumBigDecimals(ordenProdMpList, mp -> mp.getCostoDolar().multiply(mp.getCantidad())))
                .add(Utils.sumBigDecimals(ordenProdMdList, md -> md.getCostoDolar().multiply(md.getCantidad())))
                .add(Utils.sumBigDecimals(ordenProdRubroList, r -> r.getCostoDolar().multiply(r.getCantidad())));
        return costo.divide(this.getCantidadArticulo(), 2, RoundingMode.HALF_EVEN);
    }
    
    public BigDecimal getCantidadProducida() {
        return cantidadArticulo;
    }
    
    public boolean isTieneEmpaque() {
        return empaqueEncabezadoList != null && !empaqueEncabezadoList.isEmpty();
    }
    
    public List<IngresoProduccion> getIngresoProduccionByMp(MateriaPrima mp) {
        return getIngresoProduccoinList().stream()
                .filter(ip->ip.getMateriaPrima().getMateriaPrimaPK()
                .getArticuloHijo().equals(mp.getMateriaPrimaPK().getArticuloHijo()))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalMdReceta(Articulo articulo) {
        return Utils.sumBigDecimals(ingresoProduccoinList, ip -> ip.getCantMaterialReceta(articulo));
    }

    public BigDecimal getTotalRendimiento() {
        BigDecimal totalMp = Utils.sumBigDecimals(ordenProdMpList, mp -> mp.getCantidad());
        BigDecimal totalPt = Utils.sumBigDecimals(ingresoProduccoinList, ip -> ip.getCantidad());
        if(totalMp.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO.setScale(4);
        return totalPt.divide(totalMp, 4, RoundingMode.HALF_EVEN);
    }

}
