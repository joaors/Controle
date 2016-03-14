    package com.controle.jsf;

import com.controle.entity.Servico;
import com.controle.facade.AbstractFacade;
import com.controle.facade.ServicoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ServicoController extends AbstractController<Servico> implements Serializable {

    @Inject
    private ServicoFacade servicoFacade;

    public ServicoController() {
        super(Servico.class);
    }

    @Override
    protected AbstractFacade getFacade() {
        return servicoFacade;
    }

}
