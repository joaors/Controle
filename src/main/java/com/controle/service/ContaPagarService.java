package com.controle.service;

import com.controle.entity.ContaPagar;
import com.controle.entity.ContaPagarItem;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao
 */
@Stateless
public class ContaPagarService implements Serializable{
    
    @PersistenceContext
    private EntityManager em;
    
    public ContaPagar pagarParcela(ContaPagarItem pgi, ContaPagar cp) {        
        pgi.pagar();
        em.merge(cp);
        //return em.find(ContaPagar.class, cp.getId());
        return cp;
    }
    
    
}
