package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.reports.bonoproduccion.BonoProduccionModel;
import com.dosrobles.produccion.reports.bonoproduccion.BonoProduccionReport;
import com.dosrobles.produccion.service.OrdenProduccionService;
import com.dosrobles.produccion.utils.MimeUtils;
import lombok.Getter;
import lombok.Setter;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Named @ViewScoped
public class RptBonoProduccionController implements Serializable {

    private Date fechaInicial;
    private Date fechaFinal;

    @Inject
    private OrdenProduccionService ordenProduccionService;

    public List<OrdenProduccion> getOrdenProduccionList() {
        List<OrdenProduccion> ordenProduccionList = ordenProduccionService.findByFecha(fechaInicial, fechaFinal).stream().filter(op -> op.getEstadoProd() == EstadosProd.LIBERADO && op.isAplicarManoDeObra()).collect(Collectors.toList());
//        ordenProduccionList.removeIf(op -> op.getEstadoProd() != EstadosProd.LIBERADO);
        return ordenProduccionList;
    }

    public List<BonoProduccionModel> getBonoProduccionModelList() {
        return BonoProduccionModel.from(getOrdenProduccionList());
    }

    public void generarReporte() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("xlsx"));
        String filename = String.format("rpt_%s_%s.xlsx", "bono_produccion", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);

        try {
            BonoProduccionReport.createReportFrom(getOrdenProduccionList(), fechaInicial, fechaFinal, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fc.responseComplete();
        }
    }
}
