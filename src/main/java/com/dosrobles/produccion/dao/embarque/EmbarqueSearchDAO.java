package com.dosrobles.produccion.dao.embarque;

import com.dosrobles.produccion.dao.AbstractDAO;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueSearch;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmbarqueSearchDAO extends AbstractDAO<EmbarqueSearch> {
    public EmbarqueSearchDAO() {
        super(EmbarqueSearch.class);
    }

    public List<EmbarqueSearch> findAllRecibidosSinEnviar() {
        String jpql = "SELECT E FROM EmbarqueSearch E where e.Estado = 'R' AND e.Enviado = 'N' and e.Embarque in (select e.Id.Embarque from EmbarqueLinea e where e.Bodega.bodega = 'MPB') order by e.Embarque DESC";
        return getEm().createQuery(jpql,EmbarqueSearch.class).getResultList();
    }

    public List<EmbarqueSearch> findAllRecibidosSinEnviarByBodega(String bodega) {
        String jpql = "SELECT E FROM EmbarqueSearch E where e.Estado = 'R' AND e.Enviado = 'N' and e.Embarque in (select e.Id.Embarque from EmbarqueLinea e where e.Bodega.bodega = ?1) order by e.Embarque DESC";
        return getEm().createQuery(jpql,EmbarqueSearch.class).setParameter(1, bodega).getResultList();
    }
}
