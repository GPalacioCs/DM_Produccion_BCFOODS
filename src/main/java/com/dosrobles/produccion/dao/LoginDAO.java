/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.dao;

/**
 *
 * @author Corpsoft S.A.
 */
//@Stateless
public class LoginDAO extends AbstractDAO<Object> {

    public LoginDAO() {
        super(Object.class);
    }
    
    public boolean authenticateUser(String username, String password) {
        String sql = "select count(*) from pwd sys.sql_logins "
                + "where pwdcompare(?1,password_hash) = 1 and name = ?2";        
        
        return ((Long)getEm().createNativeQuery(sql, Long.class)
                .setParameter(1, password)
                .setParameter(2, username)
                .getSingleResult()) > 0;
    }
    
}
