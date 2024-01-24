package com.dosrobles.produccion.entities.embarque;

import com.dosrobles.produccion.entities.Proveedor;
import com.dosrobles.produccion.jpaconverters.EstadoEmbarqueConverter;
import com.dosrobles.produccion.jpaconverters.SiNoConverter;
import com.dosrobles.produccion.jpaconverters.SiNoStringConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "EMBARQUE")
public class Embarque {
    @Id
    private String Embarque;
    @ManyToOne
    @JoinColumn(name = "PROVEEDOR")
    private Proveedor Proveedor;
    private Date Fecha_Embarque;
    private Date Fecha_Requerida;
    @Convert(converter = EstadoEmbarqueConverter.class)
    private String Estado = "Planeado";
    @Convert(converter = SiNoStringConverter.class)
    private String Liquidado = "NO";
    private String Referencia = "";
    private String asiento_recibo = "N";
    private String asiento_liquidacio = "N";
    private String notas = "";
    private String tiene_factura = "N";
    private Date fecha_hora_creado = new Date();
    private String usuario_creado = "ERPADMIN";
    private String recmas_afectaback = "N";
    private String demas_sepaga = "N";
    private String recibido_de_mas = "N";
    private String multimoneda= "S";
    private Date fecha_crm = new Date();
    private String Enviado = "N";
    @Column(name = "AUDIT_TRANS_INV")
    private int AuditTransInv = 0;

    @OrderBy("Id.Embarque_Linea")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "EMBARQUE",name = "EMBARQUE")
    private List<EmbarqueLinea> embarqueLineas = new ArrayList<>();
}
