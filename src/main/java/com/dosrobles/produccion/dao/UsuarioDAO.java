/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.dao;

import com.dosrobles.produccion.entities.Usuario;

import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author Administrador
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public Usuario findUsuarioByUsername(String username) {
        String jpql = "select u from Usuario u where u.usuario = :username";
    
        List<Usuario> usuarios = getEm().createQuery(jpql, Usuario.class)
                .setParameter("username", username)
                .getResultList();
    
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
    }

    public List<Usuario> getActiveUsers() {
        return getEm().createQuery("select u from Usuario u where u.activo = 'S'", Usuario.class).getResultList();
    }
    
}
