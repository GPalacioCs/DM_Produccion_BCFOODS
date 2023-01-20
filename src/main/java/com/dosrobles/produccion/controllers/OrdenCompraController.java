package com.dosrobles.produccion.controllers;

import com.dosrobles.produccion.entities.*;
import com.dosrobles.produccion.enums.EstadoRecepcion;
import com.dosrobles.produccion.exceptions.BusinessValidationException;
import com.dosrobles.produccion.service.*;
import com.dosrobles.produccion.utils.MessageUtils;
import com.dosrobles.produccion.utils.MimeUtils;
import com.dosrobles.produccion.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class OrdenCompraController extends AbstractController<OrdenCompraService, OrdenCompra> {
    
    @Getter @Setter
    private OrdenCompra ordenCompra = new OrdenCompra();
    @Getter
    private List<Articulo> articulosMateriaPrima;
    @Getter @Setter
    private Articulo selectedArticuloMateriaPrima;
    @Inject
    private ProveedorService proveedorService;
    @Inject
    private ProveedorAlinsaService proveedorAlinsaService;
    @Inject
    private BodegaService bodegaService;
    @Inject
    private ArticuloService articuloService;
    @Inject
    private ClienteService clienteService;
    @Inject
    private OrdenProduccionService ordenProduccionService;
    @Inject
    private TermoService termoService;
    @Inject
    private MateriaPrimaService materiaPrimaService;
    @Inject
    private ParametrosPrService parametrosPrService;
    @Inject
    private PesadaService pesadaService;
    @Inject
    private EmpaqueEncabezadoService empaqueEncabezadoService;
    @Getter @Setter
    private boolean fullScreen;
    @Getter @Setter
    private boolean fresco = true;
    @Getter @Setter
    private Pesada pesada = new Pesada();
    
    @Getter @Setter
    private ParametrosPr parametrosPr;
    @Getter @Setter
    private Articulo productoTerminado;
    
    private Articulo articuloSugerido;
    
    @PostConstruct
    private void init() {
        cargarLista();
        this.articulosMateriaPrima = articuloService.getArticulosMateriaPrima();
        parametrosPr = parametrosPrService.getParametro();
    }
    
    @Override
    public void create() {
        this.pesada = new Pesada();
        if(parametrosPr.getBodegaInventario() == null || parametrosPr.getBodegaExportacion() == null) {
            MessageUtils.showGrowlError("Debe configurar las bodegas de exportación e inventario");
            return;
        }
        create(true);
    }
    
    public void create(boolean isFresco) {
        entity = new OrdenCompra();
        entity.setOrdenCompra(service.getConsecutivo());
        entity.setFecha(Utils.stripTime(new Date()));
        entity.setFechaRequerida(entity.getFechaRequerida());
        entity.setFresco(isFresco);
        entity.setBodega(parametrosPr.getBodegaExportacion());
        fullScreen = true;
        create(entity);
    }

    public void checkTipoAlinsa(SelectEvent event){
        String selectedItem = (String) event.getObject();
        entity.setTipo_alinsa(selectedItem);
    }
    
    @Override
    public void edit() {
        this.pesada = new Pesada();
        if(parametrosPr.getBodegaInventario() == null || parametrosPr.getBodegaExportacion() == null) {
            MessageUtils.showGrowlError("Debe configurar las bodegas de exportación e inventario");
            return;
        }
        if (selection.isAprobado()) {
            MessageUtils.showGrowlError("No se puede editar una orden aprobada");
            return;
        }
        edit(service.findByEntity(selection));
        fullScreen = true;
    }
    
    public List<Proveedor> getProveedores() {
        return proveedorService.findAll();
    }

    public List<ProveedorAlinsa> getProveedoresAlinsa() {
        return proveedorAlinsaService.findAll();
    }

    public List<Bodega> getBodegas() {
        return bodegaService.findAll();
    }
    
    public List<OrdenProduccion> getOrdenProduccionList() {
        return ordenProduccionService.findFrescoPlaneado();
    }
    
    public List<Cliente> getClientes() {
        List<Cliente> clientes = clienteService.findAll().stream().filter(c -> c.getCliente().startsWith("CE"))
                .collect(Collectors.toList());
        return clientes;
    }
    
    public List<Termo> getTermos() {
        return termoService.findAll();
    }
    
    public List<Articulo> getProductosTerminados() {
        return articuloService.getArticulosProduccion();
    }
    
    public Articulo getArticuloPesada() {
        if(pesada == null) return null;
        return pesada.getArticulo();
    }
    
    public void setArticuloPesada(Articulo articulo) {
        if (pesada != null) {
            pesada.setArticulo(articulo);
            sugerirProductoTerminado();
        }
    }
    
    @Override
    public void cargarLista() {
        list = service.findByModuloOrigen("DM");
    }
    
    public void agregarPesada() {
        int numPesada = entity.getPesadas().stream().mapToInt(p -> p.getPesada()).max().orElse(0) + 1;
        if (pesada.getArticulo() == null) {
            MessageUtils.showMessage("Debe seleccionar un artículo");
            return;
        }
        if (pesada.getCliente() == null && this.isFresco()) {
            MessageUtils.showMessage("Debe seleccionar un cliente");
            return;
        }
        if (pesada.getTermo() == null && !this.isFresco()) {
            MessageUtils.showMessage("Debe seleccionar un termo");
            return;
        }
        if (fresco) {
            pesada.setTermo(null);
            pesada.setProductoTerminado(this.productoTerminado == null? articuloSugerido : productoTerminado);
            if (pesada.getProductoTerminado() == null) {
                MessageUtils.showMessage("Debe seleccionar un producto terminado");
                return;
            }
        }
        pesada.setOrdenCompra(entity);
        pesada.setPesada(numPesada);
    
        int maxCaja1 = entity.getPesadas().stream().filter(p -> !this.isFresco() || p.getCliente().equals(pesada.getCliente())).map(p -> p.getCaja()).filter(n -> n != null).mapToInt(nn -> nn).max().orElse(0);
        EmpaqueEncabezado empaqueEncabezado = empaqueEncabezadoService.findByOrdenProduccionAndCliente(entity.getOrdenProduccion(), pesada.getCliente());
        int maxCaja2 = empaqueEncabezado == null? 0 : empaqueEncabezado.getEmpaqueList().stream().mapToInt(e -> e.getId().getEmpaque()).max().orElse(0);
        int maxCaja3 = pesadaService.getMaxCaja(entity.getOrdenProduccion(), pesada.getCliente());
        int numCajaActual = Math.max(Math.max(maxCaja1, maxCaja2), maxCaja3);
        /*if (numCajaActual == 0) {
            numCajaActual = pesadaService.getMaxCaja(entity.getOrdenProduccion(), pesada.getCliente());
        }*/
        int numCaja = numCajaActual + 1;
        if (this.isFresco()) {
            pesada.setCaja(numCaja);
        }
    
        entity.getPesadas().add(pesada);
        updateLineas();
        
        
        Pesada newPesada = new Pesada();
        newPesada.setCliente(pesada.getCliente());
        newPesada.setTermo(pesada.getTermo());
        newPesada.setArticulo(pesada.getArticulo());
        newPesada.setProductoTerminado(pesada.getProductoTerminado());
        pesada = newPesada;
        
        RequestContext.getCurrentInstance().execute("$('.txt-cantidad-pesada').focus();$('.txt-cantidad-pesada').select()");
    }
    
    public void quitarPesada(Pesada pesada) {
        entity.getPesadas().removeIf(p -> p.getPesada() == pesada.getPesada() && entity.getOrdenCompra().equals(pesada.getOrdenCompra().getOrdenCompra()));
        if (pesada.isExportacion()) {
            renumerarCajas(pesada.getCliente(), pesada.getCaja());
        }
        updateLineas();
    }
    
    private void updateLineas() {
        entity.getLineas().clear();
        entity.getLineas().addAll(service.getLineasFromPesadas(entity));
    }
    
    public void onCellEdit(CellEditEvent event) {
        updateLineas();
    }

    public void enviarAProduccion(){
        try {
            service.enviarAProduccion(selection);
            MessageUtils.showGrowlSuccess("Proceso Completado");
        }catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.showGrowlError();
        }
    }
    
    public void aprobarRecepcion() {
        try {
            service.aprobarRecepcion(selection);
            selection.setEstadoRecepcion(EstadoRecepcion.APROBADO);
            MessageUtils.showGrowlSuccess("La orden fue aprobada exitosamente");
        } catch (BusinessValidationException e) {
            MessageUtils.showGrowlError(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.showGrowlError();
        }
    }
    
    @Override
    public void save() {
        fullScreen = false;
        super.save();
    }
    
    @Override
    public void cancel() {
        super.cancel();
        fullScreen = false;
    }
    
    public List<Cliente> getClientesAgregados() {
        return entity.getPesadas().stream().map(p -> p.getCliente()).distinct().collect(Collectors.toList());
    }
    
    public void sugerirProductoTerminado() {
        if(!isFresco()) return;
        MateriaPrima mp = materiaPrimaService.findMateriaPrimaByArticuloHijo(pesada.getArticulo());
        if (mp != null) {
            setProductoTerminado(mp.getArticuloPadre());
            articuloSugerido = mp.getArticuloPadre();
        }
    }
    
    private void renumerarCajas(Cliente cliente, int cajaEliminada) {
        List<Pesada> pesadas = entity.getPesadas().stream().filter(p -> p.isExportacion() && p.getCliente().getCliente().equals(cliente.getCliente()))
                .sorted(Comparator.comparing(Pesada::getCaja))
                .collect(Collectors.toList());
        
        if(pesadas.isEmpty()) return;
    
        int firstVal = 0;
        for (int i = 0; i < pesadas.size(); i++) {
            Pesada pesada = pesadas.get(i);
            if (pesada.getCaja() > cajaEliminada) {
                pesada.setCaja(pesada.getCaja() - 1);
            }
            
        }
    }
    
    public void imprimirOrden() {
        OrdenCompra orden = selection;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_orden_recepcion_%s.pdf", orden.getOrdenCompra());
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/rpt_orden_recepcion/");
    
        try {
            service.imprimirOrden(orden, response.getOutputStream(), sourceFileName);
            fc.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(OrdenCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imprimirVoucher(){
        OrdenCompra orden = selection;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.responseComplete();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType(MimeUtils.getMimeType("pdf"));
        String filename = String.format("rpt_voucher_recepcion_%s.pdf", orden.getOrdenCompra());
        response.setHeader("Content-disposition","attachment; filename="+filename);
        String sourceFileName = request.getServletContext().getRealPath("/reportes/rpt_voucher_recepcion/");

        try {
            service.imprimirVoucher(orden, response.getOutputStream(), sourceFileName);
            fc.responseComplete();
        } catch (Exception e) {

        }
    }
    
    @Override
    public void reset() {
        super.reset();
        setFullScreen(false);
    }
    
    @Override
    public void delete() {
        if (selection.isAprobado()) {
            MessageUtils.showGrowlError("La orden ya está aprobada");
            return;
        }
        super.delete();
    }
    
    /*public Articulo getProductoTerminado() {
        if(pesada == null) return null;
        return pesada.getProductoTerminado();
    }
    
    public void setProductoTerminado(Articulo pt) {
        if (pesada != null) {
            pesada.setProductoTerminado(pt);
        }
    }*/
}
