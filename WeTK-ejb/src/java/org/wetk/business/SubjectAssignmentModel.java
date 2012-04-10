/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import org.wetk.business.local.ISubjectAssignment;
import org.wetk.model.Subject;
import org.wetk.model.SubjectAssignment;
import org.wetk.model.SubjectAssignmentComposite;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class SubjectAssignmentModel extends AbstractModel implements ISubjectAssignment {

	@Override
	public List<SubjectAssignment> getAllAssignments() {
		return getEntityManager().createNamedQuery(SubjectAssignment.GET_ALL_ASSIGNMENTS).getResultList();
	}


	@Override
	public void save(Long teacherId, Long subjectId) {
		delete(teacherId, subjectId);

		SubjectAssignmentComposite key = getCompositeKey(teacherId, subjectId);

		SubjectAssignment assign = new SubjectAssignment();
		assign.setTeacher(key.getTeacher());
		assign.setSubject(key.getSubject());

		getEntityManager().persist(assign);
	}


	@Override
	public SubjectAssignment find(Long teacherId, Long subjectId) {
		SubjectAssignmentComposite key = getCompositeKey(teacherId, subjectId);
		return getEntityManager().find(SubjectAssignment.class, key);
	}


	@Override
	public void delete(Long teacherId, Long subjectId) {
		SubjectAssignment entity = find(teacherId, subjectId);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	private SubjectAssignmentComposite getCompositeKey(Long teacherId, Long subjectId) {
		EntityManager em = getEntityManager();

		Teacher t = em.getReference(Teacher.class, teacherId);
		Subject s = em.getReference(Subject.class, subjectId);

		return new SubjectAssignmentComposite(t, s);
	}

}
