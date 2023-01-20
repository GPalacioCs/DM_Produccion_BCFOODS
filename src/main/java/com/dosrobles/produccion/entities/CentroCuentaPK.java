/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class CentroCuentaPK implements Serializable {

    @Column(name="CENTRO_COSTO")
    @NotEmpty
    @Size(max=25)
    private String centroCosto;
    
    @Column(name="CUENTA_CONTABLE")
    @NotEmpty
    @Size(max=25)
    private String cuentaContable;

    public CentroCuentaPK() {
    }

    public CentroCuentaPK(String centroCosto, String cuentaContable) {
        this.centroCosto = centroCosto;
        this.cuentaContable = cuentaContable;
    }
    
    

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.centroCosto);
        hash = 73 * hash + Objects.hashCode(this.cuentaContable);
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
        final CentroCuentaPK other = (CentroCuentaPK) obj;
        if (!Objects.equals(this.centroCosto, other.centroCosto)) {
            return false;
        }
        if (!Objects.equals(this.cuentaContable, other.cuentaContable)) {
            return false;
        }
        return true;
    }
    
    

}
