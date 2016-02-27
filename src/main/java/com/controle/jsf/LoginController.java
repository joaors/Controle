/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.entity.ContaPagarItem;
import com.controle.entity.Usuario;
import com.controle.service.UsuarioService;
import com.controle.jsf.util.JsfUtil;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable{
    
    public LoginController() {}
    
    @Inject
    private UsuarioService service;
    
    private boolean logedIn;
    
    private Usuario usuarioLogado;
    
    private String login;
    
    private String senha;
    
    public String doLogin() {
        try {
            Usuario usuarioFound = service.isUsuarioReadyToLogin(login, senha);
            if (usuarioFound == null) {                
                FacesContext.getCurrentInstance().validationFailed();
                JsfUtil.addErrorMessage("loginInvalido");
                senha = null;
                return null;
            } else {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession session = request.getSession(true);  
                session.setAttribute("usuario", usuarioFound);
                logedIn = true;
                usuarioLogado = usuarioFound;
                return "/restricted/principal.jsf?faces-redirect=true";
            }
        } catch (Exception ex) {
            return "/login/login.jsf?faces-redirect=true";
        }
     }
    
  
     
    public String doLogout() {
        usuarioLogado = null;
        senha = null;
        logedIn = false;        
        JsfUtil.addSuccessMessage("logoutEfetuado");
        return "/login/login.jsf?faces-redirect=true";
    }
    
    public Boolean getIsAdmin() {
        return login.equals("admin");
    }

    public boolean isLogedIn() {
        return logedIn;
    }

    public void setLogedIn(boolean logedIn) {
        this.logedIn = logedIn;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
