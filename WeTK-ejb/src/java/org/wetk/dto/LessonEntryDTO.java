/*
 */
package org.wetk.dto;

import java.util.Date;
import java.util.List;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Lesson;
import org.wetk.entity.LessonEntry;
import org.wetk.entity.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class LessonEntryDTO extends AbstractDTO<LessonEntry> {

	private Long id;

	private int lessonNumber;

	private String topic;

	private Date date;

	private int lessonHour;

	private Long classId;

	private String classTitle;

	private Long lessonId;

	private Long assignmentId;

	private List<Long> absentStudentIds;


	public LessonEntryDTO(LessonEntry entry) {
		if(entry != null) return;

		id = entry.getId();
		lessonNumber = entry.getLessonNumber();
		lessonHour = entry.getLessonHour();
		topic = entry.getTopic();
		date = entry.getDate();

		Lesson lesson = entry.getLesson();
		if(lesson != null) {
			lessonId = lesson.getId();

			ClassEntity clazz = lesson.getClazz();
			if(clazz != null) {
				classId = clazz.getId();
				classTitle = clazz.getTitle();
			}

			SubjectAssignment assign = lesson.getAssignment();
			if(assign != null) {
				assignmentId = assign.getId();
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getLessonHour() {
		return lessonHour;
	}


	public void setLessonHour(int lessonHour) {
		this.lessonHour = lessonHour;
	}


	public int getLessonNumber() {
		return lessonNumber;
	}


	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public List<Long> getAbsentStudentIds() {
		return absentStudentIds;
	}


	public Long getClassId() {
		return classId;
	}


	public String getClassTitle() {
		return classTitle;
	}


	public Long getLessonId() {
		return lessonId;
	}


	public Long getAssignmentId() {
		return assignmentId;
	}


	@Override
	public LessonEntry toEntity(LessonEntry entity) {
		entity.setId(id);
		entity.setLessonNumber(lessonNumber);
		entity.setLessonHour(lessonHour);
		entity.setDate(date);
		entity.setTopic(topic);
		return entity;
	}

}
