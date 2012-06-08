/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.wetk.business.local.ILesson;
import org.wetk.dto.LessonDTO;
import org.wetk.dto.TimeTable;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Lesson;
import org.wetk.entity.Lesson_;
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

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Lesson> query = builder.createQuery(Lesson.class);
		Root<Lesson> root = query.from(Lesson.class);
		
		query.where(builder.and(builder.equal(root.get(Lesson_.clazz), clazz.getId()),
				builder.equal(root.get(Lesson_.day), new Integer(day)),
				builder.equal(root.get(Lesson_.hour), new Integer(lessonHour))));

		try {
			return getEntityManager().createQuery(query).getSingleResult();
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
		return findPrevOrNextTo(day, lessonHour, teacher, true);
	}


	@Override
	public Lesson findNextTo(int day, int lessonHour, Teacher teacher) {
		return findPrevOrNextTo(day, lessonHour, teacher, false);
	}


	private Lesson findPrevOrNextTo(int day, int lessonHour, Teacher teacher, boolean prev) {
		String type = prev ? Lesson.FIND_PREV_TO : Lesson.FIND_NEXT_TO;

		Query query = getEntityManager().createNamedQuery(type);

		query.setParameter("teacher", teacher);
		query.setParameter("day", day);
		query.setParameter("hour", lessonHour);

		query.setMaxResults(1);

		Lesson lesson = (Lesson) query.getSingleResult();
		return lesson;
	}


	@Override
	public long getLessonCountFor(Long classId) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);

		Query query = getEntityManager().createNamedQuery(Lesson.COUNT_FOR_CLASS);
		query.setParameter("class", clazz);

		return (Long) query.getSingleResult();
	}


	@Override
	public TimeTable getTimeTableFor(Long teacherId) {
		Teacher teacher = getReference(Teacher.class, teacherId);

		Query query = getEntityManager().createNamedQuery(Lesson.GET_FOR_TEACHER);
		query.setParameter("teacher", teacher);

		return new TimeTable((List<Lesson>) query.getResultList());
	}

}
