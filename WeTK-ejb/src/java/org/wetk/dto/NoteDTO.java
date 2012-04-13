/*
 */
package org.wetk.dto;

import org.wetk.entity.LessonEntry;
import org.wetk.entity.Note;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class NoteDTO extends AbstractDTO<Note> {

	private Long id;

	private String description;

	private Long studentId;

	private String studentFullName;

	private Long lessonEntryId;

	private boolean solved;


	public NoteDTO() {
	}


	public NoteDTO(Note note) {
		if(note == null) return;

		id = note.getId();
		description = note.getDescription();
		solved = note.isSolved();
		
		Student student = note.getStudent();
		if(student != null) {
			studentId = student.getId();
			studentFullName = new StudentDTO(student).getFullName();
		}
		
		LessonEntry entry = note.getLessonEntry();
		if(entry != null) {
			lessonEntryId = entry.getId();
		}
	}


	@Override
	public Long getId() {
		return id;
	}


	public Long setId(Long id) {
		return id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getLessonEntryId() {
		return lessonEntryId;
	}


	public String getStudentFullName() {
		return studentFullName;
	}


	public Long getStudentId() {
		return studentId;
	}


	public boolean isSolved() {
		return solved;
	}


	public void setSolved(boolean solved) {
		this.solved = solved;
	}


	@Override
	public Note toEntity(Note entity) {
		entity.setId(id);
		entity.setDescription(description);
		entity.setSolved(solved);
		return entity;
	}

}
