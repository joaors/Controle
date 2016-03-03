/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.facade;

import com.controle.entity.Mensagem;
import com.controle.entity.Usuario;
import com.controle.util.Seguranca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author joao.rodrigo
 */
@Stateless
public class MensagemFacade extends AbstractFacade<Mensagem>{

    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Mensagem> buscarMensagemUsuario(String usuario) throws Exception {
        List<Mensagem> mensagens = em.createQuery(
                                        "SELECT m FROM Mensagem m where m.destinatario.login = :login", Mensagem.class)
                                        .setParameter("login", usuario).getResultList();
        for(Mensagem mensagem: mensagens) {
            byte[] msg = Seguranca.decryptPrivate(mensagem.getDestinatario().getPrivateKey(), mensagem.getMensagem());
            mensagem.setMensagemDecrypted(new String(msg));
        }
        return mensagens;
    }
        
}
