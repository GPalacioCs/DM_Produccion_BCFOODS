package com.dosrobles.produccion.reports.detallegeneralconsumo;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.reports.templates.ReportTemplates;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class DetalleGeneralConsumoReport {

    public static void createReportFrom(List<OrdenProduccion> ordenProduccionList, Date fechaInicial, Date fechaFinal, OutputStream outputStream) {
        List<DetalleGeneralConsumoModel> modelList = DetalleGeneralConsumoModel.from(ordenProduccionList);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(modelList);
        String title = "Detalle general de consumo";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicialString = sdf.format(fechaInicial);
        String fechaFinalString = sdf.format(fechaFinal);
        String subtitle = String.format("Periodo del %s al %s", fechaInicialString, fechaFinalString);

        int colWidth = 100;

        try {
            report().addProperty("net.sf.jasperreports.export.xls.detect.cell.type", "true")
                    .setTemplate(ReportTemplates.reportTemplate)
                    .columns(
                            col.column("Fecha", "fecha", type.dateType()).setWidth(colWidth),
                            col.column("OP", "ordenProduccion", type.longType()).setWidth(colWidth),
                            col.column("Artículo", "articulo", type.stringType()).setWidth(colWidth),
                            col.column("Descripción", "descArticulo", type.stringType()).setWidth(500),
                            col.column("Tipo", "tipo", type.stringType()).setWidth(colWidth),
                            col.column("Consumo", "consumo", type.bigDecimalType()).setWidth(colWidth)
                    )
                    .title(ReportTemplates.createTitleComponent(title, subtitle))
                    .setDataSource(ds).toXlsx(outputStream);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }

    }
}
