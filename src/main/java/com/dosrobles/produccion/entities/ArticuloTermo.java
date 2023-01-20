package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="CS_ARTICULO_TERMO")
@Getter @Setter
public class ArticuloTermo {
    
    @EmbeddedId
    private ArticuloTermoPK id = new ArticuloTermoPK();
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articulo")
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("termo")
    @JoinColumn(name="TERMO", referencedColumnName = "ID")
    private Termo termo;
    @Column(name="PESO")
    private BigDecimal peso = BigDecimal.ZERO;
}
