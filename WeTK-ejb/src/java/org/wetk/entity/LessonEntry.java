/*
 */
package org.wetk.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = LessonEntry.GET_BY_CLASS_DATE_HOUR,
	query = "SELECT e FROM LessonEntry e WHERE e.clazz = :class AND e.date = :date AND e.lessonHour = :lessonHour"),
	@NamedQuery(name = LessonEntry.FIND_PREV_TO_DATE_HOUR,
	query = "SELECT e FROM LessonEntry e"
	+ " WHERE ((e.date = :date AND e.lessonHour < :lessonHour) OR e.date < :date)"
	+ " AND e.assignment.teacher = :teacher"
	+ " ORDER BY e.date DESC, e.lessonHour DESC"),
	@NamedQuery(name = LessonEntry.FIND_NEXT_TO_DATE_HOUR,
	query = "SELECT e FROM LessonEntry e"
	+ " WHERE ((e.date = :date AND e.lessonHour > :lessonHour) OR e.date > :date)"
	+ " AND e.assignment.teacher = :teacher"
	+ " ORDER BY e.date ASC, e.lessonHour ASC"),
	@NamedQuery(name = LessonEntry.COUNT_BETWEEN,
	query = "SELECT count(e) FROM LessonEntry e WHERE e.clazz = :class"
	+ " AND date >= :from AND date <= :to")
})
public class LessonEntry extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_BY_CLASS_DATE_HOUR = "LessonEntry.getByClassDateHour";

	public static final String FIND_PREV_TO_DATE_HOUR = "LessonEntry.findPrev";

	public static final String FIND_NEXT_TO_DATE_HOUR = "LessonEntry.findNext";

	public static final String COUNT_BETWEEN = "LessonEntry.countBetween";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false)
	private SubjectAssignment assignment;

	@ManyToOne(optional = false)
	private ClassEntity clazz;

	private String topic;

	@ManyToOne
	private Teacher signer;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;

	private int lessonHour;

	@OneToMany(mappedBy = "lessonEntry", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Absence> absences;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getLessonHour() {
		return lessonHour;
	}


	public void setLessonHour(int hour) {
		this.lessonHour = hour;
	}


	public Set<Absence> getAbsences() {
		return absences;
	}


	public void setAbsences(Set<Absence> absences) {
		this.absences = absences;
	}


	public SubjectAssignment getAssignment() {
		return assignment;
	}


	public void setAssignment(SubjectAssignment assignment) {
		this.assignment = assignment;
	}


	public ClassEntity getClazz() {
		return clazz;
	}


	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}


	public Teacher getSigner() {
		return signer;
	}


	public void setSigner(Teacher signer) {
		this.signer = signer;
	}

}
