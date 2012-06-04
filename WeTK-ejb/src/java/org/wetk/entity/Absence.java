/*
 */
package org.wetk.entity;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "student", "lessonEntry" }))
public class Absence extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "lessonEntry")
	private LessonEntry lessonEntry;

	private boolean late;

	private String exuse;


	public Absence() {
	}


	public Absence(LessonEntry entry, boolean late, Student student) {
		this.lessonEntry = entry;
		this.late = late;
		this.student = student;
	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LessonEntry getLessonEntry() {
		return lessonEntry;
	}


	public void setLessonEntry(LessonEntry lessonEntry) {
		this.lessonEntry = lessonEntry;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public String getExuse() {
		return exuse;
	}


	public void setExuse(String exuse) {
		this.exuse = exuse;
	}


	public boolean isLate() {
		return late;
	}


	public void setLate(boolean late) {
		this.late = late;
	}

}
