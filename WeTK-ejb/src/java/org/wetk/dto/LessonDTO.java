/*
 */
package org.wetk.dto;

import org.wetk.entity.Lesson;
import org.wetk.entity.SubjectAssignment;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class LessonDTO extends AbstractDTO<Lesson> {

	private Long id;

	private int day;

	private int hour;

	private Long classId;

	private Long assignmentId;

	private String classTitle;

	private String teacherFullName;

	private String subjectTitle;


	public LessonDTO() {
	}


	public LessonDTO(Lesson lesson) {
		if(lesson == null) return;

		id = lesson.getId();
		day = lesson.getDay();
		hour = lesson.getHour();

		if(lesson.getClazz() != null) {
			classId = lesson.getClazz().getId();
			classTitle = lesson.getClazz().getTitle();
		}

		SubjectAssignment assign = lesson.getAssignment();
		if(assign != null) {
			assignmentId = assign.getId();
			Teacher t = assign.getTeacher();
			if(t != null) {
				teacherFullName = new TeacherDTO(t).getFullName();
			}
			if(assign.getSubject() != null) {
				subjectTitle = assign.getSubject().getTitle();
			}
		}

	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public Long getAssignmentId() {
		return assignmentId;
	}


	public Long getClassId() {
		return classId;
	}


	public String getClassTitle() {
		return classTitle;
	}


	public String getSubjectTitle() {
		return subjectTitle;
	}


	public String getTeacherFullName() {
		return teacherFullName;
	}


	@Override
	public Lesson toEntity(Lesson lesson) {
		lesson.setId(id);
		lesson.setDay(day);
		lesson.setHour(hour);
		return lesson;
	}

}
