/*
 */
package org.wetk.business;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.wetk.dto.AbstractDTO;
import org.wetk.entity.AbstractEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
abstract public class AbstractModel<T extends AbstractEntity, TDTO extends AbstractDTO<T>> {

	@PersistenceContext
	private EntityManager em;


	protected EntityManager getEntityManager() {
		return em;
	}


	public T find(Long id) {
		return (T) em.find(getType(), id);
	}


	public T getReference(Object id) {
		return (T) getEntityManager().getReference(getType(), id);
	}


	public <TT extends AbstractEntity> TT getReference(Class<TT> type, Object id) {
		return (TT) getEntityManager().getReference(type, id);
	}


	public <TT extends AbstractEntity> TT find(Class<TT> type, Object id) {
		return (TT) getEntityManager().find(type, id);
	}


	public void saveEntity(T entity) {
		if(entity.getId() == null) {
			em.persist(entity);
		} else {
			em.merge(entity);
		}
		em.flush();
	}


	public void delete(Long id) {
		T entity = getReference(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	protected T dtoToEntity(TDTO dto) {
		if(dto.getId() != null) {
			T entity = getReference(dto.getId());
			if(entity != null) {
				return dto.toEntity(entity);
			}
		}
		return dto.toEntity(newEntity());
	}


	private T newEntity() {
		try {
			return getType().newInstance();
		} catch(Exception ex) {
			return null;
		}
	}


	protected Class<T> getType() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<T>) paramType.getActualTypeArguments()[0];
	}

}
