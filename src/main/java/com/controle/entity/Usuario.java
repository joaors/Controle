/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.entity;

import java.util.Date; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries; 
import javax.persistence.NamedQuery; 
import javax.persistence.Table; 
import javax.persistence.Temporal; 
import javax.persistence.TemporalType; 

@Entity 
@NamedQueries(value = { @NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT c FROM Usuario c " + "WHERE c.login = :login AND c.senha = :senha")}) 
@Table(name = "usuario") 
public class Usuario implements BaseEntity{
    
    /** * */ 
    private static final long serialVersionUID = 1L; 
    
    public static final String FIND_BY_LOGIN_SENHA = "Usuario.findByLoginSenha"; 
    
    @Id 
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) 
    private Integer id; 
    
    @Column 
    private String nome; 
    
    @Column(unique = true) 
    private String login; 
    
    @Column private String senha; 
    
    @Column(name = "dataCadastro") 
    @Temporal(TemporalType.DATE) 
    private Date dataCadastro; 

    public static Usuario criaUsuarioLogin(String login) {
        Usuario u = new Usuario();
        u.setLogin(login);
        return u;
    }
    
    @Override
    public Integer getId() { 
        return id; 
    } 
    
    @Override
    public void setId(Integer id) { 
        this.id = id; 
    } 
    
    public String getNome() { 
        return nome; 
    } 
    
    public void setNome(String nome) { 
        this.nome = nome.trim(); 
    } 

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() { 
        return senha; 
    } 
    
    public void setSenha(String senha) { 
        this.senha = senha.trim(); 
    } 
    
    public Date getDataCadastro() { 
        return dataCadastro; 
    } 
    
    public void setDataCadastro(Date dataCadastro) { 
        this.dataCadastro = dataCadastro; 
    } 
    
    @Override 
    public int hashCode() { 
        final int prime = 31; 
        int result = 1; 
        result = prime * result + ((id == null) ? 0 : id.hashCode()); 
        return result; 
    } 
    
    @Override 
    public boolean equals(Object obj) { 
        if (this == obj) 
            return true; 
        if (obj == null) 
            return false; 
        if (getClass() != obj.getClass()) 
            return false; return (obj instanceof BaseEntity) ? (this.getId() == null ? this == obj : this.getId().equals(( (BaseEntity)obj).getId())):false; 
    } 

}

