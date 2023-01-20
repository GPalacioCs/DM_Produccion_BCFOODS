package com.dosrobles.produccion.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmpleadoGrupoManoObraPK implements Serializable {
    @Column(name="EMPLEADO")
    private String empleado;
    @Column(name="GRUPO")
    private String grupo;
}
