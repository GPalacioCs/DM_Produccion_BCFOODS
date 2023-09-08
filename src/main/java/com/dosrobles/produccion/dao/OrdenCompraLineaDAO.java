package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.OrdenCompra;
import com.dosrobles.produccion.entities.OrdenCompraLinea;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrdenCompraLineaDAO extends AbstractDAO<OrdenCompraLinea> {

    public OrdenCompraLineaDAO() {
        super(OrdenCompraLinea.class);
    }

    public List<OrdenCompraLinea> findAllPendingByOc(String oc) {
        String query = "select ocl from OrdenCompraLinea ocl where ocl.estado in ('E','I') and ocl.ordenCompraLineaId.ordenCompra = :oc and ocl.cantidadOrdenada - ocl.cantidadRecibida - ocl.cantidadEmbarcada > 0";
        return getEm().createQuery(query).setParameter("oc", oc).getResultList();
    }
}
