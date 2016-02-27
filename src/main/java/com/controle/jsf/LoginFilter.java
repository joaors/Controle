/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.jsf;

import com.controle.entity.Usuario;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain; 
import javax.servlet.FilterConfig; 
import javax.servlet.ServletException; 
import javax.servlet.ServletRequest; 
import javax.servlet.ServletResponse; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author joao
 */
public class LoginFilter implements Filter { 
    
    @Override
    public void destroy() { 
        // TODO Auto-generated method stub 
    } 
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException { 
        //Captura o ManagedBean chamado “usuarioMB” 
        LoginController usuarioMB = (LoginController) ((HttpServletRequest) request).getSession().getAttribute("loginController"); 
        Usuario usuario = (Usuario) ((HttpServletRequest) request) .getSession().getAttribute("usuario");
        //Verifica se nosso ManagedBean ainda não 
        //foi instanciado ou caso a 
        //variável loggedIn seja false, assim saberemos que 
        // o usuário não está logado 
        if (usuarioMB == null || !usuarioMB.isLogedIn()) { 
            String contextPath = ((HttpServletRequest) request) .getContextPath();
         

            //Redirecionamos o usuário imediatamente 
            //para a página de login.xhtml 
            ((HttpServletResponse) response).sendRedirect (contextPath + "/login/login.jsf"); 
        } else { 
            //Caso ele esteja logado, apenas deixamos 
            //que o fluxo continue 
            chain.doFilter(request, response); 
        } 
    } 
    
    public void init(FilterConfig arg0) throws ServletException { 
        // TODO Auto-generated method stub } 
    }

}


