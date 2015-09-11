/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.facade;

import com.controle.entity.Fornecedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao
 */
@Stateless
public class FornecedorFacade extends AbstractFacade<Fornecedor>{

    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FornecedorFacade() {
        super(Fornecedor.class);
    }
    
    public void destroy(Fornecedor fornecedor) {
        em.remove(em.merge(fornecedor));
    }
    
}
