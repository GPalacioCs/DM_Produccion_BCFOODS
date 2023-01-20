/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.entities.Empleado;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.models.RptNomina;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Stateless
public class RptNominaService {
    
    @Inject
    OrdenProduccionService ordenProduccionService;
    @Inject
    ParametrosPrService parametrosPrService;
    
    public List<RptNomina> getRptNominaList(Date fechaInicio, Date fechaFin) {
        List<OrdenProduccion> ordenProduccionList = ordenProduccionService.findByFecha(fechaInicio, fechaFin)
                .stream().filter(o -> o.getEstadoProd() == EstadosProd.LIBERADO && o.isAplicarManoDeObra()).collect(Collectors.toList());
        Map<Empleado, BigDecimal> empleadoMap = new HashMap<>();
        for (OrdenProduccion orden : ordenProduccionList) {
            orden.getOrdenProduccionActividadList().forEach(opa -> {
                List<Empleado> empleadosActividad = orden.getEmpleadoList().stream()
                        .filter(emp -> Objects.equals(emp.getActividad(), opa.getActividad()))
                        .map(emp -> emp.getEmpleado())
                        .collect(Collectors.toList());
                int count = empleadosActividad.size();
                if(count > 0) {
                    for (Empleado empleado : empleadosActividad) {
                        BigDecimal monto = empleadoMap.getOrDefault(empleado, BigDecimal.ZERO);
                        empleadoMap.put(empleado, monto.add(opa.getActividad().getCostoLocal().multiply(opa.getLibras()).divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_EVEN)));
                    }
                }
            });
        }
        String concepto = parametrosPrService.getParametro().getConceptoProduccion();
        List<RptNomina> rptNominaList = new ArrayList<>();
        for (Map.Entry<Empleado, BigDecimal> entry : empleadoMap.entrySet()) {
            RptNomina rptNomina = new RptNomina(entry.getKey().getEmpleado(), concepto, entry.getValue());
            rptNominaList.add(rptNomina);
        }
        return rptNominaList;
    }
    
    public void generarReporte(List<RptNomina> rptNominaList, OutputStream os) throws IOException {
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row encabezado = sheet.createRow(0);
            encabezado.createCell(0).setCellValue("empleado");
            encabezado.createCell(1).setCellValue("concepto");
            encabezado.createCell(2).setCellValue("monto");
            
            int rowNum=1;
            for (RptNomina rptNomina : rptNominaList) {
                Row row = sheet.createRow(rowNum++);
                int colNum=0;
                Cell cellEmpleado = row.createCell(colNum++);
                cellEmpleado.setCellValue(rptNomina.getEmpleado());
                Cell cellConcepto = row.createCell(colNum++);
                cellConcepto.setCellValue(rptNomina.getConcepto());
                Cell cellMonto = row.createCell(colNum++);
                cellMonto.setCellValue(rptNomina.getMonto().doubleValue());
            }
            
            workbook.write(os);
        }
    }
}
