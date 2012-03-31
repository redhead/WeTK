/*
 */
package org.wetk.model;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
public class Lesson extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private SubjectAssignment assignment;

	private int day;

	private int hour;

	@OneToOne
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

}
