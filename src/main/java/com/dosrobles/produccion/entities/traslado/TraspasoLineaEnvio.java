package com.dosrobles.produccion.entities.traslado;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.embarque.EmbarqueLineaPK;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "CS_TRASPASO_LINEA_ENVIO")
@Data
@EqualsAndHashCode(of = "Id")
public class TraspasoLineaEnvio{
    @EmbeddedId
    private TraspasoLineaEnvioPK Id = new TraspasoLineaEnvioPK();
    @ManyToOne
    @JoinColumn(name = "ARTICULO")
    private Articulo Articulo;
    private Double Cantidad;
    private Double Precio_Unitario;
    @MapsId("Id_Traspaso")
    @ManyToOne
    @JoinColumn(name = "Id_Traspaso")
    private Traspaso Traspaso;
    private String Lote;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "embarque", referencedColumnName = "embarque"),
            @JoinColumn(name = "embarque_linea", referencedColumnName = "embarque_linea")
    })
    private EmbarqueLinea EmbarqueLinea;
}
