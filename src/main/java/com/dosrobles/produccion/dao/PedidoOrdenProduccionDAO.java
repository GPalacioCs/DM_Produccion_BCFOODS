/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadosProd;
import com.dosrobles.produccion.utils.LazyQueryObject;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class PedidoOrdenProduccionDAO extends AbstractDAO<PedidoOrdenProduccion> {

    public PedidoOrdenProduccionDAO() {
        super(PedidoOrdenProduccion.class);
    }
    
    public List<PedidoOrdenProduccion> filtrar(LazyQueryObject<PedidoOrdenProduccion> lqo) {
        Class<PedidoOrdenProduccion> clazz = PedidoOrdenProduccion.class;
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<PedidoOrdenProduccion> cq = cb.createQuery(clazz);
        Root<PedidoOrdenProduccion> pop = cq.from(clazz);
        List<Predicate> predicates = new ArrayList<>();
        
        
        for(List<LazyQueryObject.CustomFilter> cfList : lqo.getCustomFiltersList()) {            
            List<Predicate> orPredicates = new ArrayList<>();
            for (LazyQueryObject.CustomFilter cf : cfList) {
                if("clasificacion".equals(cf.getField())) {
                    orPredicates.add(cb.equal(pop.get("ordenProduccion").get("articulo").get("clasificacion1"), (Clasificacion)cf.getValue()));
                }
                else if("pedidoDesde".equals(cf.getField())) {
                    orPredicates.add(cb.greaterThanOrEqualTo(pop.<PedidoOrdenProduccionPK>get("pedidoOrdenProduccionPK").<String>get("pedido"), (String) cf.getValue()));
                } else if("pedidoHasta".equals(cf.getField())) {
                    orPredicates.add(cb.lessThanOrEqualTo(pop.<PedidoOrdenProduccionPK>get("pedidoOrdenProduccionPK").<String>get("pedido"), (String) cf.getValue()));
                } else if("ordenDesde".equals(cf.getField())) {
                    orPredicates.add(cb.greaterThanOrEqualTo(pop.<PedidoOrdenProduccionPK>get("pedidoOrdenProduccionPK").<String>get("ordenProduccion"), (String) cf.getValue()));
                } else if("ordenHasta".equals(cf.getField())) {
                    orPredicates.add(cb.lessThanOrEqualTo(pop.<PedidoOrdenProduccionPK>get("pedidoOrdenProduccionPK").<String>get("ordenProduccion"), (String) cf.getValue()));
                } else if("fechaInicioDesde".equals(cf.getField())) {
                    orPredicates.add(cb.greaterThanOrEqualTo(pop.<CSOrdenProduccion>get("ordenProduccion").<Date>get("fechaInicio"), (Date) cf.getValue()));
                } else if("fechaInicioHasta".equals(cf.getField())) {
                    orPredicates.add(cb.lessThanOrEqualTo(pop.<CSOrdenProduccion>get("ordenProduccion").<Date>get("fechaInicio"), (Date) cf.getValue()));
                } else if("fechaFinDesde".equals(cf.getField())) {
                    orPredicates.add(cb.greaterThanOrEqualTo(pop.<CSOrdenProduccion>get("ordenProduccion").<Date>get("fechaFin"), (Date) cf.getValue()));
                } else if("fechaFinHasta".equals(cf.getField())) {
                    orPredicates.add(cb.lessThanOrEqualTo(pop.<CSOrdenProduccion>get("ordenProduccion").<Date>get("fechaFin"), (Date) cf.getValue()));
                } else if ("estado".equals(cf.getField())) {
                    for (String estado : (List<String>) cf.getValue()) {
                        if ("enproceso".equals(estado)) {
                            orPredicates.add(cb.equal(pop.get("ordenProduccion").get("estado"), EstadosProd.LIBERADO.getEstado()));
                        } else if ("terminado".equals(estado)) {
                            orPredicates.add(cb.equal(pop.get("ordenProduccion").get("estado"), EstadosProd.TERMINADO.getEstado()));
                        } else if ("asignado".equals(estado)) {
                            orPredicates.add(cb.equal(pop.get("ordenProduccion").get("estado"), EstadosProd.PLANEADO.getEstado()));                            
                        }
                    }
                } else if ("articulo".equals(cf.getField())) {
                    orPredicates.add(cb.equal(pop.get("ordenProduccion").get("articulo"), (Articulo) cf.getValue()));
                }
            }
            predicates.add(cb.or(orPredicates.toArray(new Predicate[]{})));        
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        List<PedidoOrdenProduccion> pedidoList = getEm().createQuery(cq).getResultList();
        lqo.setResultList(pedidoList);
        return pedidoList;
    }
    
}
