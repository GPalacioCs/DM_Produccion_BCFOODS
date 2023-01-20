/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.User;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.AbstractService;
import com.dosrobles.produccion.utils.MessageUtils;
import org.primefaces.event.data.FilterEvent;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Corpsoft S.A.
 */

public abstract class AbstractController<S extends AbstractService, E> implements Serializable {
    @Inject
    protected S service;        
    protected List<E> list = new ArrayList<>();
    
    protected List<String> selectedColumns = new ArrayList<>();
    
    protected E entity;
    
    protected E selection;
    
    private boolean viewing;
    private boolean editing;
    private boolean creating;
    private boolean formView;
    private boolean allColumns = true;
    
    private int activeIndexMenu;
    private int accActiveIndex = -1;
    
    protected Map<String, Object> filterMap = new HashMap<>();
    
    private String field;
    
    public abstract void create();
    protected void create(E entity) {
        this.entity = entity;
        reset();
        setCreating(true);
        setFormView(true);
    }
    public abstract void edit();
    protected void edit(E entity) {
        this.entity = entity;
        reset();
        setEditing(true);
        setFormView(true);
    }
    public void delete() {
        try {
            service.delete(selection);
            selection = null;
            cargarLista();
            MessageUtils.showGrowlSuccess("Operación realizada con éxito");
        } catch (BusinessValidationException ex) {
            MessageUtils.showGrowlError(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showErrorMessageInDialog(MessageUtils.ERROR);
            e.printStackTrace();
        }
    }
    public void view() {
        setFormView(true);
        setViewing(true);
        setEditing(false);
    }
    
    public void cancel() {
        reset();
        setEntity(null);
    }
    
    protected void addDefaultColumns() {
        
    }
    public void save() {
        try {
            if (editing) {
                service.save(entity);
            } else {
                service.insert(entity);
            }
            cancel();
            MessageUtils.showGrowlSuccess();
            cargarLista();
        } catch (BusinessValidationException ex) {
            MessageUtils.showMessage(ex.getMessage());
        } catch (Exception e) {
            MessageUtils.showMessage(MessageUtils.ERROR);
            e.printStackTrace();
        }
    }
    
    public void reset() {
        setFormView(false);
        setViewing(false);
        setEditing(false);
        setCreating(false);
    }

    public S getService() {
        return service;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public E getSelection() {
        return selection;
    }

    public void setSelection(E selection) {
        this.selection = selection;
    }

    public List<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List<String> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }

    public boolean isViewing() {
        return viewing;
    }

    public void setViewing(boolean viewing) {
        this.viewing = viewing;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
    
    public boolean isCreating() {
        return creating;
    }
    
    public void setCreating(boolean creating) {
        this.creating = creating;
    }

    public boolean isFormView() {
        return formView;
    }

    public void setFormView(boolean formView) {
        this.formView = formView;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public boolean isAllColumns() {
        return allColumns;
    }

    public void setAllColumns(boolean allColumns) {
        this.allColumns = allColumns;
    }
    
    public User getUser() {
        User user = new User();
        user.setUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        user.setNombre(user.getUsername());
        return user;
    }    
    
    public String getUsername() {
        return getUser().getUsername();
    }
    
    public void onAllColumnsSelected() {
        if(allColumns) {
            addDefaultColumns();
        } else {
            selectedColumns.clear();
        }
    }
    
    public int getActiveIndex() {
        return formView ? 1 : 0;
    }
    
    public void setActiveIndex(int index) {
        
    }

    public int getActiveIndexMenu() {
        return activeIndexMenu;
    }

    public void setActiveIndexMenu(int activeIndexMenu) {
        this.activeIndexMenu = activeIndexMenu;
    }

    public int getAccActiveIndex() {
        return accActiveIndex;
    }

    public void setAccActiveIndex(int accActiveIndex) {
        this.accActiveIndex = accActiveIndex;
    }
    
    public void onFilter(FilterEvent event) {
        this.filterMap = event.getFilters();
    }
    
    public String getFilterValue(String field) {
        if(filterMap != null)
            return (String) filterMap.get(field);
        return null;
    }
    
    public String getFilterValue() {
        return getFilterValue(field);
    }
    
    public void setFilterValue(String field) {
        this.field = field;
    }
    
    public void cargarLista() {
        
    }
    
    public boolean isBtnGuardarVisible() {
        return true;
    }
    
}
