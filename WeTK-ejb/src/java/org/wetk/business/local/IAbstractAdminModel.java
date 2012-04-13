/*
 */
package org.wetk.business.local;

import java.util.List;
import org.wetk.entity.AbstractEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public interface IAbstractAdminModel<T extends AbstractEntity> extends IAbstractModel<T> {

	public List<T> getAll();

}
