package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Clasificacion;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ClasificacionDAO extends AbstractDAO<Clasificacion> {    

    public ClasificacionDAO() {
        super(Clasificacion.class);
    }
    
    public List<Clasificacion> getClasificacionesPorAgrupacion(short agrupacion) {
        String jpql = "select c from Clasificacion c where c.agrupacion = :agrupacion";
        
        return getEm().createQuery(jpql, Clasificacion.class)
                .setParameter("agrupacion", agrupacion)
                .getResultList();
    }
    
}
