/*
 */
package org.wetk.entity;

import java.util.Date;
import java.util.List;
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
	+ " ORDER BY e.date DESC, e.lessonHour DESC LIMIT 1"),
	@NamedQuery(name = LessonEntry.FIND_NEXT_TO_DATE_HOUR,
	query = "SELECT e FROM LessonEntry e"
	+ " WHERE ((e.date = :date AND e.lessonHour > :lessonHour) OR e.date > :date)"
	+ " AND e.assignment.teacher = :teacher"
	+ " ORDER BY e.date ASC, e.lessonHour ASC LIMIT 1")
})
public class LessonEntry extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String GET_BY_CLASS_DATE_HOUR = "LessonEntry.getByClassDateHour";
	
	public static final String FIND_PREV_TO_DATE_HOUR = "LessonEntry.findPrev";
	
	public static final String FIND_NEXT_TO_DATE_HOUR = "LessonEntry.findNext";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private SubjectAssignment assignment;
	
	@ManyToOne(optional = false)
	private ClassEntity clazz;
	
	private String topic;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	
	private int lessonHour;
	
	@OneToMany(mappedBy = "lessonEntry", cascade = CascadeType.ALL)
	private List<Absence> absences;
	
	
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
	
	
	public List<Absence> getAbsences() {
		return absences;
	}
	
	
	public void setAbsences(List<Absence> absences) {
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
	
}
