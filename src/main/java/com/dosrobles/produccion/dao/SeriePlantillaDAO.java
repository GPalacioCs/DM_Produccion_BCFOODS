/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.SeriePlantilla;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class SeriePlantillaDAO extends AbstractDAO<SeriePlantilla> {

    public SeriePlantillaDAO() {
        super(SeriePlantilla.class);
    }
    
}
