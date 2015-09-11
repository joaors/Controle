/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.dto;

/**
 *
 * @author joao
 */
public class EmissaoOrdemServico {
    
	private String filial;
	
	private int numero;
	
	public EmissaoOrdemServico(String filial, int numero) {
		this.filial = filial;
		this.numero = numero;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the filial
	 */
	public String getFilial() {
		return filial;
	}

	/**
	 * @param filial the filial to set
	 */
	public void setFilial(String filial) {
		this.filial = filial;
	}    
    
}
