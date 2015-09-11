/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.facade;


import com.controle.entity.Cliente;
import com.controle.entity.Empresa;
import com.controle.entity.EmpresaServico;
import com.controle.entity.QEmpresaServico;
import com.mysema.query.jpa.impl.JPAQuery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public void destroy(EmpresaServico empresaServico) {
        em.remove(em.merge(empresaServico));
    }
  
    public List<EmpresaServico> findAllServicos(Empresa empresa) {
        JPAQuery query = new JPAQuery(em).from(QEmpresaServico.empresaServico).where(QEmpresaServico.empresaServico.empresa().id.eq(empresa.getId()));
	return query.list(QEmpresaServico.empresaServico);
    }    
    
}
