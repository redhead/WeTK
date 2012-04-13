/*
 */
package org.wetk.business;

import java.util.List;
import javax.persistence.Query;
import org.wetk.business.local.IGrade;
import org.wetk.dto.GradeDTO;
import org.wetk.dto.NoteDTO;
import org.wetk.entity.Grade;
import org.wetk.entity.Student;
import org.wetk.entity.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class GradeModel extends AbstractModel<Grade, GradeDTO> implements IGrade {

	@Override
	public List<NoteDTO> getAllFor(Long studentId) {
		Student student = getReference(Student.class, studentId);

		Query query = getEntityManager().createNamedQuery(Grade.GET_ALL_FOR_STUDENT);
		query.setParameter("student", student);

		return query.getResultList();

	}


	@Override
	public void create(GradeDTO dto, Long studentId, Long subjectId) {
		Grade grade = dtoToEntity(dto);

		Student student = find(Student.class, studentId);
		Subject subject = find(Subject.class, subjectId);

		grade.setSubject(subject);
		grade.setStudent(student);

		saveEntity(grade);
	}


	@Override
	public void edit(GradeDTO dto) {
		if(dto.getId() == null) return;

		Grade grade = dtoToEntity(dto);
		saveEntity(grade);
	}

}
