/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.ClassDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IClass extends IAbstractAdminModel<ClassEntity> {

	public void save(ClassDTO clazz, Long teacherId);


	public List<ClassEntity> getAllFor(Teacher teacher);


	public List<ClassEntity> getAllExcept(List<ClassEntity> classes);

}
