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
	@NamedQuery(name = Lesson.GET_ALL_LESSONS, query = "SELECT l FROM Lesson l ORDER BY clazz.title, day, hour"),
	/*@NamedQuery(name = Lesson.FIND_PREV_TO,
	query = "SELECT l FROM Lesson l WHERE l.assignment.teacher = ?"
		+ " ORDER BY (l.day > ? OR (l.day = ? AND l.hour >= ?)), l.day DESC, l.hour DESC"),
	@NamedQuery(name = Lesson.FIND_NEXT_TO,
	query = "SELECT l FROM Lesson l WHERE l.assignment.teacher = :teacher"
		+ " ORDER BY (l.day < ? OR (l.day = ? AND l.hour <= ?)), l.day ASC, l.hour ASC")*/
})
public class Lesson extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_LESSONS = "Lesson.getAllLessons";

	public static final String GET_FOR_CLASS_DAY_HOUR = "Lesson.getAllForClassDayHour";

	public static final String FIND_PREV_TO = "Lesson.findPrevTo";

	public static final String FIND_NEXT_TO = "Lesson.findNextTo";

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
