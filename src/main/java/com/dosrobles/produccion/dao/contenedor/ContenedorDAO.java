package com.dosrobles.produccion.dao.contenedor;

import com.dosrobles.produccion.dao.AbstractDAO;
import com.dosrobles.produccion.entities.contenedor.Contenedor;
import com.dosrobles.produccion.entities.contenedor.ContenedorDetalle;
import com.dosrobles.produccion.entities.traslado.Traspaso;

import javax.ejb.Stateless;
import java.util.List;

@Stateless

public class ContenedorDAO extends AbstractDAO<Contenedor> {
    public ContenedorDAO() {
        super(Contenedor.class);
    }

    public String getNextContenedor () {
        List<Contenedor> results = getEm().createQuery("SELECT T FROM Contenedor T order by T.ContenedorId desc", Contenedor.class).setMaxResults(1).getResultList();
        if (results.isEmpty()) {
            return "C00000001";
        }
        int numContenedor = Integer.parseInt(results.get(0).getContenedorId().substring(1));
        int nextContenedor = numContenedor + 1;
        return String.format("C%08d", nextContenedor);
    }

    public List<Contenedor> getFreeContenedores() {
        List<Contenedor> results = getEm().createQuery("SELECT C FROM Contenedor C where C.ContenedorId not in (SELECT CD.Contenido from ContenedorDetalle CD)", Contenedor.class).getResultList();
        return results;
    }
}
