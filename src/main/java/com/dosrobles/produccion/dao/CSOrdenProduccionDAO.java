/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.*;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Corpsoft S.A.
 */
@Stateless
public class CSOrdenProduccionDAO extends AbstractDAO<CSOrdenProduccion> {

    public CSOrdenProduccionDAO() {
        super(CSOrdenProduccion.class);
    }
    
    public List<CSOrdenProduccion> getOrdenesProduccion(Date fechaInicial, Date fechaFinal, List<String> estados) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<CSOrdenProduccion> cq = cb.createQuery(CSOrdenProduccion.class);
        Root<CSOrdenProduccion> root = cq.from(CSOrdenProduccion.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.between(root.<Date>get("fechaInicio"), fechaInicial, fechaFinal));
        predicates.add(root.get("estado").in(estados));
        cq.where(predicates.toArray(new Predicate[]{}));
        return getEm().createQuery(cq).getResultList();
    }
    
    public List<String> getPedidosString(Map<String, Object> filters) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<PedidoLinea> cq = cb.createQuery(PedidoLinea.class);
        Root<PedidoLinea> root = cq.from(PedidoLinea.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("articulo").get("clasificacion1").get("clasificacion"), "05"));
        predicates.add(cb.between(root.<Pedido>get("pedido").<Date>get("fechaPedido"), (Date)filters.get("fechaInicial"), (Date)filters.get("fechaFinal")));
        predicates.add(cb.equal(root.get("pedido").get("estado"), "N"));
        if(filters.get("nivelPrecio") != null) {
            predicates.add(cb.equal(root.get("pedido").get("cliente").get("nivelPrecio"), filters.get("nivelPrecio")));
        }
        
        if(filters.get("pedido1")!= null) {
            predicates.add(cb.between(root.<Pedido>get("pedido").<String>get("pedido"), (String)filters.get("pedido1"), (String)filters.get("pedido2")));
        }
        
        if(filters.get("cliente1") != null) {
            predicates.add(cb.between(root.<Pedido>get("pedido").<Cliente>get("cliente").<String>get("cliente"), (String)filters.get("cliente1"), (String)filters.get("cliente2")));
        }
        
        cq.where(predicates.toArray(new Predicate[]{}));
        return getEm().createQuery(cq).getResultList().stream().map(pl -> pl.getPedidoLineaPK().getPedido()).collect(Collectors.toList());
    }
    
    public List<String> getPedidosString(Map<String, Object> filters, int n) {
        String sql = "select pl.pedido from dos_robles.pedido_linea pl "
                + "join dos_robles.articulo a on a.articulo = pl.articulo and a.CLASIFICACION_1 = '05' "
                + "join dos_robles.pedido p on p.pedido = pl.pedido "
                + "where pl.estado = 'N' and p.fecha_pedido between ?1 and ?2 "
                + "and (p.nivel_precio = ?3 or ?3 is null) "
                + "and (pl.pedido.pedido between ?5 and ?6 or(:pedido1 is null and :pedido2 is null)) "
                + "and (pl.pedido.cliente.cliente >= :cliente1 and pl.pedido.cliente.cliente <= :cliente2 or (:cliente1 is null and :cliente2 is null)) "
                + "group by pl.pedido ";
        List list = getEm().createNativeQuery(sql)
                .setParameter(1, filters.get("fechaInicial"))
                .setParameter(2, filters.get("fechaFinal"))
                .setParameter(3, filters.get("nivelPrecio"))
                .setParameter(4, filters.get(""))
                .setParameter("cliente1", filters.get("cliente1"))
                .getResultList();
        
        return list;
    }
    
    public List<Pedido> getPedidos(Map<String, Object> filters) {
        String jpql = "select p from Pedido p where p.pedido in :pedidos "
                + "and p not in (select pop.pedido from PedidoOrdenProduccion pop)";
        
        List<String> pedidosString = getPedidosString(filters);
        if(pedidosString.isEmpty()) {
            return new ArrayList<>();
        }
        
        return getEm().createQuery(jpql, Pedido.class)
                .setParameter("pedidos", getPedidosString(filters))
                .getResultList();
    }
    
    public List<String> getNivelesPrecio() {
        String sql = "select distinct np.nivel_precio from dos_robles.nivel_precio np order by np.nivel_precio";
        
        return getEm().createNativeQuery(sql).getResultList();
    }
    public List<ProductoPedido> getProductosPedidos(List<String> pedidos) {
//        String jpql = "select pl.articulo, sum(pl.cantidadPedida) as cantidad from PedidoLinea pl "
//                + "where pl.pedido.pedido in :pedidos and pl.articulo.clasificacion1.clasificacion = '05' "
//                + "group by pl.articulo";
        
        StringJoiner joiner = new StringJoiner(",", "(", ")");
        for (String pedido : pedidos) {
            joiner.add("'"+pedido+"'");
        }
    
        String sql = "select pl.articulo, sum(cantidad_pedida) as cantidad from dos_robles.pedido_linea pl "
                + "join DOS_ROBLES.ARTICULO a on a.articulo = pl.articulo "
                + "where a.CLASIFICACION_1 = '05' and pedido in "+joiner.toString()+" "
                + "group by pl.ARTICULO";
        
        return getEm().createNativeQuery(sql, ProductoPedido.class)
                .getResultList();        
        
    }
    
    public List<MateriaPrimaConsumo> getMateriaPrimaConsumoList(List<PaqueteOrdenProduccion> paquetes) {
        String jpql = "select mp from MateriaPrima mp where mp.etapa.articulo in :articulos and mp.etapa.etapaPK.etapa = 1";
        
        List<Articulo> articulos = paquetes.stream().map(p -> p.getArticulo()).collect(Collectors.toList());
        
        List<MateriaPrima> materias = getEm().createQuery(jpql, MateriaPrima.class)
                .setParameter("articulos", articulos)
                .getResultList();
        
        List<MateriaPrimaConsumo> mpcList = new ArrayList<>();
        materias.forEach(m -> {
            MateriaPrimaConsumo mpc = new MateriaPrimaConsumo(m.getArticuloHijo().getArticulo(), m.getArticuloHijo());
            mpc.setArticuloHijo(m.getArticuloHijo());
            BigDecimal cantTotal = paquetes.stream().filter(p -> p.getArticulo().equals(m.getEtapa().getArticulo()))
                    .map(p -> p.getCantidad()).reduce(BigDecimal.ZERO, BigDecimal::add);
            mpc.setCantTotal(cantTotal.multiply(m.getCantidad()));
            mpcList.add(mpc);
        });
        return mpcList;
    }
    
    public List<CSOrdenProduccion> getOrdenesProduccion(Map<String, Object> filters) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<CSOrdenProduccion> cq = cb.createQuery(CSOrdenProduccion.class);
        Root<CSOrdenProduccion> root = cq.from(CSOrdenProduccion.class);
        List<Predicate> predicates = new ArrayList<>();
        boolean masivo = true;
        if(filters.get("masivo")!= null)
            masivo = (boolean)filters.get("masivo");
        if(masivo) {
            predicates.add(cb.equal(root.get("articulo").get("articulo"), "ND"));
        } else {
            predicates.add(cb.notEqual(root.get("articulo").get("articulo"), "ND"));
        }
        boolean showTerminado = false;
        if(filters.get("terminado")!=null) {
            showTerminado = (boolean) filters.get("terminado");
        }
        
        if(!showTerminado) {
            predicates.add(cb.notEqual(root.get("estado"), "T"));
        }
                
        cq.where(predicates.toArray(new Predicate[]{}));
        
        return getEm().createQuery(cq).getResultList();
    }
    
}
