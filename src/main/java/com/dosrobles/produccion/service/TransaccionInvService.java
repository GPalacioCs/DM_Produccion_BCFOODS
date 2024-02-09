/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.TransaccionInvDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaEnvio;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaRecepcion;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.embarque.EmbarqueService;
import com.dosrobles.produccion.utils.Utils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Corpsoft S.A.
 */
@Stateless
public class TransaccionInvService extends AbstractService<TransaccionInvDAO, TransaccionInv> {

    @Inject
    private AuditTransInvService auditTransInvService;
    @Inject
    private TraspasoService traspasoService;
    @Inject
    private EmbarqueService embarqueService;
    @Inject
    private ParametrosPrService parametrosPrService;
    @Inject
    private ExistenciaBodegaService existenciaBodegaService;
    @Inject
    private CSOrdenProduccionService ordenProduccionService;
    @Inject
    private ConsumoMateriaService consumoMateriaService;
    @Inject
    private ExistenciaLoteService existenciaLoteService;
    @Inject
    private LoteService loteService;
    @Inject
    private ArticuloService articuloService;
    @Inject
    private IngresosLoteService ingresosLoteService;
    @Inject
    private CostoUepsPepsService costoUepsPepsService;

    public void aplicarTransaccionInvConsumo(CSOrdenProduccion ordenProduccion, List<Etapa> etapas, String usuario) throws BusinessValidationException {
        List<ConsumoMateria> consumosList = ordenProduccion.getConsumoMateriaList().stream()
                .filter(c -> etapas.contains(c.getMateriaPrima().getEtapa())
                        && c.getTransAplicada().equals("N")).collect(Collectors.toList());
        aplicarTransaccionInvConsumo(consumosList, ordenProduccion, usuario);
    }

    public void aplicarTransaccionInvConsumo(List<ConsumoMateria> consumosList, CSOrdenProduccion ordenProduccion, String usuario) throws BusinessValidationException {

        if (consumosList == null || consumosList.isEmpty()) {
            return;
        }
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("OP#" + ordenProduccion.getOrdenProduccion());
        audit.setReferencia("CLIENTE");
        AuditTransInv savedAudit = auditTransInvService.save(audit);
        dao.getEm().flush();
        int i = 1;

        for (ConsumoMateria consumo : consumosList) {
            TransaccionInv trans = new TransaccionInv(savedAudit.getAuditTransInv(), i++);
            trans.setArticulo1(consumo.getMateriaPrima().getArticuloHijo().getArticulo());
            trans.setFechaHoraTransac(new Date());//3
            trans.setNit("ND");//4
            trans.setAjusteConfig(new AjusteConfig("~CC~"));//5
            trans.setArticulo(consumo.getMateriaPrima().getArticuloHijo());//6
            if (consumo.getExistenciaBodega() != null) {
                trans.setBodega(consumo.getExistenciaBodega().getBodega().getBodega());//7
            } else {
                String articulo = consumo.getMateriaPrima().getArticuloHijo().getArticulo();
                String bodega = parametrosPrService.getParametro().getBodegaMp(false).getBodega();
                throw new BusinessValidationException(String.format("La materia prima %s no existe en la bodega %s", articulo, bodega));
            }
            trans.setTipo("C");//8
            trans.setSubtipo("D");//9
            trans.setSubsubtipo("N");//10            
            trans.setNaturaleza("S");//11
            ExistenciaBodega eb = existenciaBodegaService.find(consumo.getExistenciaBodega().getExistenciaBodegaPK());
            if (consumo.getCantidadConsumida().compareTo(eb.getCantDisponible()) <= 0) {
                trans.setCantidad(consumo.getCantidadConsumida());//12                
                eb.setCantDisponible(eb.getCantDisponible().subtract(consumo.getCantidadConsumida()));
            } else {
                throw new BusinessValidationException(String.format("La cantidad consumida excede la cantidad disponible en bodega (Materia Prima: %s)", consumo.getMateriaPrima().getMateriaPrimaPK().getArticuloHijo()));
            }
            trans.setCostoTotFiscLoc(consumo.getMateriaPrima().getArticuloHijo().getCostoPromLoc().multiply(consumo.getCantidadConsumida()));//13
            trans.setCostoTotFiscDol(consumo.getMateriaPrima().getArticuloHijo().getCostoPromDol().multiply(consumo.getCantidadConsumida()));//14
            trans.setCostoTotCompLoc(consumo.getMateriaPrima().getArticuloHijo().getCostoPromLoc().multiply(consumo.getCantidadConsumida()));//15
            trans.setCostoTotCompDol(consumo.getMateriaPrima().getArticuloHijo().getCostoPromDol().multiply(consumo.getCantidadConsumida()));//16
            trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
            trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
            trans.setContabilizada("S");//19
            trans.setFecha(new Date());//20            
            save(trans);
            if (consumo.getConsumoMateriaPK() != null && !"ND".equals(consumo.getConsumoMateriaPK().getArticuloPadre())) {
                ConsumoMateria consumodb = consumoMateriaService.find(consumo.getConsumoMateriaPK());
                consumodb.setTransAplicada("S");
                consumodb.setFechaAplicacion(new Date());
                consumo.setTransAplicada("");
            }
        }
    }

