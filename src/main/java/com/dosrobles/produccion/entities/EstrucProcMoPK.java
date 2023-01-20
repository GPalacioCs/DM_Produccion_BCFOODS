/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author DEV-PC
 */
@Embeddable
public class EstrucProcMoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ARTICULO")
    private String articulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VERSION")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "OPERACION")
    private String operacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private short secuencia;

    public EstrucProcMoPK() {
    }

    public EstrucProcMoPK(String articulo, String version, String operacion, short secuencia) {
        this.articulo = articulo;
        this.version = version;
        this.operacion = operacion;
        this.secuencia = secuencia;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public short getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(short secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (version != null ? version.hashCode() : 0);
        hash += (operacion != null ? operacion.hashCode() : 0);
        hash += (int) secuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucProcMoPK)) {
            return false;
        }
        EstrucProcMoPK other = (EstrucProcMoPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        if ((this.operacion == null && other.operacion != null) || (this.operacion != null && !this.operacion.equals(other.operacion))) {
            return false;
        }
        if (this.secuencia != other.secuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucProcMoPK[ articulo=" + articulo + ", version=" + version + ", operacion=" + operacion + ", secuencia=" + secuencia + " ]";
    }
    
}
