/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "SERIE_PLANTILLA")
@Data
public class SeriePlantilla {
    @Id
    @Column(name="SERIE_PLANTILLA")
    private String id;
    @Column(name="U_VALOR")
    private String ultimoValor;
}
