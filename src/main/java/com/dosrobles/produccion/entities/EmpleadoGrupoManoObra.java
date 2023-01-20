package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CS_EMPLEADO_GRUPO_MANO_OBRA")
@Getter @Setter
public class EmpleadoGrupoManoObra {
    @EmbeddedId
    private EmpleadoGrupoManoObraPK id = new EmpleadoGrupoManoObraPK();
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empleado")
    @JoinColumn(name = "EMPLEADO")
    private Empleado empleado;
    @ManyToOne
    @MapsId("grupo")
    @JoinColumn(name = "GRUPO", referencedColumnName = "CODIGO")
    private GrupoManoObra grupo;
}
