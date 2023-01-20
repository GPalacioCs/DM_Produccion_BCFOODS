package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="CS_PESADA")
@Getter @Setter
public class Pesada {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PESADA")
    private int pesada = 1;
    @ManyToOne
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    @Column(name="PESO")
    private BigDecimal peso = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name="PRODUCTO_TERMINADO")
    private Articulo productoTerminado;
    @Column(name="CAJA")
    private Integer caja;
    @ManyToOne
    @JoinColumn(name="CLIENTE")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="ORDEN_COMPRA")
    private OrdenCompra ordenCompra;
    @ManyToOne
    @JoinColumn(name="TERMO")
    private Termo termo;
    @Column(name="EN_PRODUCCION")
    private Integer enProduccion = 0;
    @Column(name="RECHAZO")
    private BigDecimal rechazo = BigDecimal.ZERO;
    
    
    public boolean isFresco() {
        return caja != null;
    }
    public boolean isExportacion() {
        return isFresco();
    }

    public  boolean isEnProduccion() {
        return enProduccion == 1;
    }
}
