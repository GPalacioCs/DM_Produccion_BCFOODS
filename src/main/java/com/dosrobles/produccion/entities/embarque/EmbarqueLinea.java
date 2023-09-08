package com.dosrobles.produccion.entities.embarque;

import com.dosrobles.produccion.entities.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMBARQUE_LINEA")
@Data
@EqualsAndHashCode(of = "Id")
@NoArgsConstructor
public class EmbarqueLinea {
    @EmbeddedId
    private EmbarqueLineaPK Id = new EmbarqueLineaPK();
    @ManyToOne
    @JoinColumn(name = "ORDEN_COMPRA")
    private OrdenCompra Orden_Compra;
    private short Orden_Compra_Linea;
    @ManyToOne
    @JoinColumn(name = "ARTICULO")
    private Articulo Articulo;
    @ManyToOne
    @JoinColumn(name = "BODEGA")
    private Bodega Bodega;
    private Double Cantidad_Embarcada;
    private Double Cantidad_recibida;
    private Double Cantidad_rechazada;
    private Double Precio_unitario;
    private Double cost_un_fisc_local;
    private Double cost_un_fisc_dolar;
    private Double cost_un_esti_local;
    private Double cost_un_esti_dolar;
    private Double cost_un_real_local = 0.0;
    private Double cost_un_real_dolar = 0.0;
    private int plazo_reabast = 0;
    private Double porc_ajuste_costo = 0.0;
    private Double existencia_tot_ing = 0.0;
    private Double cant_recibida_ua;
    private Double cant_rechazada_ua;
    private String imp1_afectacosto = "N";
    private String recibido_de_mas = "N";
    private Double cantidad_devuelta = 0.0;
    private Double cant_devueltaua = 0.0;
    @JoinColumn(name = "PROVEEDOR")
    @ManyToOne
    private Proveedor proveedor;
    private String moneda_oc;
    private Double precio_unit_oc_local;
    private Double precio_unit_oc_dolar;
    private Double tc_precio_oc_local;
    private Double tc_precio_oc_dolar;
    private Double porc_desc_unitario = 0.0;
    private Double monto_desc_unitario = 0.0;
    private Double cost_un_comp_local = 0.0;
    private Double cost_un_comp_dolar = 0.0;
    private Double cost_un_esti_comp_local;
    private Double cost_un_esti_comp_dolar;
    private Double cost_un_real_comp_local = 0.0;
    private Double cost_un_real_comp_dolar = 0.0;
    private Double monto_aplicado_oc = 0.0;
    private String backorder_monto = "N";
//    @MapsId("Embarque")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "EMBARQUE", referencedColumnName = "EMBARQUE")
//    private Embarque Embarque;
    @OneToMany()
    @JoinColumns({
            @JoinColumn(referencedColumnName = "EMBARQUE", name = "EMBARQUE"),
            @JoinColumn(referencedColumnName = "EMBARQUE_LINEA", name = "EMBARQUE_LINEA")
    })
    private List<EmbarqueDesglose> EmbarqueLineaDesgloses = new ArrayList<>();

    public EmbarqueLinea(OrdenCompraLinea ocl, Embarque embarque, int maxLinea, BigDecimal tipoCambio, Bodega bodega) {
        double cantPendienteEmbarque = ocl.getCantidadOrdenada().subtract(ocl.getCantidadRecibida()).subtract(ocl.getCantidadEmbarcada()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

        Id = new EmbarqueLineaPK(embarque.getEmbarque(), maxLinea);
        Articulo = ocl.getArticulo();
        Cantidad_Embarcada = cantPendienteEmbarque;
        Cantidad_recibida = cantPendienteEmbarque;
        Precio_unitario = ocl.getPrecioUnitario().doubleValue();
        cost_un_fisc_local = ocl.getArticulo().getCostoPromLoc().doubleValue();
        cost_un_fisc_dolar = ocl.getArticulo().getCostoPromDol().doubleValue();
        cost_un_esti_local = Objects.equals(ocl.getOrdenCompra().getMoneda().getMoneda(), "USD") ? ocl.getPrecioUnitario().multiply(tipoCambio).doubleValue() : ocl.getPrecioUnitario().doubleValue();
        cost_un_esti_dolar = Objects.equals(ocl.getOrdenCompra().getMoneda().getMoneda(), "USD") ? ocl.getPrecioUnitario().doubleValue() : ocl.getPrecioUnitario().divide(tipoCambio, 4, RoundingMode.HALF_EVEN).doubleValue();
        proveedor = ocl.getOrdenCompra().getProveedor();
        moneda_oc = ocl.getOrdenCompra().getMoneda().getMoneda();
        precio_unit_oc_local = cost_un_esti_local;
        precio_unit_oc_dolar = cost_un_esti_dolar;
        tc_precio_oc_dolar = tipoCambio.doubleValue();
        tc_precio_oc_local = 1.0;
        cost_un_comp_local = cost_un_fisc_local;
        cost_un_comp_dolar = cost_un_fisc_dolar;
        cost_un_esti_comp_local = cost_un_esti_local;
        cost_un_esti_comp_dolar = cost_un_esti_dolar;
        cant_recibida_ua = Cantidad_recibida;
        cant_rechazada_ua = Cantidad_rechazada;
        Bodega = bodega;
        Orden_Compra = ocl.getOrdenCompra();
        Orden_Compra_Linea = ocl.getOrdenCompraLineaId().getOrdenCompraLinea();
        Cantidad_rechazada = 0.0;
    }
}

