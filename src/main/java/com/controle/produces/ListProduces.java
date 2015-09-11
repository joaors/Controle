/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.produces;

import com.controle.component.FornecedorEntityList;
import com.controle.component.ServicoEmpresaEntityList;
import com.controle.component.ClienteEntityList;
import com.controle.component.FilialEntityList;
import com.controle.entity.Cliente;
import com.controle.entity.EmpresaServico;
import com.controle.entity.Filial;
import com.controle.entity.Fornecedor;
import com.controle.facade.ClienteFacade;
import com.controle.facade.EmpresaServicoFacade;
import com.controle.facade.FilialFacade;
import com.controle.facade.FornecedorFacade;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author joao
 */
public class ListProduces {
    
    public enum TipoFiltro { Like, Equals, In, GreaterEqual, LessEqual, Different, IsNull, IsNotNull, Betweenm, Greater };
    
    @Inject
    private FilialFacade filialFacade;
    
    @Inject
    private ClienteFacade clienteFacade;
    
    @Inject
    private FornecedorFacade fornecedorFacade;
    
    @Inject
    private EmpresaServicoFacade empresaServicoFacade;    
    
    
    @Produces @FilialEntityList
    public EntityList filialAutoList() {
        EntityList list = new EntityList(filialFacade, Filial.FIND_FILIAL_LIKE, "descricao");
        return list;
    }    
    
    @Produces @ClienteEntityList
    public EntityList clienteAutoList() {
        EntityList list = new EntityList(clienteFacade, Cliente.FIND_CLIENTE_LIKE, "descricao");        
        return list;
    }
    
    @Produces @ServicoEmpresaEntityList
    public EntityList servicoAutoList() {
        EntityList list = new EntityList(empresaServicoFacade, EmpresaServico.FIND_SERVICO_LIKE, "descricao");
        return list;
    }
    
    @Produces @FornecedorEntityList
    public EntityList fornecedorAutoList() {
        EntityList list = new EntityList(fornecedorFacade, Fornecedor.FIND_FORNECEDOR_LIKE, "descricao");
        return list;
    }
    
}
