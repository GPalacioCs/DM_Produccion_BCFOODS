package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.PrecioArtProv;
import com.dosrobles.produccion.entities.Proveedor;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PrecioArtProvDAO extends AbstractDAO<PrecioArtProv> {
    
    public PrecioArtProvDAO() {
        super(PrecioArtProv.class);
    }
    
    public List<PrecioArtProv> findByProveedor(Proveedor proveedor) {
        return getEm().createQuery("select pap from PrecioArtProv pap where pap.proveedor = :prov")
                .setParameter("prov", proveedor)
                .getResultList();
    }
    
}
