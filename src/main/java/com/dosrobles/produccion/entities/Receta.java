/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CS_RECETA_PRODUCCION")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Receta {    
    @Id
    private String id;    
//    @MapsId("id")    
//    @JoinColumn(name="producto")
//    private Articulo producto;
    @OneToMany(mappedBy="producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaLinea> recetaLineaList = new ArrayList<>();
    
    public BigDecimal getCostoLocal() {
        return Utils.sumBigDecimals(recetaLineaList, linea -> linea.getCostoLocal().multiply(linea.getCantidad()));
    }
    
    public BigDecimal getCostoDolar() {
        return Utils.sumBigDecimals(recetaLineaList, linea -> linea.getCostoDolar().multiply(linea.getCantidad()));
    }
}
