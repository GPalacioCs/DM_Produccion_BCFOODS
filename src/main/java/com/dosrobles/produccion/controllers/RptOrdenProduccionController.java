/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.CSOrdenProduccion;
import com.dosrobles.produccion.entities.Pedido;
import com.dosrobles.produccion.service.CSOrdenProduccionService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MimeUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Corpsoft S.A.
 */
@Named
@ViewScoped
public class RptOrdenProduccionController implements Serializable {
    
    @Inject
    private CSOrdenProduccionService service;    
    
    private List<CSOrdenProduccion> list = new ArrayList<>();
    private List<Pedido> pedidoList = new ArrayList<>();
//    private List<OrdenProduccion> selectionList = new ArrayList<>();
    private List<Pedido> selectionList = new ArrayList<>();
    
     private List<String> nivelesPrecio = new ArrayList<>();
    
    private Date fechaInicial;
    private Date fechaFinal;
    
    private String nivelPrecio;
    
    private String cliente1;
    private String cliente2;
    
    private String pedido1;
    private String pedido2;  

    public List<CSOrdenProduccion> getList() {
        return list;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }    

    public List<Pedido> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<Pedido> selectionList) {
        this.selectionList = selectionList;
    }

    public List<String> getNivelesPrecio() {
        return nivelesPrecio;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNivelPrecio() {
        return nivelPrecio;
    }

    public void setNivelPrecio(String nivelPrecio) {
        this.nivelPrecio = nivelPrecio;
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
    
    @PostConstruct
    private void init() {
        fechaInicial = new Date();
        fechaFinal = new Date();
        this.nivelesPrecio = service.getNivelesPrecio();
    }
    
    public void filtrar() {
        Map<String, Object> filters = new HashMap<>();
        filters.put("fechaInicial", fechaInicial);
        filters.put("fechaFinal", fechaFinal);
        filters.put("nivelPrecio", nivelPrecio);
        filters.put("pedido1", pedido1);
        filters.put("pedido2", pedido2);
        filters.put("cliente1", cliente1);
        filters.put("cliente2", cliente2);        
        this.pedidoList = service.getPedidos(filters);
        setSelectionList(pedidoList);
    }
    
    public void consolidar(CSOrdenProduccion op) {
        if(selectionList == null || selectionList.isEmpty()) {
            MessageUtils.showGrowlError("Debe seleccionar al menos un pedido");
            return;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_consolidado_%s.pdf", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/consolidado_pedidos/consolidado_pedidos.jasper");
        String logoPath = request.getServletContext().getRealPath("/resources/images/pimgpsh_fullsize_distr.png");
        Map<String, Object> params = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        
        params.put("p_logo_path", logoPath);
//        params.put("p_ordenes_produccion", selectionList.stream().map(op -> op.getOrdenProduccion()).collect(Collectors.toList()));
        params.put("p_pedidos", selectionList.stream().map(Pedido::getPedido).collect(Collectors.toList()));
        if (op != null) {
            params.put("p_num_orden", op.getOrdenProduccion());
            params.put("p_hora_prod_fin", new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(op.getFechaFin()));
        }
        params.put("p_usuario", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        try {
            service.generarReporteConsolidado(response.getOutputStream(), sourceFileName, params,op);
            fc.responseComplete();
        } catch (Exception ex) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            Logger.getLogger(OrdenProduccionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
