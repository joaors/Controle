package com.controle.jsf;

import com.controle.entity.Cliente;
import com.controle.entity.EmpresaServico;
import com.controle.entity.Servico;
import com.controle.facade.AbstractFacade;
import com.controle.facade.ClienteFacade;
import com.controle.facade.EmpresaServicoFacade;
import com.controle.jsf.util.JsfUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class ClienteController extends AbstractController<Cliente> {

    @Inject
    private ClienteFacade ejbFacade;
    
    @Inject
    private EmpresaServicoFacade empresaServicoFacade;
    
    @Inject
    private ServicoController servicoController;
    
    @Inject
    private EmpresaServicoController empresaServicoController;

    private List<EmpresaServico> servicos;
        
    public ClienteController() {
        super(Cliente.class);
    }
    
    public void onRowSelect(SelectEvent event) {
        Servico servico = (Servico) event.getObject();
        empresaServicoFacade.persist(new EmpresaServico(getSelected(), servico));
        JsfUtil.addSuccessMessage("registroSalvo");
        RequestContext.getCurrentInstance().execute("PF('dlgServico').hide()");
    }
      
    @Override
    public void setSelected(Cliente selected) {
       empresaServicoController.setFiltroMaster(selected);
       super.setSelected(selected);
    }    
    
    public List<EmpresaServico> getServicos() {
        servicos = ejbFacade.findAllServicos(getSelected());
        return servicos;
    }

    public void setServicos(List<EmpresaServico> servicos) {
        this.servicos = servicos;
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    public ClienteFacade getEjbFacade() {
        return ejbFacade;
    }
    
    public ServicoController getServicoController() {
        return servicoController;
    }
    
    public EmpresaServicoController getEmpresaServicoController() {
        return empresaServicoController;
    }
     
}
