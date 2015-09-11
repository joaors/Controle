package com.controle.facade;

import com.controle.entity.BaseEntity;
import com.controle.util.Filtro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

public abstract class AbstractFacade<T extends BaseEntity> {
   
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public BaseEntity find(Serializable id) {
        return (BaseEntity) getEntityManager().find(entityClass, id);
    }
    
    public AbstractFacade() {}

    protected abstract EntityManager getEntityManager();
        
    public List<T> load(int first, int count, String sortField, SortOrder sortOrder, Map<String, String> filters, List<Filtro> filtros) {
        javax.persistence.criteria.CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = builder.createQuery();
        Root root = cq.from(entityClass);
        cq.select(root);
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(builder.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(builder.desc(root.get(sortField)));
            }
        }

/*        
        if (filtros != null) {
            Set<Entry<String, Object>> entrySet = filtros.entrySet();
            ArrayList<Predicate> predicatesList = new ArrayList<>(entrySet.size());
            for (Entry<String, Object> entry : entrySet) {
		predicatesList.add(builder.equal(root.get(entry.getKey()), entry.getValue()));
            }
            cq.where(predicatesList.<Predicate>toArray(new Predicate[predicatesList.size()]));
        }
*/
        if (filtros != null) {
            ArrayList<Predicate> predicatesList = new ArrayList<>();
            for (Filtro filtro: filtros) {
		switch (filtro.getTipoFiltro()) {
		case Equals: predicatesList.add(builder.equal(root.get(filtro.getDescricao()), filtro.getValor())); break;
                case LessEqual: 
                    Path<Date> date = root.get(filtro.getDescricao());
                    predicatesList.add(builder.lessThanOrEqualTo(date, ((Date) filtro.getValor()))); break;
                case GreaterEqual:
                    Path<Date> date2 = root.get(filtro.getDescricao());
                    predicatesList.add(builder.greaterThanOrEqualTo(date2, ((Date) filtro.getValor()))); break;
                default:
                    predicatesList.add(builder.equal(root.get(filtro.getDescricao()), filtro.getValor())); break;
		}
            }
            cq.where(predicatesList.<Predicate>toArray(new Predicate[predicatesList.size()]));                    
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setFirstResult(first);
        q.setMaxResults(count);
        return q.getResultList();
    }

    public void create(BaseEntity entity) {
        getEntityManager().persist(entity);
    }

    public void edit(BaseEntity entity) {
        getEntityManager().merge(entity);
    }

    public void remove(BaseEntity entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public void persist(BaseEntity entity) {
        getEntityManager().persist(entity);
    }

    public BaseEntity find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<BaseEntity> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<BaseEntity> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);        
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(List<Filtro> filtros) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<BaseEntity> rt = cq.from(entityClass);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        
        if (filtros != null) {
            ArrayList<Predicate> predicatesList = new ArrayList<>();
            for (Filtro filtro: filtros) {
		switch (filtro.getTipoFiltro()) {
		case Equals: predicatesList.add(builder.equal(rt.get(filtro.getDescricao()), filtro.getValor())); break;
                case LessEqual: 
                    Path<Date> date = rt.get(filtro.getDescricao());
                    predicatesList.add(builder.lessThanOrEqualTo(date, ((Date) filtro.getValor()))); break;
                case GreaterEqual:
                    Path<Date> date2 = rt.get(filtro.getDescricao());
                    predicatesList.add(builder.greaterThanOrEqualTo(date2, ((Date) filtro.getValor()))); break;
                default:
                    predicatesList.add(builder.equal(rt.get(filtro.getDescricao()), filtro.getValor())); break;
		}
            }
            cq.where(predicatesList.<Predicate>toArray(new Predicate[predicatesList.size()]));                    
        }      
               
        cq.select(builder.count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<BaseEntity> getAutoComplete(String query, String queryAutoComplete, String paramAutoComplete, List<Filtro> filtros) {
        if (query.isEmpty()) {
            query = "%";
        } else {
            query = "%%" + query + "%%";
        }
        
        TypedQuery t =  getEntityManager().createNamedQuery(queryAutoComplete, entityClass).setParameter(paramAutoComplete, query);
        if (filtros != null) {
            ArrayList<Predicate> predicatesList = new ArrayList<>();
            for (Filtro filtro: filtros) {
		switch (filtro.getTipoFiltro()) {
                    case Equals: t.setParameter(filtro.getDescricao(), filtro.getValor()); break;
		default:
                    t.setParameter(filtro.getDescricao(), filtro.getValor()); break;
		}
            }            
        }         
                
        return t.getResultList();        
    }
    
}
