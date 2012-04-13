/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.TeacherDTO;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ITeacher extends IAbstractAdminModel<Teacher> {

	public void save(TeacherDTO teacher, String password);


	public Teacher findByUsername(String username);

}
