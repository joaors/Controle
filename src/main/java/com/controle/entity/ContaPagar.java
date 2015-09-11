/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class ContaPagar implements BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFORNECEDOR")
    private Fornecedor fornecedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFILIAL")
    private Filial filial;
    
    @Column(name = "DATACONTAPAGAR")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataContaPagar;
    
    @Column(name = "VALORTOTAL")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    
    @Column(name="NOTAFORNEC")
    private String notaFornecedor;
    
    @OneToMany(mappedBy = "contaPagar", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ContaPagarItem> contaPagarItem = new ArrayList<>();
    
    public void addValorTotal(BigDecimal valor) {
        this.valorTotal = this.valorTotal.add(valor);
    }
    
    public void subtractValorTotal(BigDecimal valor) {
        this.valorTotal = this.valorTotal.subtract(valor);
        if (valorTotal.compareTo(BigDecimal.ZERO) < 0) {
            valorTotal = BigDecimal.ZERO;
        }
    }    
    
    public void addItem(ContaPagarItem item) {
        if (contaPagarItem == null) {
            contaPagarItem = new ArrayList<>();
        }
        contaPagarItem.add(item);
        addValorTotal(item.getValorFinal());
    }
    
    public void removeItem(ContaPagarItem item) {
        if (contaPagarItem != null) {
            contaPagarItem.remove(item);
            subtractValorTotal(item.getValorFinal());
        }
    }    
    
    public ContaPagar() {
        this.contaPagarItem = new ArrayList<>();
    }
    
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    

    public List<ContaPagarItem> getContaPagarItem() {
        return contaPagarItem;
    }

    public void setContaPagarItem(List<ContaPagarItem> contaPagarItem) {
        this.contaPagarItem = contaPagarItem;
    }
    
    public Date getDataContaPagar() {
        return dataContaPagar;
    }

    public void setDataContaPagar(Date dataContaPagar) {
        this.dataContaPagar = dataContaPagar;
    }    

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getNotaFornecedor() {
        return notaFornecedor;
    }

    public void setNotaFornecedor(String notaFornecedor) {
        this.notaFornecedor = notaFornecedor;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContaPagar other = (ContaPagar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
