package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter @Setter
@Embeddable
public class ArticuloTermoPK implements Serializable {
    @Column(name="ARTICULO")
    private String articulo;
    @Column(name="TERMO")
    private Long termo;
}
