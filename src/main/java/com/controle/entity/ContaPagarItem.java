package com.controle.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class ContaPagarItem implements BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="SEQUENCIA")
    private Integer sequencia;
    
    @Column(name="VALORINICIAL")
    private BigDecimal valorInicial = BigDecimal.ZERO;
    
    @Column(name="DESCONTO")
    private BigDecimal desconto = BigDecimal.ZERO;
    
    @Column(name="VALORFINAL")
    private BigDecimal valorFinal;
    
    @Column(name="VALORPAGO")
    private BigDecimal valorPago;
    
    @Column(name="DATAITEM")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataItem;
    
    @Column(name="PAGO")
    private Boolean pago = Boolean.FALSE;
    
    @Column(name="DATAVENCIMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCONTAPAGAR")
    private ContaPagar contaPagar;

    public ContaPagar getContaPagar() {
        return contaPagar;
    }

    public void setContaPagar(ContaPagar contaPagar) {
        this.contaPagar = contaPagar;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Date getDataItem() {
        return dataItem;
    }

    public void setDataItem(Date dataItem) {
        this.dataItem = dataItem;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }
    
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ContaPagarItem other = (ContaPagarItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String getClassSituacao() {
          if (pago) {
            return "label label-success";
        } else {
            return "label label-warning";
        }      
    }
    
    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
    
    
    public String getStringPago() {
        if (pago) {
            return "Sim";
        } else {
            return "Não";
        }
    }
 /*   
    public void pagar() {        
        if (valorPago.compareTo(valorFinal) < 0) {
            desconto = desconto.add(valorFinal.subtract(valorPago));
            valorFinal = valorFinal.subtract(valorPago);            
        }
        pago = true;
    }
   */
    
    public void pagar() {        
        setPago(Boolean.TRUE);
    }
    
    public static ContaPagarItem createContaPagarItem() {
        ContaPagarItem ci = new ContaPagarItem();
        ci.setDataItem(new Date());
        ci.setDataVencimento(new Date());
        return ci;
    }
}
