package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.OrdenCompra;
import com.dosrobles.produccion.entities.OrdenCompraLinea;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class OrdenCompraLineaDAO extends AbstractDAO<OrdenCompraLinea> {

    public OrdenCompraLineaDAO() {
        super(OrdenCompraLinea.class);
    }

    public List<OrdenCompraLinea> findAllPendingByOc(String oc) {
        String query = "select ocl from OrdenCompraLinea ocl where ocl.estado in ('E','I') and ocl.ordenCompraLineaId.ordenCompra = ?2 and ocl.cantidadOrdenada - (ocl.cantidadRecibida + ocl.cantidadEmbarcada) > ?1";
        return getEm().createQuery(query).setParameter(1, BigDecimal.ZERO).setParameter(2, oc).getResultList();
    }
}
