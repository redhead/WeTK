/*
 */
package org.wetk.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.LessonEntry;
import org.wetk.entity.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class LessonEntryDTO extends AbstractDTO<LessonEntry> implements Serializable {

	private Long id;

	private String topic;

	private Date date;

	private int lessonHour;

	private Long classId;

	private String classTitle;

	private Long lessonId;

	private Long assignmentId;

	private String subjectTitle;

	private String teacherFullname;

	private Long teacherId;

	private List<Long> absentStudentIds;


	public LessonEntryDTO() {
	}


	public LessonEntryDTO(LessonEntry entry) {
		if(entry == null) return;

		id = entry.getId();
		lessonHour = entry.getLessonHour();
		topic = entry.getTopic();
		date = entry.getDate();

		ClassEntity clazz = entry.getClazz();
		if(clazz != null) {
			classId = clazz.getId();
			classTitle = clazz.getTitle();
		}

		SubjectAssignment assign = entry.getAssignment();
		if(assign != null) {
			assignmentId = assign.getId();
			if(assign.getSubject() != null) {
				subjectTitle = assign.getSubject().getTitle();
				teacherFullname = new TeacherDTO(assign.getTeacher()).getFullName();
				teacherId = assign.getTeacher().getId();
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


	public int getLessonHour() {
		return lessonHour;
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


	public String getSubjectTitle() {
		return subjectTitle;
	}


	public String getTeacherFullname() {
		return teacherFullname;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	@Override
	public LessonEntry toEntity(LessonEntry entity) {
		entity.setId(id);
		entity.setTopic(topic);
		return entity;
	}

}
