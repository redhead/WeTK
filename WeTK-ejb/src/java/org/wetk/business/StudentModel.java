/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.IStudent;
import org.wetk.dto.ClassDTO;
import org.wetk.dto.StudentDTO;
import org.wetk.model.ClassEntity;
import org.wetk.model.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class StudentModel extends AbstractModel implements IStudent {

	@Override
	public List<Student> getAllStudents() {
		return getEntityManager().createNamedQuery(Student.GET_ALL_STUDENTS).getResultList();
	}


	@Override
	public void save(StudentDTO dto, Long clazzId) {
		Student student = dtoToEntity(dto);

		ClassEntity clazz = getEntityManager().getReference(ClassEntity.class, clazzId);
		student.setClazz(clazz);

		saveEntity(student);
	}


	@Override
	public Student find(Long id) {
		return getEntityManager().find(Student.class, id);
	}


	@Override
	public void delete(Long id) {
		Student entity = find(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	private Student dtoToEntity(StudentDTO dto) {
		if(dto.getId() != null) {
			Student student = find(dto.getId());
			if(student != null) {
				return dto.toEntity(student);
			}
		}
		return dto.toEntity(new Student());
	}

}
