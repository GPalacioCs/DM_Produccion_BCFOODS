package com.dosrobles.produccion.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CS_EMPAQUE_ENCABEZADO")
@Data
@EqualsAndHashCode(of="id")
public class EmpaqueEncabezado implements Serializable {    
    @EmbeddedId
    private EmpaqueEncabezadoPK id = new EmpaqueEncabezadoPK();
    @MapsId("ordenProduccion")
    @ManyToOne
    @JoinColumn(name="ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @MapsId("cliente")
    @ManyToOne
    @JoinColumn(name="CLIENTE")
    private Cliente cliente;
    private String pedido;
    @OneToMany(mappedBy="empaqueEncabezado", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.empaque DESC")
    private List<Empaque> empaqueList = new ArrayList<>();
    @Column(name = "MAQUILA")
    private Boolean maquila = false;
}
