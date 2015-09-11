package com.controle.model;

import com.controle.facade.AbstractFacade;
import com.controle.util.Filtro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class LazyDataModelBase<BaseEntity> extends LazyDataModel<BaseEntity> implements Serializable{
    
    public LazyDataModelBase() {
        this.filtros = new ArrayList<>();
    }

    protected abstract AbstractFacade getFacade();
 
    
    private List<Filtro> filtros;

    @Override
    public List<BaseEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<BaseEntity> list = getFacade().load(first, pageSize, sortField, sortOrder, filters, filtros);

        if (list == null) {
            setRowCount(0);
        } else {
            setRowCount(getFacade().count(filtros));
        }
        return list;
    }
    
    public List<Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Filtro> filtros) {
        this.filtros = filtros;
    }
    
    public void addFiltro(Filtro filtro) {
        if (filtros == null) {
            filtros = new ArrayList<>();
        }
        filtros.add(filtro);
    }
    
    
    public void limpaFiltros() {
        filtros = new ArrayList<>();
    }   
    
}