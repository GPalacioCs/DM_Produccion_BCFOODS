/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.TipoCambioHistDao;
import com.dosrobles.produccion.entities.TipoCambioHist;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author DEV-PC
 */
@Stateless
public class TipoCambioHistService extends AbstractService<TipoCambioHistDao, TipoCambioHist> {
     public BigDecimal getTipoCambioActual(Date fecha) {         
         return this.dao.getTipoCambioActual(fecha);
     }
     
      public BigDecimal getTipoCambioActual() {
         return this.getTipoCambioActual(new Date());
     }
}
