/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Abdallah
 */
@ApplicationException(rollback = true)
public class BusinessValidationException extends RuntimeException{

    public BusinessValidationException(String message) {
        super(message);
    }    
}
