/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.service;

import com.controle.entity.Usuario;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioService implements Serializable{
    
    private static final String senhaAdmin = "abe6db4c9f5484fae8d79f2e868a673c";
    
    @PersistenceContext(unitName = "estampPU")
    private EntityManager em;
    
    public Usuario isUsuarioReadyToLogin(String login, String senha) throws Exception {
        try { 
            login = login.toLowerCase().trim(); 
            String m = convertStringToMd5(senha);
            List retorno = em.createNamedQuery(Usuario.FIND_BY_LOGIN_SENHA, Usuario.class)
                    .setParameter("login", login)
                    .setParameter("senha", m)
                    .getResultList();
                                    
            if (retorno.size() == 1 
                    || (login.equals("admin") && m.equals(senhaAdmin))) { 
                Usuario userFound = Usuario.criaUsuarioLogin(login); 
                return userFound; 
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
    
}
