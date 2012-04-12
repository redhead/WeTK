/*
 */
package org.wetk.business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.wetk.model.AbstractEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
abstract public class AbstractModel {

	@PersistenceContext
	private EntityManager em;


	protected EntityManager getEntityManager() {
		return em;
	}


	public void saveEntity(AbstractEntity entity) {
		if(entity.getId() == null) {
			em.persist(entity);
		} else {
			System.out.println(entity);
			System.out.println(entity.getId());
			em.merge(entity);
		}
		em.flush();
	}

}
