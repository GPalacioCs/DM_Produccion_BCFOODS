package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.entities.Empaque;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pc
 */
@Stateless
public class EmpaqueDAO extends AbstractDAO<Empaque> {
    public EmpaqueDAO() {
        super(Empaque.class);
    }
    
    public void deleteRemovedEmpaques(List<Empaque> empaqueList) {
        getEm().createQuery("delete from Empaque e where e.id not in :empaques")
                .setParameter("empaques", empaqueList.stream().map(e -> e.getId()).collect(Collectors.toList()))
                .executeUpdate();
    }
    
    public List<Empaque> findByCliente(Cliente cliente) {
        return getEm().createQuery("select e from Empaque e where e.cliente = :cliente  ")
                .setParameter("cliente", cliente)
                .getResultList();
    }
}
