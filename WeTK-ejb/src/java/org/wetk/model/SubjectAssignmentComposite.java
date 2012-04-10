/*
 */
package org.wetk.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Embeddable
public class SubjectAssignmentComposite implements Serializable {

	@ManyToOne
	@JoinColumn(name = "teacher")
	private Teacher teacher;

	@OneToOne
	@JoinColumn(name = "subject")
	private Subject subject;


	public SubjectAssignmentComposite() {
	}


	public SubjectAssignmentComposite(Teacher teacher, Subject subject) {
		this.teacher = teacher;
		this.subject = subject;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}