/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.EmpleadoConcNomiService;
import com.dosrobles.produccion.service.RptNominaService;
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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
@Named
@ViewScoped
@Getter @Setter
public class RptNominaController implements Serializable {
    
    @Inject
    private RptNominaService service;
    @Inject
    private EmpleadoConcNomiService empleadoConcNomiService;
    
    private Date fechaInicio;
    private Date fechaFin;
    
    @PostConstruct
    private void init() {
        setFechaInicio(Utils.getFirstDayOfMonth(new Date()));
        setFechaFin(Utils.stripTime(new Date()));
    }
    
    public void generarReporte() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("xls"));
        String filename = String.format("rpt_consolidado_%s.xls", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);
        try {
            service.generarReporte(service.getRptNominaList(fechaInicio, fechaFin), response.getOutputStream());
        } catch (Exception ex) {
            Logger.getLogger(RptNominaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fc.responseComplete();
        }
    }
    
    public void procesarNomina() {
        try {
            empleadoConcNomiService.procesarNomina(service.getRptNominaList(fechaInicio, fechaFin));
            MessageUtils.showGrowlSuccess();
        } catch (BusinessValidationException e) {
            MessageUtils.showMessage(e.getMessage());
        } catch (Exception e) {
            MessageUtils.showGrowlError();
            e.printStackTrace();
        }
    }    
}
