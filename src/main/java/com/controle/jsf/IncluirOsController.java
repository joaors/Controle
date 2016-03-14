/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.jsf;

import com.controle.component.ClienteEntityList;
import com.controle.component.FilialEntityList;
import com.controle.component.ServicoEmpresaEntityList;
import com.controle.entity.Cliente;
import com.controle.entity.EmpresaServico;
import com.controle.entity.Filial;
import com.controle.entity.OrdemServico;
import com.controle.entity.Usuario;
import com.controle.facade.OrdemServicoFacade;
import com.controle.jsf.util.JsfUtil;
import com.controle.produces.EntityList;
import com.controle.produces.ListProduces;
import com.controle.util.Filtro;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author joao
 */
@Named
@ViewScoped
public class IncluirOsController extends AbstractController<OrdemServico> {
    
    @Inject
    private OrdemServicoFacade ejbFacade;
    
    @Inject @ClienteEntityList
    private EntityList clientes;

    @Inject @ServicoEmpresaEntityList
    private EntityList empresaServicos;
    
    @Inject @FilialEntityList
    private EntityList filiais;
    
    private String senha;
    
    private OrdemServico os = new OrdemServico();
    
    @PostConstruct
    public void inti(){
        if (empresaServicos.getFiltros().isEmpty()) {
            empresaServicos.addFiltro(new Filtro("empresa", new Cliente(0, null), ListProduces.TipoFiltro.Equals));        
        }        
    }

    public EntityList getFiliais() {
        return filiais;
    }    

    public EntityList getClientes() {
        return clientes;
    }
    
    public EntityList getEmpresaServicos() {
        return empresaServicos;
    }    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
    
    public void onRowSelectCliente(SelectEvent event) {
        JsfUtil.cleanSubmittedValues("form:cliente:valor");
        JsfUtil.cleanSubmittedValues("form:servico:valor");
        JsfUtil.cleanSubmittedValues("form:valorUnitario");
        Cliente cliente = (Cliente) event.getObject();
        os.setCliente(cliente);
        os.setEmpresaServico(null);
        os.setValorUnitario(BigDecimal.ZERO);
        os.calculaValorTotal();
        empresaServicos.limpaFiltros();
        empresaServicos.addFiltro(new Filtro("empresa", cliente, ListProduces.TipoFiltro.Equals) );
        RequestContext.getCurrentInstance().execute("PF('dlgCliente').hide()");
    }
    
    public void onRowSelectServico(SelectEvent event) {
        JsfUtil.cleanSubmittedValues("form:servico:valor");
        JsfUtil.cleanSubmittedValues("form:valorUnitario");
        os.setEmpresaServico((EmpresaServico) event.getObject());
        os.setValorUnitario(os.getEmpresaServico().getValorUnitario());
        os.calculaValorTotal();
        RequestContext.getCurrentInstance().execute("PF('dlgServico').hide()");
    }
    
    public void onRowSelectFilial(SelectEvent event) {        
        JsfUtil.cleanSubmittedValues("form:filial:valor");
        Filial filial = (Filial) event.getObject();
        os.setFilial(filial);
        RequestContext.getCurrentInstance().execute("PF('dlgFilial').hide()");
    }    
     
    @Override
    public void salvar() {
        try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession(true);  
                Usuario user = (Usuario) session.getAttribute("usuario");            
            if (ejbFacade.OpExists(os.getNumero())) {
                throw new Exception("ordemServicoJaCadastrada");
            }
            ejbFacade.salvarOrdemServico(os, senha, user);
            os = new OrdemServico();            
            JsfUtil.addSuccessMessage("registroSalvo");
        } catch (Exception ex) {            
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }  
    
    @Override
    protected OrdemServicoFacade getFacade() {
        return ejbFacade;
    }
       
    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }
}
