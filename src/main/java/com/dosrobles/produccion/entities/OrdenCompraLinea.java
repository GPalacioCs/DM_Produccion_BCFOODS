package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.utils.Utils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ORDEN_COMPRA_LINEA")
@Getter @Setter
@EqualsAndHashCode(of="ordenCompraLineaId")
public class OrdenCompraLinea {
    
    @EmbeddedId
    private OrdenCompraLineaId ordenCompraLineaId = new OrdenCompraLineaId();
    @ManyToOne
    @JoinColumn(name="ORDEN_COMPRA")
    @MapsId("ordenCompra")
    private OrdenCompra ordenCompra;
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @ManyToOne
    @JoinColumn(name="BODEGA")
    private Bodega bodega;
    @Column(name="LINEA_USUARIO")
    private short lineaUsuario = 1;
    @Column(name="DESCRIPCION")
    private String descripcion;
    @Column(name="CANTIDAD_ORDENADA")
    private BigDecimal cantidadOrdenada = BigDecimal.ZERO;
    @Column(name="CANTIDAD_EMBARCADA")
    private BigDecimal cantidadEmbarcada = BigDecimal.ZERO;
    @Column(name="CANTIDAD_RECIBIDA")
    private BigDecimal cantidadRecibida = BigDecimal.ZERO;
    @Column(name="CANTIDAD_RECHAZADA")
    private BigDecimal cantidadRechazada = BigDecimal.ZERO;
    @Column(name="PRECIO_UNITARIO")
    private BigDecimal precioUnitario = BigDecimal.ZERO;
    @Column(name="MONTO_DESCUENTO")
    private BigDecimal montoDescuento = BigDecimal.ZERO;
    @Column(name="FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = Utils.stripTime(new Date());
    @Column(name="ESTADO")
    private String estado = "O";
    @Column(name="FACTOR_CONVERSION")
    private BigDecimal factorConversion = BigDecimal.ONE;
    @Column(name="FECHA_REQUERIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRequerida = Utils.stripTime(new Date());
    @Column(name="IMP1_AFECTA_COSTO")
    private BigDecimal imp1afectaCosto = BigDecimal.ZERO;
    @Column(name="PRECIO_ART_PROV")
    private BigDecimal precioArtProv = BigDecimal.ZERO;
    @Column(name="IMPUESTO1")
    private BigDecimal impuesto1 = BigDecimal.ZERO;
    @Column(name="IMPUESTO2")
    private BigDecimal impuesto2 = BigDecimal.ZERO;
    @Column(name="TIPO_DESCUENTO")
    private String tipoDescuento = "P";
    @Column(name="PORC_DESCUENTO")
    private BigDecimal porcDescuento = BigDecimal.ZERO;
}
