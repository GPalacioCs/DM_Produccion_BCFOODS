/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Entity
@Table(name = "EMPLEADO", schema = "ALINSA")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EMPLEADO")
    private String empleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Size(max = 4)
    @Column(name = "TIPO_SANGRE")
    private String tipoSangre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo;
    @Size(max = 20)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 20)
    @Column(name = "PASAPORTE")
    private String pasaporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Column(name = "DEPENDIENTES")
    private Short dependientes;
    @Size(max = 20)
    @Column(name = "ASEGURADO")
    private String asegurado;
    @Size(max = 20)
    @Column(name = "PERMISO_CONDUCIR")
    private String permisoConducir;
    @Size(max = 20)
    @Column(name = "PERMISO_SALUD")
    private String permisoSalud;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VACS_PENDIENTES")
    private BigDecimal vacsPendientes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VACS_ULT_CALCULO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vacsUltCalculo;
    @Column(name = "FECHA_PROX_EVAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProxEval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARIO_REFERENCIA")
    private BigDecimal salarioReferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "FORMA_PAGO")
    private String formaPago;
    @Size(max = 50)
    @Column(name = "CUENTA_ENTIDAD")
    private String cuentaEntidad;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DIRECCION_HAB")
    private String direccionHab;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DIRECCION_POSTAL")
    private String direccionPostal;
    @Size(max = 20)
    @Column(name = "TELEFONO1")
    private String telefono1;
    @Size(max = 40)
    @Column(name = "NOTAS_TEL1")
    private String notasTel1;
    @Size(max = 20)
    @Column(name = "TELEFONO2")
    private String telefono2;
    @Size(max = 40)
    @Column(name = "NOTAS_TEL2")
    private String notasTel2;
    @Size(max = 20)
    @Column(name = "TELEFONO3")
    private String telefono3;
    @Size(max = 40)
    @Column(name = "NOTAS_TEL3")
    private String notasTel3;
    @Lob
    @Column(name = "FOTOGRAFIA")
    private byte[] fotografia;
    @Size(max = 40)
    @Column(name = "RUBRO1")
    private String rubro1;
    @Size(max = 40)
    @Column(name = "RUBRO2")
    private String rubro2;
    @Size(max = 40)
    @Column(name = "RUBRO3")
    private String rubro3;
    @Size(max = 40)
    @Column(name = "RUBRO4")
    private String rubro4;
    @Size(max = 40)
    @Column(name = "RUBRO5")
    private String rubro5;
    @Size(max = 40)
    @Column(name = "RUBRO6")
    private String rubro6;
    @Size(max = 40)
    @Column(name = "RUBRO7")
    private String rubro7;
    @Size(max = 40)
    @Column(name = "RUBRO8")
    private String rubro8;
    @Size(max = 40)
    @Column(name = "RUBRO9")
    private String rubro9;
    @Size(max = 40)
    @Column(name = "RUBRO10")
    private String rubro10;
    @Size(max = 40)
    @Column(name = "RUBRO11")
    private String rubro11;
    @Size(max = 40)
    @Column(name = "RUBRO12")
    private String rubro12;
    @Size(max = 40)
    @Column(name = "RUBRO13")
    private String rubro13;
    @Size(max = 40)
    @Column(name = "RUBRO14")
    private String rubro14;
    @Size(max = 40)
    @Column(name = "RUBRO15")
    private String rubro15;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NO_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNoPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_SALARIO_AUMEN")
    private String tipoSalarioAumen;
    @Column(name = "FECHA_ANTIG_EMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAntigEmp;
    @Column(name = "FECHA_ANTIG_GOB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAntigGob;
    @Column(name = "SALARIO_DIARIO_INT")
    private BigDecimal salarioDiarioInt;
    @Size(max = 20)
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Size(max = 20)
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @Size(max = 30)
    @Column(name = "NOMBRE_PILA")
    private String nombrePila;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 249)
    @Column(name = "E_MAIL")
    private String eMail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VACS_ADICIONALES")
    private BigDecimal vacsAdicionales;
    @Size(max = 40)
    @Column(name = "RUBRO16")
    private String rubro16;
    @Size(max = 40)
    @Column(name = "RUBRO17")
    private String rubro17;
    @Size(max = 40)
    @Column(name = "RUBRO18")
    private String rubro18;
    @Size(max = 40)
    @Column(name = "RUBRO19")
    private String rubro19;
    @Size(max = 40)
    @Column(name = "RUBRO20")
    private String rubro20;
    @Size(max = 40)
    @Column(name = "RUBRO21")
    private String rubro21;
    @Size(max = 40)
    @Column(name = "RUBRO22")
    private String rubro22;
    @Size(max = 40)
    @Column(name = "RUBRO23")
    private String rubro23;
    @Size(max = 40)
    @Column(name = "RUBRO24")
    private String rubro24;
    @Size(max = 40)
    @Column(name = "RUBRO25")
    private String rubro25;
    @Column(name = "SALARIOPD_LOCAL")
    private BigDecimal salariopdLocal;
    @Column(name = "SALARIOPD_DOLAR")
    private BigDecimal salariopdDolar;
    @Column(name = "SALARIOPD_NOMINA")
    private BigDecimal salariopdNomina;
    @Column(name = "DIAS_DISP_INCAP")
    private BigDecimal diasDispIncap;
    @Size(max = 25)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_HORA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;
    @Size(max = 25)
    @Column(name = "USUARIO_ULT_MOD")
    private String usuarioUltMod;
    @Column(name = "FCH_HORA_ULT_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchHoraUltMod;
    @Size(max = 50)
    @Column(name = "CTA_ELECTRONICA")
    private String ctaElectronica;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA1")
    private String divisionGeografica1;
    @Size(max = 12)
    @Column(name = "DIVISION_GEOGRAFICA2")
    private String divisionGeografica2;
    @Size(max = 1)
    @Column(name = "TIPO_SALARIO")
    private String tipoSalario;
    @Size(max = 2)
    @Column(name = "BENEFICIO_COLECTIVO")
    private String beneficioColectivo;
    @Column(name = "TIPO_MEDIDAS_CERTIFICADAS")
    private Integer tipoMedidasCertificadas;
    @Column(name = "TIPO_NIVEL_EDUCATIVO")
    private Integer tipoNivelEducativo;
    @Column(name="CENTRO_COSTO")
    @Getter @Setter
    private String centroCosto;
    @JoinColumn(name="NOMINA", referencedColumnName = "NOMINA")
    @ManyToOne
    @Getter @Setter
    private Nomina nomina;

    public Empleado() {
    }

    public Empleado(String empleado) {
        this.empleado = empleado;
    }

    

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Short getDependientes() {
        return dependientes;
    }

    public void setDependientes(Short dependientes) {
        this.dependientes = dependientes;
    }

    public String getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(String asegurado) {
        this.asegurado = asegurado;
    }

    public String getPermisoConducir() {
        return permisoConducir;
    }

    public void setPermisoConducir(String permisoConducir) {
        this.permisoConducir = permisoConducir;
    }

    public String getPermisoSalud() {
        return permisoSalud;
    }

    public void setPermisoSalud(String permisoSalud) {
        this.permisoSalud = permisoSalud;
    }

    public BigDecimal getVacsPendientes() {
        return vacsPendientes;
    }

    public void setVacsPendientes(BigDecimal vacsPendientes) {
        this.vacsPendientes = vacsPendientes;
    }

    public Date getVacsUltCalculo() {
        return vacsUltCalculo;
    }

    public void setVacsUltCalculo(Date vacsUltCalculo) {
        this.vacsUltCalculo = vacsUltCalculo;
    }

    public Date getFechaProxEval() {
        return fechaProxEval;
    }

    public void setFechaProxEval(Date fechaProxEval) {
        this.fechaProxEval = fechaProxEval;
    }

    public BigDecimal getSalarioReferencia() {
        return salarioReferencia;
    }

    public void setSalarioReferencia(BigDecimal salarioReferencia) {
        this.salarioReferencia = salarioReferencia;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getCuentaEntidad() {
        return cuentaEntidad;
    }

    public void setCuentaEntidad(String cuentaEntidad) {
        this.cuentaEntidad = cuentaEntidad;
    }

    public String getDireccionHab() {
        return direccionHab;
    }

    public void setDireccionHab(String direccionHab) {
        this.direccionHab = direccionHab;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getNotasTel1() {
        return notasTel1;
    }

    public void setNotasTel1(String notasTel1) {
        this.notasTel1 = notasTel1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNotasTel2() {
        return notasTel2;
    }

    public void setNotasTel2(String notasTel2) {
        this.notasTel2 = notasTel2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getNotasTel3() {
        return notasTel3;
    }

    public void setNotasTel3(String notasTel3) {
        this.notasTel3 = notasTel3;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getRubro1() {
        return rubro1;
    }

    public void setRubro1(String rubro1) {
        this.rubro1 = rubro1;
    }

    public String getRubro2() {
        return rubro2;
    }

    public void setRubro2(String rubro2) {
        this.rubro2 = rubro2;
    }

    public String getRubro3() {
        return rubro3;
    }

    public void setRubro3(String rubro3) {
        this.rubro3 = rubro3;
    }

    public String getRubro4() {
        return rubro4;
    }

    public void setRubro4(String rubro4) {
        this.rubro4 = rubro4;
    }

    public String getRubro5() {
        return rubro5;
    }

    public void setRubro5(String rubro5) {
        this.rubro5 = rubro5;
    }

    public String getRubro6() {
        return rubro6;
    }

    public void setRubro6(String rubro6) {
        this.rubro6 = rubro6;
    }

    public String getRubro7() {
        return rubro7;
    }

    public void setRubro7(String rubro7) {
        this.rubro7 = rubro7;
    }

    public String getRubro8() {
        return rubro8;
    }

    public void setRubro8(String rubro8) {
        this.rubro8 = rubro8;
    }

    public String getRubro9() {
        return rubro9;
    }

    public void setRubro9(String rubro9) {
        this.rubro9 = rubro9;
    }

    public String getRubro10() {
        return rubro10;
    }

    public void setRubro10(String rubro10) {
        this.rubro10 = rubro10;
    }

    public String getRubro11() {
        return rubro11;
    }

    public void setRubro11(String rubro11) {
        this.rubro11 = rubro11;
    }

    public String getRubro12() {
        return rubro12;
    }

    public void setRubro12(String rubro12) {
        this.rubro12 = rubro12;
    }

    public String getRubro13() {
        return rubro13;
    }

    public void setRubro13(String rubro13) {
        this.rubro13 = rubro13;
    }

    public String getRubro14() {
        return rubro14;
    }

    public void setRubro14(String rubro14) {
        this.rubro14 = rubro14;
    }

    public String getRubro15() {
        return rubro15;
    }

    public void setRubro15(String rubro15) {
        this.rubro15 = rubro15;
    }

    public Date getFechaNoPago() {
        return fechaNoPago;
    }

    public void setFechaNoPago(Date fechaNoPago) {
        this.fechaNoPago = fechaNoPago;
    }

    public String getTipoSalarioAumen() {
        return tipoSalarioAumen;
    }

    public void setTipoSalarioAumen(String tipoSalarioAumen) {
        this.tipoSalarioAumen = tipoSalarioAumen;
    }

    public Date getFechaAntigEmp() {
        return fechaAntigEmp;
    }

    public void setFechaAntigEmp(Date fechaAntigEmp) {
        this.fechaAntigEmp = fechaAntigEmp;
    }

    public Date getFechaAntigGob() {
        return fechaAntigGob;
    }

    public void setFechaAntigGob(Date fechaAntigGob) {
        this.fechaAntigGob = fechaAntigGob;
    }

    public BigDecimal getSalarioDiarioInt() {
        return salarioDiarioInt;
    }

    public void setSalarioDiarioInt(BigDecimal salarioDiarioInt) {
        this.salarioDiarioInt = salarioDiarioInt;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombrePila() {
        return nombrePila;
    }

    public void setNombrePila(String nombrePila) {
        this.nombrePila = nombrePila;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public BigDecimal getVacsAdicionales() {
        return vacsAdicionales;
    }

    public void setVacsAdicionales(BigDecimal vacsAdicionales) {
        this.vacsAdicionales = vacsAdicionales;
    }

    public String getRubro16() {
        return rubro16;
    }

    public void setRubro16(String rubro16) {
        this.rubro16 = rubro16;
    }

    public String getRubro17() {
        return rubro17;
    }

    public void setRubro17(String rubro17) {
        this.rubro17 = rubro17;
    }

    public String getRubro18() {
        return rubro18;
    }

    public void setRubro18(String rubro18) {
        this.rubro18 = rubro18;
    }

    public String getRubro19() {
        return rubro19;
    }

    public void setRubro19(String rubro19) {
        this.rubro19 = rubro19;
    }

    public String getRubro20() {
        return rubro20;
    }

    public void setRubro20(String rubro20) {
        this.rubro20 = rubro20;
    }

    public String getRubro21() {
        return rubro21;
    }

    public void setRubro21(String rubro21) {
        this.rubro21 = rubro21;
    }

    public String getRubro22() {
        return rubro22;
    }

    public void setRubro22(String rubro22) {
        this.rubro22 = rubro22;
    }

    public String getRubro23() {
        return rubro23;
    }

    public void setRubro23(String rubro23) {
        this.rubro23 = rubro23;
    }

    public String getRubro24() {
        return rubro24;
    }

    public void setRubro24(String rubro24) {
        this.rubro24 = rubro24;
    }

    public String getRubro25() {
        return rubro25;
    }

    public void setRubro25(String rubro25) {
        this.rubro25 = rubro25;
    }

    public BigDecimal getSalariopdLocal() {
        return salariopdLocal;
    }

    public void setSalariopdLocal(BigDecimal salariopdLocal) {
        this.salariopdLocal = salariopdLocal;
    }

    public BigDecimal getSalariopdDolar() {
        return salariopdDolar;
    }

    public void setSalariopdDolar(BigDecimal salariopdDolar) {
        this.salariopdDolar = salariopdDolar;
    }

    public BigDecimal getSalariopdNomina() {
        return salariopdNomina;
    }

    public void setSalariopdNomina(BigDecimal salariopdNomina) {
        this.salariopdNomina = salariopdNomina;
    }

    public BigDecimal getDiasDispIncap() {
        return diasDispIncap;
    }

    public void setDiasDispIncap(BigDecimal diasDispIncap) {
        this.diasDispIncap = diasDispIncap;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getUsuarioUltMod() {
        return usuarioUltMod;
    }

    public void setUsuarioUltMod(String usuarioUltMod) {
        this.usuarioUltMod = usuarioUltMod;
    }

    public Date getFchHoraUltMod() {
        return fchHoraUltMod;
    }

    public void setFchHoraUltMod(Date fchHoraUltMod) {
        this.fchHoraUltMod = fchHoraUltMod;
    }

    public String getCtaElectronica() {
        return ctaElectronica;
    }

    public void setCtaElectronica(String ctaElectronica) {
        this.ctaElectronica = ctaElectronica;
    }

    public String getDivisionGeografica1() {
        return divisionGeografica1;
    }

    public void setDivisionGeografica1(String divisionGeografica1) {
        this.divisionGeografica1 = divisionGeografica1;
    }

    public String getDivisionGeografica2() {
        return divisionGeografica2;
    }

    public void setDivisionGeografica2(String divisionGeografica2) {
        this.divisionGeografica2 = divisionGeografica2;
    }

    public String getTipoSalario() {
        return tipoSalario;
    }

    public void setTipoSalario(String tipoSalario) {
        this.tipoSalario = tipoSalario;
    }

    public String getBeneficioColectivo() {
        return beneficioColectivo;
    }

    public void setBeneficioColectivo(String beneficioColectivo) {
        this.beneficioColectivo = beneficioColectivo;
    }

    public Integer getTipoMedidasCertificadas() {
        return tipoMedidasCertificadas;
    }

    public void setTipoMedidasCertificadas(Integer tipoMedidasCertificadas) {
        this.tipoMedidasCertificadas = tipoMedidasCertificadas;
    }

    public Integer getTipoNivelEducativo() {
        return tipoNivelEducativo;
    }

    public void setTipoNivelEducativo(Integer tipoNivelEducativo) {
        this.tipoNivelEducativo = tipoNivelEducativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleado != null ? empleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empleado == null && other.empleado != null) || (this.empleado != null && !this.empleado.equals(other.empleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.empleado, this.nombre);
    }
    
}
