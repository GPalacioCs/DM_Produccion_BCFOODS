package com.dosrobles.produccion.entities.embarque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class EmbarqueDesglosePk implements Serializable {
    private EmbarqueLineaPK EmbarqueLineaPk;
    private short Secuencia;
}
