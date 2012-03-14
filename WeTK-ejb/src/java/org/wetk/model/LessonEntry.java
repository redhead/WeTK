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
public class LessonEntry extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int lessonNumber;

	private String topic;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;

	private int hour;

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


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public List<Absence> getAbsences() {
		return absences;
	}


	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

}
