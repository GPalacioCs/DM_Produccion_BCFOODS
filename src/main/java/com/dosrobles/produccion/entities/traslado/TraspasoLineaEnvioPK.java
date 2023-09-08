package com.dosrobles.produccion.entities.traslado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TraspasoLineaEnvioPK implements Serializable {
    private String Id_Traspaso;
    private int Linea;
}
