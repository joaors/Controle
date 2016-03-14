/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.service;

import com.controle.entity.Mensagem;
import com.controle.entity.Usuario;
import com.controle.util.ExportarPublica;
import com.controle.util.Seguranca;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioService implements Serializable{
    
    private static final String senhaAdmin = "alice";
    
    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;
    
    public Usuario isUsuarioReadyToLogin(String login, String senha) throws Exception {
        try { 
                        
            login = login.toLowerCase().trim(); 
            if (login.equals("admin") && senha.equals(senhaAdmin)) {
                return Usuario.criaUsuarioLogin(login);
            }            
            
            byte[] m = convertStringToMd5Char(senha);
            Usuario retorno = em.createNamedQuery(Usuario.FIND_BY_LOGIN, Usuario.class)
                    .setParameter("login", login)
                    .getSingleResult();
            
            if (Arrays.equals(retorno.getPassword(),m)) {
                return retorno;
            }
            return null;            
        } catch (Exception e) { 
            e.printStackTrace();
            throw new Exception(e.getMessage()); 
        }
    }
    
    public String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b: valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));            
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
            return null; 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
            return null; 
        }
    }
    
    public byte[] convertStringToMd5Char(String valor) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
            return valorMD5;
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
            return null; 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
            return null; 
        }
    }
    
    public void enviarMensagem(String body, Usuario usuario) throws Exception {
            UUID uuid = UUID.randomUUID();
            String random = uuid.toString();
            
            PublicKey publicKey = ExportarPublica.getPublicKey(usuario.getPathPublicKey());
            byte[] secretKeyCifrada = ExportarPublica.cifrarMsgComChavePublica(random.getBytes(), publicKey);
            
            Mensagem mensagem = new Mensagem();
            mensagem.setChaveSecreta(secretKeyCifrada);
            String textoCifrado = Seguranca.Encrypt(body, random.getBytes());
            
            mensagem.setMensagem(textoCifrado.getBytes());
            mensagem.setDestinatario(usuario);
            em.persist(mensagem);
    }
    
}
