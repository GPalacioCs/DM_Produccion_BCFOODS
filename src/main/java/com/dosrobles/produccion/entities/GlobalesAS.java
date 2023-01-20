/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name="GLOBALES_AS")
public class GlobalesAS implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="TIPO_CAMBIO_DOLAR")
    private String tipoCambioDolar;
    
    @Column(name="PATRON_CCOSTO")
    private String patronCCosto;
    
    @Column(name="MONEDA_LOCAL")
    private String monedaLocal;
    
    @Column(name="MONEDA_DOLAR")
    private String monedaDolar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IMPUESTO1_DESC", referencedColumnName = "IMPUESTO")
    private Impuesto impuesto;

    public String getTipoCambioDolar() {
        return tipoCambioDolar;
    }

    public void setTipoCambioDolar(String tipoCambioDolar) {
        this.tipoCambioDolar = tipoCambioDolar;
    }

    public String getPatronCCosto() {
        return patronCCosto;
    }

    public void setPatronCCosto(String patronCCosto) {
        this.patronCCosto = patronCCosto;
    }

    public String getMonedaLocal() {
        return monedaLocal;
    }

    public void setMonedaLocal(String monedaLocal) {
        this.monedaLocal = monedaLocal;
    }

    public String getMonedaDolar() {
        return monedaDolar;
    }

    public void setMonedaDolar(String monedaDolar) {
        this.monedaDolar = monedaDolar;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.tipoCambioDolar);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GlobalesAS other = (GlobalesAS) obj;
        if (!Objects.equals(this.tipoCambioDolar, other.tipoCambioDolar)) {
            return false;
        }
        return true;
    }    
    
    

}
