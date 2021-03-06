/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.util;

import java.security.*;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 *
 * @author joaorodrigo
 */
public class Seguranca {
    
    public static byte[] gerarHash(byte[] msg) throws NoSuchAlgorithmException {
        //cria uma instância do gerador de hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //coloca a mensagem no gerador de hash
        md.update(msg);

        return md.digest();
    }

    private static SecretKey resumo_chave(byte[] chave) throws Exception {

        byte[] key = gerarHash(chave);
        DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        return keyFactory.generateSecret(desKeySpec);
    }

    public static String Encrypt(String texto, byte[] key) throws Exception {
        SecretKey secretKey = resumo_chave(key);
        //Cria uma instância do cifrador
        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.ENCRYPT_MODE, secretKey);// modo encriptação

        byte[] encrypted = desCipher.doFinal(texto.getBytes());
        return new String(encrypted);
    }

    public static String Decrypt(byte[] key, byte[] encrypted) throws Exception {
        SecretKey secretKey = resumo_chave(key);

        Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decrypted = desCipher.doFinal(encrypted);
        return new String(decrypted);
    }
    
    public static byte[] DecryptPublic(PublicKey key, byte[] encrypted) throws Exception {

        Cipher cifra = Cipher.getInstance("RSA");
        cifra.init(Cipher.DECRYPT_MODE, key);

        // Decifra a mensagem
        byte[] mensagemOriginal = cifra.doFinal(encrypted);
        return mensagemOriginal;
    }    

    public static KeyPair generateKeyPairRSA() throws Exception {
        // Gera um par de chaves RSA de 1024 bits  
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        gerador.initialize(1024);
        KeyPair chaves = gerador.generateKeyPair();
        return chaves;
    }

    public static byte[] encryptPublic(byte[] publicKey, byte[] mensagem) throws Exception {
        // Cria uma implementação do RSA  
        Cipher cifra = Cipher.getInstance("RSA");

        //Inicializa o algoritmo para criptografiar a mensagem com a chave pública  
        PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey));
        cifra.init(Cipher.ENCRYPT_MODE, key);

        // Criptgrafa o texto inteiro  
        byte[] mensagemCifrada = cifra.doFinal(mensagem);
        return mensagemCifrada;

    }

    public static byte[] decryptPrivate(PrivateKey key, byte[] mensagem) throws Exception {

        Cipher cifra = Cipher.getInstance("RSA");
        cifra.init(Cipher.DECRYPT_MODE, key);

        // Decifra a mensagem
        byte[] mensagemOriginal = cifra.doFinal(mensagem);
        return mensagemOriginal;
    }
    
    public static byte[] assinaturaRSA(byte[] mensagem, byte[] chavePrivada) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

        Signature sgn = null;
        Signature vrf = null;
        byte [] assina = null;

        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(chavePrivada));

        sgn = Signature.getInstance("MD5withRSA");

        //inicializa o algoritmo de assinatura
        sgn.initSign(privateKey); 
        
        sgn.update(mensagem);
        
        //faz a assinatura
        assina = sgn.sign();
        return assina;
    }
    
    public static boolean verificaAssinaturaRSA(byte[]mensagem, byte[] assinatura, byte[] chavePublica) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException{

        // cria uma instância do RSA com MD5
        Signature vrf = Signature.getInstance("MD5withRSA");
        
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(chavePublica));        
        
        //inicializa uma verificação com a chave pública do parâmetro
        vrf.initVerify(publicKey);
        
        //adiciona o texto ao algoritmo
        vrf.update(mensagem);
        
        //faz a verificação com a assinatura do parâmetro
        return vrf.verify(assinatura);
        
    }
    

}
