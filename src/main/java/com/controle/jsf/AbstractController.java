/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controle.jsf;

import com.controle.entity.BaseEntity;
import com.controle.jsf.util.JsfUtil;
import com.controle.model.LazyDataModelBase;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 * @param <T>
 */
public abstract class AbstractController<T extends BaseEntity> extends LazyDataModelBase<T> {

    private Class<T> itemClass;
    private T selected;

    public AbstractController() {}

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    public void salvar() {

        try {
            if (selected.getId() == null) {
                getFacade().persist(selected);
            } else {
                getFacade().edit(selected);
            }
            selected = null;
            JsfUtil.addSuccessMessage("registroSalvo");
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }

    }

    public void delete(T entity) {
        try {
            getFacade().remove(entity);
            JsfUtil.addSuccessMessage("registroExcluido");
            if (!JsfUtil.isValidationFailed()) {
                selected = null;
            }            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("problemasAoExcluir");
        }
    }    
    
    public void voltar() {
        selected = null;
    }    

    public void prepareCreate() {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            selected = (T) newItem;
        } catch (IllegalAccessException | InstantiationException  ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }
    
    @Override
    public T getRowData(String rowKey) {
        return (T) getFacade().find(Integer.parseInt(rowKey));
    }

    @Override
    public Object getRowKey(T object) {
       return object.getId().toString();
    }     
}
