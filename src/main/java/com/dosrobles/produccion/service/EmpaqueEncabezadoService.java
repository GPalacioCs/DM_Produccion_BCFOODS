/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EmpaqueEncabezadoDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.Utils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
@Stateless
public class EmpaqueEncabezadoService extends AbstractService<EmpaqueEncabezadoDAO, EmpaqueEncabezado> {
    
    @Inject
    PedidoService pedidoService;
    @Inject
    ArticuloService articuloService;
    @Inject
    OrdenProduccionService ordenProduccionService;
    
    
    public List<EmpaqueEncabezado> findByOrdenProduccion(OrdenProduccion ordenProduccion) {
        return dao.findByOrdenProduccion(ordenProduccion);
    }

    @Override
    public EmpaqueEncabezado insert(EmpaqueEncabezado entity) throws BusinessValidationException {        
        entity.getOrdenProduccion().getEmpaqueEncabezadoList().add(entity);
        return super.insert(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpaqueEncabezado save(EmpaqueEncabezado entity) throws BusinessValidationException {
        if(entity.getOrdenProduccion().getOrdenProduccion() == null) {
            return entity;    
        }
        return super.save(entity);
    }
    
    public void generarPedido(EmpaqueEncabezado empaqueEncabezado, String usuario) throws BusinessValidationException {
        if(empaqueEncabezado.getOrdenProduccion().getOrdenProduccion() == null) {
            throw new BusinessValidationException("Debe guardar la orden de producción antes de generar el pedido");
        }
        List<Empaque> empaqueList = empaqueEncabezado.getEmpaqueList(); 
        Cliente cliente = empaqueEncabezado.getCliente();
        
        String consec = pedidoService.generarConsecutivo("PED");
        Pedido pedido = Pedido.from(new Pedido(consec), cliente, "ND", "COR", new Bodega("PTA"), cliente.getCondicionPago(), usuario);
        int i = 1;
        for (Empaque empaque : empaqueList) {
            if(empaque.getPedido() != null) {
                throw new BusinessValidationException("El pedido ya ha sido generado para este cliente");
            }
            PedidoLinea linea = PedidoLinea.from(pedido, (short)0, empaque.getArticulo(), articuloService.getPrecioArticulo(empaque.getArticulo().getArticulo(), cliente.getNivelPrecio(), cliente.getMonedaNivel()), empaque.getPesoInyectado(), new Bodega(pedido.getBodega()));
            linea.setArticulo(empaque.getArticulo());
            linea.setCantidadPedida(empaque.getPesoInyectado());
            pedidoService.agregarLinea(pedido, linea);
        }        
        actualizarTotalesPedido(pedido);        
        pedidoService.save(pedido);
        empaqueEncabezado.setPedido(pedido.getPedido());
        EmpaqueEncabezado ee = findByEntity(empaqueEncabezado);
        if(ee != null) {
            ee.setPedido(pedido.getPedido());
        }
    }
    
    private void actualizarTotalesPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		pedido.setMontoDescuento1(Utils.sumBigDecimals(pedido.getPedidoLineaList(), PedidoLinea::getMontoDescuento));		
		pedido.setTotalImpuesto1(Utils.sumBigDecimals(pedido.getPedidoLineaList(), pl -> pl.getArticulo().getImpuesto().getImpuesto1().divide(new BigDecimal("100")).multiply(pl.getPrecioTotal())));
		pedido.setTotalImpuesto2(Utils.sumBigDecimals(pedido.getPedidoLineaList(), pl -> pl.getArticulo().getImpuesto().getImpuesto2().divide(new BigDecimal("100")).multiply(pl.getPrecioTotal())));
		pedido.setTotalMercaderia(Utils.sumBigDecimals(pedido.getPedidoLineaList(), pl->pl.getPrecioTotal()));
		pedido.setTotalUnidades(Utils.sumBigDecimals(pedido.getPedidoLineaList(), pl -> pl.getCantidadPedida()));
		pedido.setTotalAFacturar(
				pedido.getTotalMercaderia()
				.add(pedido.getTotalImpuesto1())
				.add(pedido.getTotalImpuesto2())
				.subtract(pedido.getMontoDescuento1())
				.subtract(pedido.getMontoDescuento2())
		);
	}
    
    
    public void generarReporte(OutputStream os, String sourceFileName, Map<String, Object> params) throws JRException {
        Connection conn = dao.getEm().unwrap(Connection.class);
        JasperPrint jp = JasperFillManager.fillReport(sourceFileName, params, conn);        
        JasperExportManager.exportReportToPdfStream(jp, os);
    }
    
    public void generarReporteExcel(OutputStream os, String sourceFileName, Map<String, Object> params) throws JRException {
        Connection conn = dao.getEm().unwrap(Connection.class);
        JasperPrint jp = JasperFillManager.fillReport(sourceFileName, params, conn);        
//        JasperExportManager.exportReportToPdfStream(jp, os);
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jp));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        configuration.setDetectCellType(true);//Set configuration as you like it!!
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
    
    public Integer getMaxEmpaque() {
        return dao.getMaxEmpaque();
    }

    @Override
    public void delete(EmpaqueEncabezado entity) throws BusinessValidationException {
        entity.getOrdenProduccion().getEmpaqueEncabezadoList().remove(entity);
        dao.delete(entity);
    }
    
    public Integer getCurrentEmpaque(EmpaqueEncabezado entity)  {
        Integer max = 0;
        if(entity != null && CollectionUtils.isNotEmpty(entity.getEmpaqueList())) {
            max = entity.getEmpaqueList().stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
        }
        
        return max + 1;
    }
    
    public EmpaqueEncabezado findByOrdenProduccionAndCliente(OrdenProduccion ordenProduccion, Cliente cliente) {
        List<EmpaqueEncabezado> list = dao.findByOrdenProduccionAndCliente(ordenProduccion, cliente);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
}
