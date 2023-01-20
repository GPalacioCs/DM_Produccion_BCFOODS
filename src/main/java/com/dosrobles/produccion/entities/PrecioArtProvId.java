package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class PrecioArtProvId implements Serializable {
    @Column(name="ARTICULO")
    private String articulo;
    @Column(name="PROVEEDOR")
    private String proveedor;
    @Column(name="LINEA")
    private short linea;
}
