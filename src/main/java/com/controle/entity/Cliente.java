/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author joao
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Cliente.FIND_CLIENTE_LIKE, query = "SELECT c FROM Cliente c where upper(c.descricao) like upper(:descricao)")
})
@DiscriminatorValue("Cliente")
public class Cliente extends Empresa {
    
    public static final String FIND_CLIENTE_LIKE = "Cliente.findClienteLike";    
    
    private BigDecimal saldo = BigDecimal.ZERO;
    
    private String nomeFantasia;
    
    private String cnpj;
    
    private String inscEstadual;
    
    public Cliente(){}
    
    public Cliente(Integer id) {
        this.setId(id);
    }
    
    @OneToMany(mappedBy="empresa",fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<EmpresaServico> servicos;

    public List<EmpresaServico> getServicos() {
        return servicos;
    }

    public void setServicos(List<EmpresaServico> servicos) {
        this.servicos = servicos;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
        
    public Cliente(Integer id, String descricao) {
        setId(id);
        setDescricao(descricao);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.getDescricao();
    }       
    
}
