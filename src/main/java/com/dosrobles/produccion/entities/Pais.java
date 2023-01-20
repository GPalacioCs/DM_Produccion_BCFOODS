package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAIS")
@Getter @Setter
public class Pais {
    @Id
    @Column(name="PAIS")
    private String pais;
    @Column(name="NOMBRE")
    private String nombre;
    
    public Pais() {}
    
    public Pais(String pais) {
        this.pais = pais;
    }
}
