/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.EstrucMaterial;

import javax.ejb.Stateless;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucMaterialDAO extends AbstractDAO<EstrucMaterial> {
    
    public EstrucMaterialDAO() {
        super(EstrucMaterial.class);
    }
    
}
