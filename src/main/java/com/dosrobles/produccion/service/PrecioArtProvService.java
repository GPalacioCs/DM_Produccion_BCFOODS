package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.PrecioArtProvDAO;
import com.dosrobles.produccion.entities.PrecioArtProv;
import com.dosrobles.produccion.entities.Proveedor;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PrecioArtProvService extends AbstractService<PrecioArtProvDAO, PrecioArtProv> {
    
    public List<PrecioArtProv> findByProveedor(Proveedor proveedor) {
        return dao.findByProveedor(proveedor);
    }

}
