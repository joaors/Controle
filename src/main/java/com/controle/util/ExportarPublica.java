/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import javax.crypto.Cipher;
import javax.security.cert.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author joaorodrigo
 */
public class ExportarPublica {
    
    public static PublicKey getPublicKey(String filePath) 
            throws FileNotFoundException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        //cria o input para o arquivo do certificado
        FileInputStream fr = new FileInputStream(filePath);

        //cria a instância do certificado
        X509Certificate c = X509Certificate.getInstance(fr);

        //Verificação do certificado                     
        //Neste caso, auto assinado
        c.verify(c.getPublicKey());

        //Verifica a validade
         c.checkValidity();
         return c.getPublicKey();

    }
    
    public static byte[] cifrarMsgComChavePublica(byte[] body, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
                    //cifrar com a chave pública
            Cipher cifra = Cipher.getInstance("RSA");  

            // Criptografa a mensagem com a chave pública  
            // inicializa o algoritmo para a criptografia  
            cifra.init(Cipher.ENCRYPT_MODE, publicKey);  

            // criptgrafia o texto inteiro  
            byte[] mensagemCifrada = cifra.doFinal(body);  
            return mensagemCifrada;
    }
    
}
