/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.AbstractDAO;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.Constants;
import com.dosrobles.produccion.utils.LazyQueryObject;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 * @param <D>
 */
public abstract class AbstractService<D extends AbstractDAO, E> {
    
    @Inject
    protected D dao;
    
    public void setSchema(String schema) {
        dao.setSchema(schema);
    }
    
    public void lazyFind(LazyQueryObject lqo) {
        dao.lazyFind(lqo);
    }
    
    public List<E> findAll() {
        return findAll(Constants.DEFAULT_SCHEMA);
    }
    
    public List<E> findAll(String schema) {
        return dao.findAll(schema);
    }
    
    public E find(Object id) {
        return (E) dao.find(id);
    }
    
    public E find(Object id, String schema) {
        return (E) dao.find(id, schema);
    }
    
    public E findByEntity(E entity) {
        if(entity == null) return null;
        E dbEntity = (E) dao.findByEntity(entity);
        return dbEntity;
    }
    
    public E save(E entity) throws BusinessValidationException {
        return save(entity, Constants.DEFAULT_SCHEMA);
    }
    
    public E save(E entity, String schema) throws BusinessValidationException {
        return (E) dao.save(entity, schema);
    }
    
    public E insert(E entity) throws BusinessValidationException {
        return insert(entity, Constants.DEFAULT_SCHEMA);
    }
    
    public E insert(E entity, String schema) throws BusinessValidationException {
        return save(entity, schema);
    }
    
    public void delete(E entity) throws BusinessValidationException {
        throw new UnsupportedOperationException("Acción no soportada");
    }
    
    public void refresh (E entity) {
        dao.refresh(entity);
    }
    
    public void detach(E entity) {
        dao.getEm().detach(entity);
    }
    
}
