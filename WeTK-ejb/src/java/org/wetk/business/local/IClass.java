/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.ClassDTO;
import org.wetk.dto.TeacherDTO;
import org.wetk.model.ClassEntity;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IClass {

	public List<ClassEntity> getAllClasses();


	public void save(ClassDTO clazz, TeacherDTO headTeacher);


	public ClassEntity find(Long id);


	public void delete(Long id);

}
