package com.controle.jsf;

import com.controle.entity.Produto;
import com.controle.facade.ProdutoFacade;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class ProdutoController extends AbstractController<Produto> {

    @EJB
    private ProdutoFacade ejbFacade;

    @Override
    protected ProdutoFacade getFacade() {
        return ejbFacade;
    }
    
    public ProdutoController() {
        super(Produto.class);
    }

}
