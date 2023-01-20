package com.dosrobles.produccion.models;

import com.dosrobles.produccion.entities.Pesada;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RptOrdenRecepcion {
    private Integer pesada;
    private String materiaPrima;
    private BigDecimal peso;
    private String termo;
    private String productoTerminado;
    private Integer caja;
    private String cliente;
    
    public static RptOrdenRecepcion from(Pesada pesada) {
        RptOrdenRecepcion rpt = new RptOrdenRecepcion();
        rpt.setPesada(pesada.getPesada());
        rpt.setCaja(pesada.getCaja());
        if (pesada.getArticulo() != null) {
            rpt.setMateriaPrima(String.format("%s - %s", pesada.getArticulo().getArticulo(), pesada.getArticulo().getDescripcion()));
        }
        rpt.setPeso(pesada.getPeso());
        if (pesada.getTermo() != null) {
            rpt.setTermo(pesada.getTermo().getNombre());
        }
        if (pesada.getProductoTerminado() != null) {
            rpt.setProductoTerminado(String.format("%s - %s", pesada.getProductoTerminado().getArticulo(), pesada.getProductoTerminado().getDescripcion()));
        }
        rpt.setCaja(pesada.getCaja());
        if (pesada.getCliente() != null) {
            rpt.setCliente(String.format("%s", pesada.getCliente().toString()));
        }
        return rpt;
    }
}
