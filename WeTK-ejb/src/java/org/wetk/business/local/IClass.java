/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.ClassDTO;
import org.wetk.entity.ClassEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IClass extends IAbstractModel<ClassEntity> {

	public void save(ClassDTO clazz, Long teacherId);

}
