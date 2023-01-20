/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

//import com.corpsoftsa.erp.admin.entities.User;

import com.dosrobles.produccion.controllers.SchemaController;
import com.dosrobles.produccion.utils.Constants;
import com.dosrobles.produccion.utils.LazyQueryObject;
import com.dosrobles.produccion.utils.LazyQueryObject.CustomOper;
import com.dosrobles.produccion.utils.SchemaUtils;
import org.apache.commons.lang3.ClassUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 *
 * @author Corpsoft S.A.
 */
public abstract class AbstractDAO<E> {

    @PersistenceContext(unitName = "corpsoft-erpPU01")
    private EntityManager em01;
    @PersistenceContext(unitName = "corpsoft-erpPU02")
    private EntityManager em02;
//    @PersistenceContext(unitName = "corpsoft-erpPU03")
//    private EntityManager em03;
//    @PersistenceContext(unitName = "corpsoft-erpPU04")
//    private EntityManager em04;
//    @PersistenceContext(unitName = "corpsoft-erpPU05")
//    private EntityManager em05;
//    @PersistenceContext(unitName = "corpsoft-erpPU06")
//    private EntityManager em06;
//    @PersistenceContext(unitName = "corpsoft-erpPU07")
//    private EntityManager em07;
//    @PersistenceContext(unitName = "corpsoft-erpPU08")
//    private EntityManager em08;
//    @PersistenceContext(unitName = "corpsoft-erpPU09")
//    private EntityManager em09;
//    @PersistenceContext(unitName = "corpsoft-erpPU10")
//    private EntityManager em10;
//    
//    @PersistenceContext(unitName="resource-localPU")
//    protected EntityManager em2;

    private String schema = "MATCASA";
    private final Map<String, EntityManager> schemaMap = new HashMap<>();

    @Inject
    private SchemaController schemaController;

    private Class<E> clazz;
    
    @PostConstruct
    private void init() {
        buildSchemaMap();
    }

