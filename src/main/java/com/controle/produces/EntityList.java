/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controle.produces;

import com.controle.entity.BaseEntity;
import com.controle.facade.AbstractFacade;
import com.controle.model.LazyDataModelBase;
import java.util.List;

/**
 *
 * @author joao
 */
public class EntityList extends LazyDataModelBase<BaseEntity> {

    AbstractFacade facade;
    
    String paramAutoComplete;
    
    String queryAutoComplete;
    

    public EntityList(AbstractFacade filialFacade, String queryAutoComplete, String paramAutoComplete) {
        this.facade = filialFacade;
        this.queryAutoComplete = queryAutoComplete;
        this.paramAutoComplete = paramAutoComplete;
    }
    
    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }
    
    public List<BaseEntity> getCompleteQuery(String query) {
        return getFacade().getAutoComplete(query, queryAutoComplete, paramAutoComplete, getFiltros());
    }
    
    @Override
    public BaseEntity getRowData(String rowKey) {
        return (BaseEntity) getFacade().find(Integer.parseInt(rowKey));
    }

    @Override
    public Object getRowKey(BaseEntity object) {
       return object.getId().toString();
    }    
    
    
}
