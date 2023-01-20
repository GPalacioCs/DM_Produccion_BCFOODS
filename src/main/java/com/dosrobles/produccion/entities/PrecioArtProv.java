package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PRECIO_ART_PROV")
@Getter @Setter
public class PrecioArtProv {
    
    @EmbeddedId
    private PrecioArtProvId id = new PrecioArtProvId();
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    @MapsId("articulo")
    private Articulo articulo;
    @ManyToOne
    @JoinColumn(name="PROVEEDOR")
    @MapsId("proveedor")
    private Proveedor proveedor;
    @Column(name="CANTIDAD_HASTA")
    private BigDecimal cantidadHasta = BigDecimal.ZERO;
    @Column(name="PRECIO")
    private BigDecimal precio = BigDecimal.ZERO;
    
}