    public AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
    }

    public String getSchema() {
        return SchemaUtils.getSchema("schema01");
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

//    public User getUser() {
//        User user = new User();
//        user.setUsername("TESTUSER");
//        return user;
//    }

    public List<E> findAll() {
        return getEm().createQuery(String.format("select c from %s c", clazz.getSimpleName())).getResultList();
    }

    public List<E> findAll(String schema) {
        return getEm(schema).createQuery(String.format("select c from %s c", clazz.getSimpleName())).getResultList();
    }

    public void lazyFind(LazyQueryObject lqo) {
        lazyFind(lqo, new ArrayList<>());
    }

    public E find(Object id) {
        return (E) getEm().find(clazz, id);
    }

    public E find(Object id, String schema) {
        return (E) getEm(schema).find(clazz, id);
    }

    public void filter(List<List<LazyQueryObject.CustomFilter>> list, Class<E> clazz, List<Predicate> predicates, CriteriaBuilder cb, Root<E> root) {
        for (List<LazyQueryObject.CustomFilter> orFiltersList : list) {
            List<Predicate> orPredicates = new ArrayList<>();
            for (LazyQueryObject.CustomFilter customFilter : orFiltersList) {

                try {

                    Class<?> fieldType = ClassUtils.primitiveToWrapper(clazz.getDeclaredField(customFilter.getField()).getType());
                    String field = customFilter.getField();
                    if (customFilter.getOper() == CustomOper.ISNULL) {
                        orPredicates.add(cb.isNull(root.get(field)));
                    } else if (customFilter.getOper() == CustomOper.ISNOTNULL) {
                        orPredicates.add(cb.isNotNull(root.get(field)));
                    } else if (customFilter.getOper() == CustomOper.NOTEQ) {
                        orPredicates.add(cb.notEqual(root.get(field), customFilter.getValue()));
                    }
                    if (String.class.isAssignableFrom(fieldType)) {
                        if (customFilter.getOper() == CustomOper.EQUALS) {
                            orPredicates.add(cb.equal(root.get(field), customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.LIKE) {
                            orPredicates.add(cb.like(root.get(field), (String) customFilter.getValue()));
                        }
                    } else if (Number.class.isAssignableFrom(fieldType)) {
                        if (customFilter.getOper() == CustomOper.EQUALS) {
                            orPredicates.add(cb.equal(root.get(field), customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.GT) {
                            orPredicates.add(cb.greaterThan(root.<String>get(field), (String) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.LT) {
                            orPredicates.add(cb.lessThan(root.<String>get(field), (String) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.GTOREQ) {
                            orPredicates.add(cb.greaterThanOrEqualTo(root.<String>get(field), (String) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.LTOREQ) {
                            orPredicates.add(cb.lessThanOrEqualTo(root.<String>get(field), (String) customFilter.getValue()));
                        }
                    } else if (Date.class.isAssignableFrom(fieldType)) {
                        if (customFilter.getOper() == CustomOper.EQUALS) {
                            orPredicates.add(cb.equal(root.get(field), customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.GT) {
                            orPredicates.add(cb.greaterThan(root.<Date>get(field), (Date) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.LT) {
                            orPredicates.add(cb.lessThan(root.<Date>get(field), (Date) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.GTOREQ) {
                            orPredicates.add(cb.greaterThanOrEqualTo(root.<Date>get(field), (Date) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.LTOREQ) {
                            orPredicates.add(cb.lessThanOrEqualTo(root.<Date>get(field), (Date) customFilter.getValue()));
                        } else if (customFilter.getOper() == CustomOper.NOTEQ) {
                            orPredicates.add(cb.notEqual(root.get(field), (String) customFilter.getValue()));
                        }
                    } else if (Boolean.class.isAssignableFrom(fieldType)) {
                        if (customFilter.getOper() == CustomOper.EQUALS) {
                            orPredicates.add(cb.equal(root.<Boolean>get(field), customFilter.getValue()));
                        }
                    }

                } catch (NoSuchFieldException | SecurityException e) {
                    // TODO Auto-generated catch block
//                    e.printStackTrace();
                }
            }
            predicates.add(cb.or(orPredicates.toArray(new Predicate[]{})));
        }
    }

    public void lazyFind(LazyQueryObject<E> lqo, List<Predicate> predicates) {

        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(clazz);
        Root<E> root = cq.from(clazz);
        CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);

        Set<String> keyset = lqo.getFilters().keySet();
        String rangeSeparator = "-";
        String datePattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

        for (String key : keyset) {
            String[] values = ((String) lqo.getFilters().get(key)).split(rangeSeparator);
            try {
                Class<?> fieldType = clazz.getDeclaredField(key).getType();

                if (String.class.isAssignableFrom(fieldType)) {
                    String value = (String) lqo.getFilters().get(key);
                    String[] orValues = value.split(",");
                    List<Predicate> orPredicates = new ArrayList<>();
                    for (String v : orValues) {
                        orPredicates.add(cb.like(root.get(key), "%" + v.trim() + "%"));
                    }
                    predicates.add(cb.or(orPredicates.toArray(new Predicate[]{})));
                } else if (Number.class.isAssignableFrom(ClassUtils.primitiveToWrapper(fieldType))) {
                    try {
                        if (values.length >= 2) {
                            try {
                                predicates.add(cb.greaterThanOrEqualTo(root.<String>get(key), values[0].trim()));
                                predicates.add(cb.lessThanOrEqualTo(root.<String>get(key), values[1].trim()));
                            } catch (Exception e) {
                            }
                        } else if (values.length == 1) {
                            predicates.add(cb.equal(root.get(key), ClassUtils.primitiveToWrapper(fieldType).getMethod("valueOf", String.class).invoke(null, values[0].trim())));
                        }
                    } catch (InvocationTargetException e) {
                        if (e.getTargetException() != null && e.getTargetException().getClass().equals(NumberFormatException.class)) {
                            predicates.add(cb.isNull(root));
                            predicates.add(cb.isNotNull(root));
                        }
                    } catch (ReflectiveOperationException e) {
                        if (e.getCause() != null && e.getCause().equals(NumberFormatException.class)) {
                            System.out.println("Number format exception occurred");
                        } else {
                            e.printStackTrace();
                        }
                    }
                } else if (Date.class.isAssignableFrom(fieldType)) {
                    if (values.length >= 2) {
                        try {
                            LocalDate date = sdf.parse(values[1]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
                            predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(key), sdf.parse(values[0])));
                            predicates.add(cb.lessThanOrEqualTo(root.<Date>get(key), Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())));
                        } catch (ParseException e) {
                        }
                    } else if (values.length == 1) {
                        try {
                            LocalDate date = sdf.parse(values[0]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
                            predicates.add(cb.greaterThanOrEqualTo(root.<Date>get(key), sdf.parse(values[0])));
                            predicates.add(cb.lessThanOrEqualTo(root.<Date>get(key), Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())));
                        } catch (ParseException e) {
                        }
                    }
                } else if (Boolean.class.isAssignableFrom(ClassUtils.primitiveToWrapper(fieldType))) {
                    predicates.add(cb.equal(root.<Boolean>get(key), Boolean.valueOf(values[0])));
                }

            } catch (NoSuchFieldException | SecurityException e) {
                break;
            }
        }

        filter(lqo.getDefaultFiltersList(), clazz, predicates, cb, root);
        filter(lqo.getCustomFiltersList(), clazz, predicates, cb, root);

        cq.where(predicates.toArray(new Predicate[]{}));

        List<Order> orderList = new ArrayList<>();
        for (LazyQueryObject.CustomSort customSort : lqo.getCustomSortList()) {
            if (customSort.getOrder().equals("DESCENDING")) {
                orderList.add(cb.desc(root.get(customSort.getSortField())));
            } else {
                orderList.add(cb.asc(root.get(customSort.getSortField())));
            }
        }

        cq.orderBy(orderList);

        List<E> resultList = getEm().createQuery(cq).setFirstResult(lqo.getFirst()).setMaxResults(lqo.getPageSize()).getResultList();
        lqo.setResultList(resultList);
        cqCount.select(cb.count(root));
        if (cq.getRestriction() != null) {
            cqCount.where(cq.getRestriction());
        }
        lqo.setRowCount(getEm().createQuery(cqCount).getSingleResult().intValue());
    }

    public E save(E entity) {        
        return (E) getEm().merge(entity);
    }

    public E save(E entity, String schema) {        
        return (E) getEm(schema).merge(entity);
    }

    public void delete(Object entity) {
        getEm().remove(getEm().merge(entity));
    }

    public E findByEntity(E entity) {
        Object id = getEm().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        return getEm().find(clazz, id);
    }
    
    private void buildSchemaMap() {
        schemaMap.put("schema01", em01);
        schemaMap.put("schema02", em02);
//        schemaMap.put("schema03", em03);
//        schemaMap.put("schema04", em04);
//        schemaMap.put("schema05", em05);
//        schemaMap.put("schema06", em06);
//        schemaMap.put("schema07", em07);
//        schemaMap.put("schema08", em08);
//        schemaMap.put("schema09", em09);
//        schemaMap.put("schema10", em10);
    }

    public EntityManager getSchema(String schema) {
        return schemaMap.get(schema);
        
    }

    public EntityManager getEm() {
        return getEm(Constants.DEFAULT_SCHEMA);
    }
    
    public EntityManager getEm(String schema) {
        return getSchema(schema);
    }

    public void refresh(E entity) {
//        Object id = getEm().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        getEm().refresh(getEm().merge(entity));
    }

}
