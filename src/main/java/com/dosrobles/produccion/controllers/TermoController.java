package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.ArticuloTermo;
import com.dosrobles.produccion.entities.Termo;
import com.dosrobles.produccion.service.ArticuloService;
import com.dosrobles.produccion.service.TermoService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MimeUtils;
import com.dosrobles.produccion.utils.Utils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class TermoController extends AbstractController<TermoService, Termo> {
    
    @Inject
    private ArticuloService articuloService;
    
    @Getter @Setter
    private ArticuloTermo articuloTermo = new ArticuloTermo();
    
    @Getter @Setter
    private List<ArticuloTermo> filteredArticuloTermoList = new ArrayList<>();
    
    @PostConstruct
    private void init() {
        cargarLista();
    }
    
    @Override
    public void create() {
        entity = new Termo();
        create(entity);
    }
    
    
    
    @Override
    public void edit() {
        edit(service.findByEntity(selection));
        resetFilter();
    }
    
    private void resetFilter() {
        filteredArticuloTermoList.clear();
        filteredArticuloTermoList.addAll(entity.getArticuloTermoList());
    }
    
    @Override
    public void cargarLista() {
        this.list = service.findAll();
    }
    
    public BigDecimal getPesoTotal() {
        return getPesoTotal(entity);
    }
    
    public BigDecimal getPesoTotal(Termo termo) {
        if(termo == null) return BigDecimal.ZERO.setScale(2);
        return Utils.sumBigDecimals(termo.getArticuloTermoList(), at -> at.getPeso()).setScale(2);
    }
    
    public List<Articulo> getArticulos() {
        return articuloService.getArticulosMateriaPrima();
    }
    
    public void vaciarTermo() {
        if (entity != null) {
            entity.getArticuloTermoList().clear();
        }
    }
    
    public boolean isAdjustable() {
        return true;
    }
    
    public void generarReporte() {
        Termo termo = selection;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_termo_%d.pdf", termo.getId());
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/rpt_termo/rpt_termo.jrxml");
        
        try {
            service.generarReporte(termo, sourceFileName, response.getOutputStream());
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(OrdenCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarArticulo() {
        if (articuloTermo.getArticulo() == null) {
            MessageUtils.showMessage("Debe seleccionar un artículo");
            return;
        }
    
        if (articuloTermo.getPeso() == null) {
            MessageUtils.showMessage("Debe indicar el peso");
            return;
        }
    
        if (entity.getArticuloTermoList().stream().anyMatch(at -> at.getArticulo().equals(articuloTermo.getArticulo()))) {
            MessageUtils.showMessage("El artículo ya fue agregado");
            return;
        }
        articuloTermo.setTermo(entity);
        entity.getArticuloTermoList().add(articuloTermo);
        articuloTermo = new ArticuloTermo();
    }
    
    public void quitarArticulo(String articulo) {
//        entity.getArticuloTermoList().removeIf(at -> at.getArticulo().equals(articuloTermo.getArticulo()));
//        filteredArticuloTermoList.removeIf(at -> at.getArticulo().equals(articuloTermo.getArticulo()));
        entity.getArticuloTermoList().removeIf(at -> at.getArticulo().getArticulo().equals(articulo));
    }
}
