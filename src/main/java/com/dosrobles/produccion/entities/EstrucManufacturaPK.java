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
public class EstrucManufacturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 25)
    @Column(name = "ARTICULO")
    private String articulo;
    /*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ARTICULO",table = "ARTICULO")
    private Articulo articulo;*/
            
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VERSION")
    private String version;

    public EstrucManufacturaPK() {
    }

    public EstrucManufacturaPK(String articulo, String version) {
        this.articulo = articulo;
        this.version = version;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articulo != null ? articulo.hashCode() : 0);
        hash += (version != null ? version.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucManufacturaPK)) {
            return false;
        }
        EstrucManufacturaPK other = (EstrucManufacturaPK) object;
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucManufacturaPK[ articulo=" + articulo + ", version=" + version + " ]";
    }
    
}
