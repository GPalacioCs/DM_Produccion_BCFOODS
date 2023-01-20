/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "BODEGA")
@NamedQueries({
    @NamedQuery(name = "Bodega.findAll", query = "SELECT b FROM Bodega b")})
public class Bodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BODEGA")
    private String bodega;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono = "ND";
    @Column(name = "DIRECCION")
    private String direccion;
    @JoinColumn(name = "CONSEC_TRASLADOS", referencedColumnName = "CONSECUTIVO")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConsecutivoCi consecutivoCi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega", fetch = FetchType.LAZY)
    private List<ExistenciaBodega> existenciaBodegaList = new ArrayList<>();

    public Bodega() {
    }

    public Bodega(String bodega) {
        this.bodega = bodega;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ConsecutivoCi getConsecutivoCi() {
        return consecutivoCi;
    }

    public void setConsecutivoCi(ConsecutivoCi consecutivoCi) {
        this.consecutivoCi = consecutivoCi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bodega != null ? bodega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodega)) {
            return false;
        }
        Bodega other = (Bodega) object;
        if ((this.bodega == null && other.bodega != null) || (this.bodega != null && !this.bodega.equals(other.bodega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", bodega, nombre);
    }
    
}
