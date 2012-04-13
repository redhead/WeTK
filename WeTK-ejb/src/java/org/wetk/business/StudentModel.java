/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.IStudent;
import org.wetk.dto.StudentDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class StudentModel extends AbstractModel<Student, StudentDTO> implements IStudent {

	@Override
	public List<Student> getAll() {
		return getEntityManager().createNamedQuery(Student.GET_ALL_STUDENTS).getResultList();
	}


	@Override
	public void save(StudentDTO dto, Long classId) {
		Student student = dtoToEntity(dto);

		ClassEntity clazz = find(ClassEntity.class, classId);
		student.setClazz(clazz);

		saveEntity(student);
	}

}
