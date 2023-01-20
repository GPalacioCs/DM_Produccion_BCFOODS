package com.dosrobles.produccion.models;

import com.dosrobles.produccion.entities.ArticuloTermo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RptArticuloTermo {
    private String articulo;
    private String descArticulo;
    private BigDecimal peso;
    
    public static RptArticuloTermo from(ArticuloTermo articuloTermo) {
        RptArticuloTermo rpt = new RptArticuloTermo();
        rpt.setArticulo(articuloTermo.getArticulo().getArticulo());
        rpt.setDescArticulo(articuloTermo.getArticulo().getDescripcion());
        rpt.setPeso(articuloTermo.getPeso());
        return rpt;
    }
}
