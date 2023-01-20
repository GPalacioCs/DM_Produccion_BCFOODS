package com.dosrobles.produccion.reports.variacionconsumomd;

import com.dosrobles.produccion.entities.OrdenProdMd;
import com.dosrobles.produccion.entities.OrdenProduccion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class VariacionConsumoMdModel {
    private Date fecha;
    private Long ordenProduccion;
    private String articulo;
    private String descArticulo;
    private String tipo;
    private BigDecimal consumo = BigDecimal.ZERO;
    private BigDecimal receta = BigDecimal.ZERO;
    private BigDecimal diferencia = BigDecimal.ZERO;

    public static VariacionConsumoMdModel from(OrdenProdMd material) {
        OrdenProduccion op = material.getOrdenProduccion();
        VariacionConsumoMdModel model = new VariacionConsumoMdModel();
        model.setFecha(op.getFechaRequerida());
        model.setOrdenProduccion(op.getOrdenProduccion());
        model.setArticulo(material.getComponente().getArticulo());
        model.setDescArticulo(material.getComponente().getDescripcion());
        model.setTipo(!op.isFresco() ? "Congelado" : "Fresco");
        model.setConsumo(material.getCantidad());
        model.setReceta(op.getTotalMdReceta(material.getComponente()));
        model.setDiferencia(model.getConsumo().subtract(model.getReceta()));

        return model;
    }

    public static List<VariacionConsumoMdModel> from(OrdenProduccion ordenProduccion) {
        return ordenProduccion.getOrdenProdMdList().stream().map(md -> from(md))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static List<VariacionConsumoMdModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<VariacionConsumoMdModel> list = new ArrayList<>();
        ordenProduccionList.forEach(op -> list.addAll(from(op)));
        return list;
    }
}
