package com.controle.jsf;

import com.controle.entity.Filial;
import com.controle.facade.FilialFacade;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named
@ViewScoped
public class FilialController extends AbstractController<Filial> {

    @Inject
    private FilialFacade ejbFacade;

    public FilialController() {
        super(Filial.class);
    }

    @Override
    protected FilialFacade getFacade() {
        return ejbFacade;
    }

}
