package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.OrdenCompra;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrdenCompraDAO extends AbstractDAO<OrdenCompra> {

    public OrdenCompraDAO() {
        super(OrdenCompra.class);
    }

    public List<OrdenCompra> findByModuloOrigen(String modulo) {
        String jpql = "select oc from OrdenCompra oc where oc.moduloOrigen = :mo";
        return getEm().createQuery(jpql)
                .setParameter("mo", modulo).getResultList();
    }

    public List<OrdenCompra> findAllOnBackorderAndTransit() {
        String query = "select oc from OrdenCompra oc " +
                "where oc.ordenCompra in (select oc2.ordenCompra from OrdenCompra oc2, OrdenCompraLinea ocl where oc2.estado in ('I','E') " +
                "and ocl.cantidadOrdenada - ocl.cantidadRecibida - ocl.cantidadEmbarcada > 0 and oc.ordenCompra = ocl.ordenCompraLineaId.ordenCompra )";
        return getEm().createQuery(query).getResultList();
    }

    public List<OrdenCompra> findAllOnBackorderAndTransitByProveedor(String proveedor) {
        String query = "select oc from OrdenCompra oc " +
                "where oc.ordenCompra in (select oc2.ordenCompra from OrdenCompra oc2, OrdenCompraLinea ocl where oc2.estado in ('I','E') " +
                "and ocl.cantidadOrdenada - ocl.cantidadRecibida - ocl.cantidadEmbarcada > 0 and oc.ordenCompra = ocl.ordenCompraLineaId.ordenCompra) and oc.proveedor.proveedor = :proveedor";
        return getEm()
                .createQuery(query)
                .setParameter("proveedor", proveedor)
                .getResultList();
    }
}
