/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.entity.Fornecedor;
import com.controle.facade.AbstractFacade;
import com.controle.facade.FornecedorFacade;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author joao
 */
@Named
@ViewScoped
public class FornecedorController extends AbstractController<Fornecedor>{

    @EJB
    private FornecedorFacade facade;
    
    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }
    
    public FornecedorController() {
        super(Fornecedor.class);
    }    
    
}
