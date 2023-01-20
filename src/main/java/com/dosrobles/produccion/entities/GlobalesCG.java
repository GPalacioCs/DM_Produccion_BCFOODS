package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="GLOBALES_CG")
public class GlobalesCG {
    @Id
    private String patron;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CTA_DIFCAMBIOLOCAL", referencedColumnName = "CUENTA_CONTABLE")
    private CuentaContable ctaDifCambioLocal;    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CTA_DIFCAMBIODOLAR", referencedColumnName = "CUENTA_CONTABLE")
    private CuentaContable ctaDifCambioDolar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CTR_DIFCAMBIOLOCAL", referencedColumnName = "CENTRO_COSTO")
    private CentroCosto ctrDifCambioLocal;
    @JoinColumn(name="CTR_DIFCAMBIODOLAR", referencedColumnName = "CENTRO_COSTO")
    private CentroCosto ctrDifCambioDolar;

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public CuentaContable getCtaDifCambioLocal() {
        return ctaDifCambioLocal;
    }

    public void setCtaDifCambioLocal(CuentaContable ctaDifCambioLocal) {
        this.ctaDifCambioLocal = ctaDifCambioLocal;
    }

    public CuentaContable getCtaDifCambioDolar() {
        return ctaDifCambioDolar;
    }

    public void setCtaDifCambioDolar(CuentaContable ctaDifCambioDolar) {
        this.ctaDifCambioDolar = ctaDifCambioDolar;
    }

    public CentroCosto getCtrDifCambioLocal() {
        return ctrDifCambioLocal;
    }

    public void setCtrDifCambioLocal(CentroCosto ctrDifCambioLocal) {
        this.ctrDifCambioLocal = ctrDifCambioLocal;
    }

    public CentroCosto getCtrDifCambioDolar() {
        return ctrDifCambioDolar;
    }

    public void setCtrDifCambioDolar(CentroCosto ctrDifCambioDolar) {
        this.ctrDifCambioDolar = ctrDifCambioDolar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.patron);
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
        final GlobalesCG other = (GlobalesCG) obj;
        if (!Objects.equals(this.patron, other.patron)) {
            return false;
        }
        return true;
    }
}