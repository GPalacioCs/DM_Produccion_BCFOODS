package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.TraspasoDAO;
import com.dosrobles.produccion.entities.Lote;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.traslado.Traspaso;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaEnvio;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaRecepcion;
import com.dosrobles.produccion.entities.traslado.TraspasoLineaRecepcionPK;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TraspasoService extends AbstractService<TraspasoDAO, Traspaso> {

    @Inject
    private TransaccionInvService transaccionInvService;

    @Override
    public void delete(Traspaso entity) throws BusinessValidationException {
        dao.delete(entity);
    }

    @Override
    public Traspaso save(Traspaso entity) throws BusinessValidationException {

        return super.save(entity);
    }

    public String getNextTraspaso(String bodega) {
        return dao.getNextTraspaso(bodega);
    }

    public void enviarTraspaso(Traspaso tras, String usuario) {
        for (TraspasoLineaEnvio le :
                tras.getLineasEnvio()) {
            TraspasoLineaRecepcion lr = new TraspasoLineaRecepcion();
            lr.setArticulo(le.getArticulo());
            lr.setCantidad(le.getCantidad());
            lr.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(tras.getIdTraspaso(), le.getId().getLinea()));
            lr.setTraspaso(tras);
            lr.setLote(Lote.CreateLoteStringForTraspaso(lr.getTraspaso().getIdTraspaso(),
                    lr.getTraspasoLineaRecepcionPK().getLinea()
            ));
        }
        save(tras);

        if (CollectionUtils.isEmpty(tras.getLineasEnvio())) {
            throw new BusinessValidationException("No se ha agregado lineas al traspaso");
        }
        transaccionInvService.enviarTraslado(tras, usuario);
        Traspaso traspasoDb = find(tras.getIdTraspaso());
        traspasoDb.setEstado("E");
        save(traspasoDb);

    }
}
