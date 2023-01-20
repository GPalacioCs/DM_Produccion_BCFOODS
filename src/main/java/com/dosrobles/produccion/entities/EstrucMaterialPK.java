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
public class EstrucMaterialPK implements Serializable {

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
    @Column(name = "COMPONENTE")
    private String componente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ENTRADA_SALIDA")
    private String entradaSalida;

    public EstrucMaterialPK() {
    }

    public EstrucMaterialPK(String articulo, String version, String operacion, String componente, String entradaSalida) {
        this.articulo = articulo;
        this.version = version;
        this.operacion = operacion;
        this.componente = componente;
        this.entradaSalida = entradaSalida;
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

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(String entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (version != null ? version.hashCode() : 0);
        hash += (operacion != null ? operacion.hashCode() : 0);
        hash += (componente != null ? componente.hashCode() : 0);
        hash += (entradaSalida != null ? entradaSalida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucMaterialPK)) {
            return false;
        }
        EstrucMaterialPK other = (EstrucMaterialPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        if ((this.operacion == null && other.operacion != null) || (this.operacion != null && !this.operacion.equals(other.operacion))) {
            return false;
        }
        if ((this.componente == null && other.componente != null) || (this.componente != null && !this.componente.equals(other.componente))) {
            return false;
        }
        if ((this.entradaSalida == null && other.entradaSalida != null) || (this.entradaSalida != null && !this.entradaSalida.equals(other.entradaSalida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucMaterialPK[ articulo=" + articulo + ", version=" + version + ", operacion=" + operacion + ", componente=" + componente + ", entradaSalida=" + entradaSalida + " ]";
    }
    
}
