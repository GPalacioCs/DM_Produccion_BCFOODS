package com.dosrobles.produccion.entities.contenedor;

import com.dosrobles.produccion.entities.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "CS_CONTENEDOR")
public class Contenedor {
    @Id
    @Column(name = "CONTENEDOR")
    private String ContenedorId;
    private String Estado = "A";
    @JoinColumn(name = "Usuario_Creacion", referencedColumnName = "USUARIO")
    private Usuario Usuario_Creacion;
    @JoinColumn(name = "Usuario_Ult_Mov", referencedColumnName = "USUARIO")
    private Usuario Usuario_Ult_Mov;
    private Date Fecha_Creacion;
    private Date Fecha_Ult_Mov;

    @OneToMany(mappedBy = "ContenedorId", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<ContenedorDetalle> ContenedorDetalles = new ArrayList<>();
}
