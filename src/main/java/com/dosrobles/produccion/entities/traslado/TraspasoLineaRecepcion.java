package com.dosrobles.produccion.entities.traslado;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CS_TRASPASO_LINEA_RECEPCION")
@Data
public class TraspasoLineaRecepcion {
    @EmbeddedId
    private TraspasoLineaRecepcionPK traspasoLineaRecepcionPK;
    @MapsId("Id_Traspaso")
    @ManyToOne
    @JoinColumn(name = "Id_Traspaso")
    private Traspaso Traspaso;
    @ManyToOne
    @JoinColumn(name = "ARTICULO")
    private com.dosrobles.produccion.entities.Articulo Articulo;
    private Double Cantidad;
    private Double Precio_Unitario;
    private String Lote;
}
