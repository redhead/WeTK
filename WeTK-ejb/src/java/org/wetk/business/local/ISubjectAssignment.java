/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.model.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ISubjectAssignment {

	public List<SubjectAssignment> getAllAssignments();


	public void save(Long teacherId, Long subjectId);


	public SubjectAssignment find(Long teacherId, Long subjectId);


	public void delete(Long id);

}
