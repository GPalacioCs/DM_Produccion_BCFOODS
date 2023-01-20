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
import java.util.Date;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "PEDIDO_LINEA")
@NamedQueries({
    @NamedQuery(name = "PedidoLinea.findAll", query = "SELECT p FROM PedidoLinea p")})
public class PedidoLinea implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoLineaPK pedidoLineaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado = "N";
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA_USUARIO")
    private short lineaUsuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_PEDIDA")
    private BigDecimal cantidadPedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_A_FACTURA")
    private BigDecimal cantidadAFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_FACTURADA")
    private BigDecimal cantidadFacturada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_RESERVADA")
    private BigDecimal cantidadReservada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_BONIFICAD")
    private BigDecimal cantidadBonificad = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_CANCELADA")
    private BigDecimal cantidadCancelada = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_DESCUENTO")
    private String tipoDescuento = "P";
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DESCUENTO")
    private BigDecimal montoDescuento = BigDecimal.ZERO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_DESCUENTO")
    private BigDecimal porcDescuento = BigDecimal.ZERO;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "PEDIDO_LINEA_BONIF")
    private Short pedidoLineaBonif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PROMETIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrometida = new Date();
    @Column(name = "LINEA_ORDEN_COMPRA")
    private Integer lineaOrdenCompra;
    @Size(max = 25)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Size(max = 25)
    @Column(name = "FASE")
    private String fase;    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICULO")
    private Articulo articulo;
    @ManyToOne
    @JoinColumn(name = "BODEGA")
    private Bodega bodega;
    @Size(max = 25)
    @Column(name = "CENTRO_COSTO")
    private String centroCosto;
    @Size(max = 25)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;    
    @Size(max = 8)
    @Column(name = "LOCALIZACION")
    private String localizacion;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO", insertable = false, updatable = false)
    private String articulo1;
    @Size(max = 15)
    @Column(name = "LOTE")
    private String lote;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO", insertable = false, updatable = false)
    private Pedido pedido;
    @Size(max = 6)
    @Column(name = "UNIDAD_DISTRIBUCIO")
    private String unidadDistribucio;

    public PedidoLinea() {
    }

    public PedidoLinea(PedidoLineaPK pedidoLineaPK) {
        this.pedidoLineaPK = pedidoLineaPK;
    }

    public PedidoLinea(String pedido, short pedidoLinea) {
        this.pedidoLineaPK = new PedidoLineaPK(pedido, pedidoLinea);
    }

    public PedidoLineaPK getPedidoLineaPK() {
        return pedidoLineaPK;
    }

    public void setPedidoLineaPK(PedidoLineaPK pedidoLineaPK) {
        this.pedidoLineaPK = pedidoLineaPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public short getLineaUsuario() {
        return lineaUsuario;
    }

    public void setLineaUsuario(short lineaUsuario) {
        this.lineaUsuario = lineaUsuario;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(BigDecimal cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public BigDecimal getCantidadAFactura() {
        return cantidadAFactura;
    }

    public void setCantidadAFactura(BigDecimal cantidadAFactura) {
        this.cantidadAFactura = cantidadAFactura;
    }

    public BigDecimal getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(BigDecimal cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public BigDecimal getCantidadReservada() {
        return cantidadReservada;
    }

    public void setCantidadReservada(BigDecimal cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }

    public BigDecimal getCantidadBonificad() {
        return cantidadBonificad;
    }

    public void setCantidadBonificad(BigDecimal cantidadBonificad) {
        this.cantidadBonificad = cantidadBonificad;
    }

    public BigDecimal getCantidadCancelada() {
        return cantidadCancelada;
    }

    public void setCantidadCancelada(BigDecimal cantidadCancelada) {
        this.cantidadCancelada = cantidadCancelada;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public BigDecimal getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(BigDecimal porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Short getPedidoLineaBonif() {
        return pedidoLineaBonif;
    }

    public void setPedidoLineaBonif(Short pedidoLineaBonif) {
        this.pedidoLineaBonif = pedidoLineaBonif;
    }

    public Date getFechaPrometida() {
        return fechaPrometida;
    }

    public void setFechaPrometida(Date fechaPrometida) {
        this.fechaPrometida = fechaPrometida;
    }

    public Integer getLineaOrdenCompra() {
        return lineaOrdenCompra;
    }

    public void setLineaOrdenCompra(Integer lineaOrdenCompra) {
        this.lineaOrdenCompra = lineaOrdenCompra;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getArticulo1() {
        return articulo1;
    }

    public void setArticulo1(String articulo1) {
        this.articulo1 = articulo1;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getUnidadDistribucio() {
        return unidadDistribucio;
    }

    public void setUnidadDistribucio(String unidadDistribucio) {
        this.unidadDistribucio = unidadDistribucio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoLineaPK != null ? pedidoLineaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoLinea)) {
            return false;
        }
        PedidoLinea other = (PedidoLinea) object;
        if ((this.pedidoLineaPK == null && other.pedidoLineaPK != null) || (this.pedidoLineaPK != null && !this.pedidoLineaPK.equals(other.pedidoLineaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.PedidoLinea[ pedidoLineaPK=" + pedidoLineaPK + " ]";
    }
    
    public BigDecimal getPrecioTotal() {
        return precioUnitario.multiply(cantidadPedida);
    }
    
    public static PedidoLinea from(Pedido pedido, short linea, Articulo articulo, BigDecimal precioUnitario, BigDecimal cantidad, Bodega bodega) {
    	PedidoLinea pedidoLinea = new PedidoLinea(pedido.getPedido(), linea);
        pedidoLinea.setLineaUsuario((short)((int)linea - 1));
    	pedidoLinea.setPedido(pedido);
    	pedidoLinea.setArticulo(articulo);
    	pedidoLinea.setPrecioUnitario(precioUnitario);
    	pedidoLinea.setCantidadPedida(cantidad);
    	pedidoLinea.setCantidadAFactura(cantidad);
    	pedidoLinea.setBodega(bodega);
        pedidoLinea.setCentroCosto(articulo.getArticuloCuenta().getCtrVentasLoc());
        pedidoLinea.setCuentaContable(articulo.getArticuloCuenta().getCtaVentasLoc());
//    	pedidoLinea.setLocalizacion("ND");
//    	pedidoLinea.setLote("ND");
    	return pedidoLinea;    	
    }

}
