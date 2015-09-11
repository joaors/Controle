/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.facade;

import com.controle.entity.ContaPagar;
import com.controle.entity.ContaPagarItem;
import com.controle.entity.QContaPagarItem;
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
public class ContaPagarFacade extends AbstractFacade<ContaPagar>{

    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaPagarFacade() {
        super(ContaPagar.class);
    }
    
    public List<ContaPagarItem> getParcelas(ContaPagar contaPagar) {
        List<ContaPagarItem> parcelas;
        QContaPagarItem i = QContaPagarItem.contaPagarItem;
        JPAQuery query = new JPAQuery(em);
	query.from(i).where(i.contaPagar().id.eq(contaPagar.getId()));
        parcelas = query.list(i);
        return parcelas;
    }
    
    
    public Integer getSequencia(ContaPagar contaPagar) {
        QContaPagarItem i = QContaPagarItem.contaPagarItem;
        JPAQuery query = new JPAQuery(em);
	query.from(i).where(contaPagar.getId() != null ? i.contaPagar().id.eq(contaPagar.getId()) : i.contaPagar().id.isNull());
        Integer parcela = query.uniqueResult(i.sequencia.max());
        if (parcela == null) {
            parcela = 0;
        }
        return parcela;
    }
    
    public ContaPagar find(ContaPagar contaPagar) {
        em.refresh(contaPagar);
        return contaPagar;
    }
    
    public void deleteContaPagarItem(ContaPagar cp, ContaPagarItem item) {
        cp.removeItem(item);
        em.merge(cp);
    }
    
}
