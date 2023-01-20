/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EstrucManufacturaDAO;
import com.dosrobles.produccion.entities.EstrucManufactura;

import javax.ejb.Stateless;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucManufacturaService extends AbstractService<EstrucManufacturaDAO, EstrucManufactura> {
    public EstrucManufactura findByArticuloString(String articulo)
    {
        return this.dao.getByArticuloString(articulo);
    }
    public String getLastVersion(String articulo)
    {
        return this.dao.getLastVersion(articulo);        
    }
    public boolean existEstrucManufactura(String articulo)
    {
        return this.dao.existEstrucManufactura(articulo);
    }
}
