package com.dosrobles.produccion.reports.detallegeneralconsumo;

import com.dosrobles.produccion.entities.OrdenProdMd;
import com.dosrobles.produccion.entities.OrdenProdMp;
import com.dosrobles.produccion.entities.OrdenProduccion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DetalleGeneralConsumoModel {
    private Date fecha;
    private Long ordenProduccion;
    private String articulo;
    private String descArticulo;
    private String tipo;
    private BigDecimal consumo;

    public static DetalleGeneralConsumoModel from(OrdenProdMp ordenProdMp) {
        OrdenProduccion op = ordenProdMp.getOrdenProduccion();
        DetalleGeneralConsumoModel model = new DetalleGeneralConsumoModel();
        model.setFecha(op.getFechaRequerida());
        model.setOrdenProduccion(op.getOrdenProduccion());
        model.setArticulo(ordenProdMp.getComponente().getArticulo());
        model.setDescArticulo(ordenProdMp.getComponente().getDescripcion());
        model.setTipo(!op.isFresco() ? "Congelado" : "Fresco");
        model.setConsumo(ordenProdMp.getCantidad());
        return model;
    }

    public static DetalleGeneralConsumoModel from(OrdenProdMd ordenProdMd) {
        OrdenProduccion op = ordenProdMd.getOrdenProduccion();
        DetalleGeneralConsumoModel model = new DetalleGeneralConsumoModel();
        model.setOrdenProduccion(op.getOrdenProduccion());
        model.setFecha(op.getFechaRequerida());
        model.setArticulo(ordenProdMd.getComponente().getArticulo());
        model.setDescArticulo(ordenProdMd.getComponente().getDescripcion());
        model.setTipo(!op.isFresco() ? "Congelado" : "Fresco");
        model.setConsumo(ordenProdMd.getCantidad());
        return model;
    }

    public static List<DetalleGeneralConsumoModel> from(OrdenProduccion ordenProduccion) {
        List<DetalleGeneralConsumoModel> modelList = ordenProduccion.getOrdenProdMpList().stream()
                .map(mp -> from(mp)).collect(Collectors.toCollection(ArrayList::new));

        modelList.addAll(ordenProduccion.getOrdenProdMdList().stream()
                .map(md -> from(md)).collect(Collectors.toCollection(ArrayList::new)));

        return modelList;
    }

    public static List<DetalleGeneralConsumoModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<DetalleGeneralConsumoModel> modelList = new ArrayList<>();
        ordenProduccionList.forEach(op -> modelList.addAll(from(op)));
        return modelList;
    }



}
