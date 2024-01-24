package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.traslado.Traspaso;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TraspasoDAO extends AbstractDAO<Traspaso> {
    public TraspasoDAO() {
        super(Traspaso.class);
    }

    public String getNextTraspaso(String bodega) {
        List<Traspaso> results = getEm().createQuery("SELECT T FROM Traspaso T where T.n_Traspaso LIKE :bodega order by T.n_Traspaso desc", Traspaso.class).setParameter("bodega", "%" + bodega + "%").setMaxResults(1).getResultList();
        if (results.isEmpty()) {
            return bodega + "001";
        }
        int numTraspaso = Integer.parseInt(results.get(0).getN_Traspaso().substring(3));
        int nextTraspaso = numTraspaso + 1;
        return bodega + String.format("%02d", nextTraspaso);
    }

}
