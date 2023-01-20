/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.utils.SchemaUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdallah
 */
@Named
@SessionScoped
public class SchemaController implements Serializable {

    private String schema = "schema01";

    List<SelectItem> selectItemList = new ArrayList<>();

    @PostConstruct
    private void init() {
        String comp01 = SchemaUtils.getSchema("schema01");
        selectItemList.add(new SelectItem("schema01", SchemaUtils.getSchema(comp01)));
        for (int i = 1; i <= 10; i++) {
            String schema = String.format("schema%02d", i);
            String compania = SchemaUtils.getSchema(schema);
            if (!comp01.equals(compania)) {
                selectItemList.add(new SelectItem(schema, SchemaUtils.getSchema(schema)));
            }
        }

    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {        
        this.schema = schema;
    }

    public List<SelectItem> getSelectItemList() {
        return selectItemList;
    }

    public void invalidateSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
    }

    public String gotoClientes() {
        return "/faces/cliente/cliente.xhtml?faces-redirect=true";
    }
}