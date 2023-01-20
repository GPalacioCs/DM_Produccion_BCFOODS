package com.dosrobles.produccion.reports.rendimientoproceso;

import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.reports.templates.ReportTemplates;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class RendimientoProcesoReport {
    public static void createReportFrom(List<OrdenProduccion> ordenProduccionList, Date fechaInicial, Date fechaFinal, OutputStream os) {
        List<RendimientoProcesoModel> rendimientoProcesoModelList = RendimientoProcesoModel.from(ordenProduccionList);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rendimientoProcesoModelList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicialString = sdf.format(fechaInicial);
        String fechaFinalString = sdf.format(fechaFinal);

        String subtitle = String.format("Periodo del %s al %s", fechaInicialString, fechaFinalString);
        int colWidth = 100;

        try {
            report()
                    .setTemplate(ReportTemplates.reportTemplate)
                    .columns(
                    col.column("Fecha", "fecha", type.dateType()).setWidth(colWidth),
                    col.column("OP", "ordenProduccion", type.longType()).setWidth(colWidth),
                    col.column("Artículo", "articulo", type.stringType()).setWidth(colWidth),
                    col.column("Descripción", "descArticulo", type.stringType()).setWidth(800),
                    col.column("Tipo","tipo",type.stringType()).setWidth(colWidth),
                    col.column("Transformación", "transformacion", type.stringType()).setWidth(300),
                    col.column("Materia Prima", "materiaPrima", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Producto Terminado", "productoTerminado", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Merma", "merma", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Rend %", "rendimientoReal", type.bigDecimalType()).setWidth(colWidth),
                    col.column("% Esperado", "rendimientoEsperado", type.bigDecimalType()).setWidth(colWidth),
                    col.column("Desviación", "desviacion", type.bigDecimalType()).setWidth(colWidth)
            )
                    .title(ReportTemplates.createTitleComponent("Rendimiento de Proceso", subtitle))
                    .setDataSource(ds).toXlsx(os);
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }



}
