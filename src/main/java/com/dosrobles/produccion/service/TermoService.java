package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.TermoDAO;
import com.dosrobles.produccion.entities.Termo;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.models.RptArticuloTermo;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import javax.ejb.Stateless;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import static net.sf.dynamicreports.report.builder.DynamicReports.report;

@Stateless
public class TermoService extends AbstractService<TermoDAO, Termo> {
    @Override
    public void delete(Termo entity) throws BusinessValidationException {
        dao.delete(entity);
    }
    
    public void generarReporte(Termo termo, String templatePath, OutputStream os) throws DRException {
        List<RptArticuloTermo> rptList = termo.getArticuloTermoList().stream().map(at -> RptArticuloTermo.from(at))
                .collect(Collectors.toList());
        JasperReportBuilder rpt = report()
                .setParameter("p_nombreTermo", termo.getNombre())
                .setDataSource(rptList)
                .setTemplateDesign(templatePath)
                .toPdf(os);
    }
}
