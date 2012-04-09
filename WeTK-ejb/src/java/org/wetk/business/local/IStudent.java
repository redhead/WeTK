/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.ClassDTO;
import org.wetk.dto.StudentDTO;
import org.wetk.model.ClassEntity;
import org.wetk.model.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IStudent {

	public List<Student> getAllStudents();


	public void save(StudentDTO dto, ClassDTO clazz);


	public Student find(Long id);

}