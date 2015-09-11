/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author joao
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Filial.FIND_FILIAL_LIKE, query = "SELECT f FROM Filial f where upper(f.descricao) like upper(:descricao)")
})
@DiscriminatorValue("Filial")
public class Filial extends Empresa {

    public static final String FIND_FILIAL_LIKE = "Filial.findFilialLike";
    
    private Integer osinicio;

    private Integer osfim; 
    
    private String nomeFantasia;
    
    private String cnpj;
    
    private String inscEstadual;

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
    
    
    public Integer getOsinicio() {
        return osinicio;
    }

    public void setOsinicio(Integer osinicio) {
        this.osinicio = osinicio;
    }

    public Integer getOsfim() {
        return osfim;
    }

    public void setOsfim(Integer osfim) {
        this.osfim = osfim;
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
        if (!(object instanceof Filial)) {
            return false;
        }
        Filial other = (Filial) object;
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
