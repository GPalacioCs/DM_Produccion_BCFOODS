/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name="Centro_Cuenta")
public class CentroCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    CentroCuentaPK id;
    @NotEmpty
    @Size(max=1)
    private String estado = "A";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CENTRO_COSTO", updatable = false, insertable = false)
    private CentroCosto centroCosto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CUENTA_CONTABLE", updatable = false, insertable = false)
    private CuentaContable cuentaContable;
    
    public CentroCuenta() {        
    }
    
    public CentroCuenta(CentroCuentaPK id) {
        this.id = id;
    }

    public CentroCuentaPK getId() {
        return id;
    }

    public void setId(CentroCuentaPK id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }

    public CuentaContable getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(CuentaContable cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final CentroCuenta other = (CentroCuenta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
