package com.dosrobles.produccion.dao.embarque;

import com.dosrobles.produccion.dao.AbstractDAO;
import com.dosrobles.produccion.entities.embarque.Embarque;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmbarqueDAO extends AbstractDAO<Embarque> {
    public EmbarqueDAO() {
        super(Embarque.class);
    }

    public List<Embarque> findAllPlaneados() {
        String jpql = "SELECT E FROM Embarque E where e.Estado = 'P' order by e.Embarque DESC";
        return getEm().createQuery(jpql,Embarque.class).getResultList();
    }
    public List<Embarque> findAllRecibidosSinEnviar() {
        String jpql = "SELECT E FROM Embarque E where e.Estado = 'R' AND e.Enviado = 'N' order by e.Embarque DESC";
        return getEm().createQuery(jpql,Embarque.class).getResultList();
    }
}
