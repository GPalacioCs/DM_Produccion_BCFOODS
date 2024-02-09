package com.dosrobles.produccion.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompraLineaId implements Serializable {
    @Column(name="ORDEN_COMPRA")
    private String ordenCompra;
    @Column(name="ORDEN_COMPRA_LINEA")
    private short ordenCompraLinea = 1;
}
