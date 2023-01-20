package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CS_GRUPO_MANO_OBRA")
@Getter @Setter
public class GrupoManoObra {
    @Id
    @Column(name="CODIGO")
    private String codigo;
    @Column(name="DESCRIPCION")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name="ACTIVIDAD")
    private ActividadProd actividad;
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoGrupoManoObra> empleados = new ArrayList<>();
}
