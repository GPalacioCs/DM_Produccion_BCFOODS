package com.dosrobles.produccion.service.embarque;

import com.dosrobles.produccion.dao.embarque.EmbarqueDAO;
import com.dosrobles.produccion.dao.embarque.EmbarqueLineaDao;
import com.dosrobles.produccion.entities.GlobalesCO;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.AbstractService;
import com.dosrobles.produccion.service.GlobalesCOService;
import com.dosrobles.produccion.service.TransaccionInvService;
import com.dosrobles.produccion.utils.Utils;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmbarqueService extends AbstractService<EmbarqueDAO, Embarque> {

    @Inject
    private GlobalesCOService globalesCOService;

    @Inject
    private EmbarqueLineaDao embarqueLineaDao;

    @Inject
    private TransaccionInvService transaccionInvService;

    public List<Embarque> getAllPlaneados() {
        return dao.findAllPlaneados();
    }

    public List<Embarque> getAllRecibidosSinEnviar() {
        return dao.findAllRecibidosSinEnviar();
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
        String consecOrden = getConsecutivoEmbarque();
        globales.setUltEmbarque(consecOrden);
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
//        globalesCOService.save(globales);
        return super.insert(entity);
    }

    public void aplicarEmbarque(Embarque entity, String usuario) throws BusinessValidationException {
        if(CollectionUtils.isEmpty(entity.getEmbarqueLineas())) {
            throw new BusinessValidationException("No se ha agregado lineas al embarque");
        }
        transaccionInvService.aplicarEmbarque(entity, usuario);
        Embarque embarqueDb = find(entity.getEmbarque());
        embarqueDb.setEstado("R");
        save(embarqueDb);
    }

    public String getConsecutivoEmbarque() {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        return Utils.generarConsecutivo(globales.getUltEmbarque());
    }
}
