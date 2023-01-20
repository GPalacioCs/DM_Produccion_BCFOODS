/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.SeriePlantillaDAO;
import com.dosrobles.produccion.entities.SeriePlantilla;
import com.dosrobles.produccion.utils.Utils;

import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@Stateless
public class SeriePlantillaService extends AbstractService<SeriePlantillaDAO, SeriePlantilla> {
    
    public String generarSerie() {
        SeriePlantilla seriePlantilla = findAll().stream().findFirst().orElse(null);
        String nuevaSerie = Utils.generarConsecutivo(seriePlantilla.getUltimoValor());
        seriePlantilla.setUltimoValor(nuevaSerie);
        return nuevaSerie;
    }
}
