/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "PAQUETE")
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p")})
public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAQUETE")
    private String paquete;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "USUARIO_CREADOR")
    private String usuarioCreador;
    @Basic(optional = false)
    @Column(name = "ULTIMO_USUARIO")
    private String ultimoUsuario;
    @Basic(optional = false)
    @Column(name = "FECHA_ULT_ACCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltAcceso;
    @Basic(optional = false)
    @Column(name = "ULTIMO_ASIENTO")
    private String ultimoAsiento;
    @Basic(optional = false)
    @Column(name = "DOBLE_ASIENTO")
    private String dobleAsiento = "N";
    @Basic(optional = false)
    @Column(name = "USA_DOBLE_ASIENTO")
    private String usaDobleAsiento = "N";
    @Basic(optional = false)
    @Column(name = "MARCADO")
    private String marcado = "N";    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquete")
    private List<AsientoDeDiario> asientoDeDiarioCollection;

    public Paquete() {
    }

    public Paquete(String paquete) {
        this.paquete = paquete;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    public Date getFechaUltAcceso() {
        return fechaUltAcceso;
    }

    public void setFechaUltAcceso(Date fechaUltAcceso) {
        this.fechaUltAcceso = fechaUltAcceso;
    }

    public String getUltimoAsiento() {
        return ultimoAsiento;
    }

    public void setUltimoAsiento(String ultimoAsiento) {
        this.ultimoAsiento = ultimoAsiento;
    }

    public String getDobleAsiento() {
        return dobleAsiento;
    }

    public void setDobleAsiento(String dobleAsiento) {
        this.dobleAsiento = dobleAsiento;
    }

    public String getUsaDobleAsiento() {
        return usaDobleAsiento;
    }

    public void setUsaDobleAsiento(String usaDobleAsiento) {
        this.usaDobleAsiento = usaDobleAsiento;
    }

    public String getMarcado() {
        return marcado;
    }

    public void setMarcado(String marcado) {
        this.marcado = marcado;
    }

    public List<AsientoDeDiario> getAsientoDeDiarioCollection() {
        return asientoDeDiarioCollection;
    }

    public void setAsientoDeDiarioCollection(List<AsientoDeDiario> asientoDeDiarioCollection) {
        this.asientoDeDiarioCollection = asientoDeDiarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paquete != null ? paquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.paquete == null && other.paquete != null) || (this.paquete != null && !this.paquete.equals(other.paquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paquete[ paquete=" + paquete + " ]";
    }

}
