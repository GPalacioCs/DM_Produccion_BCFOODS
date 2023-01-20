package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MONEDA")
@Getter @Setter
public class Moneda {
    @Id
    @Column(name = "MONEDA")
    private String moneda;
    @Column(name = "NOMBRE")
    private String nombre;
}
