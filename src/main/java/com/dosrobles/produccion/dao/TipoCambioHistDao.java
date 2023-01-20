/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.TipoCambioHist;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class TipoCambioHistDao extends AbstractDAO<TipoCambioHist>{
    
    public TipoCambioHistDao() {
        super(TipoCambioHist.class);
    }
    
    public BigDecimal getTipoCambioActual(Date fecha)
    {
        TipoCambioHist t = this.getEm().createQuery("select t from TipoCambioHist t where t.tipoCambioHistPK.fecha <= :fecha order by t.tipoCambioHistPK.fecha desc",
                TipoCambioHist.class).setParameter("fecha", fecha).setMaxResults(1).getSingleResult();
        
        if(t!=null)
            return t.getMonto();
        else
            return BigDecimal.ONE;
    }
            
    
    
}
