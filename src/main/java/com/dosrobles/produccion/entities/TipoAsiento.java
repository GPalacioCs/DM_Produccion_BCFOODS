/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "TIPO_ASIENTO")
@NamedQueries({
    @NamedQuery(name = "TipoAsiento.findAll", query = "SELECT t FROM TipoAsiento t")})
public class TipoAsiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_ASIENTO")
    private String tipoAsiento;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAsiento")
    private Collection<AsientoDeDiario> asientoDeDiarioCollection;

    public TipoAsiento() {
    }

    public TipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<AsientoDeDiario> getAsientoDeDiarioCollection() {
        return asientoDeDiarioCollection;
    }

    public void setAsientoDeDiarioCollection(Collection<AsientoDeDiario> asientoDeDiarioCollection) {
        this.asientoDeDiarioCollection = asientoDeDiarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoAsiento != null ? tipoAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsiento)) {
            return false;
        }
        TipoAsiento other = (TipoAsiento) object;
        if ((this.tipoAsiento == null && other.tipoAsiento != null) || (this.tipoAsiento != null && !this.tipoAsiento.equals(other.tipoAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TipoAsiento[ tipoAsiento=" + tipoAsiento + " ]";
    }

}
