package com.dosrobles.produccion.reports.costeoproductoterminado;

import com.dosrobles.produccion.entities.IngresoProduccion;
import com.dosrobles.produccion.entities.Maquila;
import com.dosrobles.produccion.entities.OrdenProdMp;
import com.dosrobles.produccion.entities.OrdenProduccion;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CosteoProductoTerminadoModel {
    private String code;
    private String Descripcion;
    private Date fecha;
    private Long ordenProduccion;
    private BigDecimal materiaPrima = BigDecimal.ZERO;
    private BigDecimal maquila = BigDecimal.ZERO;
    private BigDecimal totalCosto = BigDecimal.ZERO;
    private BigDecimal productoTerminado = BigDecimal.ZERO;
    private BigDecimal unitario = BigDecimal.ZERO;

    public static CosteoProductoTerminadoModel from(IngresoProduccion ingresoProduccion) {
        CosteoProductoTerminadoModel model = new CosteoProductoTerminadoModel();
        model.setCode(ingresoProduccion.getArticulo().getArticulo());
        model.setDescripcion(ingresoProduccion.getArticulo().getDescripcion());
        model.setFecha(ingresoProduccion.getOrdenProduccion().getFechaRequerida());
        model.setOrdenProduccion(ingresoProduccion.getOrdenProduccion().getOrdenProduccion());
        OrdenProdMp ordenProdMp = ingresoProduccion.getMateriaPrimaProduccion();
        if (ordenProdMp != null) {
            model.setMateriaPrima(ordenProdMp.getComponente().getCostoPromLoc());
        }
        Maquila maquila = ingresoProduccion.getMaquila();
        model.setMaquila(maquila.getMonto());
        model.setTotalCosto(model.getMateriaPrima().add(model.getMaquila()));
        model.setProductoTerminado(ingresoProduccion.getCantidad());
        model.setUnitario(model.getTotalCosto().divide(model.getProductoTerminado(), 2, RoundingMode.HALF_EVEN));
        return model;
    }

    public static List<CosteoProductoTerminadoModel> from(OrdenProduccion ordenProduccion) {
        return ordenProduccion.getIngresoProduccoinList().stream().map(ip -> from(ip))
                .collect(Collectors.toList());
    }

    public static List<CosteoProductoTerminadoModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<CosteoProductoTerminadoModel> costeoProductoTerminadoModelList = new ArrayList<>();
        ordenProduccionList.stream().forEach(op -> costeoProductoTerminadoModelList.addAll(from(op)));
        return costeoProductoTerminadoModelList;
    }
}
