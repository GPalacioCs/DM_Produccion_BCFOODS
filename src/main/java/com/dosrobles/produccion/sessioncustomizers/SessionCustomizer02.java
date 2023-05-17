/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.sessioncustomizers;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;


/**
 *
 * @author Corpsoft S.A.
 */
public class SessionCustomizer02 implements SessionCustomizer {

    
    private String schema;
    public SessionCustomizer02() {
//            this.schema = SchemaUtils.getSchema("schema01");
            this.schema = "ALINSA";
    }
    
    

    @Override
    public void customize(Session sn) throws Exception {
        sn.getLogin().setTableQualifier(schema);
    }

}
