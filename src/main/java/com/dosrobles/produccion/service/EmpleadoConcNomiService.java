/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.EmpleadoConcNomiDAO;
import com.dosrobles.produccion.entities.Empleado;
import com.dosrobles.produccion.entities.EmpleadoConcNomi;
import com.dosrobles.produccion.entities.Nomina;
import com.dosrobles.produccion.entities.NominaHistorico;
import com.dosrobles.produccion.models.RptNomina;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Administrador
 */
@Stateless
public class EmpleadoConcNomiService extends AbstractService<EmpleadoConcNomiDAO, EmpleadoConcNomi> {
    
    @Inject
    private EmpleadoService empleadoService;
    @Inject
    private NominaHistoricoService nominaHistoricoService;
    @Inject
    private NominaBitacoraService nominaBitacoraService;
    
    public void procesarNomina(List<RptNomina> rptNominaList) {
        Map<String, Nomina> nominaMap = new HashMap<>();        
        List<String> codEmpleadoList = rptNominaList.stream().map(rpt -> rpt.getEmpleado()).distinct().collect(Collectors.toList()); 
        List<Empleado> empleadoList = empleadoService.getEmpleadoList(codEmpleadoList);
        Map<String, Empleado> empleadoMap = empleadoList.stream().collect(Collectors.toMap(Empleado::getEmpleado, e->e));
        Map<Empleado, RptNomina> rptNominaMap = rptNominaList.stream().collect(Collectors.toMap(rpt -> empleadoMap.get(rpt.getEmpleado()), rpt->rpt));
        
        Map<Nomina, NominaHistorico> nominaHistoricoMap = new HashMap<>();
        
        String concepto = rptNominaList.stream().map(rpt -> rpt.getConcepto()).findFirst().orElse(null);        
        nominaMap.putAll(empleadoList.stream().filter(emp -> emp.getNomina().getNomina().equals("02")).map(e -> e.getNomina()).distinct().collect(Collectors.toMap(n -> n.getNomina(), n->n))); 
//        for (Nomina nomina : nominaMap.values()) {
//            
//        }
        nominaHistoricoMap.put(new Nomina("06"), nominaHistoricoService.getNominaHistoricoActual(new Nomina("06")));
        
        Map<Empleado, EmpleadoConcNomi> ecnMap = dao.getEmpleadoConcNomi(concepto, nominaHistoricoMap.values())
                .stream().collect(Collectors.toMap(ecn -> ecn.getEmpleado(), ecn->ecn));
        
        rptNominaList.forEach(rpt -> {
            Empleado empleado = empleadoMap.get(rpt.getEmpleado());
            EmpleadoConcNomi ecn = ecnMap.get(empleado);
            
            if (ecn !=null) {                
                ecn.setCantidad(ecn.getCantidad().add(rpt.getMonto()));
//                nominaBitacoraService.insert(NominaBitacora.of(empleado, new Concepto(ecn.getConcepto()), ecn.getNominaHistorico(), ecn.getCantidad()));
            }
            
        });        
               
    }
}
