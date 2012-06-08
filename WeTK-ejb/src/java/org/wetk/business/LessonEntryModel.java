/*
 */
package org.wetk.business;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.wetk.business.local.ILesson;
import org.wetk.business.local.ILessonEntry;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.dto.LessonEntryDTO.LessonEntryStudentDTO;
import org.wetk.entity.*;
import org.wetk.helper.Utils;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonEntryModel extends AbstractModel<LessonEntry, LessonEntryDTO> implements ILessonEntry {

	@EJB
	private ILesson lessonModel;


	@Override
	public LessonEntryDTO getLessonEntryFor(Long classId, Date date, int lessonHour) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);

		Query query = getEntityManager().createNamedQuery(LessonEntry.GET_BY_CLASS_DATE_HOUR);
		query.setParameter("class", clazz);
		query.setParameter("date", date);
		query.setParameter("lessonHour", lessonHour);

		try {
			return new LessonEntryDTO((LessonEntry) query.getSingleResult());
		} catch(NoResultException e) {
			LessonEntry entry = computeNewEntryFor(classId, date, lessonHour);
			return (entry == null ? null : new LessonEntryDTO(entry));
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
	public void save(LessonEntryDTO dto, Long classId, Date date, int lessonHour, Long assignmentId, Long signerId) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);
		SubjectAssignment assign = getReference(SubjectAssignment.class, assignmentId);

		LessonEntry entry = dtoToEntity(dto);
		entry.setClazz(clazz);
		entry.setDate(date);
		entry.setLessonHour(lessonHour);
		entry.setAssignment(assign);

		persistAbsences(entry, dto.getStudents());

		if(signerId != null && signerId != 0) {
			Teacher signer = getReference(Teacher.class, signerId);
			entry.setSigner(signer);
		}

		saveEntity(entry);
	}


	private void persistAbsences(LessonEntry entry, List<LessonEntryStudentDTO> students) {
		Set<Absence> absences = entry.getAbsences();
		if(absences == null) {
			absences = new HashSet<Absence>();
		}

		Map<Long, Absence> map = new HashMap<Long, Absence>();
		for(Absence a : absences) {
			map.put(a.getId(), a);
		}

		for(LessonEntryStudentDTO s : students) {
			if(!s.hasChanged()) continue;

			if(!s.isAbsent() && s.getAbsenceId() != null) {
				Absence a = map.get(s.getAbsenceId());
				if(a != null) {
					a.setLessonEntry(null);
					absences.remove(a);
				}
			} else if(s.isAbsent()) {
				if(s.getAbsenceId() != null) {
					Absence a = map.get(s.getAbsenceId());
					if(a != null) {
						a.setLate(s.isLate());
					}
				} else {
					Student student = getReference(Student.class, s.getStudentId());
					Absence a = new Absence(entry, s.isLate(), student);
					absences.add(a);
				}
			}
		}
		if(entry.getAbsences() == null) {
			entry.setAbsences(absences);
		}
	}


	@Override
	public LessonEntryDTO findPreviousTo(Date date, int lessonHour, Teacher teacher) {
		return new LessonEntryDTO(findPrevOrNext(true, date, lessonHour, teacher));
	}


	@Override
	public LessonEntryDTO findNextTo(Date date, int lessonHour, Teacher teacher) {
		return new LessonEntryDTO(findPrevOrNext(false, date, lessonHour, teacher));
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
			if(roll > 0 && prev) {
				roll = -7 + roll;
			}
			if(roll < 0 && !prev) {
				roll = 7 + roll;
			}
		}

		cal.add(Calendar.DATE, roll);

		LessonEntry entry = new LessonEntry();
		entry.setClazz(lesson.getClazz());
		entry.setDate(cal.getTime());
		entry.setLessonHour(lesson.getHour());
		entry.setAssignment(lesson.getAssignment());

		return entry;
	}


	@Override
	public long getEntryCount(Long classId, Date from, Date to) {
		ClassEntity clazz = getReference(ClassEntity.class, classId);

		Query query = getEntityManager().createNamedQuery(LessonEntry.COUNT_BETWEEN);

		query.setParameter("class", clazz);
		query.setParameter("from", from);
		query.setParameter("to", to);

		return (Long) query.getSingleResult();
	}

}
