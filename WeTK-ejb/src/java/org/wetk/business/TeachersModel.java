/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
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
		return getEntityManager().createNamedQuery(Teacher.GET_ALL_TEACHERS).getResultList();
	}


	@Override
	public void save(TeacherDTO teacher, String password) {
		Teacher t = dtoToEntity(teacher);
		if(password != null && !password.isEmpty()) {
			t.setPassword(password);
		}
		saveEntity(t);
	}


	@Override
	public Teacher find(Long id) {
		return getEntityManager().find(Teacher.class, id);
	}


	@Override
	public void delete(Long id) {
		Teacher entity = find(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
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
