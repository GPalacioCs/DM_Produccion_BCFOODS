package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONDICION_PAGO")
@Getter @Setter
public class CondicionPago {
    @Id
    @Column(name="CONDICION_PAGO")
    private String CONDICION_PAGO;
    @Column(name="DESCRIPCION")
    private String descripcion;
    @Column(name="DIAS_NETO")
    private String dias_neto;
}
