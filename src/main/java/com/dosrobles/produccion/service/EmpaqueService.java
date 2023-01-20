package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EmpaqueDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.Utils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
public class EmpaqueService extends AbstractService<EmpaqueDAO, Empaque> {
    
    @Inject
    PedidoService pedidoService;
    @Inject
    ArticuloService articuloService;
    
    public void insertarEmpaques(List<Empaque> empaqueList, Cliente cliente) throws BusinessValidationException {
        if(!findByCliente(cliente).isEmpty()) {
            throw new BusinessValidationException("El cliente ya existe");
        }
        guardarEmpaques(empaqueList, cliente);
    }
    
    public void guardarEmpaques(List<Empaque> empaqueList, Cliente cliente) throws BusinessValidationException {
        
//        empaqueList.forEach(e -> {
//            e.getId().setCliente(cliente.getCliente());
//            e.setCliente(cliente);
//        });
        
        deleteRemovedEmpaques(empaqueList, cliente);        
        for (Empaque empaque : empaqueList) {
            save(empaque);
        }
    }
    
    public void deleteRemovedEmpaques(List<Empaque> empaqueList, Cliente cliente) throws BusinessValidationException {
        List<Empaque> empaquesdb = findByCliente(cliente);
        for (Empaque empaque : empaquesdb) {
            if(!empaqueList.contains(empaque)) {
                delete(empaque);
            }
        }
    }

    @Override
    public void delete(Empaque empaque) throws BusinessValidationException {
        dao.delete(empaque);
    }
    
    public List<Empaque> findByCliente(Cliente cliente) {
        return dao.findByCliente(cliente);
    }
    
    public void generarPedido(List<Empaque> empaqueList, Cliente cliente, String usuario) throws BusinessValidationException {
        if(empaqueList == null || empaqueList.isEmpty()||true) {
            empaqueList = findByCliente(cliente);
        }
        String consec = pedidoService.generarConsecutivo("PED");
        Pedido pedido = Pedido.from(new Pedido(consec), cliente, "ND", "COR", new Bodega("PTA"), cliente.getCondicionPago(), usuario);
        int i = 1;
        for (Empaque empaque : empaqueList) {
            if(empaque.getPedido() != null) {
                throw new BusinessValidationException("El pedido ya ha sido generado para este cliente");
            }
            PedidoLinea linea = PedidoLinea.from(pedido, (short)0, empaque.getArticulo(), articuloService.getPrecioArticulo(empaque.getArticulo().getArticulo(), cliente.getNivelPrecio(), cliente.getMonedaNivel()), empaque.getPesoNeto(), new Bodega(pedido.getBodega()));
            linea.setArticulo(empaque.getArticulo());
            linea.setCantidadPedida(empaque.getPesoNeto());
            pedidoService.agregarLinea(pedido, linea);
        }        
        actualizarTotalesPedido(pedido);
        pedidoService.save(pedido);
        empaqueList.forEach(e -> e.setPedido(pedido.getPedido()));
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
}
