/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Entity
@Table(name = "ASIENTO_DE_DIARIO")
@NamedQueries({
    @NamedQuery(name = "AsientoDeDiario.findAll", query = "SELECT a FROM AsientoDeDiario a")})
public class AsientoDeDiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ASIENTO")
    private String asiento;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "CONTABILIDAD")
    private String contabilidad = "F";
    @Basic(optional = false)
    @Column(name = "ORIGEN")
    private String origen;
    @Basic(optional = false)
    @Column(name = "CLASE_ASIENTO")
    private String claseAsiento = "N";
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TOTAL_DEBITO_LOC")
    private BigDecimal totalDebitoLoc;
    @Basic(optional = false)
    @Column(name = "TOTAL_DEBITO_DOL")
    private BigDecimal totalDebitoDol;
    @Basic(optional = false)
    @Column(name = "TOTAL_CREDITO_LOC")
    private BigDecimal totalCreditoLoc;
    @Basic(optional = false)
    @Column(name = "TOTAL_CREDITO_DOL")
    private BigDecimal totalCreditoDol;
    @Basic(optional = false)
    @Column(name = "ULTIMO_USUARIO")
    private String ultimoUsuario;
    @Basic(optional = false)
    @Column(name = "FECHA_ULT_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltModif;
    @Basic(optional = false)
    @Column(name = "MARCADO")
    private String marcado = "N";
    @Lob
    @Column(name = "NOTAS")
    private String notas;
    @Basic(optional = false)
    @Column(name = "TOTAL_CONTROL_LOC")
    private BigDecimal totalControlLoc = BigDecimal.ZERO;
    @Basic(optional = false)
    @Column(name = "TOTAL_CONTROL_DOL")
    private BigDecimal totalControlDol = BigDecimal.ZERO;
    @Basic(optional = false)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @NotNull(message="El tipo de asiento es requerido")
    @JoinColumn(name = "PAQUETE", referencedColumnName = "PAQUETE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paquete paquete;
    @JoinColumn(name = "TIPO_ASIENTO", referencedColumnName = "TIPO_ASIENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoAsiento tipoAsiento;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("diarioPK.consecutivo ASC")
    private List<Diario> diarios = new ArrayList<>();
    

    public AsientoDeDiario() {
    }

    public AsientoDeDiario(String asiento) {
        this.asiento = asiento;
    }

    public AsientoDeDiario(String asiento, Date fecha, String contabilidad, String origen, String claseAsiento, BigDecimal totalDebitoLoc, BigDecimal totalDebitoDol, BigDecimal totalCreditoLoc, BigDecimal totalCreditoDol, String ultimoUsuario, Date fechaUltModif, String marcado, BigDecimal totalControlLoc, BigDecimal totalControlDol, String usuarioCreacion, Date fechaCreacion) {
        this.asiento = asiento;
        this.fecha = fecha;
        this.contabilidad = contabilidad;
        this.origen = origen;
        this.claseAsiento = claseAsiento;
        this.totalDebitoLoc = totalDebitoLoc;
        this.totalDebitoDol = totalDebitoDol;
        this.totalCreditoLoc = totalCreditoLoc;
        this.totalCreditoDol = totalCreditoDol;
        this.ultimoUsuario = ultimoUsuario;
        this.fechaUltModif = fechaUltModif;
        this.marcado = marcado;
        this.totalControlLoc = totalControlLoc;
        this.totalControlDol = totalControlDol;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(String contabilidad) {
        this.contabilidad = contabilidad;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getClaseAsiento() {
        return claseAsiento;
    }

    public void setClaseAsiento(String claseAsiento) {
        this.claseAsiento = claseAsiento;
    }

    public BigDecimal getTotalDebitoLoc() {
        return totalDebitoLoc;
    }

    public void setTotalDebitoLoc(BigDecimal totalDebitoLoc) {
        this.totalDebitoLoc = totalDebitoLoc;
    }

    public BigDecimal getTotalDebitoDol() {
        return totalDebitoDol;
    }

    public void setTotalDebitoDol(BigDecimal totalDebitoDol) {
        this.totalDebitoDol = totalDebitoDol;
    }

    public BigDecimal getTotalCreditoLoc() {
        return totalCreditoLoc;
    }

    public void setTotalCreditoLoc(BigDecimal totalCreditoLoc) {
        this.totalCreditoLoc = totalCreditoLoc;
    }

    public BigDecimal getTotalCreditoDol() {
        return totalCreditoDol;
    }

    public void setTotalCreditoDol(BigDecimal totalCreditoDol) {
        this.totalCreditoDol = totalCreditoDol;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    public Date getFechaUltModif() {
        return fechaUltModif;
    }

    public void setFechaUltModif(Date fechaUltModif) {
        this.fechaUltModif = fechaUltModif;
    }

    public String getMarcado() {
        return marcado;
    }

    public void setMarcado(String marcado) {
        this.marcado = marcado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getTotalControlLoc() {
        return totalControlLoc;
    }

    public void setTotalControlLoc(BigDecimal totalControlLoc) {
        this.totalControlLoc = totalControlLoc;
    }

    public BigDecimal getTotalControlDol() {
        return totalControlDol;
    }

    public void setTotalControlDol(BigDecimal totalControlDol) {
        this.totalControlDol = totalControlDol;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }    

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
        this.tipoAsiento = new TipoAsiento(paquete.getPaquete());
    }

    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(TipoAsiento tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public List<Diario> getDiarios() {
        return diarios;
    }

    public void setDiarios(List<Diario> diarios) {
        this.diarios = diarios;
    }
    
    public boolean isAsientoCuadrado() {
        return totalDebitoLoc.compareTo(totalCreditoLoc)==0
                &&totalCreditoDol.compareTo(totalDebitoDol) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiento != null ? asiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsientoDeDiario)) {
            return false;
        }
        AsientoDeDiario other = (AsientoDeDiario) object;
        if ((this.asiento == null && other.asiento != null) || (this.asiento != null && !this.asiento.equals(other.asiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AsientoDeDiario[ asiento=" + asiento + " ]";
    }

}
