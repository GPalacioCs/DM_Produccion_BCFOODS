/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.ParametrosPr;
import com.dosrobles.produccion.entities.Usuario;
import com.dosrobles.produccion.service.ParametrosPrService;
import com.dosrobles.produccion.service.UsuarioService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
@Getter @Setter
public class UsuarioController implements Serializable{
    
    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private UsuarioService service;
    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private ParametrosPrService parametrosPrService;
    
    private Usuario usuario;
    private ParametrosPr parametro;
    
    @PostConstruct
    private void init() {
        setUsuario(service.find(getUsername()));
        setParametro(parametrosPrService.getParametro());
    }
    
    private String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }
    
    public boolean isAdmin() {
        return getUsuario().isAdmin(parametro.getPaquete());
    }
}
