/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.corpsoft.csvtigersoftland.entities.ConsecutivoFa;
import com.dosrobles.produccion.dao.PedidoDAO;
import com.dosrobles.produccion.entities.Pedido;
import com.dosrobles.produccion.entities.PedidoLinea;
import com.dosrobles.produccion.utils.Utils;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pc
 */
@Stateless
public class PedidoService extends AbstractService<PedidoDAO, Pedido> {
    
    @Inject
    ConsecutivoFaService consecutivoFaService;
    
    public void agregarLinea(Pedido pedido, PedidoLinea pedidoLinea) {
        int max = pedido.getPedidoLineaList().stream().mapToInt(pl -> (int)pl.getPedidoLineaPK().getPedidoLinea()).max().orElse(0);
        PedidoLinea linea = pedido.getPedidoLineaList().stream().filter(pl -> pl.getArticulo().getArticulo().equals(pedidoLinea.getArticulo().getArticulo()))
                .findFirst().orElse(pedidoLinea);
        if(linea == pedidoLinea) {
            linea.getPedidoLineaPK().setPedidoLinea((short) (max + 1));
            pedido.getPedidoLineaList().add(linea);
        } else {
            linea.setCantidadPedida(linea.getCantidadPedida().add(pedidoLinea.getCantidadPedida()));
            linea.setCantidadAFactura(linea.getCantidadPedida());
        }        
    }
    
    public String generarConsecutivo(String idConsecutivo) {
                ConsecutivoFa consecutivo = consecutivoFaService.find(idConsecutivo);
		String valorNuevo = Utils.generarConsecutivo(consecutivo.getValorConsecutivo());
//		repo.updateConsecutivo(valorNuevo, consecutivo);
                consecutivo.setValorConsecutivo(valorNuevo);
		return valorNuevo;
    }
}
