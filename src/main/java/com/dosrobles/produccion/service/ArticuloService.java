/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dosrobles.produccion.service;

import com.dosrobles.produccion.dao.ArticuloDAO;
import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.utils.MontoLocalDolar;
import com.dosrobles.produccion.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author KC
 */
@Stateless
public class ArticuloService extends AbstractService<ArticuloDAO, Articulo> {
    
    @Inject
    private ConsecutivoService consecutivoService;
    @Inject
    private CostoUepsPepsService costoUepsPepsService;
    @Inject
    private LoteService loteService;
    @Inject
    private EtapaService etapaService;
    
    @Inject
    private ParametrosPrService parametrosPrService;
    

    @Override
    public Articulo insert(Articulo articulo) throws BusinessValidationException {
        if(find(articulo.getArticulo()) != null) {
            throw new BusinessValidationException("Ya existe un artículo con el mismo código");
        }
        
        if(articulo.getConsecutivo() != null) {
            Consecutivo consecutivo = consecutivoService.findByEntity(articulo.getConsecutivo());
            consecutivo.setUltimoValor(Utils.generarConsecutivo(consecutivo.getUltimoValor()));            
        }
        
        for (Etapa etapa : articulo.getEtapaList()) {
            etapa.getEtapaPK().setArticulo(articulo.getArticulo());
            for (MateriaPrima mp : etapa.getMateriaPrimaList()) {
                mp.getMateriaPrimaPK().setArticuloPadre(articulo.getArticulo());
            }
        }        
        articulo.setFchHoraCreacion(new Date());
        
        loteService.save(new Lote("ND", articulo.getArticulo()));
        
        return save(articulo);
    }    

    @Override
    public Articulo save(Articulo entity) throws BusinessValidationException {
        
        if(entity.getCostoFiscal().equals(entity.getCostoComparativo())) {
            throw new BusinessValidationException("El costo fiscal y el corporativo no pueden ser iguales");
        }
        
        for (ExistenciaBodega existencia : entity.getExistenciaBodegaList()) {
            existencia.getExistenciaBodegaPK().setArticulo(entity.getArticulo());
        }
        
        for (Etapa etapa : entity.getEtapaList()) {
            if(etapa.getMateriaPrimaList().isEmpty()) {
                throw new BusinessValidationException("No puede dejar etapas sin componentes");
            }
        }
        
        Etapa etapa1 = entity.getEtapaList().stream().filter(etapa -> etapa.getOrden() == 1).findFirst().orElse(null);
        if (etapa1 != null) {
            entity.setArticuloEnsambleList(entity.getEtapaList().stream().filter(etapa -> etapa.getOrden() == 1).findFirst().get()
                    .getMateriaPrimaList().stream().map(mp -> new ArticuloEnsamble(mp.getMateriaPrimaPK().getArticuloPadre(), mp.getMateriaPrimaPK().getArticuloHijo(), mp.getCantidad())).collect(Collectors.toList()));
        }
        
        calcularCostoArticulo(entity);
        
        return dao.save(entity);
    }

    @Override
    public void delete(Articulo entity) throws BusinessValidationException {
        dao.delete(entity);
    }
    
    public Articulo findByArticuloString(String ariticulo)
    {
        return dao.findByArticuloString(ariticulo);
    }
    
    public List<Articulo> cargarServicios() {
        return dao.cargarServicios();
    }
    
    public List<Articulo> getArticulosActivos() {
        return dao.getArticulosActivos();
    }
    
    public List<Articulo> getArticulosPorTipo(String... tipo) {
        return dao.getArticulosPorTipo(tipo);
    }
    
    public MontoLocalDolar getCostoArticulo(Articulo articulo, String tipoCosto) {
        switch (tipoCosto) {
            case "P" : return new MontoLocalDolar(articulo.getCostoPromLoc(), articulo.getCostoPromDol());
            case "S" : return new MontoLocalDolar(articulo.getCostoStdLoc(), articulo.getCostoStdDol());
            case "L" : return new MontoLocalDolar(articulo.getCostoUltLoc(), articulo.getCostoUltDol());            
            default : return new MontoLocalDolar();
        }
    }
    
    public MontoLocalDolar getCostoArticulo(Articulo articulo) {
        BigDecimal totalLocal = BigDecimal.ZERO;
        BigDecimal totalDolar = BigDecimal.ZERO;
        
        for (Etapa etapa : articulo.getEtapaList()) {
            for (MateriaPrima materia : etapa.getMateriaPrimaList()) {
                totalLocal = totalLocal.add(materia.getArticuloHijo().getCostoPromLoc().multiply(materia.getCantidad()));
                totalDolar = totalDolar.add(materia.getArticuloHijo().getCostoPromDol().multiply(materia.getCantidad()));
            }
        }
        MontoLocalDolar monto = new MontoLocalDolar(totalLocal, totalDolar);
        return monto;
    }
    
