/*
 */
package org.wetk.model;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
public class Note extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;

	@ManyToOne
	private Student student;

	@ManyToOne
	private LessonEntry lessonEntry;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public LessonEntry getLessonEntry() {
		return lessonEntry;
	}


	public void setLessonEntry(LessonEntry lessonEntry) {
		this.lessonEntry = lessonEntry;
	}

}