/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.StudentDTO;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IStudent extends IAbstractModel<Student> {

	public void save(StudentDTO dto, Long clazzId);

}