    public void aplicarTransaccionInvProduccion(CSOrdenProduccion ordenProduccion, String usuario) throws BusinessValidationException {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("OP#" + ordenProduccion.getOrdenProduccion());
        audit.setReferencia("CLIENTE");
        AuditTransInv savedAudit = auditTransInvService.insert(audit);
        dao.getEm().flush();
        int i = 1;

        TransaccionInv trans = new TransaccionInv(savedAudit.getAuditTransInv(), i++);
        trans.setArticulo1(ordenProduccion.getArticulo().getArticulo());
        trans.setFechaHoraTransac(new Date());//3
        trans.setNit("ND");//4
        trans.setAjusteConfig(new AjusteConfig("~PP~"));//5
        trans.setArticulo(ordenProduccion.getArticulo());//6
        if (ordenProduccion.getExistenciaBodega() != null) {
            trans.setBodega(ordenProduccion.getExistenciaBodega().getBodega().getBodega());//7
        } else {
            String articulo = ordenProduccion.getArticulo().getArticulo();
            String bodega = parametrosPrService.getParametro().getBodegaMp(false).getBodega();
            throw new BusinessValidationException(String.format("La materia prima %s no existe en la bodega %s", articulo, bodega));
        }
        trans.setTipo("P");//8
        trans.setSubtipo("D");//9
        trans.setSubsubtipo("");//10            
        trans.setNaturaleza("S");//11
        ExistenciaBodega eb = existenciaBodegaService.find(ordenProduccion.getExistenciaBodega().getExistenciaBodegaPK());

        trans.setCantidad(ordenProduccion.getCantidadProducida());//12                
        eb.setCantDisponible(eb.getCantDisponible().add(ordenProduccion.getCantidadProducida()));

        trans.setCostoTotFiscLoc(ordenProduccion.getCostoRealLoc());//13
        trans.setCostoTotFiscDol(ordenProduccion.getCostoRealDol());//14
        trans.setCostoTotCompLoc(ordenProduccion.getCostoRealLoc());//15
        trans.setCostoTotCompDol(ordenProduccion.getCostoRealDol());//16
        trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
        trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
        trans.setContabilizada("S");//19
        trans.setFecha(new Date());//20            
        insert(trans);
        CSOrdenProduccion ordendb = ordenProduccionService.find(ordenProduccion.getOrdenProduccion());
        ordendb.setTransAplicada("S");
        ordendb.setFechaAplicacion(new Date());
        ordenProduccion.setTransAplicada("S");
        ordenProduccion.setFechaAplicacion(ordendb.getFechaAplicacion());

    }

