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
			return computeNewEntryFor(classId, date, lessonHour);
		}
	}


	private LessonEntry computeNewEntryFor(Long classId, Date date, int lessonHour) {
		int day = Utils.getDayOfWeek(date);
		Lesson lesson = lessonModel.getLessonFor(classId, day, lessonHour);

		LessonEntry entry = new LessonEntry();

		if(lesson != null) {
			entry.setAssignment(lesson.getAssignment());
			entry.setDate(date);
			entry.setLessonHour(lessonHour);
			entry.setClazz(getReference(ClassEntity.class, classId));
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
		
		query.setMaxResults(1);

		int day = Utils.getDayOfWeek(date);
		Lesson lesson;
		if(prev) {
			lesson = lessonModel.findPrevTo(day, lessonHour, teacher);
		} else {
			lesson = lessonModel.findNextTo(day, lessonHour, teacher);
		}
		
		LessonEntry newEntry = null;
		if(lesson != null) {
			 newEntry = createEntryFrom(lesson, date, lessonHour, prev);
		}

		try {
			LessonEntry entry = (LessonEntry) query.getSingleResult();
			if(prev && (entry.getDate().before(newEntry.getDate()) || entry.getLessonHour() < newEntry.getLessonHour())) {
				return newEntry;
			}
			if(!prev && (entry.getDate().after(newEntry.getDate()) || entry.getLessonHour() > newEntry.getLessonHour())) {
				return newEntry;
			}
			return entry;
		} catch(Exception e) {
			return newEntry;
		}

	}


	private LessonEntry createEntryFrom(Lesson lesson, Date from_date, int from_hour, boolean prev) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(from_date);

		int from_day = cal.get(Calendar.DAY_OF_WEEK);

		int roll = 0;
		if(from_day == lesson.getDay()) {
			if(prev && lesson.getHour() > from_hour) {
				roll = -7;
			}
			if(!prev && lesson.getHour() < from_hour) {
				roll = 7;
			}
		} else {
			roll = lesson.getDay() - from_day;
		}

		cal.add(Calendar.DAY_OF_WEEK, roll);

		LessonEntry entry = new LessonEntry();
		entry.setClazz(lesson.getClazz());
		entry.setDate(cal.getTime());
		entry.setLessonHour(lesson.getHour());
		entry.setAssignment(lesson.getAssignment());

		return entry;
	}

}
