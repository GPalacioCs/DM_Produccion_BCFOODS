package com.dosrobles.produccion.reports.bonoproduccion;

import com.dosrobles.produccion.entities.ActividadProd;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.entities.OrdenProduccionActividad;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Data
public class BonoProduccionModel {
    private String codigo;
    private String actividad;
    private BigDecimal libras;
    private BigDecimal costo;
    private BigDecimal total = BigDecimal.ZERO;

    public static List<BonoProduccionModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<OrdenProduccionActividad> ordenProduccionActividadList = new ArrayList<>();
        ordenProduccionList.forEach(op -> ordenProduccionActividadList.addAll(op.getOrdenProduccionActividadList()));

        Map<ActividadProd, BigDecimal> opaMap = ordenProduccionActividadList.stream()
                .collect(groupingBy(OrdenProduccionActividad::getActividad, reducing(BigDecimal.ZERO, OrdenProduccionActividad::getLibras, BigDecimal::add)));

        return opaMap.entrySet().stream().map(entry -> {
            BonoProduccionModel model = new BonoProduccionModel();
            model.setCodigo(entry.getKey().getCodigo());
            model.setActividad(entry.getKey().getDescripcion());
            model.setLibras(entry.getValue());
            model.setCosto(entry.getKey().getCostoLocal());
            model.setTotal(model.getLibras().multiply(model.getCosto()));
            return model;
        }).collect(toList());
    }
}