    public void aplicarTransaccionInvProduccionMasivo(CSOrdenProduccion ordenProduccion, List<PaqueteOrdenProduccion> paquetes, String usuario) throws BusinessValidationException {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("OP#" + ordenProduccion.getOrdenProduccion());
        audit.setReferencia("CLIENTE");
        AuditTransInv savedAudit = auditTransInvService.insert(audit);
        dao.getEm().flush();
        int i = 1;

        for (PaqueteOrdenProduccion pop : paquetes) {
            TransaccionInv trans = new TransaccionInv(savedAudit.getAuditTransInv(), i++);
            trans.setArticulo1(pop.getArticulo().getArticulo());
            trans.setFechaHoraTransac(new Date());//3
            trans.setNit("ND");//4
            trans.setAjusteConfig(new AjusteConfig("~PP~"));//5
            trans.setArticulo(pop.getArticulo());//6
            if (pop.getExistenciaBodega() != null) {
                trans.setBodega(pop.getExistenciaBodega().getBodega().getBodega());//7
            } else {
                String articulo = pop.getArticulo().getArticulo();
                String bodega = parametrosPrService.getParametro().getBodegaMp(false).getBodega();
                throw new BusinessValidationException(String.format("El producto %s no existe en la bodega %s", articulo, bodega));
            }
            trans.setTipo("P");//8
            trans.setSubtipo("D");//9
            trans.setSubsubtipo("");//10            
            trans.setNaturaleza("S");//11
            ExistenciaBodega eb = existenciaBodegaService.find(pop.getExistenciaBodega().getExistenciaBodegaPK());

            trans.setCantidad(pop.getCantidad());//12                
            eb.setCantDisponible(eb.getCantDisponible().add(pop.getCantidad()));

            trans.setCostoTotFiscLoc(pop.getArticulo().getCostoPromLoc().multiply(pop.getCantidad()));//13
            trans.setCostoTotFiscDol(pop.getArticulo().getCostoPromDol().multiply(pop.getCantidad()));//14
            trans.setCostoTotCompLoc(pop.getArticulo().getCostoPromLoc().multiply(pop.getCantidad()));//15
            trans.setCostoTotCompDol(pop.getArticulo().getCostoPromDol().multiply(pop.getCantidad()));//16
            trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
            trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
            trans.setContabilizada("N");//19
            trans.setFecha(new Date());//20            
            insert(trans);
        }
        CSOrdenProduccion ordendb = ordenProduccionService.find(ordenProduccion.getOrdenProduccion());
        ordendb.setTransAplicada("S");
        ordendb.setFechaAplicacion(new Date());
        ordenProduccion.setTransAplicada("S");
        ordenProduccion.setFechaAplicacion(ordendb.getFechaAplicacion());

    }

    public void enviarTraslado(Traspaso tras, String usuario) {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("TRA#" + tras.getN_Traspaso());
        audit.setReferencia("Envio# " + tras.getN_Traspaso());

        AuditTransInv savedAudit = auditTransInvService.save(audit);
        dao.getEm().flush();
        tras.setAuditTransInvEnvio(savedAudit.getAuditTransInv());
        traspasoService.save(tras);
        int consec = 1;
        for (TraspasoLineaEnvio el : tras.getLineasEnvio()) {
            ExistenciaBodega ebo = existenciaBodegaService.find(new ExistenciaBodegaPK(el.getArticulo().getArticulo(), tras.getBodegaOrigen().getBodega()));
            ExistenciaLote exlo = existenciaLoteService.find(new ExistenciaLotePK(tras.getBodegaOrigen().getBodega(), el.getArticulo().getArticulo(), "ND", el.getLote()));
            if (ebo == null) {
                throw new BusinessValidationException(String.format("El artículo %s no existe en la bodega %s", el.getArticulo().getArticulo(), tras.getBodegaOrigen().getBodega()));
            }
//            if (ebo.getCantDisponible().doubleValue() < el.getCantidad()){
//                throw new BusinessValidationException(String.format("El artículo %s no tiene suficientes existencias en la bodega %s", el.getArticulo().getArticulo(), tras.getBodegaOrigen().getBodega()));
//            }
            if (exlo == null) {
                throw new BusinessValidationException(String.format("El lote %s no tiene existencia para el artículo %s", el.getLote(), el.getArticulo().getArticulo()));
            }
//            if (exlo.getCantDisponible().doubleValue() < el.getCantidad()) {
//                throw new BusinessValidationException(String.format("El artículo %s no tiene suficientes existencias en la bodega %s para el lote %s", el.getArticulo().getArticulo(), tras.getBodegaOrigen().getBodega(), exlo.getLote().getLotePK().getLote()));
//            }

            BigDecimal costoMdLocal = articuloService.find(el.getArticulo().getArticulo(), "schema02").getCostoPromLoc();
            BigDecimal costoMdDolar = articuloService.find(el.getArticulo().getArticulo(), "schema02").getCostoPromDol();
            generarSalidaTraslado(exlo, BigDecimal.valueOf(el.getCantidad()), costoMdLocal, costoMdDolar, savedAudit, consec++, true);
        }
    }

