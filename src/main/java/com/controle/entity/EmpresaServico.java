/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author joao
 */
@Entity
@NamedQueries({
    @NamedQuery(name = EmpresaServico.FIND_SERVICO_LIKE, query = "SELECT c FROM EmpresaServico c where c.empresa = :empresa and upper(c.servico.descricao) like upper(:descricao)")
})
public class EmpresaServico implements BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    public static final String FIND_SERVICO_LIKE = "EmpresaServico.findServicoLike";        
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @JoinColumn(name="IDEMPRESA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;
    
    @JoinColumn(name="IDSERVICO")
    @ManyToOne(fetch = FetchType.EAGER)    
    private Servico servico;
    
    private BigDecimal valorUnitario;
    
    public EmpresaServico(){}
    
    public EmpresaServico(Empresa empresa, Servico servico) {
        this.empresa = empresa;
        this.servico = servico;
        this.valorUnitario = servico.getValor();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaServico)) {
            return false;
        }
        EmpresaServico other = (EmpresaServico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return servico.getDescricao();
    }
    
}
