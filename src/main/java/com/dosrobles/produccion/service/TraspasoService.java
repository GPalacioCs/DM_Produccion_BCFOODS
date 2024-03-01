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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class TraspasoService extends AbstractService<TraspasoDAO, Traspaso> {

    @Inject
    private TransaccionInvService transaccionInvService;
    @Inject
    private UsuarioService usuarioService;

    @Inject
    private LoteService loteService;

    @Override
    public void delete(Traspaso entity) throws BusinessValidationException {
        dao.delete(entity);
    }

    @Override
    public Traspaso save(Traspaso entity) throws BusinessValidationException {
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

        entity.setUsuarioModificacion(usuarioService.find(user));
        entity.setFechaModificacion(new Date());
        return super.save(entity);
    }

    @Override
    public Traspaso insert(Traspaso entity) throws BusinessValidationException {
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();

        entity.setUsuarioCreacion(usuarioService.find(user));
        entity.setFechaCreacion(new Date());
        entity.setUsuarioModificacion(usuarioService.find(user));
        entity.setFechaModificacion(new Date());
        return super.insert(entity);
    }

    public String getNextTraspaso(String bodega) {
        return dao.getNextTraspaso(bodega);
    }

    public void enviarTraspaso(Traspaso tras, String usuario) {
        double totalLibras = tras.getLineasEnvio().stream().mapToDouble(TraspasoLineaEnvio::getCantidad).sum();
        double totalPrecio = tras.getLineasEnvio().stream().mapToDouble(l -> l.getPrecio_Unitario() * l.getCantidad()).sum();
        double precioUnitario = totalPrecio / totalLibras;

        for (TraspasoLineaEnvio le :
                tras.getLineasEnvio()) {
            TraspasoLineaRecepcion lr = new TraspasoLineaRecepcion();
            lr.setArticulo(le.getArticulo());
            lr.setCantidad(le.getCantidad());
            lr.setTraspasoLineaRecepcionPK(new TraspasoLineaRecepcionPK(tras.getIdTraspaso(), le.getId().getLinea()));
            lr.setTraspaso(tras);
            lr.setLote(Lote.CreateLoteStringForTraspaso(lr.getTraspaso().getN_Traspaso(),
                    lr.getTraspasoLineaRecepcionPK().getLinea()
            ));
            lr.setPrecio_Unitario(precioUnitario);
            tras.getLineasRecepcion().add(lr);
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

    public void recibirTraspaso(Traspaso tras, String usuario) {
        if (CollectionUtils.isEmpty(tras.getLineasRecepcion())) {
            throw new BusinessValidationException("No se ha agregado lineas al traspaso");
        }

        for (TraspasoLineaRecepcion lr :
                tras.getLineasRecepcion()) {
            lr.setTraspaso(tras);
            lr.setLote(Lote.CreateLoteStringForTraspaso(lr.getTraspaso().getN_Traspaso(),
                    lr.getTraspasoLineaRecepcionPK().getLinea()
            ));
            Lote newLote = Lote.CreateNewLoteForTraspaso(tras.getN_Traspaso(), lr.getArticulo(), lr.getCantidad(), lr.getTraspasoLineaRecepcionPK().getLinea());
            loteService.save(newLote);
        }
        save(tras);

        transaccionInvService.recibirTraslado(tras, usuario);
        Traspaso traspasoDb = find(tras.getIdTraspaso());
        traspasoDb.setEstado("R");
        save(traspasoDb);

    }
}
