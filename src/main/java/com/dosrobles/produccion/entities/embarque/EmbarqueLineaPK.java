package com.dosrobles.produccion.entities.embarque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbarqueLineaPK implements Serializable {
    private String Embarque = "";
    private int Embarque_Linea = 0;
}