    public void recibirTraslado(Traspaso tras, String usuario) {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("TRA#" + tras.getN_Traspaso());
        audit.setReferencia("Recepcion# " + tras.getN_Traspaso());

        AuditTransInv savedAudit = auditTransInvService.save(audit);
        dao.getEm().flush();
        tras.setAuditTransInvRecepcion(savedAudit.getAuditTransInv());
        traspasoService.save(tras);
        int consec = 1;
        for (TraspasoLineaRecepcion el : tras.getLineasRecepcion()) {
            ExistenciaBodega ebo = existenciaBodegaService.find(new ExistenciaBodegaPK(el.getArticulo().getArticulo(), tras.getBodegaDestino().getBodega()));
            ExistenciaLote exlo = existenciaLoteService.find(new ExistenciaLotePK(tras.getBodegaDestino().getBodega(), el.getArticulo().getArticulo(), "ND", el.getLote()));
            if (ebo == null) {
                throw new BusinessValidationException(String.format("El artículo %s no existe en la bodega %s", el.getArticulo().getArticulo(), tras.getBodegaDestino().getBodega()));
            }
            if (exlo == null) {
                exlo = new ExistenciaLote(new ExistenciaLotePK(tras.getBodegaDestino().getBodega(), el.getArticulo().getArticulo(), "ND", el.getLote()));
                exlo.setArticulo(el.getArticulo());
                existenciaLoteService.save(exlo);
            }

            generarEntradaEmbarque(exlo, BigDecimal.valueOf(el.getCantidad()), BigDecimal.valueOf(10), BigDecimal.valueOf(10), savedAudit, consec++, true);
        }
    }

