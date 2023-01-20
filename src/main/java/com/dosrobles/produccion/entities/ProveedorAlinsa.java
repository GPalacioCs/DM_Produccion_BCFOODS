package com.dosrobles.produccion.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PROVEEDOR", schema = "ALINSA")
@Getter @Setter
@EqualsAndHashCode(of="proveedor")
public class ProveedorAlinsa {
    @Id
    private String proveedor;
    @Column(name="NOMBRE")
    private String nombre;
    @ManyToOne
    @JoinColumn(name="MONEDA")
    private Moneda moneda;
    @ManyToOne
    @JoinColumn(name="PAIS")
    private Pais pais;
    @ManyToOne
    @JoinColumn(name="CONDICION_PAGO")
    private CondicionPago condicionPago;
    
    @Override
    public String toString() {
        return String.format("%s - %s", proveedor, nombre);
    }
}
