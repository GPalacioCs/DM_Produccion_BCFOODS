package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.PermisosUsuarios;
import com.dosrobles.produccion.entities.Usuario;
import com.dosrobles.produccion.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UsuarioPermisoController extends AbstractController<UsuarioService, Usuario> {
    @Override
    public void create() {
        return;
    }

    @Override
    public void edit() {
        entity = selection;
        if (entity.getPermisosUsuario() == null) {
            entity.setPermisosUsuario(new PermisosUsuarios(entity));
        }
        edit(entity);
    }

    @Override
    public void cargarLista() {
        list = service.getActiveUsers();
    }

    @PostConstruct
    private void init() {
        cargarLista();
    }

    @Override
    public void save() {
        super.save();
    }
}
