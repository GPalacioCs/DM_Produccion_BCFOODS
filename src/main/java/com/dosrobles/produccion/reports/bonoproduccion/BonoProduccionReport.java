package com.dosrobles.produccion.reports.bonoproduccion;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.reports.templates.ReportTemplates;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class BonoProduccionReport {

    public static void createReportFrom(List<OrdenProduccion> ordenProduccionList, Date fechaInicial, Date fechaFinal, OutputStream outputStream) {
        List<BonoProduccionModel> costeoProductoTerminadoModelList = BonoProduccionModel.from(ordenProduccionList);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(costeoProductoTerminadoModelList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicialString = sdf.format(fechaInicial);
        String fechaFinalString = sdf.format(fechaFinal);

        String subtitle = String.format("Periodo del %s al %s", fechaInicialString, fechaFinalString);

        int colWidth = 100;
        try {
            report().addProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true")
                    .setTemplate(ReportTemplates.reportTemplate)
                    .columns(
                            col.column("Actividad", "codigo", type.stringType()).setWidth(colWidth),
                            col.column("lbs", "libras", type.bigDecimalType()).setWidth(colWidth),
                            col.column("Costo C$", "costo", type.bigDecimalType()).setWidth(colWidth),
                            col.column("Total C$", "total", type.bigDecimalType()).setWidth(colWidth)
                    )
                    .title(ReportTemplates.createTitleComponent("Bono de Producción", subtitle))
                    .summary(buildCrosstab(ordenProduccionList))
                    .setDataSource(ds).toXlsx(outputStream);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }

    public static CrosstabBuilder buildCrosstab(List<OrdenProduccion> ordenProduccionList) {
        List<BonoProduccionEmpleadoModel> bonoProduccionModelList = BonoProduccionEmpleadoModel.from(ordenProduccionList);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bonoProduccionModelList);
        CrosstabRowGroupBuilder<String> rowGroupBuilder = ctab.rowGroup("empleadoNombre", String.class)
                .setTotalHeader("Total fecha").setHeaderWidth(400);

        CrosstabColumnGroupBuilder<Date> columnGroupBuilder = ctab.columnGroup("fecha", Date.class);

        CrosstabBuilder crosstabBuilder = ctab.crosstab()
                .headerCell(cmp.text("Empleado / Fecha").setStyle(ReportTemplates.boldCenteredStyle))
                .rowGroups(rowGroupBuilder)
                .columnGroups(columnGroupBuilder)
                .measures(
                        ctab.measure("total", BigDecimal.class, Calculation.SUM)
                ).setDataSource(dataSource);

        return crosstabBuilder;

    }
}
