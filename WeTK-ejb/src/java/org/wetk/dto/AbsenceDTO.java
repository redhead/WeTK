/*
 */
package org.wetk.dto;

import java.util.Date;
import org.wetk.entity.Absence;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class AbsenceDTO extends AbstractDTO<Absence> {

	private Long id;

	private boolean late;

	private String excuse;

	private Date date;

	private int hour;


	public AbsenceDTO(Absence a) {
		if(a == null) return;
		id = a.getId();
		late = a.isLate();
		excuse = a.getExuse();
		date = a.getLessonEntry().getDate();
		hour = a.getLessonEntry().getLessonHour();
	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getExcuse() {
		return excuse;
	}


	public void setExcuse(String excuse) {
		this.excuse = excuse;
	}


	public boolean isLate() {
		return late;
	}


	public Date getDate() {
		return date;
	}


	public int getHour() {
		return hour;
	}


	@Override
	public Absence toEntity(Absence entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
