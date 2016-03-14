/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author joaorodrigo
 */
public class ExportarPrivada {
    
   public static  KeyPair getPrivateKey(String alias, char[] password) throws FileNotFoundException, IOException, CertificateException {
        try {
            
           KeyStore ks = KeyStore.getInstance("JKS");

           char[] passPhrase = "123456".toCharArray();
           //BASE64Encoder myB64 = new BASE64Encoder();

           File certificateFile = new File("C:\\Temp\\repositorio.jks");
           ks.load(new FileInputStream(certificateFile), passPhrase);            
            
            // Get private key
            Key key = ks.getKey(alias, password);
            if (key instanceof PrivateKey) {
                // Get certificate of public key
                Certificate cert = ks.getCertificate(alias);

                // Get public key
                PublicKey publicKey = cert.getPublicKey();

                // Return a key pair
                return new KeyPair(publicKey, (PrivateKey)key);
            }
        } catch (UnrecoverableKeyException e) {
        	System.out.print(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
        	System.out.print(e.getMessage());
        } catch (KeyStoreException e) {
        	System.out.print(e.getMessage());
        }
        return null;
    }
   
    public static byte[] cifrarMsgComChavePrivada(byte[] body, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
                    //cifrar com a chave pública
            Cipher cifra = Cipher.getInstance("RSA");  

            // Criptografa a mensagem com a chave pública  
            // inicializa o algoritmo para a criptografia  
            cifra.init(Cipher.ENCRYPT_MODE, privateKey);  

            // criptgrafia o texto inteiro  
            byte[] mensagemCifrada = cifra.doFinal(body);  
            return mensagemCifrada;
    }   
    
}
