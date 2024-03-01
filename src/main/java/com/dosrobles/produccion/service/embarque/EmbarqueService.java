package com.dosrobles.produccion.service.embarque;

import com.dosrobles.produccion.dao.OrdenCompraDAO;
import com.dosrobles.produccion.dao.OrdenCompraLineaDAO;
import com.dosrobles.produccion.dao.embarque.EmbarqueDAO;
import com.dosrobles.produccion.dao.embarque.EmbarqueLineaDao;
import com.dosrobles.produccion.dao.embarque.EmbarqueSearchDAO;
import com.dosrobles.produccion.entities.GlobalesCO;
import com.dosrobles.produccion.entities.OrdenCompra;
import com.dosrobles.produccion.entities.OrdenCompraLinea;
import com.dosrobles.produccion.entities.OrdenCompraLineaId;
import com.dosrobles.produccion.entities.embarque.Embarque;
import com.dosrobles.produccion.entities.embarque.EmbarqueDesglose;
import com.dosrobles.produccion.entities.embarque.EmbarqueLinea;
import com.dosrobles.produccion.entities.embarque.EmbarqueSearch;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.AbstractService;
import com.dosrobles.produccion.service.GlobalesCOService;
import com.dosrobles.produccion.service.OrdenCompraService;
import com.dosrobles.produccion.service.TransaccionInvService;
import com.dosrobles.produccion.utils.Utils;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class EmbarqueService extends AbstractService<EmbarqueDAO, Embarque> {

    @Inject
    private GlobalesCOService globalesCOService;

    @Inject
    private EmbarqueLineaDao embarqueLineaDao;
    @Inject
    private EmbarqueSearchDAO embarqueSearchDAO;

    @Inject
    private TransaccionInvService transaccionInvService;
    @Inject
    private OrdenCompraLineaDAO oclDao;
    @Inject
    private OrdenCompraDAO ocDao;

    public List<Embarque> getAllPlaneados() {
        return dao.findAllPlaneados();
    }

    public List<EmbarqueSearch> getAllRecibidosSinEnviar() {
        return embarqueSearchDAO.findAllRecibidosSinEnviar();
    }
    public List<EmbarqueSearch> getAllRecibidosSinEnviarByBodega(String bodega) {
        return embarqueSearchDAO.findAllRecibidosSinEnviarByBodega(bodega);
    }

    @Override
    public Embarque save(Embarque entity) throws BusinessValidationException {
        List<EmbarqueLinea> currentLineasEmbarque = find(entity.getEmbarque()).getEmbarqueLineas();

        for (EmbarqueLinea cel :
                currentLineasEmbarque) {
            OrdenCompraLinea originalOcl = oclDao.find(new OrdenCompraLineaId(cel.getOrden_Compra().getOrdenCompra(), cel.getOrden_Compra_Linea()));
            originalOcl.setCantidadEmbarcada(originalOcl.getCantidadEmbarcada().subtract(BigDecimal.valueOf(cel.getCantidad_Embarcada())));
            oclDao.save(originalOcl);
            if (entity.getEmbarqueLineas().stream().noneMatch(el -> cel.getId() == cel.getId())) {
                embarqueLineaDao.delete(cel);
            }
        }

        for (EmbarqueLinea el : entity.getEmbarqueLineas()) {
            OrdenCompraLinea originalOcl = oclDao.find(new OrdenCompraLineaId(el.getOrden_Compra().getOrdenCompra(), el.getOrden_Compra_Linea()));
            originalOcl.setCantidadEmbarcada(originalOcl.getCantidadEmbarcada().add(BigDecimal.valueOf(el.getCantidad_Embarcada())));
            oclDao.save(originalOcl);
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
        if (CollectionUtils.isEmpty(entity.getEmbarqueLineas())) {
            throw new BusinessValidationException("No se ha agregado lineas al embarque");
        }
        transaccionInvService.aplicarEmbarque(entity, usuario);
        Embarque embarqueDb = find(entity.getEmbarque());
        embarqueDb.setEstado("R");

        for (EmbarqueLinea el : entity.getEmbarqueLineas()) {
            OrdenCompraLinea originalOcl = oclDao.find(new OrdenCompraLineaId(el.getOrden_Compra().getOrdenCompra(), el.getOrden_Compra_Linea()));
            originalOcl.setCantidadEmbarcada(originalOcl.getCantidadEmbarcada().subtract(BigDecimal.valueOf(el.getCantidad_Embarcada())));
            originalOcl.setCantidadRecibida(originalOcl.getCantidadRecibida().add(BigDecimal.valueOf(el.getCantidad_recibida())));
            originalOcl.setCantidadRechazada(originalOcl.getCantidadRechazada().add(BigDecimal.valueOf(el.getCantidad_rechazada())));
            if (originalOcl.getCantidadOrdenada().subtract(originalOcl.getCantidadRecibida().add(originalOcl.getCantidadRechazada())).compareTo(BigDecimal.ZERO) == 0) {
                originalOcl.setEstado("R");
            } else {
                originalOcl.setEstado("I");
            }
            oclDao.save(originalOcl);

            OrdenCompra originalOc = ocDao.find(originalOcl.getOrdenCompraLineaId().getOrdenCompra());
            if (originalOc.getLineas().stream().allMatch(l -> l.getEstado().equals("R"))) {
                originalOc.setEstado("R");
            } else {
                originalOc.setEstado("I");
            }
            ocDao.save(originalOc);
        }

        save(embarqueDb);
    }

    public String getConsecutivoEmbarque() {
        GlobalesCO globales = globalesCOService.getGlobalesCO();
        return Utils.generarConsecutivo(globales.getUltEmbarque());
    }
}
