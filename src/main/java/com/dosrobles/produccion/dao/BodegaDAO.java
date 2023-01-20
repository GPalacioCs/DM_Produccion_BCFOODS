package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Articulo;
import com.dosrobles.produccion.entities.Bodega;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Admin
 */
@Stateless
public class BodegaDAO extends AbstractDAO<Bodega> {
    
   public BodegaDAO(){
    super(Bodega.class);   
   }
   
   public List<Bodega> getBodegasPorArticulo(Articulo articulo) {
       String jpql = "select eb.bodega from ExistenciaBodega eb where eb.articulo = :articulo";
       
       return getEm().createQuery(jpql, Bodega.class)
               .setParameter("articulo", articulo)
               .getResultList();
   }
   
}

