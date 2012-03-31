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
	protected EntityManager em;


	public void saveEntity(AbstractEntity entity) {
		if(entity.getId() == null) {
			System.out.println("PERSIST");
			em.persist(entity);
		} else {
			System.out.println("MERGE");
			System.out.println(entity);
			System.out.println(entity.getId());
			em.merge(entity);
		}
		System.out.println("FLUSH");
		em.flush();
	}

}
