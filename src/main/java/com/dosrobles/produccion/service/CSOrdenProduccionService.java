/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.CSOrdenProduccionDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.MontoLocalDolar;
import com.dosrobles.produccion.utils.Utils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class CSOrdenProduccionService extends AbstractService<CSOrdenProduccionDAO, CSOrdenProduccion> {

    @Inject
    private ConsumoMateriaService consumoMateriaService;
    @Inject
    private TransaccionInvService transaccionInvService;
    @Inject
    private ExistenciaBodegaService existenciaBodegaService;

    @Override
    public CSOrdenProduccion insert(CSOrdenProduccion entity) throws BusinessValidationException {

        if (find(entity.getOrdenProduccion()) != null) {
            throw new BusinessValidationException("Ya existe una orden con el mismo código");
        }

        return super.insert(entity);
    }

    @Override
    public CSOrdenProduccion save(CSOrdenProduccion entity) throws BusinessValidationException {
//        if(entity.getPedidoOrdenProduccionList() == null || entity.getPedidoOrdenProduccionList().isEmpty()) {
//            throw new BusinessValidationException("Debe seleccionar al menos un pedido");
//        }
        for (PedidoOrdenProduccion pedido : entity.getPedidoOrdenProduccionList()) {
            pedido.getPedidoOrdenProduccionPK().setOrdenProduccion(entity.getOrdenProduccion());
        }

        return super.save(entity);
    }
    
    public List<CSOrdenProduccion> getOrdenesProduccion(boolean masivo) {
        Map<String, Object> filters = new HashMap<>();
        filters.put("masivo", masivo);
        return dao.getOrdenesProduccion(filters);
    }
    
    public List<CSOrdenProduccion> getOrdenesProduccion(Map<String, Object> filters) {
        return dao.getOrdenesProduccion(filters);
    }
    
    @Override
    public void delete(CSOrdenProduccion entity) throws BusinessValidationException {
        if(entity.getEstadoProd() == EstadosProd.PLANEADO || true) {
            dao.delete(entity);
        } else {
            throw new BusinessValidationException("No se puede eliminar el registro");
        }
    }
    
    

    public void guardarConsumo(CSOrdenProduccion ordenProduccion, String usuario) throws BusinessValidationException {
        for (ConsumoMateria consumo : ordenProduccion.getConsumoMateriaList()) {
            if(consumo.getExistenciaBodega() == null || existenciaBodegaService.findByEntity(consumo.getExistenciaBodega()) == null) {
                throw new BusinessValidationException(String.format("No se seleccionó una bodega para la materia %s", consumo.getMateriaPrima().getArticuloHijo().getArticulo()));
            }
            consumo.getConsumoMateriaPK().setOrdenProduccion(ordenProduccion.getOrdenProduccion());
            consumoMateriaService.save(consumo);
        }

    }

    public void liberarOrden(CSOrdenProduccion ordenProduccion) throws BusinessValidationException {
        if (ordenProduccion.getEstadoProd() == EstadosProd.LIBERADO) {
            throw new BusinessValidationException("La orden ya fue liberada");
        }
        CSOrdenProduccion orden = find(ordenProduccion.getOrdenProduccion());
        orden.setEstado(EstadosProd.LIBERADO.getEstado());
        orden.setFechaLiberacion(new Date());
        ordenProduccion.setEstado(EstadosProd.LIBERADO.getEstado());
        ordenProduccion.setFechaLiberacion(orden.getFechaLiberacion());

    }

    public void aplicarTransaccionInventario(CSOrdenProduccion ordenProduccion, List<Etapa> etapas, String usuario) throws BusinessValidationException {

        if (ordenProduccion.getEstadoProd() == EstadosProd.TERMINADO) {
            throw new BusinessValidationException("La transacción ya fue aplicada");
        }

        if (ordenProduccion.getEstadoProd() != EstadosProd.LIBERADO) {
            throw new BusinessValidationException("La orden no ha sido liberada");
        }

        if (!ordenProduccion.getConsumoMateriaList().isEmpty()) {
            transaccionInvService.aplicarTransaccionInvConsumo(ordenProduccion, etapas, usuario);
//            OrdenProduccion ordenDb = find(ordenProduccion.getOrdenProduccion());
//            ordenDb.setEstado(EstadosProd.APLICADO.getEstado());
//            ordenDb.setFechaAplicacion(new Date());
        } else {
            throw new BusinessValidationException("La orden no tiene consumo");
        }
    }

    public void calcularCostoEstimadoOrden(CSOrdenProduccion orden) {
        //TODO        
    }
    
    public void aplicarTransEntrada(CSOrdenProduccion ordenProduccion, String usuario) throws BusinessValidationException {
        CSOrdenProduccion ordendb = find(ordenProduccion.getOrdenProduccion());
        List<ConsumoMateria> consumoList = ordendb.getConsumoMateriaList();
        if(consumoList.stream().anyMatch(cm -> "N".equals(cm.getTransAplicada()))) {
            throw new BusinessValidationException("Existen materias primas sin consumo, por favor verifique.");
        }        
        ordendb.setCantidadProducida(ordenProduccion.getCantidadProducida());
        ordendb.setCostoRealLoc(getCostoTotalProduccion(ordendb).getMontoLocal());
        ordendb.setCostoRealDol(getCostoTotalProduccion(ordendb).getMontoDolar());

        ordenProduccion.setCantidadProducida(ordendb.getCantidadProducida());
        ordenProduccion.setCostoRealLoc(ordendb.getCostoRealLoc());
        ordenProduccion.setCostoRealDol(ordendb.getCostoRealDol());
        
        transaccionInvService.aplicarTransaccionInvProduccion(ordenProduccion, usuario);
    }
    
    public MontoLocalDolar getCostoTotalProduccion(CSOrdenProduccion orden) {
        List<ConsumoMateria> consumoList = orden.getConsumoMateriaList();
        BigDecimal local = Utils.sumBigDecimalList(consumoList.stream().map(cm -> cm.getCantidadConsumida().multiply(cm.getMateriaPrima().getArticuloHijo().getCostoPromLoc())).collect(Collectors.toList()));
        BigDecimal dolar = Utils.sumBigDecimalList(consumoList.stream().map(cm -> cm.getCantidadConsumida().multiply(cm.getMateriaPrima().getArticuloHijo().getCostoPromDol())).collect(Collectors.toList()));        
        return new MontoLocalDolar(local, dolar);
    }
    
    public void generarReporteConsolidado(OutputStream os, String sourceFileName, Map<String, Object> params, CSOrdenProduccion op) throws JRException {
        Connection conn = dao.getEm().unwrap(Connection.class);
        JasperPrint jp = JasperFillManager.fillReport(sourceFileName, params, conn);        
        JasperExportManager.exportReportToPdfStream(jp, os);
        if(op != null) {
            CSOrdenProduccion opdb = findByEntity(op);
            opdb.setConsolidado(true);
            op.setConsolidado(true);
        }
    }
    
    public List<CSOrdenProduccion> getOrdenesProduccion(Date fechaInicial, Date fechaFinal, List<String> estados) {
        return dao.getOrdenesProduccion(fechaInicial, fechaFinal, estados);
    }
    
    public List<String> getPedidosString(Map<String, Object> filters) {
        return dao.getPedidosString(filters);
    }
    
    public List<Pedido> getPedidos(Map<String, Object> filters) {
        return dao.getPedidos(filters);
    }
    
    public List<String> getNivelesPrecio() {
        return dao.getNivelesPrecio();
    }
    
    public List<ProductoPedido> getProductosPedidos(List<String> pedidos) {
        return dao.getProductosPedidos(pedidos);
    }
    
    public List<MateriaPrimaConsumo> getMateriaPrimaConsumoList(List<PaqueteOrdenProduccion> paquetes) {
        List<MateriaPrimaConsumo> queryResult = dao.getMateriaPrimaConsumoList(paquetes);
        Map<Articulo, BigDecimal> groupingMap = queryResult.stream().collect(Collectors.groupingBy(mpc -> mpc.getArticuloHijo(), Collectors.mapping(MateriaPrimaConsumo::getCantTotal, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        return groupingMap.keySet().stream().map(key -> new MateriaPrimaConsumo(key.getArticulo(), key, groupingMap.get(key))).collect(Collectors.toList());
    }
    
    
    public void aplicarTransaccionInvMasiva(List<PaqueteOrdenProduccion> paquetes, Bodega bodegaEntrada, List<MateriaPrimaConsumo> materiaList, Bodega bodegaSalida, String usuario) throws BusinessValidationException {
        List<ConsumoMateria> consumosList = new ArrayList<>();
        CSOrdenProduccion orden = paquetes.stream().findFirst().get().getOrdenProduccion();
        for (MateriaPrimaConsumo materia : materiaList) {
            ConsumoMateria cm = new ConsumoMateria(orden.getOrdenProduccion(), 1, "ND", materia.getArticulo());
            cm.setMateriaPrima(new MateriaPrima(1, "ND", materia.getArticulo()));
            cm.getMateriaPrima().setArticuloHijo(materia.getArticuloHijo());
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(materia.getArticulo(), bodegaSalida.getBodega()));
            if(eb == null) throw new BusinessValidationException(String.format("El artículo %s no está asociado a la bodega %s", materia.getArticulo(), bodegaSalida.getBodega()));
            cm.setExistenciaBodega(eb);
            cm.setCantidadConsumida(materia.getCantTotal());
            consumosList.add(cm);
        }

        paquetes.forEach(p -> {
            p.setExistenciaBodega(existenciaBodegaService.find(new ExistenciaBodegaPK(p.getArticulo().getArticulo(), bodegaEntrada.getBodega())));
        });
        
        transaccionInvService.aplicarTransaccionInvConsumo(consumosList, orden, usuario);
        transaccionInvService.aplicarTransaccionInvProduccionMasivo(orden, paquetes, usuario);
        
        CSOrdenProduccion opdb = find(orden.getOrdenProduccion());
        opdb.setPaquetes(paquetes);
        opdb.setEstado(EstadosProd.TERMINADO.getEstado());
    }
    
    public void terminarOrdenProduccion(CSOrdenProduccion op) {
        findByEntity(op).setEstado(EstadosProd.TERMINADO.getEstado());
        op.setEstado(EstadosProd.TERMINADO.getEstado());
    }

}
