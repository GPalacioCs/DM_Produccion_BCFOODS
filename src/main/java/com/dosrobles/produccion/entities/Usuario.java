/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Zeus
 */
@Entity
@Table(name="USUARIO", schema = "ERPADMIN")
@Data
@EqualsAndHashCode(of="usuario")
public class Usuario implements Serializable{
    @Id
    private String usuario;
    private String nombre;
    @ManyToMany
    @JoinTable(
        name="USUARIO_BODEGA",
        joinColumns = @JoinColumn(name="USUARIO", referencedColumnName = "USUARIO"),
        inverseJoinColumns = @JoinColumn(name="BODEGA", referencedColumnName = "BODEGA")
    )
    private List<Bodega> bodegaList = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPaquete> usuarioPaqueteList = new ArrayList<>();
    @OneToOne(mappedBy = "Usuario", cascade = CascadeType.ALL)
    private PermisosUsuarios PermisosUsuario = new PermisosUsuarios();
    private String activo;

    public Usuario() {
    }

    public Usuario(String usuario, String nombre) {
        this.usuario = usuario;
        this.nombre = nombre;
    }
    
    public boolean isAdmin(Paquete paquete) {
        UsuarioPaquete usuarioPaquete = usuarioPaqueteList.stream().filter(up -> Objects.equals(up.getPaquete(), paquete))
                .findFirst().orElse(null);
        if(usuarioPaquete != null) {
            return usuarioPaquete.isTotal();
        }        
        return false;
    }
}
