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
	@NamedQuery(name = Note.GET_ALL_FOR_STUDENT, query = "SELECT n FROM Note n WHERE n.student = :student")
})
public class Note extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_FOR_STUDENT = "Note.getAllForStudent";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String description;

	@ManyToOne(optional = false)
	private Student student;

	@ManyToOne(optional = false)
	private LessonEntry lessonEntry;

	private boolean solved;


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


	public boolean isSolved() {
		return solved;
	}


	public void setSolved(boolean solved) {
		this.solved = solved;
	}

}
