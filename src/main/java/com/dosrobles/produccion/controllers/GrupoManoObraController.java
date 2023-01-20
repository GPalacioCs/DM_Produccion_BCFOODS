package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.ActividadProd;
import com.dosrobles.produccion.entities.Empleado;
import com.dosrobles.produccion.entities.EmpleadoGrupoManoObra;
import com.dosrobles.produccion.entities.GrupoManoObra;
import com.dosrobles.produccion.service.ActividadProdService;
import com.dosrobles.produccion.service.EmpleadoService;
import com.dosrobles.produccion.service.GrupoManoObraService;
import com.dosrobles.produccion.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class GrupoManoObraController extends AbstractController<GrupoManoObraService, GrupoManoObra> {
    
    @Inject
    private ActividadProdService actividadProdService;
    @Inject
    private EmpleadoService empleadoService;
    
    @Getter @Setter
    private EmpleadoGrupoManoObra empleadoGrupoManoObra;
    
    @PostConstruct
    private void init() {
        cargarLista();
        empleadoGrupoManoObra = new EmpleadoGrupoManoObra();
    }
    
    @Override
    public void create() {
        entity = new GrupoManoObra();
        create(entity);
    }
    
    @Override
    public void edit() {
        entity = service.findByEntity(selection);
        edit(entity);
    }
    
    @Override
    public void cargarLista() {
        this.list = service.findAll();
    }
    
    public List<ActividadProd> getActividades() {
        return actividadProdService.findAll();
    }
    
    public List<Empleado> getEmpleados() {
        return empleadoService.findAll("schema02").stream().filter(e -> "02.01.00".equals(e.getCentroCosto())).collect(Collectors.toList());
    }
    
    public void agregarEmpleado() {
        
        if(empleadoGrupoManoObra == null) return;
        if (entity.getEmpleados().stream().anyMatch(e -> e.getEmpleado().equals(empleadoGrupoManoObra.getEmpleado()))) {
            MessageUtils.showMessage("El empleado ya fue agregado");
            return;
        }
        empleadoGrupoManoObra.setGrupo(entity);
        entity.getEmpleados().add(empleadoGrupoManoObra);
        empleadoGrupoManoObra = new EmpleadoGrupoManoObra();
    }
    
    public void quitarEmpleado(EmpleadoGrupoManoObra empleado) {
        entity.getEmpleados().removeIf(e -> e.getEmpleado().equals(empleado.getEmpleado()));
    }
}
