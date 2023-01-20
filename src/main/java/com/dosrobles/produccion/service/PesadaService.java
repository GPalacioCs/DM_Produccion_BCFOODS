package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.PesadaDAO;
import com.dosrobles.produccion.entities.Cliente;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.entities.Pesada;

import javax.ejb.Stateless;

@Stateless
public class PesadaService extends AbstractService<PesadaDAO, Pesada> {
    
    public int getMaxCaja(OrdenProduccion ordenProduccion, Cliente cliente) {
        return dao.getMaxCaja(ordenProduccion, cliente);
    }

    public void setEnProduccion(Pesada pesada){
        dao.setEnProduccion(pesada);
    }
}
