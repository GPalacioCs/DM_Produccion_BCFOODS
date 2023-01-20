package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.GlobalesAS;
import com.dosrobles.produccion.entities.GlobalesCG;
import com.dosrobles.produccion.entities.GlobalesCi;
import com.dosrobles.produccion.entities.ParametrosPr;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.GlobalesASService;
import com.dosrobles.produccion.service.GlobalesCGService;
import com.dosrobles.produccion.service.GlobalesCiService;
import com.dosrobles.produccion.service.ParametrosPrService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class GlobalesController implements Serializable {

    @Inject
    private GlobalesASService globalesASService;
    @Inject
    private GlobalesCGService globalesCGService;
    @Inject
    private GlobalesCiService globalesCiService;
    @Inject
    private ParametrosPrService parametrosPrService;

    private GlobalesAS globalesAS;
    private GlobalesCG globalesCG;
    private GlobalesCi globalesCi;
    private ParametrosPr parametrosPr;

    @PostConstruct
    private void init() {

    }

    public GlobalesAS getGlobalesAS() throws BusinessValidationException {
        if (globalesAS == null) {
            globalesAS = globalesASService.getGlobalesAS();
        }
        return globalesAS;
    }

    public GlobalesCG getGlobalesCG() throws BusinessValidationException {
        if (globalesCG == null) {
            globalesCG = globalesCGService.getGlobalesCG();
        }
        return globalesCG;
    }

    public GlobalesCi getGlobalesCi() throws BusinessValidationException {

        if (globalesCi == null) {
            globalesCi = globalesCiService.getGlobalesCi();
        }
        return globalesCi;

    }

    public ParametrosPr getParametrosPr() throws BusinessValidationException {
        if(parametrosPr == null) {
            parametrosPr = parametrosPrService.getParametro();
        }        
        return parametrosPr;
    }

    public String getPatronCentroCosto() throws BusinessValidationException {
        return getGlobalesAS().getPatronCCosto();
    }
    
    public String getPatronCuentaContable() throws BusinessValidationException {
        return getGlobalesCG().getPatron();
    }

}
