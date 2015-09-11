package com.controle.jsf;

import com.controle.entity.Filial;
import com.controle.facade.FilialFacade;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.criteria.Predicate;

@Named
@ViewScoped
public class FilialController extends AbstractController<Filial> {

    @EJB
    private FilialFacade ejbFacade;

    public FilialController() {
        super(Filial.class);
    }

    @Override
    protected FilialFacade getFacade() {
        return ejbFacade;
    }

}
