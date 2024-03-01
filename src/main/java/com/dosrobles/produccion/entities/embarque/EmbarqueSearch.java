package com.dosrobles.produccion.entities.embarque;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "EMBARQUE")
public class EmbarqueSearch {
    @Id
    private String Embarque;
    private Date Fecha_Embarque;
    private String Enviado;
    private String Estado;
}
