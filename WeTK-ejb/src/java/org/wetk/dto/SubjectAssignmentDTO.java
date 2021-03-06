/*
 */
package org.wetk.dto;

import org.wetk.entity.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class SubjectAssignmentDTO extends AbstractDTO<SubjectAssignment> {

	private Long id;

	private TeacherDTO teacher;

	private SubjectDTO subject;


	public SubjectAssignmentDTO() {
	}


	public SubjectAssignmentDTO(SubjectAssignment assign) {
		if(assign == null) return;
		id = assign.getId();
		teacher = new TeacherDTO(assign.getTeacher());
		subject = new SubjectDTO(assign.getSubject());
	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public SubjectDTO getSubject() {
		return subject;
	}


	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}


	public TeacherDTO getTeacher() {
		return teacher;
	}


	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}


	@Override
	public SubjectAssignment toEntity(SubjectAssignment assign) {
		assign.setId(id);
		return assign;
	}

}
