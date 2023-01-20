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
public class EstrucProcRubroPK implements Serializable {

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
    @Size(min = 1, max = 20)
    @Column(name = "RUBRO")
    private String rubro;

    public EstrucProcRubroPK() {
    }

    public EstrucProcRubroPK(String articulo, String version, String operacion, String rubro) {
        this.articulo = articulo;
        this.version = version;
        this.operacion = operacion;
        this.rubro = rubro;
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

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (version != null ? version.hashCode() : 0);
        hash += (operacion != null ? operacion.hashCode() : 0);
        hash += (rubro != null ? rubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucProcRubroPK)) {
            return false;
        }
        EstrucProcRubroPK other = (EstrucProcRubroPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        if ((this.operacion == null && other.operacion != null) || (this.operacion != null && !this.operacion.equals(other.operacion))) {
            return false;
        }
        if ((this.rubro == null && other.rubro != null) || (this.rubro != null && !this.rubro.equals(other.rubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucProcRubroPK[ articulo=" + articulo + ", version=" + version + ", operacion=" + operacion + ", rubro=" + rubro + " ]";
    }
    
}
