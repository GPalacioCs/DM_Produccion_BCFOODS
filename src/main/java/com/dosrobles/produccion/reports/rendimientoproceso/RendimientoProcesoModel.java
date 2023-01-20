package com.dosrobles.produccion.reports.rendimientoproceso;

import com.dosrobles.produccion.entities.IngresoProduccion;
import com.dosrobles.produccion.entities.MateriaPrima;
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
public class RendimientoProcesoModel {
    private Date fecha;
    private Long ordenProduccion;
    private String articulo;
    private String descArticulo;
    private String tipo;
    private String transformacion;
    private BigDecimal materiaPrima;
    private BigDecimal productoTerminado;
    private BigDecimal merma;
    private BigDecimal rendimientoReal;
    private BigDecimal rendimientoEsperado;
    private BigDecimal desviacion;


    public static RendimientoProcesoModel from(IngresoProduccion ip) {
        OrdenProduccion ordenProduccion = ip.getOrdenProduccion();
        RendimientoProcesoModel model = new RendimientoProcesoModel();
        model.setFecha(ordenProduccion.getFechaRequerida());
        model.setOrdenProduccion(ordenProduccion.getOrdenProduccion());
        model.setArticulo(ip.getArticulo().getArticulo());
        model.setDescArticulo(ip.getArticulo().getDescripcion());
        model.setTipo(!ordenProduccion.isFresco() ? "Congelado" : "Fresco");
        if (ip.getArticulo().getTransformacion()!=null) {
            model.setTransformacion(ip.getArticulo().getTransformacion().getDescripcion());
        }
        OrdenProdMp ordenProdMp = ip.getMateriaPrimaProduccion();
        if (ordenProdMp != null) {
            model.setMateriaPrima(ordenProdMp.getCantidad());
            model.setProductoTerminado(ip.getCantidad());
            model.setMerma(model.getMateriaPrima().subtract(model.getProductoTerminado()));
            model.setRendimientoReal(model.getProductoTerminado().divide(model.getMateriaPrima(), 2, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(100)));
            MateriaPrima materiaPrima = ip.getMateriaPrima();
            BigDecimal cantProduccion = materiaPrima.getEtapa().getArticulo().getCantidadProduccion();
            model.setRendimientoEsperado(cantProduccion.divide(materiaPrima.getCantidad(), 2, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(100)));
            model.setDesviacion(model.getRendimientoEsperado().subtract(model.getRendimientoReal()));
        }
        return model;
    }

    public static List<RendimientoProcesoModel> from(OrdenProduccion ordenProduccion) {
        return ordenProduccion.getIngresoProduccoinList().stream().map(ip->from(ip)).collect(Collectors.toList());

    }

    public static List<RendimientoProcesoModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<RendimientoProcesoModel> rendimientoProcesoModelList = new ArrayList<>();
        ordenProduccionList.forEach(op -> rendimientoProcesoModelList.addAll(from(op)));
        return rendimientoProcesoModelList;
    }
}
