/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.entity.Mensagem;
import com.controle.facade.AbstractFacade;
import com.controle.facade.MensagemFacade;
import com.google.common.collect.Lists;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author joao.rodrigo
 */
@Named
@ViewScoped
public class MensagemController extends AbstractController<Mensagem>{
    
    private String usuario;
    
    private String senha;
    
    private List<Mensagem> mensagens = Lists.newArrayList();
    
    @Inject
    MensagemFacade facade;
    
    public void buscarMensagensUsuario() throws Exception {
        this.mensagens = facade.buscarMensagemUsuario(this.usuario, this.senha);
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
    
    
}
