/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.ISubjectAssignment;
import org.wetk.dto.SubjectAssignmentDTO;
import org.wetk.entity.Subject;
import org.wetk.entity.SubjectAssignment;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class SubjectAssignmentModel extends AbstractModel<SubjectAssignment, SubjectAssignmentDTO>
		implements ISubjectAssignment {

	@Override
	public List<SubjectAssignment> getAll() {
		return getEntityManager().createNamedQuery(SubjectAssignment.GET_ALL_ASSIGNMENTS).getResultList();
	}


	@Override
	public void save(Long teacherId, Long subjectId) {
		Teacher teacher = getEntityManager().find(Teacher.class, teacherId);
		Subject subject = getEntityManager().find(Subject.class, subjectId);

		SubjectAssignment assign = new SubjectAssignment();
		assign.setTeacher(teacher);
		assign.setSubject(subject);

		getEntityManager().persist(assign);
	}


	@Override
	public SubjectAssignment find(Long teacherId, Long subjectId) {
		Query q = getEntityManager().createNamedQuery(SubjectAssignment.GET_ALL_ASSIGNMENTS);
		q.setParameter("teacher", teacherId);
		q.setParameter("subject", teacherId);
		try {
			return (SubjectAssignment) q.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}

}
