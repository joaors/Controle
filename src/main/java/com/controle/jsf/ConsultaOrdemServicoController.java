/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.component.ClienteEntityList;
import com.controle.component.FilialEntityList;
import com.controle.entity.Cliente;
import com.controle.entity.Filial;
import com.controle.entity.OrdemServico;
import com.controle.facade.AbstractFacade;
import com.controle.facade.OrdemServicoFacade;
import com.controle.produces.EntityList;
import com.controle.produces.ListProduces;
import com.controle.util.Filtro;
import com.controle.util.Opcoes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author joao
 */
@Named
@ViewScoped
public class ConsultaOrdemServicoController extends AbstractController<OrdemServico>{

    @Inject
    private OrdemServicoFacade facade;
    
    @Inject @ClienteEntityList
    private EntityList clientes;
    
    @Inject @FilialEntityList
    private EntityList filiais;
    
    private Cliente cliente;
    
    private Filial filial;
    
    private Date dataInicio;
    
    private Date dataFim;
    
    private Opcoes pago = Opcoes.Todos;

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }
    
    public EntityList getClientes() {
        return clientes;
    }

    public EntityList getFiliais() {
        return filiais;
    }    
    
    public void onRowSelectCliente(SelectEvent event) {        
        cliente = (Cliente) event.getObject();
        RequestContext.getCurrentInstance().execute("PF('dlgCliente').hide()");
    }
    
    public void onRowSelectFilial(SelectEvent event) {
        filial = (Filial) event.getObject();
        RequestContext.getCurrentInstance().execute("PF('dlgFilial').hide()");
    }
    
    public Opcoes[] getListOpcoes() {
	return Opcoes.values();
    }	    
    
    public void consultar() {
        List<Filtro> filtros = new ArrayList<Filtro>();
        
        if (cliente != null) {
            filtros.add(new Filtro("cliente", cliente, ListProduces.TipoFiltro.Equals));
        }
        
        if (filial != null) {
            filtros.add(new Filtro("filial", filial, ListProduces.TipoFiltro.Equals));
        }
        
        if (dataFim != null) {
            filtros.add(new Filtro("fim", dataFim, ListProduces.TipoFiltro.LessEqual));
        }
        
        if (dataInicio != null) {
            filtros.add(new Filtro("inicio", dataInicio, ListProduces.TipoFiltro.GreaterEqual));
        }
        
	if (pago.equals(Opcoes.Nao)) {
            filtros.add(new Filtro("pago", Boolean.FALSE, ListProduces.TipoFiltro.Equals));
        }

        if (pago.equals(Opcoes.Sim)) {
            filtros.add(new Filtro("pago", Boolean.TRUE, ListProduces.TipoFiltro.Equals));
        }
        
        setFiltros(filtros);        
    }  
    
    public Boolean getRenderExcluirOp(OrdemServico os) {        
        return !os.getPago();
    }
    
    public void setFacade(OrdemServicoFacade facade) {
        this.facade = facade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Opcoes getPago() {
        return pago;
    }

    public void setPago(Opcoes pago) {
        this.pago = pago;
    }
    
    
    
}
