package com.dosrobles.produccion.service.embarque;

import com.dosrobles.produccion.dao.embarque.EmbarqueDAO;
import com.dosrobles.produccion.dao.embarque.EmbarqueLineaDao;
import com.dosrobles.produccion.entities.GlobalesCO;
import com.dosrobles.produccion.entities.IngresoProduccion;
import com.dosrobles.produccion.entities.OrdenCompra;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.embarque.EmbarqueLineaPK;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.AbstractService;
import com.dosrobles.produccion.service.GlobalesCOService;
import com.dosrobles.produccion.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmbarqueService extends AbstractService<EmbarqueDAO, Embarque> {

    @Inject
    private GlobalesCOService globalesCOService;

    @Inject
    private EmbarqueLineaDao embarqueLineaDao;

    public List<Embarque> getAllPlaneados() {
        return dao.findAllPlaneados();
    }

    @Override
    public Embarque save(Embarque entity) throws BusinessValidationException {
        List<EmbarqueLinea> currentLineasEmbarque = find(entity.getEmbarque()).getEmbarqueLineas();

        for (EmbarqueLinea cel :
                currentLineasEmbarque) {
            if (entity.getEmbarqueLineas().stream().noneMatch(el -> cel.getId() == cel.getId())) {
                embarqueLineaDao.delete(cel);
            }
        }

        return super.save(entity);
    }

    @Override
    public Embarque insert(Embarque entity) throws BusinessValidationException {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        String consecOrden = getConsecutivo();
        globales.setUltOrdenCompra(consecOrden);
        entity.setEmbarque(consecOrden);
        for (EmbarqueLinea el :
                entity.getEmbarqueLineas()) {
//            el.setEmbarque(entity);
            el.getId().setEmbarque(consecOrden);

            for (EmbarqueDesglose ed :
                    el.getEmbarqueLineaDesgloses()) {
//                ed.setEmbarqueLinea(el);
                ed.getId().setEmbarqueLineaPk(el.getId());
            }
        }
        return super.insert(entity);
    }

    public String getConsecutivo() {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        return Utils.generarConsecutivo(globales.getUltEmbarque());
    }
}
