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
public class MateriaPrimaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ETAPA")
    private int etapa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO_PADRE")
    private String articuloPadre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARTICULO_HIJO")
    private String articuloHijo;

    public MateriaPrimaPK() {
    }

    public MateriaPrimaPK(int etapa, String articuloPadre, String articuloHijo) {
        this.etapa = etapa;
        this.articuloPadre = articuloPadre;
        this.articuloHijo = articuloHijo;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public String getArticuloPadre() {
        return articuloPadre;
    }

    public void setArticuloPadre(String articuloPadre) {
        this.articuloPadre = articuloPadre;
    }

    public String getArticuloHijo() {
        return articuloHijo;
    }

    public void setArticuloHijo(String articuloHijo) {
        this.articuloHijo = articuloHijo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) etapa;
        hash += (articuloPadre != null ? articuloPadre.hashCode() : 0);
        hash += (articuloHijo != null ? articuloHijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPrimaPK)) {
            return false;
        }
        MateriaPrimaPK other = (MateriaPrimaPK) object;
        if (this.etapa != other.etapa) {
            return false;
        }
        if ((this.articuloPadre == null && other.articuloPadre != null) || (this.articuloPadre != null && !this.articuloPadre.equals(other.articuloPadre))) {
            return false;
        }
        if ((this.articuloHijo == null && other.articuloHijo != null) || (this.articuloHijo != null && !this.articuloHijo.equals(other.articuloHijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.MateriaPrimaPK[ etapa=" + etapa + ", articuloPadre=" + articuloPadre + ", articuloHijo=" + articuloHijo + " ]";
    }

}
