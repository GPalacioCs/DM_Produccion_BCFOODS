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
 * @author Corpsoft S.A.
 */
@Embeddable
public class LineaDocInvPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "PAQUETE_INVENTARIO")
    private String paqueteInventario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DOCUMENTO_INV")
    private String documentoInv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA_DOC_INV")
    private int lineaDocInv;

    public LineaDocInvPK() {
    }

    public LineaDocInvPK(String paqueteInventario, String documentoInv, int lineaDocInv) {
        this.paqueteInventario = paqueteInventario;
        this.documentoInv = documentoInv;
        this.lineaDocInv = lineaDocInv;
    }

    public String getPaqueteInventario() {
        return paqueteInventario;
    }

    public void setPaqueteInventario(String paqueteInventario) {
        this.paqueteInventario = paqueteInventario;
    }

    public String getDocumentoInv() {
        return documentoInv;
    }

    public void setDocumentoInv(String documentoInv) {
        this.documentoInv = documentoInv;
    }

    public int getLineaDocInv() {
        return lineaDocInv;
    }

    public void setLineaDocInv(int lineaDocInv) {
        this.lineaDocInv = lineaDocInv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paqueteInventario != null ? paqueteInventario.hashCode() : 0);
        hash += (documentoInv != null ? documentoInv.hashCode() : 0);
        hash += (int) lineaDocInv;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaDocInvPK)) {
            return false;
        }
        LineaDocInvPK other = (LineaDocInvPK) object;
        if ((this.paqueteInventario == null && other.paqueteInventario != null) || (this.paqueteInventario != null && !this.paqueteInventario.equals(other.paqueteInventario))) {
            return false;
        }
        if ((this.documentoInv == null && other.documentoInv != null) || (this.documentoInv != null && !this.documentoInv.equals(other.documentoInv))) {
            return false;
        }
        if (this.lineaDocInv != other.lineaDocInv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.corpsoftsa.erp.inventario.entities.LineaDocInvPK[ paqueteInventario=" + paqueteInventario + ", documentoInv=" + documentoInv + ", lineaDocInv=" + lineaDocInv + " ]";
    }

}
