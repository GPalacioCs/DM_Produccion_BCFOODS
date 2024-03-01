package com.dosrobles.produccion.entities.contenedor;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CS_CONTENEDOR_DETALLE")
@IdClass(ContenedorDetalleId.class)
public class ContenedorDetalle {
    @Id
    @ManyToOne
    @JoinColumn(name = "CONTENEDOR", referencedColumnName = "CONTENEDOR")
    private Contenedor ContenedorId;
    @Id
    private String Contenido;
    @Id
    private String Tipo;
}

class ContenedorDetalleId {
    private String ContenedorId;
    private String Contenido;
    private String Tipo;
}
