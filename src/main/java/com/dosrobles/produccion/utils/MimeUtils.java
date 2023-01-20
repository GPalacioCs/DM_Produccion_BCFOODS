/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dosrobles.produccion.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Corpsoft S.A.
 */
public class MimeUtils {
    
    private static final Map<String, String>  MIME = new HashMap<>();

    static {
        MIME.put("pdf", "application/pdf");
        MIME.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        MIME.put("xls", "application/vnd.ms-excel");
    }
    
    public static String getMimeType(String format) {        
        return MIME.get(format);
    }

}
