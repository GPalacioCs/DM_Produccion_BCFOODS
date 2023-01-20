/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.CSOrdenProduccion;
import com.dosrobles.produccion.entities.Pedido;
import com.dosrobles.produccion.entities.PedidoOrdenProduccion;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.CSOrdenProduccionService;
import com.dosrobles.produccion.utils.MessageUtils;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ViewScoped
public class OrdenProduccion2Controller extends AbstractController<CSOrdenProduccionService, CSOrdenProduccion> {
    
    @Inject
    private RptOrdenProduccionController rptOrdenProduccionController;
    
    private List<Pedido> pedidos = new ArrayList<>();    
    
    private String nivelPrecio;
    
    private List<String> nivelesPrecio = new ArrayList<>();
    
    private String cliente1;
    private String cliente2;
    
    private String pedido1;
    private String pedido2;
    
    private boolean showTerminado;

    private boolean success;

    public List<Pedido> getPedidos() {
        return pedidos;
    }        
    
    public List<Pedido> getSelectedPedidos() {
        return entity.getPedidoOrdenProduccionList()
                .stream().map(pop -> pop.getPedido())
                .collect(Collectors.toList());
    }

    public void setSelectedPedidos(List<Pedido> selectedPedidos) {        
        entity.setPedidoOrdenProduccionList(selectedPedidos.stream()
                .map(p -> new PedidoOrdenProduccion(p.getPedido(), 
                        entity.getOrdenProduccion()))
                .collect(Collectors.toList()));
    }

    public String getNivelPrecio() {
        return nivelPrecio;
    }

    public void setNivelPrecio(String nivelPrecio) {
        this.nivelPrecio = nivelPrecio;
    }

    public List<String> getNivelesPrecio() {
        return nivelesPrecio;
    }    

    public String getCliente1() {
        return cliente1;
    }

    public void setCliente1(String cliente1) {
        this.cliente1 = cliente1;
    }

    public String getCliente2() {
        return cliente2;
    }

    public void setCliente2(String cliente2) {
        this.cliente2 = cliente2;
    }

    public String getPedido1() {
        return pedido1;
    }

    public void setPedido1(String pedido1) {
        this.pedido1 = pedido1;
    }

    public String getPedido2() {
        return pedido2;
    }

    public void setPedido2(String pedido2) {
        this.pedido2 = pedido2;
    }

    public boolean isShowTerminado() {
        return showTerminado;
    }

    public void setShowTerminado(boolean showTerminado) {
        this.showTerminado = showTerminado;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public void save() {
        if(entity.getPedidoOrdenProduccionList() == null || entity.getPedidoOrdenProduccionList().isEmpty()) {
            MessageUtils.showMessage("Debe seleccionar al menos un pedido");
            return;
        }
        super.save(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @PostConstruct
    private void init() {
        cargarLista();
        nivelesPrecio = service.getNivelesPrecio();
    }

    @Override
    public void create() {
        entity = new CSOrdenProduccion();
        entity.setArticulo(new Articulo("ND"));
        entity.setFechaInicio(new Date());
        entity.setFechaFin(new Date());
        entity.setCantidad(BigDecimal.ZERO);
        entity.setEstado(EstadosProd.PLANEADO.getEstado());
        filtrarPedidos();
        reset();
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        if(selection.getEstadoProd() != EstadosProd.PLANEADO) {
            MessageUtils.showGrowlError("La orden ya fue liberada");
            return;
        }
        entity = service.findByEntity(selection);
        filtrarPedidos();
        reset();
        setEditing(true);
        setFormView(true);
    }

    @Override
    protected void addDefaultColumns() {
        selectedColumns.add("Orden");
    }
    
    public void filtrarPedidos() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("fechaInicial", entity.getFechaInicio());
        filters.put("fechaFinal", entity.getFechaFin());
        filters.put("nivelPrecio", entity.getSegmento());
        filters.put("pedido1", pedido1);
        filters.put("pedido2", pedido2);
        filters.put("cliente1", cliente1);
        filters.put("cliente2", cliente2);
        pedidos = service.getPedidos(filters);
        pedidos.addAll(0, entity.getPedidoOrdenProduccionList().stream().map(pop -> pop.getPedido()).collect(Collectors.toList()));
    }

    @Override
    public void cargarLista() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("masivo", true);
        filters.put("terminado", showTerminado);
        
        list = service.getOrdenesProduccion(filters);
    }
    
    public void generarConsolidado() {
        rptOrdenProduccionController.setSelectionList(
        selection.getPedidoOrdenProduccionList()
                .stream().map(pop -> pop.getPedido())
                .collect(Collectors.toList())
        );
        rptOrdenProduccionController.consolidar(selection);
    }
    
    public String consumir() {
        Faces.setRequestAttribute("orden", selection);
        return "consumo";
    }
    
    public void liberarOrden() {
        try {
            
            service.liberarOrden(selection);
            MessageUtils.showGrowlSuccess();
//            generarConsolidado();
//            cargarLista();
//            RequestContext.getCurrentInstance().execute("callRC()");
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        }
        
    }
    
    public void btnConsolidadoClicked() {
        if(!selection.isConsolidado()) {
            generarConsolidado();
        }
    }
    
    public void btnTerminarClicked() {
        service.terminarOrdenProduccion(selection);
    }
    
    public boolean isConsumoDisabled() {
        return selection == null || selection.getEstadoProd() != EstadosProd.LIBERADO;
    }
    
    public boolean isLiberarDisabled() {
        return selection == null || selection.getEstadoProd() != EstadosProd.PLANEADO;
    }
    
    public boolean isConsolidadoDisabled() {
        return selection == null || selection.getEstadoProd() == EstadosProd.PLANEADO;
    }
    
    public boolean isTerminarDisabled() {
        return selection == null || selection.getEstadoProd() == EstadosProd.TERMINADO || selection.getEstadoProd() == EstadosProd.PLANEADO;
    }
    
    public void showSuccess() {
        if(success) {
            MessageUtils.showGrowlSuccess();
        }
    }

}
