/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.EstrucManufactura;

import javax.ejb.Stateless;


/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucManufacturaDAO extends AbstractDAO<EstrucManufactura> {

    public EstrucManufacturaDAO() {
        super(EstrucManufactura.class);
        
    }
    public EstrucManufactura getByArticuloString(String articulo)
    {
        String jpql = "SELECT e FROM EstrucManufactura e WHERE e.estrucManufacturaPK.articulo = :articulo";
        return getEm().createQuery(jpql,EstrucManufactura.class).
               setParameter("articulo", articulo).getSingleResult();
    }
    public String getLastVersion(String articulo)
    {
        return getEm().createQuery("SELECT e.estrucManufacturaPK.version FROM EstrucManufactura e WHERE e.estrucManufacturaPK.articulo = :articulo",String.class)
                .setParameter("articulo", articulo).getSingleResult();
    }
    public boolean existEstrucManufactura(String articulo)
    {
        return ! getEm().createQuery("SELECT e FROM EstrucManufactura e WHERE e.estrucManufacturaPK.articulo = :articulo",String.class)
            .setParameter("articulo", articulo).getResultList().isEmpty();
    }
}
