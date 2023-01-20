/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.BodegaService;
import com.dosrobles.produccion.service.CSOrdenProduccionService;
import com.dosrobles.produccion.service.ExistenciaBodegaService;
import com.dosrobles.produccion.utils.MessageUtils;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Zeus
 */
@Named
@ViewScoped
public class ConsumoController extends AbstractController<CSOrdenProduccionService, CSOrdenProduccion> {    
    
    @Inject
    private ArticuloService articuloService;    
    @Inject
    private ExistenciaBodegaService existenciaBodegaService;
    @Inject
    private BodegaService bodegaService;
    
    private List<ProductoPedido> productosPedidos = new ArrayList<>();
    private List<MateriaPrimaConsumo> consumoList = new ArrayList<>();
    private List<Bodega> bodegaList = new ArrayList<>();
    
    private Bodega bodegaOrigen;
    private Bodega bodegaDestino;

    public List<ProductoPedido> getProductosPedidos() {
        return productosPedidos;
    }    

    public List<MateriaPrimaConsumo> getConsumoList() {
        return consumoList;
    } 

    public Bodega getBodegaOrigen() {
        return bodegaOrigen;
    }

    public void setBodegaOrigen(Bodega bodegaOrigen) {
        this.bodegaOrigen = bodegaOrigen;
    }

    public Bodega getBodegaDestino() {
        return bodegaDestino;
    }

    public void setBodegaDestino(Bodega bodegaDestino) {
        this.bodegaDestino = bodegaDestino;
    }

    public List<Bodega> getBodegaList() {
        return bodegaList;
    }
    
    

    //<editor-fold defaultstate="collapsed" desc="abstract methods">
    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void addDefaultColumns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>
    
    @PostConstruct
    private void init() {
        entity = (CSOrdenProduccion)Faces.getRequestAttribute("orden");
        List<ProductoPedido> productosPedidos = service.getProductosPedidos(entity.getPedidoOrdenProduccionList().stream().map(pop -> pop.getPedido()).map(pedido -> pedido.getPedido()).collect(Collectors.toList()));
        entity.setPaquetes(productosPedidos.stream().map(pp -> new PaqueteOrdenProduccion(new PaqueteOrdenProduccionPK(entity.getOrdenProduccion(), pp.getId()), pp.getCantidad(), articuloService.find(pp.getId()), entity)).collect(Collectors.toList()));
        bodegaList = bodegaService.findAll();
        recalcularConsumo();
    }
    
    public void recalcularConsumo() {
        this.consumoList = service.getMateriaPrimaConsumoList(entity.getPaquetes());
    }
    
    public BigDecimal getCantDisponible(Articulo articulo, Bodega bodega) {
        if (bodega != null) {
            ExistenciaBodega eb = existenciaBodegaService.find(new ExistenciaBodegaPK(articulo.getArticulo(), bodega.getBodega()));
            if(eb != null) return eb.getCantDisponible();
            return null;
        } return null;
    }
    
    public BigDecimal getCantDisponibleEntrada(Articulo articulo) {
        return getCantDisponible(articulo, bodegaDestino);
    }
    
    public BigDecimal getCantDisponibleSalida(Articulo articulo) {
        return getCantDisponible(articulo, bodegaOrigen);
    }

    @Override
    public void save() {
        try {
            recalcularConsumo();
            service.aplicarTransaccionInvMasiva(entity.getPaquetes(), bodegaDestino, consumoList, bodegaOrigen, getUsername());
            regresar(true);
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            Logger.getLogger(ConsumoController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public String regresar(boolean success) {
//        Faces.setRequestAttribute("success", success);
        return "orden-produccion_1?faces-redirect=true&success="+success;
    }
    
    
}
