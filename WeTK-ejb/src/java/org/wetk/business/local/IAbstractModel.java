/*
 */
package org.wetk.business.local;

import org.wetk.entity.AbstractEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public interface IAbstractModel<T extends AbstractEntity> {

	public T find(Long id);


	public void delete(Long id);

}