    public void aplicarEmbarque(Embarque embarque, String usuario) throws BusinessValidationException {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("EM#" + embarque.getEmbarque());
        audit.setReferencia("Acopio# " + embarque.getEmbarque());

        AuditTransInv savedAudit = auditTransInvService.save(audit);
        dao.getEm().flush();
        embarque.setAuditTransInv(savedAudit.getAuditTransInv());
        embarqueService.save(embarque);
        int consec = 1;
        for (EmbarqueLinea el : embarque.getEmbarqueLineas()) {
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(el.getArticulo().getArticulo(), el.getBodega().getBodega()));
            ExistenciaLote exl = existenciaLoteService.find(new ExistenciaLotePK(el.getBodega().getBodega(), el.getArticulo().getArticulo(), "ND", el.getEmbarqueLineaDesgloses().get(0).getLote()));
            Lote lote = Lote.CreateNewLoteForAcopio(String.valueOf(Integer.parseInt(embarque.getEmbarque().substring(2))), embarque.getProveedor().getProveedor(), el.getArticulo(), embarque.getProveedor(), el.getCantidad_recibida(), el.getId().getEmbarque_Linea());
            loteService.save(lote);
            if (eb == null) {
                throw new BusinessValidationException(String.format("El articulo %s no está definido en la bodega %s",el.getArticulo().getArticulo(),el.getBodega().getBodega()));
            }
            if (exl == null) {
                exl = new ExistenciaLote(new ExistenciaLotePK(el.getBodega().getBodega(), el.getArticulo().getArticulo(), "ND", el.getEmbarqueLineaDesgloses().get(0).getLote()));
                exl.setArticulo(el.getArticulo());
            }
            generarEntradaEmbarque(exl, BigDecimal.valueOf(el.getCantidad_recibida()), BigDecimal.valueOf(el.getPrecio_unit_oc_local()), BigDecimal.valueOf(el.getPrecio_unit_oc_dolar()), savedAudit, consec++, true);
        }

    }

    public void aplicarTransaccionInv(OrdenProduccion orden, String bodegaMp, String bodegaMd, String bodegaPt, String usuario) throws BusinessValidationException {
        AuditTransInv audit = new AuditTransInv();
        audit.setUsuario(usuario);
        audit.setFechaHora(new Date());
        audit.setModuloOrigen("PR");
        audit.setAplicacion("OP#" + orden.getOrdenProduccion());
        audit.setReferencia("CLIENTE");
        AuditTransInv savedAudit = auditTransInvService.save(audit);
        dao.getEm().flush();
        int consec = 1;
        for (OrdenProdMp mp : orden.getOrdenProdMpList()) {
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(mp.getId().getArticulo(), bodegaMp));
            ExistenciaLote el = existenciaLoteService.find(new ExistenciaLotePK(bodegaMp, mp.getComponente().getArticulo(), "ND", mp.getLote()));
            if (eb == null) {
                throw new BusinessValidationException(String.format("El artículo %s no existe en la bodega %s", mp.getId().getArticulo(), bodegaMp));
            }
            if (el == null) {
                throw new BusinessValidationException(String.format("El lote %s no tiene existencia para el artículo %s", mp.getLote(), mp.getId().getArticulo()));
            }
            generarSalida(el, mp.getCantidad(), mp.getComponente().getCostoPromLoc(), mp.getComponente().getCostoPromDol(), savedAudit, consec++, false);
        }
        AuditTransInv audit2 = new AuditTransInv();
        audit2.setUsuario(usuario);
        audit2.setFechaHora(new Date());
        audit2.setModuloOrigen("PR");
        audit2.setAplicacion("OP#" + orden.getOrdenProduccion());
        audit2.setReferencia("CLIENTE");
        AuditTransInv savedAudit2 = auditTransInvService.save(audit2, "schema02");
        dao.getEm("schema02").flush();
        for (OrdenProdMd md : orden.getOrdenProdMdList()) {

            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(md.getId().getArticulo(), bodegaMd), "schema02");
            ExistenciaLote el = existenciaLoteService.find(new ExistenciaLotePK(bodegaMp, md.getComponente().getArticulo(), "ND", md.getLote()), "schema02");
            if (eb == null) {
                throw new BusinessValidationException(String.format("El artículo %s no existe en la bodega %s", md.getId().getArticulo(), bodegaMd));
            }

            if (el == null && "S".equals(md.getComponente().getUsaLotes())) {
                throw new BusinessValidationException(String.format("El lote %s no tiene existencia para el artículo %s", md.getLote(), md.getId().getArticulo()));
            }
            //si el articulo no usa lote crear un lote falso para ser usado como parametro
            if (el == null) {
                el = new ExistenciaLote(new ExistenciaLotePK(bodegaMp, md.getComponente().getArticulo(), "ND", md.getLote()));
                el.setArticulo(md.getComponente());
            }
            BigDecimal costoMdLocal = articuloService.find(md.getComponente().getArticulo(), "schema02").getCostoPromLoc();
            BigDecimal costoMdDolar = articuloService.find(md.getComponente().getArticulo(), "schema02").getCostoPromDol();
            generarSalida(el, md.getCantidad(), costoMdLocal, costoMdDolar, savedAudit2, consec++, true);
        }

