package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GLOBALES_CO")
@Getter @Setter
public class GlobalesCO {
    @Id
    @Column(name="TIPO_CAMBIO")
    private String tipoCambio;
    @Column(name="ULT_ORDEN_COMPRA")
    private String ultOrdenCompra;
}
