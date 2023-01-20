package com.dosrobles.produccion.reports.costeoproductoterminado;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.reports.templates.ReportTemplates;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class CosteoProductoTerminadoReport {
    public static void createReportFrom(List<OrdenProduccion> ordenProduccionList, Date fechaInicial, Date fechaFinal, OutputStream outputStream) {
        List<CosteoProductoTerminadoModel> costeoProductoTerminadoModelList = CosteoProductoTerminadoModel.from(ordenProduccionList);
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
                    col.column("Code", "code", type.stringType()).setWidth(colWidth),
                    col.column("Descripción", "descripcion", type.stringType()).setWidth(800),
                    col.column("Fecha", "fecha", type.dateType()).setWidth(colWidth),
                    col.column("OP", "ordenProduccion", type.longType()).setWidth(colWidth),
                    col.column("Materia Prima","materiaPrima",type.bigDecimalType()).setWidth(colWidth),
                    col.column("Maquila", "maquila", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Total Costo", "totalCosto", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Producto Terminado", "productoTerminado", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Unitario", "unitario", type.bigDecimalType()).setWidth(colWidth)
            )
                    .title(ReportTemplates.createTitleComponent("Costeo de Producto Terminado", subtitle))
                    .setDataSource(ds).toXlsx(outputStream);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }

}
