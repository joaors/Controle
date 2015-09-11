/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.facade;

import com.controle.entity.OrdemServico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao
 */
@Stateless
public class OrdemServicoFacade extends AbstractFacade<OrdemServico>{

    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public OrdemServicoFacade() {
        super(OrdemServico.class);
    }
    
    public Boolean OpExists(Integer numero) {
        try {
            OrdemServico os = em.createQuery("select c from OrdemServico c where c.numero = :numero", OrdemServico.class).setParameter("numero", numero).getSingleResult();
            return os != null;
        } catch (NoResultException e) {
            return false;
        }        
    }

}
