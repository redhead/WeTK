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
	@NamedQuery(name = SubjectAssignment.GET_ALL_ASSIGNMENTS,
	query = "SELECT a FROM SubjectAssignment a ORDER BY a.subject.title, a.teacher.lastName"),
	@NamedQuery(name = SubjectAssignment.GET_ALL_FOR_TEACHER_CLASS,
	query = "SELECT DISTINCT a FROM SubjectAssignment a, Lesson l WHERE a.teacher = :teacher"
	+ " AND l.clazz = :class AND l.assignment.teacher = :teacher AND a = l.assignment"
	+ " ORDER BY a.subject.title, a.teacher.lastName"),
	@NamedQuery(name = SubjectAssignment.GET_ALL_FOR_CLASS_EXCEPT,
	query = "SELECT a FROM SubjectAssignment a, Lesson l"
		+ " WHERE l.clazz = :class AND a = l.assignment "
		+ " AND a NOT IN (:assignments)"
		+ " ORDER BY a.subject.title, a.teacher.lastName")
})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "teacher", "subject" }), name = "unique_assignment")
public class SubjectAssignment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_ASSIGNMENTS = "SubjectAssignment.getAllAssignments";

	public static final String GET_ALL_FOR_TEACHER_CLASS = "SubjectAssignment.getAllForTeacherClass";

	public static final String GET_ALL_FOR_CLASS_EXCEPT = "SubjectAssignment.getAllForClassExcept";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "teacher")
	private Teacher teacher;

	@ManyToOne
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