//        ExistenciaBodega ebEntrada = existenciaBodegaService.find(new ExistenciaBodegaPK(orden.getArticulo().getArticulo(), bodegaPt));        
//        if(ebEntrada == null) {
//            throw new BusinessValidationException(String.format("El artículo %s no existe en la bodega %s", orden.getArticulo().getArticulo(), bodegaPt));
//        }


        for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {
            ExistenciaLotePK elpk = new ExistenciaLotePK(bodegaPt, ingreso.getArticulo().getArticulo(), "ND", ingreso.getLote());
            if (existenciaLoteService.find(elpk) != null) {
                throw new BusinessValidationException(String.format("El lote %s ya existe para el artículo %s", elpk.getLote(), elpk.getArticulo()));
            }
            Lote lote = new Lote(ingreso.getLote(), ingreso.getArticulo().getArticulo());
            lote.setCantidadIngresada(ingreso.getCantidad());
            lote.setFechaEntrada(new Date());
            LocalDateTime ldtVencimiento = null;
            if (!orden.isFresco()) {
                ldtVencimiento = Utils.date2ldt(orden.getFechaRequerida()).plusMonths(18);
            } else {
                ldtVencimiento = Utils.date2ldt(orden.getFechaRequerida()).plusDays(15);
            }
            lote.setFechaVencimiento(Utils.ldt2date(ldtVencimiento));
            lote.setTipoIngreso("T");

            ExistenciaLote el = new ExistenciaLote(elpk);
            el.setCantDisponible(ingreso.getCantidad());
            el.setArticulo(ingreso.getArticulo());
            IngresosLote il = new IngresosLote();
            il.setCantidadIngresada(ingreso.getCantidad());
            il.setLote(lote);
            il.setFechaEntrada(new Date());
            if ("S".equals(ingreso.getArticulo().getUsaLotes())) {
                loteService.insert(lote);
                existenciaLoteService.insert(el);
                ingresosLoteService.insert(il);
            }
            CostoUepsPeps peps = new CostoUepsPeps(ingreso.getArticulo().getArticulo(), new Date());
            peps.setCantidadOriginal(ingreso.getCantidad());
            peps.setCantidadRestante(ingreso.getCantidad());
            peps.setCostoLocal(ingreso.getCostoUnitarioLocal());
            peps.setCostoDolar(ingreso.getCostoUnitarioDolar());
            peps.setArticulo1(ingreso.getArticulo().getArticulo());
            costoUepsPepsService.insert(peps);
            generarEntrada(el, ingreso.getCantidad(), ingreso.getCostoLocal(), ingreso.getCostoDolar(), savedAudit, consec++, true);
        }

    }

    private void generarSalidaTraslado(ExistenciaLote existenciaLote, BigDecimal cantidad, BigDecimal costoLocal, BigDecimal costoDolar, AuditTransInv audit, int consec, boolean material) throws BusinessValidationException {
        String articulo = existenciaLote.getId().getArticulo();
        String bodega = existenciaLote.getId().getBodega();
        if (existenciaLote.getArticulo().getUsaLotes().equals("S") && existenciaLote.getCantDisponible().compareTo(cantidad) < 0) {
            throw new BusinessValidationException(String.format("La cantidad consumida excede la cantidad disponible en lote %s (Materia Prima: %s)", existenciaLote.getId().getLote(), existenciaLote.getId().getArticulo()));
        }
        TransaccionInv trans = new TransaccionInv(audit.getAuditTransInv(), consec);
        trans.setArticulo1(articulo);
        trans.setFechaHoraTransac(new Date());
        trans.setNit("ND");
        trans.setAjusteConfig(new AjusteConfig("~CC~"));
        trans.setArticulo(existenciaLote.getArticulo());
        trans.setBodega(bodega);
        trans.setTipo("C");//8
        trans.setSubtipo("D");//9
        trans.setSubsubtipo("N");//10
        trans.setNaturaleza("S");//11
        trans.setCantidad(cantidad);
        trans.setLote(existenciaLote.getId().getLote());
        trans.setLocalizacion("ND");
        existenciaLote.setCantDisponible(existenciaLote.getCantDisponible().subtract(cantidad));
        existenciaLote.setCantRemitida(existenciaLote.getCantRemitida().add(cantidad));
        ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo, bodega));


        if (eb.getCantDisponible().compareTo(cantidad) < 0) {
            throw new BusinessValidationException(String.format("La cantidad de consumo del artículo %s excede el disponible en la bodega %s", articulo, bodega));
        }
        eb.setCantDisponible(eb.getCantDisponible().subtract(cantidad));
        eb.setCantRemitida(eb.getCantRemitida().add(cantidad));
        trans.setCostoTotFiscLoc(costoLocal.multiply(cantidad));//13
        trans.setCostoTotFiscDol(costoDolar.multiply(cantidad));//14
        trans.setCostoTotCompLoc(costoLocal.multiply(cantidad));//15
        trans.setCostoTotCompDol(costoDolar.multiply(cantidad));//16
        trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
        trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
        trans.setContabilizada("S");//19
        trans.setFecha(new Date());//20
        trans.setBodega(bodega);
        insert(trans);
    }

    private void generarSalida(ExistenciaLote existenciaLote, BigDecimal cantidad, BigDecimal costoLocal, BigDecimal costoDolar, AuditTransInv audit, int consec, boolean material) throws BusinessValidationException {
        String articulo = existenciaLote.getId().getArticulo();
        String bodega = null;
        if (!material) {
            bodega = existenciaLote.getId().getBodega();
        } else {
            bodega = parametrosPrService.getParametro().getBodegaMd().getBodega();
        }
        if (existenciaLote.getArticulo().getUsaLotes().equals("S") && existenciaLote.getCantDisponible().compareTo(cantidad) < 0) {
            throw new BusinessValidationException(String.format("La cantidad consumida excede la cantidad disponible en lote %s (Materia Prima: %s)", existenciaLote.getId().getLote(), existenciaLote.getId().getArticulo()));
        }
        TransaccionInv trans = new TransaccionInv(audit.getAuditTransInv(), consec);
        trans.setArticulo1(articulo);
        trans.setFechaHoraTransac(new Date());
        trans.setNit("ND");
        trans.setAjusteConfig(new AjusteConfig("~CC~"));
        trans.setArticulo(existenciaLote.getArticulo());
        trans.setBodega(bodega);
        trans.setTipo("C");//8
        trans.setSubtipo("D");//9
        trans.setSubsubtipo("N");//10            
        trans.setNaturaleza("S");//11
        trans.setCantidad(cantidad);
        trans.setLote(existenciaLote.getId().getLote());
        trans.setLocalizacion("ND");
        existenciaLote.setCantDisponible(existenciaLote.getCantDisponible().subtract(cantidad));
        ExistenciaBodega eb;
        if (!material) {
            eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo, bodega));
        } else {
            eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo, bodega), "schema02");
        }


        if (eb.getCantDisponible().compareTo(cantidad) < 0) {
            throw new BusinessValidationException(String.format("La cantidad de consumo del artículo %s excede el disponible en la bodega %s", articulo, bodega));
        }
        eb.setCantDisponible(eb.getCantDisponible().subtract(cantidad));
        trans.setCostoTotFiscLoc(costoLocal.multiply(cantidad));//13
        trans.setCostoTotFiscDol(costoDolar.multiply(cantidad));//14
        trans.setCostoTotCompLoc(costoLocal.multiply(cantidad));//15
        trans.setCostoTotCompDol(costoDolar.multiply(cantidad));//16
        trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
        trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
        trans.setContabilizada("S");//19
        trans.setFecha(new Date());//20                 
        trans.setBodega(bodega);
        insert(trans);

    }

    private void generarEntrada(ExistenciaLote existenciaLote, BigDecimal cantidad, BigDecimal costoLocal, BigDecimal costoDolar, AuditTransInv audit, int consec, boolean afectarExistencia) throws BusinessValidationException {
        String articulo = existenciaLote.getId().getArticulo();
        String bodega = existenciaLote.getId().getBodega();
        boolean usaLotes = existenciaLote.getArticulo().getUsaLotes().equals("S");
        TransaccionInv trans = new TransaccionInv(audit.getAuditTransInv(), consec);
        trans.setArticulo1(articulo);
        trans.setNit("ND");
        trans.setAjusteConfig(new AjusteConfig("~PP~"));
        trans.setTipo("P");//8
        trans.setSubtipo("D");//9
        trans.setSubsubtipo("");//10            
        trans.setNaturaleza("E");//11
        trans.setArticulo(existenciaLote.getArticulo());
        trans.setCantidad(cantidad);

        if (afectarExistencia) {
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo, bodega));
            if (eb == null) {
                throw new BusinessValidationException(String.format("El artículo %s no está asociado a la bodega %s", articulo, bodega));
            }
            eb.setCantDisponible(eb.getCantDisponible().add(cantidad));
        }
        trans.setCostoTotFiscLoc(costoLocal);//13
        trans.setCostoTotCompLoc(costoLocal);//15
        trans.setCostoTotFiscDol(costoDolar);//14
        trans.setCostoTotCompDol(costoDolar);//16
        trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
        trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
        trans.setContabilizada("S");//19
        trans.setFecha(new Date());//20
        trans.setFechaHoraTransac(new Date());
        trans.setBodega(bodega);
        if (usaLotes) {
            trans.setLote(existenciaLote.getId().getLote());
            trans.setLocalizacion("ND");
        }
        trans.setSubtipo("N");
        insert(trans);


    }

    private void generarEntradaEmbarque(ExistenciaLote existenciaLote, BigDecimal cantidad, BigDecimal costoLocal, BigDecimal costoDolar, AuditTransInv audit, int consec, boolean afectarExistencia) throws BusinessValidationException {
        String articulo = existenciaLote.getId().getArticulo();
        String bodega = existenciaLote.getId().getBodega();
        boolean usaLotes = existenciaLote.getArticulo().getUsaLotes().equals("S");
        TransaccionInv trans = new TransaccionInv(audit.getAuditTransInv(), consec);
        trans.setArticulo1(articulo);
        trans.setNit("ND");
        trans.setAjusteConfig(new AjusteConfig("~OO~"));
        trans.setTipo("O");//8
        trans.setSubtipo("D");//9
        trans.setSubsubtipo("L");//10
        trans.setNaturaleza("E");//11
        trans.setArticulo(existenciaLote.getArticulo());
        trans.setCantidad(cantidad);

        if (afectarExistencia) {
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo, bodega));
            if (eb == null) {
                throw new BusinessValidationException(String.format("El artículo %s no está asociado a la bodega %s", articulo, bodega));
            }
            eb.setCantDisponible(eb.getCantDisponible().add(cantidad));
        }
        trans.setCostoTotFiscLoc(costoLocal);//13
        trans.setCostoTotCompLoc(costoLocal);//15
        trans.setCostoTotFiscDol(costoDolar);//14
        trans.setCostoTotCompDol(costoDolar);//16
        trans.setPrecioTotalLocal(BigDecimal.ZERO);//17
        trans.setPrecioTotalDolar(BigDecimal.ZERO);//18
        trans.setContabilizada("S");//19
        trans.setFecha(new Date());//20
        trans.setFechaHoraTransac(new Date());
        trans.setBodega(bodega);
        if (usaLotes) {
            trans.setLote(existenciaLote.getId().getLote());
            trans.setLocalizacion("ND");
            existenciaLote.setCantDisponible(cantidad);
            existenciaLoteService.save(existenciaLote);
        }
        trans.setSubtipo("N");
        insert(trans);
    }


    public void fixTransacciones(OrdenProduccion orden) {
        AuditTransInv audit = auditTransInvService.getAuditTransInvByAplicacion("OP#" + orden.getOrdenProduccion());
        String bodegaPt = parametrosPrService.getParametro().getBodegaPt().getBodega();
        List<TransaccionInv> transList = findByAudit(audit);
        int consec = transList.stream().mapToInt(t -> t.getTransaccionInvPK().getConsecutivo()).max().orElse(1) + 1;
        Optional<TransaccionInv> opt1 = transList.stream().filter(t -> bodegaPt.equals(t.getBodega())).findFirst();
        if (opt1.isPresent()) {
            consec = opt1.get().getTransaccionInvPK().getConsecutivo();
        }
        for (IngresoProduccion ingreso : orden.getIngresoProduccoinList()) {
            ExistenciaLotePK elpk = new ExistenciaLotePK(bodegaPt, ingreso.getArticulo().getArticulo(), "ND", ingreso.getLote());
//            if(existenciaLoteService.find(elpk) != null) {
//                throw new BusinessValidationException(String.format("El lote %s ya existe para el artículo %s", elpk.getLote(), elpk.getArticulo()));
//            }
            ExistenciaLote el = new ExistenciaLote(elpk);
            el.setCantDisponible(ingreso.getCantidad());
//            existenciaLoteService.insert(el);
            el.setArticulo(ingreso.getArticulo());
            generarEntrada(el, ingreso.getCantidad(), ingreso.getCostoLocal(), ingreso.getCostoDolar(), audit, consec++, false);
        }
    }

    public List<TransaccionInv> findByAudit(AuditTransInv audit) {
        return dao.findByAudit(audit);
    }
}
