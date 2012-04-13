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
	@NamedQuery(name = Lesson.GET_ALL_LESSONS, query = "SELECT l FROM Lesson l ORDER BY clazz.title, day, hour")
})
public class Lesson extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_LESSONS = "Lesson.getAllLessons";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private SubjectAssignment assignment;

	private int day;

	private int hour;

	@ManyToOne(optional = false)
	private ClassEntity clazz;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public SubjectAssignment getAssignment() {
		return assignment;
	}


	public void setAssignment(SubjectAssignment assignment) {
		this.assignment = assignment;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public ClassEntity getClazz() {
		return clazz;
	}


	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

}
