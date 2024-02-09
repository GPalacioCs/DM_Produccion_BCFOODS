package com.dosrobles.produccion.entities.traslado;

import com.dosrobles.produccion.entities.Bodega;
import com.dosrobles.produccion.entities.Usuario;
import com.dosrobles.produccion.enums.EstadosTraslado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CS_TRASPASO")
public class Traspaso {
    @Id
    @Column(name = "ID_TRASPASO")
    private String idTraspaso;
    @Column(name = "N_TRASPASO")
    private String n_Traspaso;
    @ManyToOne
    @JoinColumn(name = "BODEGA_ORIGEN")
    private Bodega bodegaOrigen;
    @ManyToOne
    @JoinColumn(name = "BODEGA_DESTINO")
    private Bodega bodegaDestino;
    @Column(name = "FECHA_TRASPASO")
    private Date fechaTraspaso = new Date();
    @Column(name = "ESTADO")
    private String estado = "P";
    @ManyToOne
    @JoinColumn(name = "USUARIO_CREACION")
    private Usuario usuarioCreacion;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion = new Date();
    @ManyToOne
    @JoinColumn(name = "USUARIO_MODIFICACION")
    private Usuario usuarioModificacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @ManyToOne
    @JoinColumn(name = "USUARIO_APROBACION")
    private Usuario usuarioAprobacion;
    @Column(name = "FECHA_APROBACION")
    private Date fechaAprobacion;
    @OneToMany(mappedBy = "Traspaso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TraspasoLineaEnvio> LineasEnvio = new ArrayList<>();
    @OneToMany(mappedBy = "Traspaso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TraspasoLineaRecepcion> LineasRecepcion = new ArrayList<>();
    @Column(name = "AUDIT_TRANS_INV_ENVIO")
    private int AuditTransInvEnvio = 0;
    @Column(name = "AUDIT_TRANS_INV_RECEPCION")
    private int AuditTransInvRecepcion = 0;

    public EstadosTraslado getEstadoTraslado() {
        return EstadosTraslado.getEstadoTraslado(estado);
    }
}