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
public class DocumentoAnticipoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Basic(optional = false)
    @Column(name = "DOCUMENTO_CC")
    private String documentoCc;
    @Basic(optional = false)
    @Column(name = "TIPO_CC")
    private String tipoCc;

    public DocumentoAnticipoPK() {
    }

    public DocumentoAnticipoPK(String tipoDocumento, String documento, String documentoCc, String tipoCc) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.documentoCc = documentoCc;
        this.tipoCc = tipoCc;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoCc() {
        return documentoCc;
    }

    public void setDocumentoCc(String documentoCc) {
        this.documentoCc = documentoCc;
    }

    public String getTipoCc() {
        return tipoCc;
    }

    public void setTipoCc(String tipoCc) {
        this.tipoCc = tipoCc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        hash += (documento != null ? documento.hashCode() : 0);
        hash += (documentoCc != null ? documentoCc.hashCode() : 0);
        hash += (tipoCc != null ? tipoCc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoAnticipoPK)) {
            return false;
        }
        DocumentoAnticipoPK other = (DocumentoAnticipoPK) object;
        if ((this.tipoDocumento == null && other.tipoDocumento != null) || (this.tipoDocumento != null && !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        if ((this.documentoCc == null && other.documentoCc != null) || (this.documentoCc != null && !this.documentoCc.equals(other.documentoCc))) {
            return false;
        }
        if ((this.tipoCc == null && other.tipoCc != null) || (this.tipoCc != null && !this.tipoCc.equals(other.tipoCc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentoAnticipoPK[ tipoDocumento=" + tipoDocumento + ", documento=" + documento + ", documentoCc=" + documentoCc + ", tipoCc=" + tipoCc + " ]";
    }

}
