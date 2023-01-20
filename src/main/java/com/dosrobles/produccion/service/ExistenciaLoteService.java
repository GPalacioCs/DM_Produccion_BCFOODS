/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ExistenciaLoteDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Stateless
public class ExistenciaLoteService extends AbstractService<ExistenciaLoteDAO, ExistenciaLote> {
    
    @Inject
    private ParametrosPrService parametrosPrService;

    @Override
    public ExistenciaLote insert(ExistenciaLote entity) throws BusinessValidationException {
        if(findByEntity(entity)!= null){
            throw new BusinessValidationException(String.format("El lote %s ya existe para el artículo %s en la bodega %s", entity.getId().getLote(), entity.getId().getArticulo(), entity.getId().getBodega()));
        }
        
        return super.insert(entity);
    }
    
    
    public List<ExistenciaLote> getExistenciaLoteList(Articulo articulo, Bodega bodega) {
        List<ExistenciaLote> existenciaLoteList = dao.getExistenciaLoteList(articulo.getArticulo(), bodega.getBodega());
        existenciaLoteList.removeIf(el -> el.getLote().isVencido());
        return existenciaLoteList;
    }
    
    public Map<String, BigDecimal> getExistenciasPorArticulo(Articulo articulo, OrdenProduccion orden, BigDecimal cantidad) {
        Bodega bodega = parametrosPrService.getParametro().getBodegaMp(orden.isFresco());
        List<ExistenciaLote> elList = getExistenciaLoteList(articulo, bodega);
        Map<String, BigDecimal> elCantAgregadosMap = orden.getOrdenProdMpList()
                .stream().filter(mp -> mp.getComponente().equals(articulo))
                .collect(Collectors.toMap(OrdenProdMp::getLote, OrdenProdMp::getCantidad));
        orden.getOrdenProdMpList().removeIf(mp -> mp.getComponente().equals(articulo));
        Map<String, BigDecimal> elCantMap = new HashMap<>();        
        elCantMap.putAll(elCantAgregadosMap);
        BigDecimal cantFaltante = cantidad;
        for (ExistenciaLote el : elList) {
            BigDecimal cantLoteActual = elCantMap.getOrDefault(el.getId().getLote(), BigDecimal.ZERO);
            if(cantLoteActual.compareTo(el.getCantDisponible())>=0) continue;
            if(cantFaltante.compareTo(el.getCantDisponible().subtract(cantLoteActual).abs())>0) {
                cantFaltante = cantFaltante.subtract(el.getCantDisponible().subtract(cantLoteActual));
                elCantMap.put(el.getId().getLote(), el.getCantDisponible());
            } else {
                elCantMap.put(el.getId().getLote(), cantFaltante.add(cantLoteActual));
                break;
            }
            
        }
        return elCantMap;
    }    
}
