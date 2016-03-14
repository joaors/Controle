/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.facade;

import com.controle.entity.OrdemServico;
import com.controle.entity.Usuario;
import com.controle.util.ExportarPrivada;
import com.controle.util.ExportarPublica;
import com.controle.util.Seguranca;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    
    public void salvarOrdemServico(OrdemServico os, String senha, Usuario usuario) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, CertificateException {
    	os.setUsuarioAssinatura(usuario);
    	String tostring = os.toString();        
        byte[] msg = tostring.getBytes();
        byte[] hash = Seguranca.gerarHash(msg);
        KeyPair keypair = ExportarPrivada.getPrivateKey(usuario.getLogin(), senha.toCharArray());
        os.setAssinatura(ExportarPrivada.cifrarMsgComChavePrivada(hash, keypair.getPrivate()));
        Usuario user = em.find(Usuario.class, usuario.getId());
        os.setUsuarioAssinatura(user);
        em.merge(os);
    }

    public String verificarAssinatura(OrdemServico os) throws Exception {
        OrdemServico ordemServico = em.find(OrdemServico.class, os.getId());
        PublicKey publicKey = ExportarPublica.getPublicKey(ordemServico.getUsuarioAssinatura().getPathPublicKey());
        byte[] hash = Seguranca.gerarHash(os.toString().getBytes());
        byte[] hashDecrypted = Seguranca.DecryptPublic(publicKey, hash);
        if (Arrays.equals(hash, hashDecrypted)) {
            return "Assinatura confirmada pelo usuario "+os.getUsuarioAssinatura().getLogin();
        }
        return "Assinatura incorreta";
    }

}
