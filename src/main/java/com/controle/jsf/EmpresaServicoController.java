/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.jsf;

import com.controle.entity.Cliente;
import com.controle.entity.EmpresaServico;
import com.controle.facade.AbstractFacade;
import com.controle.facade.EmpresaServicoFacade;
import com.controle.produces.ListProduces;
import com.controle.util.Filtro;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Named
@ViewScoped
public class EmpresaServicoController extends AbstractController<EmpresaServico>{

    @PersistenceContext(type=PersistenceContextType.TRANSACTION)
    protected EntityManager em;	    
    
    @Inject
    private EmpresaServicoFacade ejbFacade;

    public EmpresaServicoController() {
        super(EmpresaServico.class);
    }  
    
    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }
    
    public void setFiltroMaster(Cliente cliente) {
        setFiltros(new ArrayList<Filtro>());
        getFiltros().add(new Filtro("empresa", cliente, ListProduces.TipoFiltro.Equals));                
    }
    
    public void onValorChange() {  
	EmpresaServico empresaServico = getRowData();
	ejbFacade.edit(empresaServico);
    }	    
    
}
