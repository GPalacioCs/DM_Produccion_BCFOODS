package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.entities.Pesada;

import javax.ejb.Stateless;

@Stateless
public class PesadaDAO extends AbstractDAO<Pesada> {
    
    public PesadaDAO() {
        super(Pesada.class);
    }
    
    public int getMaxCaja(OrdenProduccion ordenProduccion, Cliente cliente) {
        String jpql = "select max(p.caja) from Pesada p where p.ordenCompra.ordenProduccion = :ordenProduccion and p.cliente = :cliente";
        Object result = getEm().createQuery(jpql)
                .setParameter("ordenProduccion", ordenProduccion)
                .setParameter("cliente", cliente)
                .getSingleResult();
    
        if (result instanceof Integer) {
            return (int) result;
        }
        
        return 0;
    }

    public void setEnProduccion(Pesada pesada){
        String jpql = "update Pesada p set p.enProduccion = 1 where p.id = :pesadaId";
        getEm().createQuery(jpql)
                .setParameter("pesadaId", pesada.getId())
                .executeUpdate();
    }
}
