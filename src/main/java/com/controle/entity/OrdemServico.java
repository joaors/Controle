/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author joao
 */
@Entity
public class OrdemServico implements BaseEntity {
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name="IDEMPRESA")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    
    private Integer numero;
    
    @Temporal(TemporalType.DATE)
    private Date inicio;
    
    @Temporal(TemporalType.DATE)
    private Date fim;
    
    @JoinColumn(name="IDEMPRESASERVICO")
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpresaServico empresaServico;
    
    @JoinColumn(name="IDFILIAL")
    @ManyToOne(fetch = FetchType.LAZY)
    private Filial filial;
    
    private Integer quantidade;
    
    private BigDecimal valorUnitario;
    
    private BigDecimal valortotal;
    
    private String lote;
    
    private Boolean pago = Boolean.FALSE;
    
    public OrdemServico() {
        inicio = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(inicio);
        c.set(Calendar.DAY_OF_MONTH, 1);
        inicio = c.getTime();        
        fim = new Date();
        quantidade = 0;
        valorUnitario = BigDecimal.ZERO;
        valortotal = BigDecimal.ZERO;
    }
    
    public void calculaValorTotal() {
        valortotal = valorUnitario.multiply(new BigDecimal(quantidade));
    }
    
    public String getStringPago() {
        if (pago) {
            return "Sim";
        } else {
            return "Não";
        }
    }
    
    public String getClassSituacao() {
          if (pago) {
            return "label label-success";
        } else {
            return "label label-warning";
        }      
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.valortotal = BigDecimal.ZERO;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public EmpresaServico getEmpresaServico() {
        return empresaServico;
    }

    public void setEmpresaServico(EmpresaServico empresaServico) {
        this.empresaServico = empresaServico;
    }    
    
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.valortotal = valorUnitario.multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        this.valortotal = valorUnitario.multiply(new BigDecimal(quantidade));
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }
    
    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }    
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof OrdemServico)) {
            return false;
        }
        OrdemServico other = (OrdemServico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.controle.entity.OrdemServico[ id=" + id + " ]";
    }
    
}
