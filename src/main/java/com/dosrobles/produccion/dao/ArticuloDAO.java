/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Clasificacion;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author KC
 */
@Stateless
public class ArticuloDAO extends AbstractDAO<Articulo> {
    
    public ArticuloDAO() {
        super(Articulo.class);
    }

    @Override
    public List<Articulo> findAll() {
        String jpql = "SELECT a FROM Articulo a where a.activo = 'S' order by a.descripcion";
        return getEm().createQuery(jpql, Articulo.class).getResultList();
    }
    
    public Articulo findByArticuloString(String articulo) {
        String jpql = "SELECT a FROM Articulo a where a.articulo = :articulo";
        return getEm().createQuery(jpql, Articulo.class)
                .setParameter("articulo", articulo)
                .getSingleResult();
    }
    
    public List<Articulo> cargarServicios() {
        String jpql = "select a from Articulo a where a.tipo = 'V' and a.activo = 'S'";
        return getEm().createQuery(jpql, Articulo.class).getResultList();
    }
    
    public List<Articulo> getArticulosProduccion() {
        String jpql = "select a from Articulo a where a.tipo = 'T' and a.origenCorp = 'C' and a.activo = 'S'";
        return getEm().createQuery(jpql, Articulo.class).getResultList();
    }

    public List<Articulo> getArticulosMP() {
        String jpql = "select a from Articulo a where a.tipo = 'M' and a.origenCorp = 'T' and a.activo = 'S'";
        return getEm().createQuery(jpql, Articulo.class).getResultList();
    }
    
    
    public List<Articulo> getArticulosActivos() {
        String jpql = "select a from Articulo a where a.activo = 'S'";
        return getEm().createQuery(jpql, Articulo.class).getResultList();
    }
    
    public List<Articulo> getArticulosPorTipo(String... tipo) {
        String jpql = "select a from Articulo a where a.tipo in :tipo and a.activo = 'S'";        
        return getEm().createQuery(jpql, Articulo.class)
                .setParameter("tipo", Arrays.asList(tipo))
                .getResultList();
    }
    
    public List<Articulo> getArticulosPorClasificacion(List<Clasificacion> clasificaciones) {
        String jpql = "select a from Articulo a where a.clasificacion1 in :clasificaciones and a.activo = 'S'";
        return getEm().createQuery(jpql, Articulo.class)
                .setParameter("clasificaciones", clasificaciones)
                .getResultList();
    }
    
    public boolean tieneOrdenes(Articulo articulo) {
        String jpql = "select count(o) from OrdenProduccion o where o.articulo = :articulo";
        
        return getEm().createQuery(jpql, Long.class)
                .setParameter("articulo", articulo)
                .getSingleResult() > 0;
    }
    
    
    public BigDecimal getPrecio(String articulo, String nivelPrecio, String moneda) throws BusinessValidationException {
        try {
            Object precio = (BigDecimal) getEm().createNativeQuery("select top 1 ap.PRECIO from ALINSA.ARTICULO_PRECIO ap "
                    + "join ALINSA.VERSION_NIVEL vn on vn.VERSION = ap.VERSION and vn.NIVEL_PRECIO = ap.NIVEL_PRECIO and vn.MONEDA = ap.moneda "
                    + "where vn.ESTADO = 'A' and vn.NIVEL_PRECIO = ?1 and vn.MONEDA = ?2 and ARTICULO = ?3 "
                    + "order by vn.VERSION DESC")
                    .setParameter(1, nivelPrecio)
                    .setParameter(2, moneda)
                    .setParameter(3, articulo)
                    .getSingleResult();
            return (BigDecimal) precio;
        } catch (NoResultException e) {
//            throw new BusinessValidationException(String.format("Error al obtener el precio del artículo %s", articulo));
            return BigDecimal.ZERO;
        }
    }
    
    public List<Articulo> getMaterialesDirectos(Clasificacion clasificacion) {
        String jpql = "select a from Articulo a where a.clasificacion1 = :clasificacion";
        return getEm().createQuery(jpql)
                .setParameter("clasificacion", clasificacion)
                .getResultList();
    }
    
    public List<Articulo> getArticulosIn(List<String> articulos) {
        String jpql = "select a from Articulo a where a.articulo in :artiuclos ";
        return getEm().createQuery(jpql)
                .setParameter("articulos", articulos)
                .getResultList();
    }
}