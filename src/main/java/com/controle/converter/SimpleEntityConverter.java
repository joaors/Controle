/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.converter;

import com.controle.entity.BaseEntity;
import java.util.Map;
import java.util.Objects;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author joao
 */
@FacesConverter(value = "baseEntityConverter")
public class SimpleEntityConverter implements Converter {  
  
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {  
        if (!Objects.isNull(value)) {  
            return getAttributesFrom(component).get(value);  
        }  
        return null;  
    }  
  
    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
  
        if (value != null && !"".equals(value)) {    
            BaseEntity entity = (BaseEntity) value;
            addAttribute(component, entity);
            Integer codigo = entity.getId();  
            if (codigo != null) {  
                return String.valueOf(codigo);  
            }  
        } 
        return (String) value;  
    }  
  
    protected void addAttribute(UIComponent component, BaseEntity o) {  
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);  
    }  
  
    protected Map<String, Object> getAttributesFrom(UIComponent component) {  
        return component.getAttributes();  
    }  
  
} 
