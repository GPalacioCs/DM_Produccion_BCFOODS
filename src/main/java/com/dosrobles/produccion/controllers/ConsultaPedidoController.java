/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.PedidoOrdenProduccion;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.PedidoOrdenProduccionService;
import com.dosrobles.produccion.utils.LazyQueryObject;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dosrobles.produccion.utils.LazyQueryObject.CustomOper.*;

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ViewScoped
public class ConsultaPedidoController extends AbstractController<PedidoOrdenProduccionService, PedidoOrdenProduccion> {
    
    @Inject
    private ArticuloService articuloService;
    @Inject
    private ParametrosPrController parametrosPrController;
    
    private List<Articulo> articuloList = new ArrayList<>();
    
    private String pedidoDesde;
    private String pedidoHasta;
    private String ordenDesde;
    private String ordenHasta;
    private Date fechaInicioDesde;
    private Date fechaInicioHasta;    
    private Date fechaFinDesde;
    private Date fechaFinHasta;
    private Articulo articulo;
    private final List<String> estadoList = new ArrayList<>();
    private List<String> estadoFilterList = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public List<Articulo> getArticuloList() {
        return articuloList;
    }    
    
    public String getPedidoDesde() {
        return pedidoDesde;
    }
    
    public void setPedidoDesde(String pedidoDesde) {
        this.pedidoDesde = pedidoDesde;
    }
    
    public String getPedidoHasta() {
        return pedidoHasta;
    }
    
    public void setPedidoHasta(String pedidoHasta) {
        this.pedidoHasta = pedidoHasta;
    }
    
    public String getOrdenDesde() {
        return ordenDesde;
    }
    
    public void setOrdenDesde(String ordenDesde) {
        this.ordenDesde = ordenDesde;
    }
    
    public String getOrdenHasta() {
        return ordenHasta;
    }
    
    public void setOrdenHasta(String ordenHasta) {
        this.ordenHasta = ordenHasta;
    }
    
    public Date getFechaInicioDesde() {
        return fechaInicioDesde;
    }
    
    public void setFechaInicioDesde(Date fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }
    
    public Date getFechaInicioHasta() {
        return fechaInicioHasta;
    }
    
    public void setFechaInicioHasta(Date fechaInicioHasta) {
        this.fechaInicioHasta = fechaInicioHasta;
    }
    
    public Date getFechaFinDesde() {
        return fechaFinDesde;
    }
    
    public void setFechaFinDesde(Date fechaFinDesde) {
        this.fechaFinDesde = fechaFinDesde;
    }
    
    public Date getFechaFinHasta() {
        return fechaFinHasta;
    }
    
    public void setFechaFinHasta(Date fechaFinHasta) {
        this.fechaFinHasta = fechaFinHasta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
    public List<String> getEstadoList() {
        return estadoList;
    }

    public List<String> getEstadoFilterList() {
        return estadoFilterList;
    }

    public void setEstadoFilterList(List<String> estadoFilterList) {
        this.estadoFilterList = estadoFilterList;
    }
    
    public String getEstado(PedidoOrdenProduccion pop) {
        switch (pop.getOrdenProduccion().getEstadoProd()) {
            case PLANEADO : return "Asignado";
            case LIBERADO : return "En Proceso";
            case TERMINADO : return "Terminado";
            default : return null;
        }
    }
//</editor-fold>
    
    
    
    @PostConstruct
    private void init() {
        cargarLista();
        estadoList.add("asignado");
        estadoList.add("enproceso");
        estadoList.add("terminado");        
        estadoFilterList.add("asignado");
        estadoFilterList.add("enproceso");
        estadoFilterList.add("terminado");        
        setAccActiveIndex(0);
        this.articuloList = articuloService.getArticulosPorClasificacion(parametrosPrController.getParametro().getClasificacionPr());
        cargarLista();
    }

    @Override
    public void create() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void addDefaultColumns() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarLista() {
        filtrar();
    }
    
    public void filtrar() {
        LazyQueryObject<PedidoOrdenProduccion> lqo = new LazyQueryObject<>();
        lqo.addCustomFilter("clasificacion", EQUALS, parametrosPrController.getParametro().getClasificacionPr());
        if(getPedidoDesde()!=null) {
            lqo.addCustomFilter("pedidoDesde", GTOREQ, pedidoDesde);            
        }
        
        if(getPedidoHasta() != null) {
            lqo.addCustomFilter("pedidoHasta", LTOREQ, pedidoHasta);
        }
        
        if(getOrdenDesde() != null) {
            lqo.addCustomFilter("ordenDesde", GTOREQ, ordenDesde);            
        }
        
        if(getOrdenHasta() != null) {
            lqo.addCustomFilter("ordenHasta", LTOREQ, ordenHasta);            
        }
        
        if(getFechaInicioDesde() != null) {
            lqo.addCustomFilter("fechaInicioDesde", GTOREQ, fechaInicioDesde); 
        }
        
        if(getFechaInicioHasta() != null) {
            lqo.addCustomFilter("fechaInicioHasta", LTOREQ, fechaInicioHasta);            
        }
        
        if(getFechaFinDesde() != null) {
            lqo.addCustomFilter("fechaFinDesde", GTOREQ, fechaFinDesde);
        }
        
        if(getFechaFinHasta() != null) {
            lqo.addCustomFilter("fechaFinHasta", LTOREQ, fechaFinHasta);            
        }        
        
        if(getEstadoFilterList() != null) {
            lqo.addCustomFilter("estado", EQUALS, estadoFilterList);
        }
        
        if(getArticulo() != null) {
            lqo.addCustomFilter("articulo", EQUALS, articulo);
        }
        
        service.filtrar(lqo);
        
        setList(lqo.getResultList());
        
    }
    
    

}
