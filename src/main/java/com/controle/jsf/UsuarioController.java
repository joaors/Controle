/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.entity.ContaPagarItem;
import com.controle.entity.Mensagem;
import com.controle.entity.Usuario;
import com.controle.facade.AbstractFacade;
import com.controle.facade.ServicoFacade;
import com.controle.facade.UsuarioFacade;
import com.controle.jsf.util.JsfUtil;
import com.controle.service.UsuarioService;
import com.controle.util.Seguranca;
import com.google.common.base.Strings;
import java.io.Serializable;
import java.security.KeyPair;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author joaorodrigo
 */
@Named
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable{
    
    @Inject
    private UsuarioFacade servicoFacade;
    
    @Inject
    private UsuarioService service;
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    public void exibirAnotacao() throws Exception {
        /*
        if (!Strings.isNullOrEmpty(getSelected().getPasswordDecrypted())) {
            byte[] senha = service.convertStringToMd5Char(getSelected().getPasswordDecrypted());
            getSelected().setAnotacaoDecrypted(Seguranca.Decrypt(senha, getSelected().getAnotacao().getBytes()));
        } else {
            JsfUtil.addErrorMessage("informarSenhaAnotacao");
        }
        */
    }
    
    public void showDialogMensagem() {
        this.mensagem = "";
        JsfUtil.openDialog("dlgEnviarMensagem");
    }
    
    public void enviarMensagem() {
        try {
            service.enviarMensagem(mensagem, getSelected());
            JsfUtil.closeDialog("dlgEnviarMensagem");
            JsfUtil.addSuccessMessageNoI18N("Mensagem enviada com sucesso");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            e.printStackTrace();
        }
    }    
    
    @Override
    public void salvar() {
        try {
            if (!Strings.isNullOrEmpty(getSelected().getPasswordDecrypted())) {
                byte[] senha = service.convertStringToMd5Char(getSelected().getPasswordDecrypted());
                getSelected().setPassword(senha);
                
                if (!Strings.isNullOrEmpty(getSelected().getAnotacaoDecrypted())) {
                    String anotacao = Seguranca.Encrypt(getSelected().getAnotacaoDecrypted(), senha);
                    getSelected().setAnotacao(anotacao);
                }
                
                if (Objects.isNull(getSelected().getPublicKey()) 
                        || Objects.isNull(getSelected().getPrivateKey())) {
                    KeyPair keyPair =  Seguranca.generateKeyPairRSA();
                    getSelected().setPublicKey(keyPair.getPublic().getEncoded());
                    getSelected().setPrivateKey(keyPair.getPrivate().getEncoded());
                }
                
                super.salvar();                
            } else {
                JsfUtil.addErrorMessageNoI18N("Para Salvar informe a Senha");
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessageNoI18N("Erro inesperado ao salvar usuario");
        }
    }    

    public UsuarioController() {
        super(Usuario.class);
    }

    @Override
    protected AbstractFacade getFacade() {
        return servicoFacade;
    }    
       
}
