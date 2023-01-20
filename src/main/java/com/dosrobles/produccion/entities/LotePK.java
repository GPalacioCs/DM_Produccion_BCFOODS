/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Corpsoft S.A.
 */
@Embeddable
public class LotePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "LOTE")
    private String lote;
    @Basic(optional = false)
    @Column(name = "ARTICULO")
    private String articulo;

    public LotePK() {
    }

    public LotePK(String lote, String articulo) {
        this.lote = lote;
        this.articulo = articulo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lote != null ? lote.hashCode() : 0);
        hash += (articulo != null ? articulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LotePK)) {
            return false;
        }
        LotePK other = (LotePK) object;
        if ((this.lote == null && other.lote != null) || (this.lote != null && !this.lote.equals(other.lote))) {
            return false;
        }
        if ((this.articulo == null && other.articulo != null) || (this.articulo != null && !this.articulo.equals(other.articulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LotePK[ lote=" + lote + ", articulo=" + articulo + " ]";
    }

}
