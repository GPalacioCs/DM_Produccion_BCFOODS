package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.reports.costeoproductoterminado.CosteoProductoTerminadoReport;
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

@Named
@ViewScoped
@Getter @Setter
public class RptCosteoProductoTerminadoController implements Serializable {

    @Inject
    private OrdenProduccionService ordenProduccionService;

    private Date fechaInicial;
    private Date fechaFinal;


    public void generarReporte() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("xlsx"));
        String filename = String.format("rpt_%s_%s.xlsx", "costeo_producto_terminado", new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
        response.setHeader("Content-disposition","attachment; filename="+filename);

        try {
            CosteoProductoTerminadoReport.createReportFrom(getOrdenProduccionList(), fechaInicial, fechaFinal, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fc.responseComplete();
        }
    }

    public List<OrdenProduccion> getOrdenProduccionList() {
        List<OrdenProduccion> ordenProduccionList = ordenProduccionService.findByFecha(fechaInicial, fechaFinal);
        ordenProduccionList.removeIf(op -> op.getEstadoProd() != EstadosProd.LIBERADO);
        return ordenProduccionList;
    }

}
