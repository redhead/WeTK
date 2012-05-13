/*
 */
package org.wetk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.wetk.entity.*;


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

	private Long assignmentId;

	private String subjectTitle;

	private Long teacherId;

	private String teacherFullname;

	private Long signerId;

	private String signer;

	private List<LessonEntryStudentDTO> students = new ArrayList<LessonEntryStudentDTO>();


	public LessonEntryDTO() {
	}


	public LessonEntryDTO(LessonEntry entry) {
		if(entry == null) return;

		id = entry.getId();
		lessonHour = entry.getLessonHour();
		topic = entry.getTopic();
		date = entry.getDate();
		signer = new TeacherDTO(entry.getSigner()).getFullName();

		if(entry.getSigner() != null) {
			signerId = entry.getSigner().getId();
		}

		ClassEntity clazz = entry.getClazz();
		if(clazz != null) {
			classId = clazz.getId();
			classTitle = clazz.getTitle();

			List<Absence> absences = new ArrayList<Absence>();
			if(entry.getAbsences() != null) {
				absences = entry.getAbsences();
			}

			for(Student s : clazz.getStudents()) {
				Absence studentAbsence = null;
				for(Absence a : absences) {
					if(a.getStudent().equals(s)) {
						studentAbsence = a;
						break;
					}
				}
				students.add(new LessonEntryStudentDTO(s, studentAbsence));
			}
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


	public List<LessonEntryStudentDTO> getStudents() {
		return students;
	}


	public Long getClassId() {
		return classId;
	}


	public String getClassTitle() {
		return classTitle;
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


	public Long getSignerId() {
		return signerId;
	}


	public String getSigner() {
		return signer;
	}


	public boolean isSubstituted() {
		return (signerId != teacherId);
	}


	@Override
	public LessonEntry toEntity(LessonEntry entity) {
		entity.setId(id);
		entity.setTopic(topic);
		return entity;
	}


	public static class LessonEntryStudentDTO {

		private StudentDTO sDTO;

		private boolean absent;

		private boolean late;

		private boolean excused;


		private LessonEntryStudentDTO(Student student, Absence absence) {
			sDTO = new StudentDTO(student);
			if(absence != null) {
				absent = true;
				late = absence.isLate();
				excused = (absence.getExuse() != null);
			}
		}


		public boolean isAbsent() {
			return absent;
		}


		public int getOrdinal() {
			return sDTO.getOrdinal();
		}


		public String getFullName() {
			return sDTO.getFullName();
		}


		public boolean isExcused() {
			return excused;
		}


		public boolean isLate() {
			return late;
		}

	}

}
