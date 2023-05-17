package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.enums.EstadoRecepcion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="ORDEN_COMPRA", schema = "ALINSA")
@Getter @Setter
public class OrdenCompra {
    @Id
    @Column(name="ORDEN_COMPRA")
    private String ordenCompra;
    @ManyToOne
    @JoinColumn(name="PROVEEDOR")
    private Proveedor proveedor;
    @ManyToOne
    @JoinColumn(name="BODEGA")
    private Bodega bodega;
    @ManyToOne
    @JoinColumn(name="MONEDA")
    private Moneda moneda;
    @ManyToOne
    @JoinColumn(name="PAIS")
    private Pais pais = new Pais("505");
    @Column(name="MODULO_ORIGEN")
    private String moduloOrigen = "DM";
    @Column(name="FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();
    @Column(name="FECHA_REQUERIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRequerida = new Date();
    @ManyToOne
    @JoinColumn(name="CONDICION_PAGO")
    private CondicionPago condicionPago;
    @Column(name="TIPO_DESCUENTO")
    private String tipoDescuento = "";
    @Column(name="PORC_DESCUENTO")
    private BigDecimal porcDescuento = BigDecimal.ZERO;
    @Column(name="TOTAL_MERCADERIA")
    private BigDecimal totalMercaderia = BigDecimal.ZERO;
    @Column(name="TOTAL_IMPUESTO1")
    private BigDecimal totalImpuesto1 = BigDecimal.ZERO;
    @Column(name="TOTAL_IMPUESTO2")
    private BigDecimal totalImpuesto2 = BigDecimal.ZERO;
    @Column(name="MONTO_FLETE")
    private BigDecimal montoFlete = BigDecimal.ZERO;
    @Column(name="MONTO_SEGURO")
    private BigDecimal montoSeguro = BigDecimal.ZERO;
    @Column(name="MONTO_DOCUMENTACIO")
    private BigDecimal montoDocumentacio = BigDecimal.ZERO;
    @Column(name="MONTO_ANTICIPO")
    private BigDecimal montoAnticipo = BigDecimal.ZERO;
    @Column(name="TOTAL_A_COMPRAR")
    private BigDecimal totalComprar = BigDecimal.ZERO;
    @Column(name="PRIORIDAD")
    private String prioridad = "A";
    @Column(name="ESTADO")
    private String estado = "A";
    @Column(name="IMPRESA")
    private String impresa = "N";
    @Column(name="FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora = new Date();
    @Column(name="REQUIERE_CONFIRMA")
    private String requiereConfirma = "S";
    @Column(name="CONFIRMADA")
    private String confirmada = "N";
    @Column(name="ORDEN_PROGRAMADA")
    private String ordenProgramada = "P";
    @Column(name="RECIBIDO_DE_MAS")
    private String redibidoDeMas = "N";
    @Column(name="TIPO_PRORRATEO_OC")
    private String tipoProrrateoOc = "NI";
    @Column(name="BASE_IMPUESTO1")
    private BigDecimal baseImpuesto1 = BigDecimal.ZERO;
    @Column(name="BASE_IMPUESTO2")
    private BigDecimal baseImpuesto2 = BigDecimal.ZERO;
    @Column(name="TOT_IMP1_ASUM_DESC")
    private BigDecimal totImp1asumDesc = BigDecimal.ZERO;
    @Column(name="TOT_IMP1_ASUM_NODESC")
    private BigDecimal totImp1asumNoDesc = BigDecimal.ZERO;
    @Column(name="TOT_IMP1_RETE_DESC")
    private BigDecimal totImp1reteDesc = BigDecimal.ZERO;
    @Column(name="TOT_IMP1_RETE_NODESC")
    private BigDecimal totImp1reteNoDesc = BigDecimal.ZERO;
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenCompraLinea> lineas = new ArrayList<>();
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("pesada")
    private List<Pesada> pesadas = new ArrayList<>();
    @Column(name="CS_FRESCO")
    private boolean fresco = true;
    @Column(name="ESTADO_RECEPCION")
    @Enumerated(EnumType.STRING)
    private EstadoRecepcion estadoRecepcion = EstadoRecepcion.PLANEADO;
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION")
    @Getter @Setter
    private OrdenProduccion ordenProduccion;
    @Column(name = "ALINSA")
    private boolean alinsa = false;
    @Column(name = "TIPO_ALINSA")
    @Getter @Setter
    private String tipo_alinsa = "";
    @ManyToOne
    @Getter @Setter
    @JoinColumn(name="PROVEEDOR_ALINSA", referencedColumnName = "proveedor")
    private ProveedorAlinsa proveedorAlinsa;

    public boolean isAprobado() {
        return estadoRecepcion == EstadoRecepcion.APROBADO;
    }
    public boolean isBarcoAlinsa() { return tipo_alinsa.equals("BARCO"); }
}
