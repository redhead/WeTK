/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.TeacherDTO;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ITeacher {

	public List<Teacher> getAllTeachers();


	public void save(TeacherDTO teacher, String password);


	public Teacher find(Long id);

}
