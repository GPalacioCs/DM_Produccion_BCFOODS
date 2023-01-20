package com.dosrobles.produccion.utils;

import java.util.*;

public class LazyQueryObject<T> {

    private List<T> resultList = new ArrayList<>();
    private int rowCount;
    private int first;
    private int pageSize;
    private String sortField;
    private Map<String, Object> filters = new HashMap<>();
    private Object identifier;
    private T uniqueResult;
    private Map<String, CustomFilter> customFilters = new HashMap<>();
    private Map<String, CustomFilter> defaultFilters = new HashMap<>();
    private List<List<CustomFilter>> customFiltersList = new ArrayList<>();
    private List<List<CustomFilter>> defaultFiltersList = new ArrayList<>();
    private List<CustomSort> customSortList = new ArrayList<>();

    public LazyQueryObject() {
        super();
    }
    
    public LazyQueryObject(int first, int pageSize, List<CustomSort> customSortList, Map<String, Object> filters) {
        super();
        this.first = first;
        this.pageSize = pageSize;        
        this.filters = filters;
        this.customSortList = customSortList;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public Object getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Object identifier) {
        this.identifier = identifier;
    }

    public T getUniqueResult() {
        return uniqueResult;
    }

    public void setUniqueResult(T uniqueResult) {
        this.uniqueResult = uniqueResult;
    }

    public Map<String, CustomFilter> getCustomFilters() {
        return customFilters;
    }

    public Map<String, CustomFilter> getDefaultFilters() {
        return defaultFilters;
    }

    public List<List<CustomFilter>> getCustomFiltersList() {
        return customFiltersList;
    }

    public List<List<CustomFilter>> getDefaultFiltersList() {
        return defaultFiltersList;
    }

    public List<CustomSort> getCustomSortList() {
        return customSortList;
    }

    public void addCustomFilter(String field, CustomOper oper, Object value) {
        addCustomFilter(new CustomFilter(field, oper, value));
    }

    public void addDefaultFilter(String field, CustomOper oper, Object value) {
        addDefaultFilter(new CustomFilter(field, oper, value));
    }
    
    public void addCustomFilter(CustomFilter... customFilters) {
        customFiltersList.add(Arrays.asList(customFilters));
    }
    
    public void addDefaultFilter(CustomFilter... customFilters) {
        defaultFiltersList.add(Arrays.asList(customFilters));
    }
    
    public void addCustomFilter(List<CustomFilter> customFilters) {
        customFiltersList.add(customFilters);
    }
    
    public void addDefaultFilter(List<CustomFilter> customFilters) {
        defaultFiltersList.add(customFilters);
    }
    
    public static class CustomSort {
        
        public static final String ASCENDING = "ASCENDING";
        public static final String DESCENDING = "DESCENDING";
        
        private String sortField;
        private String order;

        public CustomSort(String sortField, String order) {
            this.sortField = sortField;
            this.order = order;
        }

        public String getSortField() {
            return sortField;
        }

        public void setSortField(String sortField) {
            this.sortField = sortField;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
        
        
    }

    public static class CustomFilter {

        private String field;
        private CustomOper oper;
        private Object value;

        public CustomFilter() {
            super();
        }

        public CustomFilter(String field, CustomOper oper, Object value) {
            super();
            this.field = field;
            this.oper = oper;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public CustomOper getOper() {
            return oper;
        }

        public void setOper(CustomOper oper) {
            this.oper = oper;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public static enum CustomOper {
        EQUALS, NOTEQ, LT, GT, LTOREQ, GTOREQ, LIKE, ISNULL, ISNOTNULL
    }
}
