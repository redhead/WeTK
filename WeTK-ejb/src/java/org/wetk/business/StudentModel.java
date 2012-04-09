/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.IStudent;
import org.wetk.dto.ClassDTO;
import org.wetk.dto.StudentDTO;
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
	public void save(StudentDTO dto, ClassDTO clazz) {
		Student student = dtoToEntity(dto);
		//fixme: clazz.setClazz();
		saveEntity(student);
	}


	@Override
	public Student find(Long id) {
		return getEntityManager().find(Student.class, id);
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
