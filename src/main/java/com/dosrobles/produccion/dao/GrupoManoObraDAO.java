package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.GrupoManoObra;

import javax.ejb.Stateless;

@Stateless
public class GrupoManoObraDAO extends AbstractDAO<GrupoManoObra> {
    
    public GrupoManoObraDAO() {
        super(GrupoManoObra.class);
    }
}
