package com.dosrobles.produccion.entities.embarque;

import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.Lote;
import com.dosrobles.produccion.entities.Serie;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DET_LIN_EMBARQUE")
@Data
@EqualsAndHashCode(of = "Id")
@NoArgsConstructor
public class EmbarqueDesglose {
    @EmbeddedId
    private EmbarqueDesglosePk Id = new EmbarqueDesglosePk();
    private Integer Serie_Cadena;
    private String Lote;
    @ManyToOne
    @JoinColumn(name = "BODEGA")
    private Bodega Bodega;
    private double Cant_Recibida;
    private double Cant_Rechazada;
    private double Cant_Recibida_Ua = getCant_Recibida();
    private double Cant_Rechazada_Ua = getCant_Rechazada();
//    @MapsId("EmbarqueLineaPk")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumns({
//            @JoinColumn(name = "EMBARQUE", referencedColumnName = "EMBARQUE"),
//            @JoinColumn(name = "EMBARQUE_LINEA", referencedColumnName = "EMBARQUE_LINEA")
//    })
//    private EmbarqueLinea EmbarqueLinea;

    public EmbarqueDesglose(EmbarqueLinea el, String lote, Bodega bodega) {
        Id = new EmbarqueDesglosePk(el.getId(), (short) 1);
        Cant_Recibida = el.getCantidad_recibida();
        Cant_Rechazada = el.getCantidad_rechazada() == null ? 0.0 : el.getCantidad_rechazada();
        Cant_Recibida_Ua = Cant_Recibida;
        Cant_Rechazada_Ua = Cant_Rechazada;
        Lote = lote;
        Bodega = bodega;
        Serie_Cadena = null;
//        setEmbarqueLinea(el);
    }
}
