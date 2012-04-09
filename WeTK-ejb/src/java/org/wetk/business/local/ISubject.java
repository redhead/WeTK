/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.SubjectDTO;
import org.wetk.model.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ISubject {

	public List<Subject> getAllSubjects();


	public void save(SubjectDTO subject);


	public Subject find(Long id);

}
