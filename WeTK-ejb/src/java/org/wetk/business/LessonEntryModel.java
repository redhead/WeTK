/*
 */
package org.wetk.business;

import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.wetk.business.local.ILesson;
import org.wetk.business.local.ILessonEntry;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.entity.*;
import org.wetk.helper.Utils;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonEntryModel extends AbstractModel<LessonEntry, LessonEntryDTO> implements ILessonEntry {

	@EJB
	ILesson lessonModel;


	@Override
	public LessonEntry getLessonEntryFor(Long classId, Date date, int lessonHour) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);

		Query query = getEntityManager().createNamedQuery(LessonEntry.GET_BY_CLASS_DATE_HOUR);
		query.setParameter("class", clazz);
		query.setParameter("date", date);
		query.setParameter("lessonHour", lessonHour);

		try {
			return (LessonEntry) query.getSingleResult();
		} catch(NoResultException e) {
			int day = Utils.getDayOfWeek(date);
			return computeNewEntryFor(classId, day, lessonHour);
		}
	}


	private LessonEntry computeNewEntryFor(Long classId, int day, int lessonHour) {
		Lesson lesson = lessonModel.getLessonFor(classId, day, lessonHour);

		LessonEntry entry = new LessonEntry();

		if(lesson != null) {
			entry.setAssignment(lesson.getAssignment());
		}

		return entry;
	}


	@Override
	public void save(LessonEntryDTO dto, Long classId, Date date, int lessonHour, Long assignmentId) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);
		SubjectAssignment assign = getReference(SubjectAssignment.class, assignmentId);

		LessonEntry entry = dtoToEntity(dto);
		entry.setClazz(clazz);
		entry.setDate(date);
		entry.setLessonHour(lessonHour);
		entry.setAssignment(assign);

		saveEntity(entry);
	}


	@Override
	public LessonEntry findPreviousTo(Date date, int lessonHour, Teacher teacher) {
		return findPrevOrNext(true, date, lessonHour, teacher);
	}


	@Override
	public LessonEntry findNextTo(Date date, int lessonHour, Teacher teacher) {
		return findPrevOrNext(false, date, lessonHour, teacher);
	}


	private LessonEntry findPrevOrNext(boolean prev, Date date, int lessonHour, Teacher teacher) {
		String type = (prev ? LessonEntry.FIND_PREV_TO_DATE_HOUR : LessonEntry.FIND_NEXT_TO_DATE_HOUR);

		Query query = getEntityManager().createNamedQuery(type);
		query.setParameter("date", date);
		query.setParameter("lessonHour", lessonHour);
		query.setParameter("teacher", teacher);

		try {
			return (LessonEntry) query.getSingleResult();
		} catch(Exception e) {
			int day = Utils.getDayOfWeek(date);
			if(prev) {
				lessonModel.findPrevTo(day, lessonHour, teacher);
			} else {
				lessonModel.findNextTo(day, lessonHour, teacher);
			}
		}

		return null;

	}

}
