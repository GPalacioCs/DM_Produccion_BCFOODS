/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.AsientoDeDiarioDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.utils.Utils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author pc
 */
@Stateless
public class AsientoDeDiarioService extends AbstractService<AsientoDeDiarioDAO, AsientoDeDiario> {
    
    @Inject
    ParametrosPrService parametrosPrService;
    @Inject
    TipoCambioHistService tipoCambioHistService;
    @Inject
    PaqueteService paqueteService;
    @Inject
    ArticuloService articuloService;    
    
    public void generarAsientoProduccion(OrdenProduccion orden, String usuario) {
        
        BigDecimal tasaCambio = tipoCambioHistService.getTipoCambioActual();
        
//        orden.getMaquilaList().forEach(m -> m.setPrecio(Utils.dol2loc(m.getPrecioDolar(), tasaCambio)));
        
        ParametrosPr parametro = parametrosPrService.getParametro();
        
        Date fechaAsiento = orden.getFechaLiberacion() == null ? new Date() : orden.getFechaLiberacion();
        String referencia = new SimpleDateFormat("yyyyMMdd").format(fechaAsiento)+"/"+orden.getOrdenProduccion()+"/"+(orden.isFresco() ? "Fresco" : "Congelado");
        List<Diario> ingresoProdDiarioList = new ArrayList<>();        
        Paquete paquete = parametro.getPaquete();
        String consecAsiento = Utils.generarConsecutivo(paquete.getUltimoAsiento());
        AsientoDeDiario asiento = new AsientoDeDiario(consecAsiento);
        asiento.setFechaCreacion(new Date());
        asiento.setFechaUltModif(new Date());
        orden.setAsientoAlinsa(consecAsiento);
        asiento.setPaquete(paquete);
        asiento.setTipoAsiento(new TipoAsiento(paquete.getPaquete()));
        paquete.setUltimoAsiento(consecAsiento);
        
        asiento.setFecha(fechaAsiento);
        asiento.setOrigen(paquete.getPaquete());
        String ccBcfoods = parametro.getCentroCif();
        if (ccBcfoods == null) {
            ccBcfoods = "02.01.00";
        }
        
        String ccBcfoodsDebito = parametro.getCentroCifDebito();
        if(ccBcfoodsDebito == null) {
            ccBcfoodsDebito = ccBcfoods;
        }
        
        BigDecimal tasa = tipoCambioHistService.getTipoCambioActual(fechaAsiento);
        
        int consec = 1;
        Diario diario1 = null;
//        for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {            
//            Diario diario = new Diario(asiento.getAsiento(), consec++);
//            diario.setAsiento(asiento);
//            diario.setCuentaContable(new CuentaContable("1.1.3.03.00.000"));
//            diario.setCentroCosto(new CentroCosto("00.00.00"));
//            diario.setReferencia(referencia);
//            diario.setFuente(paquete.getPaquete());
//            diario.setDebitoLocal(ingreso.getCostoLocal());
//            diario.setDebitoDolar(ingreso.getCostoDolar());
//            asiento.getDiarios().add(diario);
            
//        }

        BigDecimal totalProduccion = Utils.sumBigDecimals(orden.getIngresoProduccoinList(), ip -> ip.getCostoLocal());

        for (IngresoProduccion ip : orden.getIngresoProduccoinList()) {
            Diario diario = new Diario(asiento.getAsiento(), consec++);
            diario.setAsiento(asiento);
            diario.setCuentaContable(new CuentaContable("1.1.3.03.00.000"));
            diario.setCentroCosto(new CentroCosto("00.00.00"));
            diario.setReferencia(referencia);
            diario.setFuente(paquete.getPaquete());
            diario.setDebitoLocal(ip.getCostoLocal());
            diario.setDebitoDolar(Utils.loc2dol(diario.getDebitoLocal(), tasaCambio));

            Diario diario2 = new Diario(asiento.getAsiento(), consec++);
            diario2.setAsiento(asiento);
            diario2.setCuentaContable(new CuentaContable("1.1.3.02.00.000"));
            diario2.setCentroCosto(new CentroCosto("00.00.00"));
            diario2.setReferencia(referencia);
            diario2.setFuente(paquete.getPaquete());
            diario2.setCreditoLocal(ip.getCostoLocal());
            diario2.setCreditoDolar(Utils.loc2dol(diario2.getCreditoLocal(), tasaCambio));

            asiento.getDiarios().add(diario);
            asiento.getDiarios().add(diario2);
        }

        for (OrdenProdMp mp : orden.getOrdenProdMpList()) {
            Diario diario = new Diario(asiento.getAsiento(), consec++);
            diario.setAsiento(asiento);
            diario.setCuentaContable(new CuentaContable("1.1.3.03.00.000"));
            diario.setCentroCosto(new CentroCosto("00.00.00"));
            diario.setReferencia(referencia);
            diario.setFuente(paquete.getPaquete());
//            diario.setDebitoLocal(mp.getComponente().getCostoPromLoc().multiply(mp.getCantidad()));
            diario.setDebitoLocal(totalProduccion);
            diario.setDebitoDolar(Utils.loc2dol(diario.getDebitoLocal(), tasaCambio));
//            asiento.getDiarios().add(diario);
        }


        
        for (OrdenProdMp mp : orden.getOrdenProdMpList()) {
            Diario diario = new Diario(asiento.getAsiento(), consec++);
            diario.setAsiento(asiento);
            diario.setCuentaContable(new CuentaContable("1.1.3.02.00.000"));
            diario.setCentroCosto(new CentroCosto("00.00.00"));
            diario.setReferencia(referencia);
            diario.setFuente(paquete.getPaquete());
//            diario.setCreditoLocal(mp.getComponente().getCostoPromLoc().multiply(mp.getCantidad()));
            diario.setCreditoLocal(totalProduccion);
//            diario.setCreditoDolar(mp.getComponente().getCostoPromDol().multiply(mp.getCantidad()));
            diario.setCreditoDolar(Utils.loc2dol(diario.getCreditoLocal(), tasaCambio));
//            asiento.getDiarios().add(diario);
        }
        
        for(Maquila maquila : orden.getMaquilaList()) {
            Diario diario = new Diario(asiento.getAsiento(), consec++);
            diario.setAsiento(asiento);
            diario.setCuentaContable(new CuentaContable("9.8.0.00.00.000"));
            diario.setCentroCosto(new CentroCosto("00.00.00"));
            diario.setReferencia(referencia);
            diario.setFuente(paquete.getPaquete());
            diario.setCreditoLocal(maquila.getMonto());
//            diario.setCreditoDolar(maquila.getMontoDolar());
            diario.setCreditoDolar(Utils.loc2dol(diario.getCreditoLocal(), tasaCambio));
            asiento.getDiarios().add(diario);
        }
        
        for (OrdenProdMp mp : orden.getOrdenProdMpList()) {
            Diario diario = new Diario(asiento.getAsiento(), consec++);
            diario.setAsiento(asiento);
            diario.setCuentaContable(new CuentaContable("1.1.3.02.00.000"));
            diario.setCentroCosto(new CentroCosto("00.00.00"));
            diario.setReferencia(referencia);
            diario.setFuente(paquete.getPaquete());
            diario.setDebitoLocal(mp.getComponente().getCostoPromLoc().multiply(mp.getCantidad()));
            diario.setDebitoDolar(Utils.loc2dol(diario.getDebitoLocal(), tasaCambio));
            asiento.getDiarios().add(diario);
            Diario diario2 = new Diario(asiento.getAsiento(), consec++);
            diario2.setAsiento(asiento);
            diario2.setCuentaContable(new CuentaContable("1.1.3.01.00.000"));
            diario2.setCentroCosto(new CentroCosto("00.00.00"));
            diario2.setReferencia(referencia);
            diario2.setFuente(paquete.getPaquete());
            diario2.setCreditoLocal(mp.getComponente().getCostoPromLoc().multiply(mp.getCantidad()));
//            diario2.setCreditoDolar(mp.getComponente().getCostoPromDol().multiply(mp.getCantidad()));
            diario2.setCreditoDolar(Utils.loc2dol(diario2.getCreditoLocal(), tasaCambio));
            asiento.getDiarios().add(diario2);
        }
        
        Paquete paqueteBcf = paqueteService.find(paquete.getPaquete(), "schema02");
        String consecAsientoBcf = Utils.generarConsecutivo(paqueteBcf.getUltimoAsiento());
        AsientoDeDiario asientoBcf = new AsientoDeDiario(consecAsientoBcf);
        orden.setAsientoBcfoods(consecAsientoBcf);
        paqueteBcf.setUltimoAsiento(consecAsientoBcf);
        asientoBcf.setPaquete(paqueteBcf);
        asientoBcf.setTipoAsiento(new TipoAsiento(paqueteBcf.getPaquete()));
        asientoBcf.setFecha(fechaAsiento);        
        asientoBcf.setOrigen(paqueteBcf.getPaquete());
        asientoBcf.setFechaCreacion(new Date());
        asientoBcf.setFechaUltModif(new Date());
        
        int consecBcf = 1;
        
//        for(Maquila maquila : orden.getMaquilaList()) {
//            Diario diario = new Diario(asientoBcf.getAsiento(), consecBcf++);
//            diario.setAsiento(asientoBcf);
//            diario.setCuentaContable(new CuentaContable("1.1.2.01.00.000"));
//            diario.setCentroCosto(new CentroCosto("00.00.00"));
//            diario.setReferencia(referencia);
//            diario.setFuente(paquete.getPaquete());
//            diario.setDebitoLocal(maquila.getMonto());
//            diario.setDebitoDolar(maquila.getMontoDolar());
//            asientoBcf.getDiarios().add(diario);
//            Diario diario2 = new Diario(asientoBcf.getAsiento(), consecBcf++);
//            diario2.setAsiento(asientoBcf);
//            diario2.setCuentaContable(new CuentaContable("4.2.1.01.00.000"));
//            diario2.setCentroCosto(new CentroCosto("00.00.00"));
//            diario2.setReferencia(referencia);
//            diario2.setFuente(paquete.getPaquete());
//            diario2.setCreditoLocal(maquila.getMonto());
//            diario2.setCreditoDolar(maquila.getMontoDolar());
//            asientoBcf.getDiarios().add(diario2);
//        }
        
        BigDecimal totalCifLocal = Utils.sumBigDecimals(orden.getOrdenProdRubroList(), cif -> cif.getCantidad().multiply(cif.getCosto()));
//        BigDecimal totalCifDolar = Utils.sumBigDecimals(orden.getOrdenProdRubroList(), cif -> cif.getCantidad().multiply(cif.getCostoDolar()));
        BigDecimal totalCifDolar = Utils.loc2dol(totalCifLocal, tasaCambio);
        
        BigDecimal totalMdLocal = Utils.sumBigDecimals(orden.getOrdenProdMdList(), md -> md.getCantidad().multiply(articuloService.find(md.getComponente().getArticulo(), "schema02").getCostoPromLoc()));
//        BigDecimal totalMdDolar = Utils.sumBigDecimals(orden.getOrdenProdMdList(), md -> md.getCantidad().multiply(articuloService.find(md.getComponente().getArticulo(), "schema02").getCostoPromDol()));        
        BigDecimal totalMdDolar = Utils.loc2dol(totalMdLocal, tasaCambio);
        
        BigDecimal costoMaquilaLocal = totalCifLocal.add(totalMdLocal);
//        BigDecimal costoMaquilaDolar = totalCifDolar.add(totalMdDolar);
        BigDecimal costoMaquilaDolar = totalCifDolar.add(Utils.loc2dol(costoMaquilaLocal, tasaCambio));
        
        String cuentaCifDebito = parametro.getCuentaCifDebito();
        if(cuentaCifDebito == null) {
            cuentaCifDebito = "5.2.1.01.00.000";
        }
        
        Diario diarioCostoMaquila = new Diario(asientoBcf.getAsiento(), consecBcf++);
        diarioCostoMaquila.setAsiento(asientoBcf);
        diarioCostoMaquila.setCuentaContable(new CuentaContable(cuentaCifDebito));
        diarioCostoMaquila.setCentroCosto(new CentroCosto(ccBcfoodsDebito));
        diarioCostoMaquila.setReferencia(referencia);
        diarioCostoMaquila.setFuente(paquete.getPaquete());
//        diarioCostoMaquila.setDebitoLocal(costoMaquilaLocal);
//        diarioCostoMaquila.setDebitoDolar(costoMaquilaDolar);
        diarioCostoMaquila.setDebitoLocal(totalCifLocal);
        diarioCostoMaquila.setDebitoDolar(totalCifDolar);
        asientoBcf.getDiarios().add(diarioCostoMaquila);

        Diario diarioCif = new Diario(asientoBcf.getAsiento(), consecBcf++);
        diarioCif.setAsiento(asientoBcf);
//        diarioCif.setCuentaContable(new CuentaContable("5.6.1.CT.04.000"));
        String cuentaCif = parametro.getCuentaCif();
        if(cuentaCif == null) {
            cuentaCif = "5.6.1.CT.04.000";
        }
        diarioCif.setCuentaContable(new CuentaContable(cuentaCif));
        diarioCif.setCentroCosto(new CentroCosto(ccBcfoods));
        diarioCif.setReferencia(referencia);
        diarioCif.setFuente(paquete.getPaquete());
        diarioCif.setCreditoLocal(totalCifLocal);
        diarioCif.setCreditoDolar(totalCifDolar);
        asientoBcf.getDiarios().add(diarioCif);
        
        Bodega bodegaMd = parametro.getBodegaMd();        
        for (OrdenProdMd md : orden.getOrdenProdMdList()) {
            Articulo articuloBcf = articuloService.find(md.getComponente().getArticulo(), "schema02");
            if(articuloBcf.getArticuloCuenta() == null 
                    || articuloBcf.getArticuloCuenta().getArticuloCuenta().equals("ND")) {
                continue;
            }
            String cuentaDebito = articuloBcf.getArticuloCuenta().getCtaCompraImp();
            Diario diarioMaterialEmpaque = new Diario(asientoBcf.getAsiento(), consecBcf++);
            diarioMaterialEmpaque.setAsiento(asientoBcf);
            String cuentaMd = md.getComponente().getArticuloCuenta().getCtaInventario().getCuenta();
            String centroMd = md.getComponente().getArticuloCuenta().getCtrInventario().getCentroCosto();
            diarioMaterialEmpaque.setCuentaContable(new CuentaContable(cuentaMd));
            diarioMaterialEmpaque.setCentroCosto(new CentroCosto(centroMd));
            diarioMaterialEmpaque.setReferencia(referencia);
            diarioMaterialEmpaque.setFuente(paquete.getPaquete());
            diarioMaterialEmpaque.setCreditoLocal(md.getCantidad().multiply(articuloBcf.getCostoPromLoc()));
            diarioMaterialEmpaque.setCreditoDolar(Utils.loc2dol(diarioMaterialEmpaque.getCreditoLocal(), tasaCambio));            
            asientoBcf.getDiarios().add(diarioMaterialEmpaque);
            
            String cuentaConsumo = articuloBcf.getArticuloCuenta().getCtaConsNormal();
            String centroConsumo = articuloBcf.getArticuloCuenta().getCtrConsNormal();
            if (cuentaConsumo != null) {
                Diario diarioConsumo = new Diario(asientoBcf.getAsiento(), consecBcf++);
                diarioConsumo.setCuentaContable(new CuentaContable(cuentaConsumo));
                diarioConsumo.setCentroCosto(new CentroCosto(centroConsumo));
                diarioConsumo.setReferencia(referencia);
                diarioConsumo.setFuente(paqueteBcf.getPaquete());
                diarioConsumo.setDebitoLocal(md.getCantidad().multiply(articuloBcf.getCostoPromLoc()));
//                diarioConsumo.setDebitoDolar(md.getCantidad().multiply(articuloBcf.getCostoPromDol()));
                diarioConsumo.setDebitoDolar(Utils.loc2dol(diarioConsumo.getDebitoLocal(), tasaCambio));
                asientoBcf.getDiarios().add(diarioConsumo);
            }            
        }
        
//        Diario diarioMaterialEmpaque = new Diario(asientoBcf.getAsiento(), consecBcf++);
//        diarioMaterialEmpaque.setAsiento(asientoBcf);
//        diarioMaterialEmpaque.setCuentaContable(new CuentaContable("5.6.1.CT.04.000"));
//        diarioMaterialEmpaque.setCentroCosto(new CentroCosto(ccBcfoods));
//        diarioMaterialEmpaque.setReferencia(referencia);
//        diarioMaterialEmpaque.setFuente(paquete.getPaquete());
//        diarioMaterialEmpaque.setCreditoLocal(totalMdLocal);
//        diarioMaterialEmpaque.setCreditoDolar(totalMdDolar);
//        asientoBcf.getDiarios().add(diarioMaterialEmpaque);
        
        totalizarAsiento(asiento);
        totalizarAsiento(asientoBcf);
        asiento.setTotalDebitoLoc(Utils.sumBigDecimals(asiento.getDiarios(), d -> d.getDebitoLocal()));
        asiento.setTotalDebitoDol(Utils.sumBigDecimals(asiento.getDiarios(), d -> d.getDebitoDolar()));
        asiento.setTotalCreditoLoc(Utils.sumBigDecimals(asiento.getDiarios(), d -> d.getCreditoLocal()));
        asiento.setTotalCreditoDol(Utils.sumBigDecimals(asiento.getDiarios(), d -> d.getCreditoDolar()));
        asiento.setUsuarioCreacion(usuario);
        asiento.setUltimoUsuario(usuario);
        asiento.setNotas(referencia);
        
        asientoBcf.setTotalDebitoLoc(Utils.sumBigDecimals(asientoBcf.getDiarios(), d -> d.getDebitoLocal()));
        asientoBcf.setTotalDebitoDol(Utils.sumBigDecimals(asientoBcf.getDiarios(), d -> d.getDebitoDolar()));
        asientoBcf.setTotalCreditoLoc(Utils.sumBigDecimals(asientoBcf.getDiarios(), d -> d.getCreditoLocal()));
        asientoBcf.setTotalCreditoDol(Utils.sumBigDecimals(asientoBcf.getDiarios(), d -> d.getCreditoDolar()));
        asientoBcf.setUsuarioCreacion(usuario);
        asientoBcf.setUltimoUsuario(usuario);
        asientoBcf.setNotas(referencia);
        asiento.getDiarios().removeIf(d -> d.getDebitoLocal() != null && d.getDebitoLocal().compareTo(BigDecimal.ZERO) == 0);
        asiento.getDiarios().removeIf(d -> d.getCreditoLocal() != null && d.getCreditoLocal().compareTo(BigDecimal.ZERO) == 0);
        asientoBcf.getDiarios().removeIf(d -> d.getDebitoLocal() != null && d.getDebitoLocal().compareTo(BigDecimal.ZERO) == 0);
        asientoBcf.getDiarios().removeIf(d -> d.getCreditoLocal() != null && d.getCreditoLocal().compareTo(BigDecimal.ZERO) == 0);
        
        insert(asiento);
        insert(asientoBcf, "schema02");
    }
    
