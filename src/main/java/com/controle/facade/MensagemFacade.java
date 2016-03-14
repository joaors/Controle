/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.facade;

import com.controle.entity.Mensagem;
import com.controle.entity.Usuario;
import com.controle.util.ExportarPrivada;
import com.controle.util.Seguranca;
import java.security.KeyPair;
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

    public List<Mensagem> buscarMensagemUsuario(String usuario, String senha) throws Exception {
        List<Mensagem> mensagens = em.createQuery(
                                        "SELECT m FROM Mensagem m where m.destinatario.login = :login", Mensagem.class)
                                        .setParameter("login", usuario).getResultList();
        
        for(Mensagem mensagem: mensagens) {
            KeyPair chavePrivada = ExportarPrivada.getPrivateKey(usuario, senha.toCharArray());
            byte[] chaveSecreta = Seguranca.decryptPrivate(
                    chavePrivada.getPrivate(), mensagem.getChaveSecreta()
            );
            String msg = Seguranca.Decrypt(chaveSecreta, mensagem.getMensagem());
            mensagem.setMensagemDecrypted(msg);
        }
        return mensagens;
    }
        
}
