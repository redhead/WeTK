/*
 */
package org.wetk.model;

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
	query = "SELECT e FROM LessonEntry e WHERE e.lesson.clazz = :class AND e.date = :date AND e.lessonHour = :lessonHour")
})
public class LessonEntry extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	public static final String GET_BY_CLASS_DATE_HOUR = "LessonEntry.getByClassDateHour";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private Lesson lesson;

	private int lessonNumber;

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


	public int getLessonNumber() {
		return lessonNumber;
	}


	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
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


	public Lesson getLesson() {
		return lesson;
	}


	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

}
