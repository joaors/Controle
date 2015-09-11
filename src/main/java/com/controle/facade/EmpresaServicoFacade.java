/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.facade;

import com.controle.entity.EmpresaServico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao
 */
@Stateless
public class EmpresaServicoFacade extends AbstractFacade<EmpresaServico> {
    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaServicoFacade() {
        super(EmpresaServico.class);
    }
    
    public void destroy(EmpresaServico empresaServico) {
        em.remove(em.merge(empresaServico));
    } 

}
