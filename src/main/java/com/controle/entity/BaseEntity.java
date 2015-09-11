/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.entity;

import java.io.Serializable;

/**
 *
 * @author joao
 */
public interface BaseEntity extends Serializable {
	
    void setId(Integer id);
	
    Integer getId();    
}
