/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.utils.Utils;
import lombok.Data;

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
@Table(name = "PEDIDO")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
@Data
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEDIDO")
    private String pedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado = "N";
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido = Utils.stripTime(new Date());
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROMETIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrometida = Utils.stripTime(new Date());
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROX_EMBARQU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProxEmbarqu = new Date();
    @Column(name = "FECHA_ULT_EMBARQUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltEmbarque;
    @Column(name = "FECHA_ULT_CANCELAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltCancelac;
    @Size(max = 30)
    @Column(name = "ORDEN_COMPRA")
    private String ordenCompra;
    @Column(name = "FECHA_ORDEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrden;
    @Size(max = 20)
    @Column(name = "TARJETA_CREDITO")
    private String tarjetaCredito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "EMBARCAR_A")
    private String embarcarA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "DIREC_EMBARQUE")
    private String direcEmbarque = "ND";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4000)
    @Column(name = "DIRECCION_FACTURA")
    private String direccionFactura;
    @Size(max = 50)
    @Column(name = "RUBRO1")
    private String rubro1;
    @Size(max = 50)
    @Column(name = "RUBRO2")
    private String rubro2;
    @Size(max = 50)
    @Column(name = "RUBRO3")
    private String rubro3;
    @Size(max = 50)
    @Column(name = "RUBRO4")
    private String rubro4;
    @Size(max = 50)
    @Column(name = "RUBRO5")
    private String rubro5;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 40)
    @Column(name = "COMENTARIO_CXC")
    private String comentarioCxc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_MERCADERIA")
    private BigDecimal totalMercaderia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_ANTICIPO")
    private BigDecimal montoAnticipo = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_FLETE")
    private BigDecimal montoFlete = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_SEGURO")
    private BigDecimal montoSeguro = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DOCUMENTACIO")
    private BigDecimal montoDocumentacio = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_DESCUENTO1")
    private String tipoDescuento1 = "P";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_DESCUENTO2")
    private String tipoDescuento2 = "P";
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DESCUENTO1")
    private BigDecimal montoDescuento1 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DESCUENTO2")
    private BigDecimal montoDescuento2 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_DESCUENTO1")
    private BigDecimal porcDescuento1 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_DESCUENTO2")
    private BigDecimal porcDescuento2 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_IMPUESTO1")
    private BigDecimal totalImpuesto1 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_IMPUESTO2")
    private BigDecimal totalImpuesto2 = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_A_FACTURAR")
    private BigDecimal totalAFacturar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_COMI_VENDEDOR")
    private BigDecimal porcComiVendedor = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_COMI_COBRADOR")
    private BigDecimal porcComiCobrador = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_CANCELADO")
    private BigDecimal totalCancelado = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_UNIDADES")
    private BigDecimal totalUnidades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "IMPRESO")
    private String impreso = "N";
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO_VOLUMEN")
    private BigDecimal descuentoVolumen = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_PEDIDO")
    private String tipoPedido = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MONEDA_PEDIDO")
    private String monedaPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION_NP")
    private int versionNp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "AUTORIZADO")
    private String autorizado = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DOC_A_GENERAR")
    private String docAGenerar = "F";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CLASE_PEDIDO")
    private String clasePedido = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "BACKORDER")
    private String backorder = "N";
    @Size(max = 20)
    @Column(name = "CONTRATO")
    private String contrato;
    @Column(name = "PORC_INTCTE")
    private BigDecimal porcIntcte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DESCUENTO_CASCADA")
    private String descuentoCascada = "N";
    @Column(name = "TIPO_CAMBIO")
    private BigDecimal tipoCambio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "FIJAR_TIPO_CAMBIO")
    private String fijarTipoCambio = "N";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ORIGEN_PEDIDO")
    private String origenPedido = "F";
    @Size(max = 250)
    @Column(name = "DESC_DIREC_EMBARQUE")
    private String descDirecEmbarque;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA1")
    private String divisionGeografica1;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA2")
    private String divisionGeografica2;
    @Column(name = "BASE_IMPUESTO1")
    private BigDecimal baseImpuesto1;
    @Column(name = "BASE_IMPUESTO2")
    private BigDecimal baseImpuesto2;
    @Size(max = 150)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Column(name = "FECHA_PROYECTADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProyectada;
    @Column(name = "FECHA_APROBACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento = "P";
    @Size(max = 10)
    @Column(name = "VERSION_COTIZACION")
    private String versionCotizacion;
    @Size(max = 254)
    @Column(name = "DES_CANCELA_COTI")
    private String desCancelaCoti;
    @Size(max = 254)
    @Column(name = "CAMBIOS_COTI")
    private String cambiosCoti;
    @Size(max = 50)
    @Column(name = "COTIZACION_PADRE")
    private String cotizacionPadre;
    @Size(max = 4)
    @Column(name = "TASA_IMPOSITIVA")
    private String tasaImpositiva;
    @Column(name = "TASA_IMPOSITIVA_PORC")
    private BigDecimal tasaImpositivaPorc;
    @Size(max = 4)
    @Column(name = "TASA_CREE1")
    private String tasaCree1 = "";
    @Column(name = "TASA_CREE1_PORC")
    private BigDecimal tasaCree1Porc;
    @Size(max = 4)
    @Column(name = "TASA_CREE2")
    private String tasaCree2;
    @Column(name = "TASA_CREE2_PORC")
    private BigDecimal tasaCree2Porc;
    @Column(name = "TASA_GAN_OCASIONAL_PORC")
    private BigDecimal tasaGanOcasionalPorc;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "BODEGA")
    private String bodega;
    @Size(max = 10)
    @Column(name = "RAZON_CANCELA_COTI")
    private String razonCancelaCoti;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLIENTE")
    private Cliente cliente;
    @Size(max = 3)
    @Column(name = "TIPO_DOC_CXC")
    private String tipoDocCxc;
    @Column(name = "SUBTIPO_DOC_CXC")
    private Short subtipoDocCxc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ZONA")
    private String zona = "ND";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLIENTE_ORIGEN")
    private String clienteOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLIENTE_CORPORAC")
    private String clienteCorporac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLIENTE_DIRECCION")
    private String clienteDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COBRADOR")
    private String cobrador = "ND";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CONDICION_PAGO")
    private String condicionPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "NIVEL_PRECIO")
    private String nivelPrecio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MONEDA")
    private String moneda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PAIS")
    private String pais = "ND";
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "RUTA")
    private String ruta = "ND";
    @OneToMany(mappedBy = "pedido")
    private List<PedidoLinea> pedidoLineaList = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(String pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.Pedido[ pedido=" + pedido + " ]";
    }
    
    public static Pedido from(Pedido pedido, Cliente cliente, String vendedor, String moneda, Bodega bodega, String condicionPago, String usuario) {    	
    	pedido.setBodega(bodega.getBodega());
    	pedido.setVendedor(vendedor);
    	pedido.setCliente(cliente);
    	pedido.setClienteCorporac(cliente.getCliente());
        pedido.setEmbarcarA(cliente.getNombre());
    	pedido.setClienteOrigen(cliente.getCliente());
    	pedido.setCondicionPago(condicionPago);
    	pedido.setNivelPrecio(cliente.getNivelPrecio());
        pedido.setMoneda(cliente.getMonedaNivel());
    	pedido.setPais(cliente.getPais());    	
    	pedido.setUsuario(usuario);
    	pedido.setClienteDireccion(cliente.getCliente());
//    	pedido.setDireccionFactura(cliente.getDetalleDireccion() != null ? ("DETALLE: "+ cliente.getDetalleDireccion1().getCampo1()) : "ND");
    	pedido.setDireccionFactura(cliente.getDireccion());
    	pedido.setFechaPedido(new Date());
    	pedido.setFechaProyectada(pedido.getFechaPedido());
        pedido.setMonedaPedido(cliente.getMonedaNivel());
        pedido.setTipoDocCxc("FAC");
        pedido.setSubtipoDocCxc((short)0);
        //agregaciones nuevas
        pedido.setPorcIntcte(BigDecimal.ZERO);
        pedido.setBaseImpuesto1(BigDecimal.ZERO);
        pedido.setBaseImpuesto2(BigDecimal.ZERO);
        pedido.setTasaImpositivaPorc(BigDecimal.ZERO);
        pedido.setTasaCree1(null);
        pedido.setTasaCree1Porc(BigDecimal.ZERO);
        pedido.setTasaCree2(null);
        pedido.setTasaCree2Porc(BigDecimal.ZERO);
        pedido.setTasaGanOcasionalPorc(BigDecimal.ZERO);        
    	return pedido;
    }
    
    public static Pedido from(Pedido ped, Cliente cliente, String vendedor, String moneda, Bodega bodega, String condicionPago, String usuario, List<PedidoLinea> pedidoLineaList) {
    	Pedido pedido = from(ped, cliente, vendedor, moneda, bodega, condicionPago, usuario);        
    	pedido.getPedidoLineaList().addAll(pedidoLineaList);
    	return pedido;
    }

}
