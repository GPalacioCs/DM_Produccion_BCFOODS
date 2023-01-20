/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "ESTRUC_MATERIAL")
@XmlRootElement
public class EstrucMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstrucMaterialPK estrucMaterialPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIEMPO_PREVIO")
    private short tiempoPrevio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTO_UNITARIO")
    private BigDecimal costoUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_DESPERDICIO")
    private BigDecimal porcDesperdicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORC_DE_COSTO")
    private BigDecimal porcDeCosto;
    @Size(max = 10)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "SECUENCIA")
    private Integer secuencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PERMITE_BACKFLUSH")
    private String permiteBackflush;
    @Column(name = "PORC_COSTO_COPRODUCTO")
    private BigDecimal porcCostoCoproducto;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPONENTE",referencedColumnName = "ARTICULO",insertable = false,updatable = false)
    private Articulo componente;
    @ManyToOne
    @JoinColumn(name = "ARTICULO", insertable = false, updatable = false)
    private Articulo articulo;

    public Articulo getComponente() {
        return componente;
    }

    public void setComponente(Articulo componente) {
        this.componente = componente;
    }

    
    public EstrucMaterial() {
    }

    public EstrucMaterial(EstrucMaterialPK estrucMaterialPK) {
        this.estrucMaterialPK = estrucMaterialPK;
    }

    public EstrucMaterial(EstrucMaterialPK estrucMaterialPK, BigDecimal cantidad, short tiempoPrevio, BigDecimal costoUnitario, BigDecimal porcDesperdicio, BigDecimal porcDeCosto, String permiteBackflush, short noteExistsFlag, Date recordDate, String rowPointer, String createdBy, String updatedBy, Date createDate) {
        this.estrucMaterialPK = estrucMaterialPK;
        this.cantidad = cantidad;
        this.tiempoPrevio = tiempoPrevio;
        this.costoUnitario = costoUnitario;
        this.porcDesperdicio = porcDesperdicio;
        this.porcDeCosto = porcDeCosto;
        this.permiteBackflush = permiteBackflush;

    }

    public EstrucMaterial(String articulo, String version, String operacion, String componente, String entradaSalida) {
        this.estrucMaterialPK = new EstrucMaterialPK(articulo, version, operacion, componente, entradaSalida);
    }

    public EstrucMaterialPK getEstrucMaterialPK() {
        return estrucMaterialPK;
    }

    public void setEstrucMaterialPK(EstrucMaterialPK estrucMaterialPK) {
        this.estrucMaterialPK = estrucMaterialPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public short getTiempoPrevio() {
        return tiempoPrevio;
    }

    public void setTiempoPrevio(short tiempoPrevio) {
        this.tiempoPrevio = tiempoPrevio;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigDecimal getPorcDesperdicio() {
        return porcDesperdicio;
    }

    public void setPorcDesperdicio(BigDecimal porcDesperdicio) {
        this.porcDesperdicio = porcDesperdicio;
    }

    public BigDecimal getPorcDeCosto() {
        return porcDeCosto;
    }

    public void setPorcDeCosto(BigDecimal porcDeCosto) {
        this.porcDeCosto = porcDeCosto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getPermiteBackflush() {
        return permiteBackflush;
    }

    public void setPermiteBackflush(String permiteBackflush) {
        this.permiteBackflush = permiteBackflush;
    }

    public BigDecimal getPorcCostoCoproducto() {
        return porcCostoCoproducto;
    }

    public void setPorcCostoCoproducto(BigDecimal porcCostoCoproducto) {
        this.porcCostoCoproducto = porcCostoCoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estrucMaterialPK != null ? estrucMaterialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstrucMaterial)) {
            return false;
        }
        EstrucMaterial other = (EstrucMaterial) object;
        if ((this.estrucMaterialPK == null && other.estrucMaterialPK != null) || (this.estrucMaterialPK != null && !this.estrucMaterialPK.equals(other.estrucMaterialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dosrobles.produccion.entities.EstrucMaterial[ estrucMaterialPK=" + estrucMaterialPK + " ]";
    }
    
}
