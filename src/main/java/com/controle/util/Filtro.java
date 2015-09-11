/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.util;

import com.controle.produces.ListProduces;
import java.io.Serializable;

/**
 *
 * @author joao
 */
public class Filtro implements Serializable {
    
    private String descricao;
    
    private Object valor;
    
    private ListProduces.TipoFiltro tipoFiltro;
    
    public Filtro (String descricao, Object valor, ListProduces.TipoFiltro tipoFiltro) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipoFiltro = tipoFiltro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    public ListProduces.TipoFiltro getTipoFiltro() {
        return tipoFiltro;
    }
    
    public void setTipoFiltro(ListProduces.TipoFiltro tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }
    
}
