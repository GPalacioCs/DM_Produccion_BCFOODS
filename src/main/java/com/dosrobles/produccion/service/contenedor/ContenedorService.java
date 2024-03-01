package com.dosrobles.produccion.service.contenedor;

import com.dosrobles.produccion.dao.OrdenProduccionDAO;
import com.dosrobles.produccion.dao.contenedor.ContenedorDAO;
import com.dosrobles.produccion.entities.IngresoProduccion;
import com.dosrobles.produccion.entities.OrdenProduccion;
import com.dosrobles.produccion.entities.Usuario;
import com.dosrobles.produccion.entities.contenedor.Contenedor;
import com.dosrobles.produccion.entities.contenedor.ContenedorDetalle;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.AbstractService;
import com.dosrobles.produccion.service.UsuarioService;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.viewModels.ContenidoSearchViewModel;
import lombok.val;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class ContenedorService extends AbstractService<ContenedorDAO, Contenedor> {

    @Inject
    OrdenProduccionDAO opDAO;
    @Inject
    UsuarioService usuarioService;

    public String getNextContenedor() {
        return dao.getNextContenedor();
    }

    @Override
    public Contenedor insert(Contenedor entity) throws BusinessValidationException {
        Usuario user = usuarioService.findUsuarioByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        entity.setFecha_Creacion(new Date());
        entity.setFecha_Ult_Mov(new Date());
        entity.setUsuario_Creacion(user);
        entity.setUsuario_Ult_Mov(user);
        return super.insert(entity);
    }

    @Override
    public Contenedor save(Contenedor entity) throws BusinessValidationException {
        Usuario user = usuarioService.findUsuarioByUsername(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
        entity.setFecha_Ult_Mov(new Date());
        entity.setUsuario_Ult_Mov(user);
        return super.save(entity);
    }


    public List<ContenidoSearchViewModel> getFreeContenidoList() throws Exception {
        List<ContenidoSearchViewModel> results = new ArrayList<>();
        try {
            //Ordenes de Produccion
            List<OrdenProduccion> freeOrdenProduccion = opDAO.getFreeOrdenProduccion();
            List<ContenidoSearchViewModel> opCSVM = freeOrdenProduccion.stream().map(op -> new ContenidoSearchViewModel(op.getOrdenProduccion().toString(), "OP")).collect(Collectors.toList());
            //Contenedores
            List<Contenedor> freeContainer = dao.getFreeContenedores();
            List<ContenidoSearchViewModel> cCSVM = freeContainer.stream().map(container -> new ContenidoSearchViewModel(container.getContenedorId(), "C")).collect(Collectors.toList());

            results.addAll(opCSVM);
            results.addAll(cCSVM);
        } catch (Exception e) {
            throw e;
        }

        return results;
    }

    public List<IngresoProduccion> getContenidoContenedor(Contenedor contenedor) {
        List<IngresoProduccion> contenidoFinal = new ArrayList<>();

        for (ContenedorDetalle contenedorDetalle : contenedor.getContenedorDetalles()) {
            if (Objects.equals(contenedorDetalle.getTipo(), "OP")) {
                OrdenProduccion op = opDAO.find(Long.parseLong(contenedorDetalle.getContenido()));
                contenidoFinal.addAll(op.getIngresoProduccoinList());
                continue;
            }

            Contenedor containedContainer = dao.find(contenedorDetalle.getContenido());
            contenidoFinal.addAll(getContenidoContenedor(containedContainer));
        }

        return contenidoFinal;
    }
}
