/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
public class ConsolidadoPedidoLinea implements Serializable {
    @Id
    private String pedido;
}
