/*
 */
package org.wetk.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = SubjectAssignment.GET_ALL_ASSIGNMENTS, query = "SELECT a FROM SubjectAssignment a ORDER BY a.subject.title, a.teacher.lastName")
})
public class SubjectAssignment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_ASSIGNMENTS = "SubjectAssignment.getAllAssignments";

	@Id
	private SubjectAssignmentComposite key = new SubjectAssignmentComposite();


	public Teacher getTeacher() {
		return key.getTeacher();
	}


	public void setTeacher(Teacher teacher) {
		key.setTeacher(teacher);
	}


	public Subject getSubject() {
		return key.getSubject();
	}


	public void setSubject(Subject subject) {
		key.setSubject(subject);
	}

}
