package com.controle.jsf;

import com.controle.component.FilialEntityList;
import com.controle.component.FornecedorEntityList;
import com.controle.entity.ContaPagar;
import com.controle.entity.ContaPagarItem;
import com.controle.entity.Filial;
import com.controle.entity.Fornecedor;
import com.controle.facade.AbstractFacade;
import com.controle.facade.ContaPagarFacade;
import com.controle.jsf.util.JsfUtil;
import com.controle.produces.EntityList;
import com.controle.service.ContaPagarService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ContaPagarController extends AbstractController<ContaPagar>{

    @EJB
    private ContaPagarFacade facade;
    
    @Inject @FilialEntityList
    private EntityList filiais;
    
    @Inject @FornecedorEntityList
    private EntityList fornecedores;
    
    @Inject
    private ContaPagarService contaPagarService;
    
    private ContaPagarItem itemSelecionado;

    private List<ContaPagarItem> itens;
    
    private List<ContaPagarItem> selecionados;
    
    private Map<String, Object> valorInicialAttrs;


    public ContaPagarController() {        
        super(ContaPagar.class);
        selecionados = new ArrayList<ContaPagarItem>();
        this.valorInicialAttrs = new HashMap<>();
        this.valorInicialAttrs.put("type", "number");
        this.valorInicialAttrs.put("min", "1");
        this.valorInicialAttrs.put("max", "4");
        this.valorInicialAttrs.put("required", "required");
        this.valorInicialAttrs.put("title",
                "Enter a number between 1 and 4 inclusive.");        
    }    

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }        
    
    @Override
    public void voltar() {
        itemSelecionado = null;
        super.voltar();
    }
    
    public Boolean getDisableDelete() {
        return itemSelecionado == null || itemSelecionado.getPago();
    }
    
    public Boolean getDisablePagar() {
        return itemSelecionado == null || itemSelecionado.getPago();
    }
    
    public void salvarParcela() {
        try {
            Integer parcela = facade.getSequencia(getSelected());
            itemSelecionado.setSequencia(parcela+1);
            itemSelecionado.setValorFinal(itemSelecionado.getValorInicial().subtract(itemSelecionado.getDesconto()));            
            itemSelecionado.setContaPagar(getSelected());
            getSelected().addItem(itemSelecionado);
            salvar();
            JsfUtil.closeDialog("dlgFormItem");          
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            if (itemSelecionado.getId() == null) {
                getSelected().getContaPagarItem().remove(itemSelecionado);
            }
        }
    }
    
    public void pagarParcela() {
        try {            
            setSelected(contaPagarService.pagarParcela(itemSelecionado, getSelected()));
            JsfUtil.addSuccessMessage("pagamentoEfetuado");            
            itemSelecionado = null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, e.getMessage());
        }
    }
    
    @Override
    public void salvar() {

        try {
            if (getSelected().getId() == null) {
                getFacade().persist(getSelected());
            } else {
                getFacade().edit(getSelected());
            }            
            JsfUtil.addSuccessMessage("registroSalvo");
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
   
    public void deleteContaPagarItem() {                
        try {
            facade.deleteContaPagarItem(getSelected(), itemSelecionado);
            itemSelecionado = null;
            JsfUtil.addSuccessMessage("registroExcluido");            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("erroAoExcluirRegistro");            
        }
    }
    
    public void novoContaPagarItem() {
        itemSelecionado = ContaPagarItem.createContaPagarItem();                
        JsfUtil.openDialog("dlgFormItem");
    }
    
    public void onRowSelectFornecedor(SelectEvent event) {        
        JsfUtil.cleanSubmittedValues("form:fornecedor:valor");
        Fornecedor f = (Fornecedor) event.getObject();
        getSelected().setFornecedor(f);        
        RequestContext.getCurrentInstance().execute("PF('dlgFornecedor').hide()");
    }
    
    public void onRowSelectFilial(SelectEvent event) {        
        JsfUtil.cleanSubmittedValues("form:filial:valor");
        Filial f = (Filial) event.getObject();
        getSelected().setFilial(f);
        RequestContext.getCurrentInstance().execute("PF('dlgFilial').hide()");
    }
    
    public void onRowSelectContaPagar(SelectEvent event) {
        setSelected((ContaPagar) event.getObject());
        List<ContaPagarItem> parcelas = facade.getParcelas(getSelected());
        getSelected().setContaPagarItem(parcelas);
    }
    
    public Map<String, Object> getValorInicialAttrs() {
        return valorInicialAttrs;
    }

    public void setValorInicialAttrs(Map<String, Object> valorInicialAttrs) {
        this.valorInicialAttrs = valorInicialAttrs;
    }
    
    
    public List<ContaPagarItem> getItens() {
        return itens;
    }

    public void setItens(List<ContaPagarItem> itens) {
        this.itens = itens;
    }   
    
    public EntityList getFiliais() {
        return filiais;
    }

    public EntityList getFornecedores() {
        return fornecedores;
    }    
    
    public ContaPagarItem getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(ContaPagarItem itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public List<ContaPagarItem> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<ContaPagarItem> selecionados) {
        this.selecionados = selecionados;
    }
    
    
    
}