    void totalizarAsiento(AsientoDeDiario asiento) {        
        
        Map<CuentaContable, Diario> debitosMap = new HashMap<>();
        Map<CuentaContable, Diario> creditosMap = new HashMap<>();
        
        
        for (Diario diario : asiento.getDiarios()) {
            if(diario.getDebitoLocal() != null) {
                if(debitosMap.containsKey(diario.getCuentaContable())) {
                    BigDecimal debitoLocal = debitosMap.get(diario.getCuentaContable()).getDebitoLocal();
                    BigDecimal debitoDolar = debitosMap.get(diario.getCuentaContable()).getDebitoDolar();
                    diario.setDebitoLocal(debitoLocal.add(diario.getDebitoLocal()));
                    diario.setDebitoDolar(debitoDolar.add(diario.getDebitoDolar()));                    
                    debitosMap.put(diario.getCuentaContable(), diario);
                } else {
                    debitosMap.put(diario.getCuentaContable(), diario);
                }
            } else {
                if (creditosMap.containsKey(diario.getCuentaContable())) {
                    BigDecimal creditoLocal = creditosMap.get(diario.getCuentaContable()).getCreditoLocal();
                    BigDecimal creditoDolar = creditosMap.get(diario.getCuentaContable()).getCreditoDolar();
                    diario.setCreditoLocal(creditoLocal.add(diario.getCreditoLocal()));
                    diario.setCreditoDolar(creditoDolar.add(diario.getCreditoDolar()));
                    creditosMap.put(diario.getCuentaContable(), diario);                    
                } else {
                    creditosMap.put(diario.getCuentaContable(), diario);
                }
            }
        }
        asiento.getDiarios().clear();
        asiento.getDiarios().addAll(debitosMap.values());
        asiento.getDiarios().addAll(creditosMap.values());
    }
}
