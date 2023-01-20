/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.utils;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abdallah
 */
public class MessageUtils {
    
    public static String SUCCESS = "Operación realizada con éxito";
    public static String ERROR = "Error inesperado";

    public static void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
        FacesContext.getCurrentInstance().addMessage("hidden", message);
    }
    
    public static void showGrowlError(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
        FacesContext.getCurrentInstance().addMessage("hidGrowl", message);
    }
    
    public static void showGrowlError() {
        showGrowlError(ERROR);
    }
    
    public static void showGrowlSuccess(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg);
        FacesContext.getCurrentInstance().addMessage("hidGrowl", message);
    }
    
    public static void showGrowlSuccess() {
        showGrowlSuccess(SUCCESS);
    }
    
    public static void showErrorMessageInDialog(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }    
}
