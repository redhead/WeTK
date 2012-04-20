/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.entity.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ISubjectAssignment extends IAbstractAdminModel<SubjectAssignment> {

	public void save(Long teacherId, Long subjectId);


	public SubjectAssignment find(Long teacherId, Long subjectId);


	public List<SubjectAssignment> getAllExcept(List<SubjectAssignment> except, Long classId);


	public List<SubjectAssignment> getAllFor(Long teacherId, Long classId);

}
