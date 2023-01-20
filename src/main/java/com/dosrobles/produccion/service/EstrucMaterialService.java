/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EstrucMaterialDAO;
import com.dosrobles.produccion.entities.EstrucMaterial;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class EstrucMaterialService extends AbstractService<EstrucMaterialDAO,EstrucMaterial> {
    public List<EstrucMaterial> getListMaterial(String articulo, String version)
    {
        List<EstrucMaterial> res = this.dao.getEm().createQuery
        ("select e from EstrucMaterial e where e.estrucMaterialPK.articulo = :articulo and e.estrucMaterialPK.version = :version",
        EstrucMaterial.class).setParameter("articulo", articulo).setParameter("version",version)
        .getResultList();
        return res;
    }
}
