/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.UsuarioDAO;
import com.dosrobles.produccion.entities.Usuario;
import com.dosrobles.produccion.exceptions.BusinessValidationException;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Administrador
 */
@Stateless
public class UsuarioService extends AbstractService<UsuarioDAO, Usuario> {
    
    public Usuario findUsuarioByUsername(String username) {
        return dao.findUsuarioByUsername(username);
    }

    public List<Usuario> getActiveUsers() {
        return dao.getActiveUsers();
    }

    @Override
    public Usuario save(Usuario entity) throws BusinessValidationException {

        Usuario currentUser = dao.find(entity.getUsuario());
        currentUser.setPermisosUsuario(entity.getPermisosUsuario());

        return super.save(currentUser);
    }
}
