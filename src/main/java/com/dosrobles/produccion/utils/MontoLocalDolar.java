/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.utils;

import java.math.BigDecimal;

/**
 *
 * @author Corpsoft S.A.
 */
public class MontoLocalDolar {
    private BigDecimal montoLocal;
    private BigDecimal montoDolar;

    public MontoLocalDolar() {
    }

    public MontoLocalDolar(BigDecimal montoLocal, BigDecimal montoDolar) {
        this.montoLocal = montoLocal;
        this.montoDolar = montoDolar;
    }

    public BigDecimal getMontoLocal() {
        return montoLocal;
    }

    public void setMontoLocal(BigDecimal montoLocal) {
        this.montoLocal = montoLocal;
    }

    public BigDecimal getMontoDolar() {
        return montoDolar;
    }

    public void setMontoDolar(BigDecimal montoDolar) {
        this.montoDolar = montoDolar;
    }
    
    
}
