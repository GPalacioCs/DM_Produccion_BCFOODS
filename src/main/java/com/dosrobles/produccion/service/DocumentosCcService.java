/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.DocumentosCcDAO;
import com.dosrobles.produccion.entities.DocumentosCc;
import com.dosrobles.produccion.entities.OrdenProduccion;

/**
 *
 * @author Administrador
 */
public class DocumentosCcService extends AbstractService<DocumentosCcDAO, DocumentosCc> {
    
    public void generarDocumentoOrdenProduccion(OrdenProduccion orden, String usuario) {
//        String codCliente = "CR0001";
//        DocumentosCc doc = new DocumentosCc(codigoFactura, "N/C");
//        doc.setAplicacion("Cargado de Devolución: "+codigoFactura);
//        doc.setFechaDocumento(factura.getFecha());
//        doc.setFecha(factura.getFecha());
//        doc.setMonto(factura.getTotalFactura());
//        doc.setSaldo(doc.getMonto());
//        doc.setMontoLocal(Utils.sumBigDecimals(orden.getMaquilaList(), m -> m.getMonto()));
//        doc.setSaldoLocal(Utils.sumBigDecimals(orden.getMaquilaList(), m -> m.getMonto()));
//        doc.setMontoDolar("D".equals(factura.getMoneda()) ? doc.getMonto() : Utils.loc2dol(doc.getMonto(), tipoCambio));
//        doc.setSaldoDolar("D".equals(factura.getMoneda()) ? doc.getSaldo() : Utils.loc2dol(doc.getSaldo(), tipoCambio));
//        if(monedaLocal.equals(factura.getCliente1().getMoneda()) && "L".equals(factura.getMoneda())
//                || monedaDolar.equals(factura.getCliente1().getMoneda()) && "D".equals(factura.getMoneda())) {
//            doc.setMontoCliente(doc.getMonto());
//            doc.setSaldoCliente(doc.getSaldo());            
//        } else if (monedaLocal.equals(factura.getCliente1().getMoneda())) {
//            doc.setMontoCliente(Utils.dol2loc(doc.getMonto(), tipoCambio));
//            doc.setSaldoCliente(Utils.dol2loc(doc.getSaldo(), tipoCambio));
//        } else {
//            doc.setMontoCliente(Utils.loc2dol(doc.getMonto(), tipoCambio));
//            doc.setSaldoCliente(Utils.loc2dol(doc.getSaldo(), tipoCambio));
//        }        
//        doc.setTipoCambioMoneda(tipoCambio);
//        doc.setTipoCambioDolar(tipoCambio);
//        doc.setTipoCambioClient(tipoCambio);
//        doc.setTipoCambActLoc(tipoCambio);
//        doc.setTipoCambActDol(tipoCambio);
//        doc.setTipoCambActCli(tipoCambio);
//        doc.setSubtotal(factura.getTotalFactura());
//        doc.setDescuento(BigDecimal.ZERO);
//        doc.setImpuesto1(factura.getTotalImpuesto1());
//        doc.setImpuesto2(factura.getTotalImpuesto2());
//        doc.setRubro1(BigDecimal.ZERO);
//        doc.setRubro2(BigDecimal.ZERO);
//        doc.setMontoRetencion(BigDecimal.ZERO);
//        doc.setSaldoRetencion(BigDecimal.ZERO);
//        doc.setDependiente("S");
//        doc.setFechaUltCredito(Utils.stripTime(new Date()));
//        doc.setCargadoDeFact("S");
//        doc.setAprobado("S");
//        doc.setAsientoPendiente("S");
//        doc.setFechaUltMod(new Date());
//        doc.setNotas("");
//        doc.setClaseDocumento(factura.getClaseDocumento());
//        LocalDateTime ldtFactura = Utils.date2ldt(factura.getFecha());
//        Date fechaVence = Utils.ldt2date(ldtFactura.plusDays(factura.getCondicionPago1().getDiasNeto()));
//        doc.setFechaVence(fechaVence);
//        doc.setNumParcialidades((short)0);
//        doc.setFechaRevision(null);
//        doc.setCobrador(factura.getCobrador());
//        doc.setUsuarioUltMod(usuario);
//        doc.setCondicionPago(factura.getCondicionPago());
//        doc.setMoneda("D".equals(factura.getMoneda()) ? monedaDolar : monedaLocal);
//        doc.setCtaBancaria(null);
//        doc.setVendedor("ND");
//        String cliente = factura.getCliente();
//        doc.setClienteReporte(cliente);
//        doc.setClienteOrigen(cliente);
//        doc.setCliente(cliente);
//        doc.setSubtipo((short)0);
//        doc.setPorcIntcte(BigDecimal.ZERO);
//        doc.setUsuarioAprobacion(usuario);
//        doc.setFechaAprobacion(Utils.stripTime(factura.getFecha()));
//        doc.setAnulado("N");
//        doc.setCodigoImpuesto(null);
//        doc.setPais(factura.getPais());
//        doc.setDivisionGeografica1(StringUtils.trimToNull(factura.getDivisionGeografica1()));
//        doc.setDivisionGeografica2(StringUtils.trimToNull(factura.getDivisionGeografica2()));
//        doc.setBaseImpuesto1(factura.getBaseImpuesto1());
//        doc.setBaseImpuesto2(factura.getBaseImpuesto2());
//        doc.setDependienteGp("N");
//        doc.setSaldoTrans(BigDecimal.ZERO);
//        doc.setSaldoTransLocal(BigDecimal.ZERO);
//        doc.setSaldoTransDolar(BigDecimal.ZERO);
//        doc.setFechaProyectada(fechaVence);
//        doc.setPorcRecuperacion(BigDecimal.ZERO);
//        doc.setTipoAsiento(null);
//        doc.setPaquete(null);
//        doc.setSaldoTransCli(BigDecimal.ZERO);
//        doc.setDocumentoFiscal(null);
//        doc.setFacturado("N");
//        doc.setGeneraDocFe("N");
//        doc.setTasaImpositiva(null);
//        doc.setTasaImpositivaPorc(BigDecimal.ZERO);
//        doc.setTasaCree1(null);
//        doc.setTasaCree1Porc(BigDecimal.ZERO);
//        doc.setTasaCree2(null);
//        doc.setTasaCree2Porc(BigDecimal.ZERO);
//        doc.setTasaGanOcasionalPorc(BigDecimal.ZERO);
//        BigDecimal saldoCliente = factura.getCliente1().getSaldo().subtract(monedaDolar.equals(factura.getCliente1().getMoneda()) ? doc.getSaldoDolar() : doc.getSaldoLocal());
//        doc.setSaldoCliente(saldoCliente);
//        factura.getCliente1().setSaldo(saldoCliente);
//        documentosCcService.insert(doc);
    }
}