    public void calcularCostoArticulo(Articulo articulo) {
        MontoLocalDolar monto = getCostoArticulo(articulo);
        articulo.setCostoPromLoc(monto.getMontoLocal());
        articulo.setCostoPromDol(monto.getMontoDolar());
    }
    
    public boolean tieneOrdenes(Articulo articulo) {
        return dao.tieneOrdenes(articulo);
    }
    
    public List<Articulo> getArticulosPorClasificacion(Clasificacion... clasificaciones) {
        return dao.getArticulosPorClasificacion(Arrays.asList(clasificaciones));
    }
    
    public List<Articulo> getArticulosMateriaPrima()
    {
        return getArticulosPorClasificacion(parametrosPrService.getParametro().getClasificacionMateriaPrima());
    }
    
    public List<Articulo> getArticulosProduccion()
    {
        return dao.getArticulosProduccion();
    }
    
    public BigDecimal getPrecioArticulo(String articulo, String nivelPrecio, String moneda) throws BusinessValidationException {
        return dao.getPrecio(articulo, nivelPrecio, moneda);
    }
    
    public List<Articulo> getMaterialesDirectos(Clasificacion clasificacion) {
        return dao.getMaterialesDirectos(clasificacion);
    }
    
    public List<Articulo> getMaterialesDirectos() {
        return getMaterialesDirectos(parametrosPrService.getParametro().getClasificacionMaterialesDirectos());
    }
    
    
    
    public List<Articulo> getMateriasPrimasAndMaterialesDirectors() {
        List<Articulo> list = new ArrayList<>(getArticulosMateriaPrima());
        list.addAll(getMaterialesDirectos());
        return list;
    }
    
    public void cargarReceta(InputStream is) throws IOException {
        Workbook workbook = new HSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Set<String> articulosNoExistentes = new HashSet();
        while(iterator.hasNext()) {
            Row row = iterator.next();
            if(row.getRowNum() == 0) continue;
            String codArticulo = row.getCell(1).getStringCellValue();
            if(StringUtils.isBlank(codArticulo)) break;
            Articulo articuloPt = find(codArticulo);
            if(articuloPt == null) continue;
            articuloPt.setCantidadProduccion(BigDecimal.valueOf(row.getCell(0).getNumericCellValue()));
            articuloPt.getEtapaList().clear();
            Etapa etapa = etapaService.find(new EtapaPK(1, codArticulo));
            if (etapa != null) {
                etapa.getMateriaPrimaList().clear();
            } else {
                etapa = new Etapa(1, articuloPt.getArticulo());                
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cell.getColumnIndex() < 2) continue;
                if(cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                    if(cell.getColumnIndex()%2 == 0) {
                        String codArticuloMp = cell.getStringCellValue();
                        Articulo articuloMp = find(codArticuloMp);
                        if(articuloMp == null) {
                            articulosNoExistentes.add(codArticuloMp);
                            continue;
                        }
                        MateriaPrima mp = new MateriaPrima(1, codArticulo, articuloMp.getArticulo());
                        mp.setCantidad(BigDecimal.valueOf(row.getCell(cell.getColumnIndex()+1).getNumericCellValue()));
                        etapa.getMateriaPrimaList().add(mp);
                    }
                } else {
                    break;
                }
            }
            articuloPt.getEtapaList().add(etapa);
            
        }
        if(!articulosNoExistentes.isEmpty()) {
            throw new BusinessValidationException(String.format("Los articulos \n%s\n no existen", articulosNoExistentes.stream().distinct().collect(Collectors.joining(System.lineSeparator()))));
        }
    }
    
    public List<Articulo> getArticulosIn(List<String> articulos) {
        return dao.getArticulosIn(articulos);
    }
    
    public void cargarRendimientos(InputStream is, Set<String> articulosNoExistentes) throws IOException {
        Workbook workbook = new HSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();        
        while(iterator.hasNext()){
            Row row = iterator.next();
            if(row.getRowNum() == 0) continue;
            if(row.getCell(0) == null) break;
            String codArticulo = StringUtils.trimToEmpty(row.getCell(0).getStringCellValue());
            Articulo articulo = find(codArticulo);
            if(articulo != null) {
                articulo.setRendimiento(BigDecimal.valueOf(row.getCell(1).getNumericCellValue()));
            } else {
                articulosNoExistentes.add(codArticulo);
            }
        }
        System.err.println(articulosNoExistentes.stream().collect(Collectors.joining(System.lineSeparator(),StringUtils.repeat("=", 20)+System.lineSeparator(),System.lineSeparator()+StringUtils.repeat("=", 20))));
    }
}