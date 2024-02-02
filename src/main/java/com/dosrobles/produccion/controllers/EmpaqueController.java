/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.entities.Empaque;
import com.dosrobles.produccion.entities.EmpaqueEncabezado;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.ClienteService;
import com.dosrobles.produccion.service.EmpaqueService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MimeUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Named
@ViewScoped
public class EmpaqueController extends AbstractController<EmpaqueService, Empaque> {    
    
    @Inject
    private ClienteService clienteService;
    @Inject
    private ArticuloService articuloService;
    
    @Getter @Setter
    private List<Cliente> clienteList = new ArrayList<>();
    @Getter @Setter
    private List<Cliente> clienteListDialog = new ArrayList<>();
    @Getter @Setter
    private Cliente selectedCliente = new Cliente();
    private Cliente cliente;
    @Getter @Setter
    private Empaque empaque;
    @Getter @Setter
    private List<Empaque> empaqueList = new ArrayList<>();
    @Getter @Setter
    private List<Articulo> articuloList;

    @Getter @Setter
    private EmpaqueEncabezado empaqueEncabezado;
    
    public EmpaqueController(){}
    
    @PostConstruct
    private void init() {
        cargarClientes();
        this.clienteListDialog = clienteService.findAll();
        this.articuloList = articuloService.getArticulosProduccion();
    }

    @Override
    public void create() {        
        empaque = new Empaque();
        selectedCliente = null;
        empaqueList = new ArrayList<>();
        reset();
        setCreating(true);
        setFormView(true);
    }

    @Override
    public void edit() {
        empaque = new Empaque();
        empaqueList = service.findByCliente(selectedCliente);
        reset();        
        setEditing(true);
        setFormView(true);
    }

    private void cargarClientes() {
        setClienteList(service.findAll().stream().map(empaque -> empaque.getCliente())
                .distinct().collect(Collectors.toList()));
    }
    
    public void agregarEmpaque() {
        agregarEmpaque(empaque);
    }

    public void agregarEmpaque(Empaque empaque) {
        if(empaque == null) return;
        try {
            if (selectedCliente == null) {
                throw new BusinessValidationException("Debe seleccionar un cliente");
            }
            
            if(empaque.getCajas() == null) {
                throw new BusinessValidationException("Debe ingresar el número de cajas");
            }
            
            if(empaque.getArticulo() == null) {
                throw new BusinessValidationException("Debe ingresar el artículo");
            }
            
            if(empaque.getPesoBruto() == null) {
                throw new BusinessValidationException("Debe ingresar el peso bruto");
            }
            
            if(empaque.getPesoNeto() == null) {
                throw new BusinessValidationException("Debe ingresar el peso neto");
            }

            if(empaque.getPesoInyectado() == null) {
                throw new BusinessValidationException("Debe ingresar el peso Inyectado");
            }
            
            if(empaque.getPescados() == null) {
                throw new BusinessValidationException("Debe ingresar el número de pescados");
            }
            
            if(empaque.getTalla() == null) {
                throw new BusinessValidationException("Debe ingresar la talla");
            }
            
            if (!empaqueList.contains(empaque)) {
                int max = empaqueList.stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
                empaque.getId().setEmpaqueEncabezadoPK(null);
                empaque.getId().setEmpaque(max+1);
                empaqueList.add(empaque);
                this.empaque = new Empaque();
                this.empaque.setArticulo(empaque.getArticulo());
                this.empaque.setCajas(empaque.getCajas().add(BigDecimal.ONE));
                this.empaque.setTalla(empaque.getTalla());
            }
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        }
    }
    
    public void quitarEmpaque(Empaque empaque) {
        empaqueList.removeIf(e -> Objects.equals(empaque.getId().getEmpaque(), e.getId().getEmpaque()));
    }

    @Override
    public void cargarLista() {
        cargarClientes();
    }
    

    @Override
    public void save() {
        try {
            if (!isCreating()) {
                service.guardarEmpaques(empaqueList, selectedCliente);
            } else {
                service.insertarEmpaques(empaqueList, selectedCliente);
            }
            cancel();
            MessageUtils.showGrowlSuccess();
            cargarLista();
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            e.printStackTrace();
        }
    }
    
    public void generarReporte(boolean consolidado) {
        if(selectedCliente == null) {
            MessageUtils.showGrowlError("Debe seleccionar al menos un pedido");
            return;
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_%s_%s.pdf", consolidado ? "consolidado": "packing_list", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/rpt_packing_list/rpt_packing_list.jasper");
        String logoPath = request.getServletContext().getRealPath("/resources/images/pimgpsh_fullsize_distr.png");
        Map<String, Object> params = new HashMap<>();
        params.put("p_cliente","'"+selection.getCliente().getCliente()+"'");
        params.put("p_consolidado",consolidado);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        
        try {
            service.generarReporte(response.getOutputStream(), sourceFileName, params);
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarPedido() {
        try {
            service.generarPedido(empaqueList, selection.getCliente(), getUsername());
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch(Exception ex) {
            Logger.getLogger(EmpaqueController.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showGrowlError();
        }
    }
    
    
    
}
