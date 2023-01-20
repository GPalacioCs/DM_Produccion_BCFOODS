/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.entities;

import com.dosrobles.produccion.utils.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author pc
 */
@Entity
@Table(name="CS_INGRESO_PRODUCCION")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class IngresoProduccion {
    @EmbeddedId
    private IngresoProduccionPK id = new IngresoProduccionPK();
    @ManyToOne
    @MapsId(value="ordenProduccion")
    @JoinColumn(name="ORDEN_PRODUCCION")
    private OrdenProduccion ordenProduccion;
    @ManyToOne
    @MapsId("articulo")
    @JoinColumn(name="ARTICULO")
    private Articulo articulo;
    private BigDecimal cantidad_inicial = BigDecimal.ZERO;
    private BigDecimal cantidad = BigDecimal.ZERO;
    private String lote = "ND";
    @Column(name="FECHA_VENCIMIENTO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento = Utils.getDate(1980, 1, 1);

    public IngresoProduccion(IngresoProduccionPK id) {
        this.id = id;
    }    
    
//    public BigDecimal getCosto(boolean local) {
//        BigDecimal bd = BigDecimal.ZERO;
//        for (OrdenProdMp mp : ordenProduccion.getOrdenProdMpList()) {
//            if(mp.getProducto().equals(articulo)) {
//                BigDecimal costo = local ? mp.getComponente().getCostoPromLoc() : mp.getComponente().getCostoPromDol();
//                bd = bd.add(costo.multiply(mp.getCantidad()).multiply(getCantidad()));
//            }
//        }
//        for (OrdenProdMd md : ordenProduccion.getOrdenProdMdList()) {
//            if(md.getProducto().equals(articulo)) {
//                BigDecimal costo = local ? md.getComponente().getCostoPromLoc() : md.getComponente().getCostoPromDol();
//                bd = bd.add(costo.multiply(md.getCantidad()).multiply(getCantidad()));
//            }
//        }
//        
//        return bd;
//    }
    
    public BigDecimal getCosto(Function<Maquila, BigDecimal> funcMaquila, Function<OrdenProdMp, BigDecimal> funcMp, Function<OrdenProdRubro, BigDecimal> funcRubro, Function<OrdenProdMd, BigDecimal> funcMd) {
        boolean fresco = getOrdenProduccion().isFresco();
        BigDecimal totalMaquila = ordenProduccion.getMaquilaList().stream()
                .filter(maquila -> maquila.getArticulo().getArticulo().equals(this.getArticulo().getArticulo()))
                .map(funcMaquila).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalMp = BigDecimal.ZERO;
        BigDecimal totalRubros = Utils.sumBigDecimals(getOrdenProduccion().getOrdenProdRubroList(), funcRubro);
        BigDecimal totalMaterialesDirectos = Utils.sumBigDecimals(getOrdenProduccion().getOrdenProdMdList(), funcMd);
        if (fresco) {
            MateriaPrima mp2 = getMateriaPrima();
            if (mp2 != null) {

                BigDecimal totalProduccion = Utils.sumBigDecimals(getOrdenProduccion().getIngresoProduccionByMp(mp2), ip->ip.getCantidad()); //total libras producidas
                totalMp = ordenProduccion.getOrdenProdMpList().stream()
                        .filter(mp -> Objects.equals(mp2.getArticuloHijo(), mp.getComponente()))
                        .map(funcMp).reduce(BigDecimal.ZERO, BigDecimal::add); //


                totalMp = totalMp.divide(totalProduccion, 16, RoundingMode.HALF_EVEN).multiply(getCantidad());
            }
            
        } else {
            totalMaquila = Utils.sumBigDecimals(getOrdenProduccion().getMaquilaList(), funcMaquila);
            BigDecimal totalProduccion = Utils.sumBigDecimals(getOrdenProduccion().getIngresoProduccoinList(), ip -> ip.getCantidad());
            BigDecimal totalCantMateriaPrima = Utils.sumBigDecimals(getOrdenProduccion().getOrdenProdMpList(), mp -> mp.getCantidad());
            BigDecimal costoTotalMateriaPrima = Utils.sumBigDecimals(getOrdenProduccion().getOrdenProdMpList(), funcMp);
            totalMp = costoTotalMateriaPrima.add(totalMaquila);
            BigDecimal totalCostos = totalMp.add(totalRubros).add(totalMaterialesDirectos);
            return totalCostos.divide(totalProduccion,4,RoundingMode.HALF_EVEN).multiply(getCantidad());
        }
        return (totalMp.add(totalMaquila));
    }
    
    public BigDecimal getCostoLocal() {
        return getCosto(Maquila::getMonto, mp-> mp.getComponente().getCostoPromLoc().multiply(mp.getCantidad()), r -> r.getCantidad().multiply(r.getCosto()), md -> md.getCantidad().multiply(md.getCosto()));
    }
    
    public BigDecimal getCostoDolar() {
        return getCosto(Maquila::getMontoDolar, mp-> mp.getComponente().getCostoPromDol().multiply(mp.getCantidad()), r-> r.getCantidad().multiply(r.getCostoDolar()!=null ? r.getCostoDolar() : BigDecimal.ZERO), md -> md.getCantidad().multiply(md.getCostoDolar()));
    }
    
    public BigDecimal getCostoUnitarioLocal() {
        return getCostoLocal().divide(this.getCantidad(), 2, RoundingMode.HALF_EVEN);
    }
    
    public BigDecimal getCostoUnitarioDolar() {
        return getCostoDolar().divide(this.getCantidad(), 2, RoundingMode.HALF_EVEN);
    }
    
    public MateriaPrima getMateriaPrima() {
        return getArticulo().getMateriaPrima();
    }

    public OrdenProdMp getMateriaPrimaProduccion() {
        if(getMateriaPrima() == null) return null;
        return ordenProduccion.getOrdenProdMpList().stream()
                .filter(mp -> mp.getComponente().getArticulo().equals(getMateriaPrima().getArticuloHijo().getArticulo()))
                .findFirst().orElse(null);
    }

    public Maquila getMaquila() {
        return getOrdenProduccion().getMaquilaList().stream()
                .filter(maquila -> maquila.getArticulo().getArticulo().equals(getArticulo().getArticulo()))
                .findFirst().orElse(null);
    }

    public BigDecimal getCantMaterialReceta(Articulo articulo) {
        MateriaPrima materiaPrima = getMateriaPrima(articulo);
        if(materiaPrima == null) return BigDecimal.ZERO;
        return materiaPrima.getCantidad().multiply(getCantidad()).divide(getArticulo().getCantidadProduccion(),4,RoundingMode.HALF_EVEN);
    }

    public MateriaPrima getMateriaPrima(Articulo articulo) {
        return getArticulo().getMateriaPrimaByArticulo(articulo);
    }
}
