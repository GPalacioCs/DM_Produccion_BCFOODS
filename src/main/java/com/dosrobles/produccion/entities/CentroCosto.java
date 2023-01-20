/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "CENTRO_COSTO")
@NamedQueries({
    @NamedQuery(name = "CentroCosto.findAll", query = "SELECT c FROM CentroCosto c")})
public class CentroCosto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CENTRO_COSTO")
    private String centroCosto;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "ACEPTA_DATOS")
    private String aceptaDatos = "S";
    @Column(name="TIPO")
    private String tipo = "A";
    @OneToMany(mappedBy = "centroCosto", fetch = FetchType.LAZY)
    private List<CentroCuenta> centroCuentaList = new ArrayList<>();
    @Column(name="CreateDate", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    

    public CentroCosto() {
    }

    public CentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }


    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAceptaDatos() {
        return aceptaDatos;
    }

    public void setAceptaDatos(String aceptaDatos) {
        this.aceptaDatos = aceptaDatos;
    }    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<CentroCuenta> getCentroCuentaList() {
        return centroCuentaList;
    }

    public void setCentroCuentaList(List<CentroCuenta> centroCuentaList) {
        this.centroCuentaList = centroCuentaList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (centroCosto != null ? centroCosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroCosto)) {
            return false;
        }
        CentroCosto other = (CentroCosto) object;
        if ((this.centroCosto == null && other.centroCosto != null) || (this.centroCosto != null && !this.centroCosto.equals(other.centroCosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CentroCosto[ centroCosto=" + centroCosto + " ]";
    }

}
