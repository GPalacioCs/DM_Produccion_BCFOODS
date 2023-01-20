/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.jpaconverters.SiNoConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="USUARIO_PAQUETE")
@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class UsuarioPaquete {
    @EmbeddedId
    private UsuarioPaquetePK id;
    @MapsId("usuario")
    @JoinColumn(name="USUARIO")
    @ManyToOne
    private Usuario usuario;
    @MapsId("paquete")
    @JoinColumn(name="PAQUETE")
    @ManyToOne
    private Paquete paquete;
    @Convert(converter = SiNoConverter.class)
    private boolean total;

    public UsuarioPaquete(UsuarioPaquetePK id) {
        this.id = id;
    }
    
    
    
    
}
