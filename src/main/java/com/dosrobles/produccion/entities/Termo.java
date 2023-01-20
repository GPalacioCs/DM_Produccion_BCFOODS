package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="CS_TERMO")
@Getter @Setter
public class Termo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "termo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloTermo> articuloTermoList = new ArrayList<>();
    
    @Override
    public String toString() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termo termo = (Termo) o;
        return id.equals(termo.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
