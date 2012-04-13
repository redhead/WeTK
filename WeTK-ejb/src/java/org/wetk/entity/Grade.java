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
	@NamedQuery(name = Grade.GET_ALL_FOR_STUDENT, query = "SELECT g FROM Grade g WHERE g.student = :student")
})
public class Grade extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_FOR_STUDENT = "Grade.getAllForStudent";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double grade;

	private String comment;

	@ManyToOne(optional = false)
	private Subject subject;

	@ManyToOne(optional = false)
	private Student student;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public double getGrade() {
		return grade;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public void setGrade(double grade) {
		this.grade = grade;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

}
