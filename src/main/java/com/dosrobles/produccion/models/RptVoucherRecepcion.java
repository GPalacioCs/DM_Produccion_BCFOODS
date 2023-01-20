package com.dosrobles.produccion.models;

import com.dosrobles.produccion.entities.OrdenCompraLinea;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RptVoucherRecepcion {
    private String articulo;
    private BigDecimal peso;
    private BigDecimal rechazo;

    public static RptVoucherRecepcion from(OrdenCompraLinea linea){
        RptVoucherRecepcion rpt = new RptVoucherRecepcion();
        rpt.setArticulo(String.format("%s - %s", linea.getArticulo(), linea.getArticulo().getDescripcion()));
        rpt.setPeso(linea.getCantidadOrdenada());
        rpt.setRechazo(linea.getCantidadRechazada());
        return rpt;
    }
}
