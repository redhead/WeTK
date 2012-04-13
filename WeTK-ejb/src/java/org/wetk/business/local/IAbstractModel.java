/*
 */
package org.wetk.business.local;

import java.util.List;
import org.wetk.entity.AbstractEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public interface IAbstractModel<T extends AbstractEntity> {

	public List<T> getAll();


	public T find(Long id);


	public void delete(Long id);

}
