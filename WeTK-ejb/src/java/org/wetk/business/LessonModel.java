/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.ILesson;
import org.wetk.dto.LessonDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Lesson;
import org.wetk.entity.SubjectAssignment;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonModel extends AbstractModel<Lesson, LessonDTO> implements ILesson {

	@Override
	public List<Lesson> getAll() {
		return getEntityManager().createNamedQuery(Lesson.GET_ALL_LESSONS).getResultList();
	}


	@Override
	public Lesson getLessonFor(Long classId, int day, int lessonHour) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);

		Query query = getEntityManager().createNamedQuery(Lesson.GET_FOR_CLASS_DAY_HOUR);
		query.setParameter("class", clazz);
		query.setParameter("day", day);
		query.setParameter("hour", lessonHour);

		try {
			return (Lesson) query.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}


	@Override
	public void save(LessonDTO dto, Long classId, Long assignmentId) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);
		SubjectAssignment assignment = getReference(SubjectAssignment.class, assignmentId);

		Lesson entity = dtoToEntity(dto);
		entity.setClazz(clazz);
		entity.setAssignment(assignment);

		saveEntity(entity);
	}


	@Override
	public Lesson findPrevTo(int day, int lessonHour, Teacher teacher) {
		Query query = getEntityManager().createQuery("SELECT l FROM Lesson l WHERE l.assignment.teacher = :teacher"
									+ " ORDER BY (l.day > :day OR (l.day = :day AND l.hour >= :hour)),"
									+ " l.day DESC, l.hour DESC");
		query.setParameter("teacher", teacher);
		query.setParameter("day", day);
		query.setParameter("hour", lessonHour);
		
		Lesson lesson = (Lesson) query.getSingleResult();
		return lesson;
	}


	@Override
	public Lesson findNextTo(int day, int lessonHour, Teacher teacher) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
