/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.ITeacher;
import org.wetk.dto.TeacherDTO;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class TeachersModel extends AbstractModel<Teacher, TeacherDTO> implements ITeacher {

	@Override
	public List<Teacher> getAll() {
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
	public Teacher findByUsername(String username) {
		Query q = getEntityManager().createNamedQuery(Teacher.GET_BY_USERNAME);
		q.setParameter("username", username);
		try {
			return (Teacher) q.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}


	@Override
	public List<Teacher> getWithClass() {
		return getEntityManager().createNamedQuery(Teacher.GET_ALL_WITH_CLASS).getResultList();
	}

}
