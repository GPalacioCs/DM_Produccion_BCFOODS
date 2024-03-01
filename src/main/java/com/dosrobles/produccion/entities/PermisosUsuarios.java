package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CS_PROD_PERMISOS_USUARIOS")
@Getter
@Setter
@NoArgsConstructor
public class PermisosUsuarios {
    @Id
    @OneToOne
    @JoinColumn(referencedColumnName = "USUARIO", name = "USUARIO")
    private Usuario Usuario;
    private boolean Enviar_Traslados = false;
    private boolean Recibir_Traslados = false;
    private boolean Liberar_Op = false;
    private boolean Contenedores = false;
    private boolean Anular_Op = false;
    public PermisosUsuarios(Usuario usuario) {
        Usuario = usuario;
    }
}
