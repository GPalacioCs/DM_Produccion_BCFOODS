package com.dosrobles.produccion.reports.bonoproduccion;

import com.dosrobles.produccion.entities.OrdenProdEmpleado;
import com.dosrobles.produccion.entities.OrdenProduccion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BonoProduccionEmpleadoModel {
    private String empleado;
    private String nombre;
    private Date fecha;
    private BigDecimal total;

    public String getEmpleadoNombre() {
        return String.format("%s %s", empleado, nombre);
    }

    public static List<BonoProduccionEmpleadoModel> from(List<OrdenProduccion> ordenProduccionList) {
        List<BonoProduccionEmpleadoModel> list = new ArrayList<>();

        for (OrdenProduccion ordenProduccion : ordenProduccionList) {
            for (OrdenProdEmpleado ordenProdEmpleado : ordenProduccion.getEmpleadoList()) {
                BonoProduccionEmpleadoModel model = new BonoProduccionEmpleadoModel();
                model.setEmpleado(ordenProdEmpleado.getEmpleado().getEmpleado());
                model.setNombre(ordenProdEmpleado.getEmpleado().getNombre());
                model.setFecha(ordenProduccion.getFechaRequerida());
                model.setTotal(ordenProdEmpleado.getCostoActividadEmpleado());
                list.add(model);
            }
        }

        return list;
    }
}
