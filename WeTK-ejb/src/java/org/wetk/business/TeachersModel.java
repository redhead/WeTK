/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.wetk.business.local.ITeacher;
import org.wetk.dto.TeacherDTO;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class TeachersModel extends AbstractModel implements ITeacher {

	@Override
	public List<Teacher> getAllTeachers() {
		TypedQuery<Teacher> query = (TypedQuery<Teacher>) em.createNamedQuery(Teacher.GET_ALL_TEACHERS);
		return query.getResultList();
	}


	@Override
	public void save(TeacherDTO teacher, String password) {
		System.out.println(teacher.getUsername());
		System.out.println(teacher.getFirstName());
		System.out.println(teacher.getLastName());
		Teacher t = dtoToEntity(teacher);
		if(password != null && !password.isEmpty()) {
			t.setPassword(password);
		}
		saveEntity(t);
	}


	@Override
	public Teacher find(Long id) {
		return em.find(Teacher.class, id);
	}


	private Teacher dtoToEntity(TeacherDTO dto) {
		if(dto.getId() != null) {
			Teacher teacher = find(dto.getId());
			if(teacher != null) {
				return dto.toEntity(teacher);
			}
		}
		return dto.toEntity(new Teacher());
	}

}
