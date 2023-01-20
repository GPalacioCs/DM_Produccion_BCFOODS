package com.dosrobles.produccion.models;

import com.dosrobles.produccion.entities.OrdenCompraLinea;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RptLineaOrdenRecepcion {
    private String articulo;
    private BigDecimal peso;
    private String bodega;
    
    public static RptLineaOrdenRecepcion from(OrdenCompraLinea linea) {
        RptLineaOrdenRecepcion rpt = new RptLineaOrdenRecepcion();
        rpt.setArticulo(String.format("%s - %s", linea.getArticulo(), linea.getArticulo().getDescripcion()));
        rpt.setPeso(linea.getCantidadOrdenada());
        rpt.setBodega(linea.getBodega().toString());
        return rpt;
    }
}
