/*
 */
package org.wetk.entity;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = SubjectAssignment.GET_ALL_ASSIGNMENTS, query = "SELECT a FROM SubjectAssignment a ORDER BY a.subject.title, a.teacher.lastName")
})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "teacher", "subject" }), name = "unique_assignment")
public class SubjectAssignment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_ASSIGNMENTS = "SubjectAssignment.getAllAssignments";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "teacher")
	private Teacher teacher;

	@OneToOne
	@JoinColumn(name = "subject")
	private Subject subject;


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
