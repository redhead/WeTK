/*
 */
package org.wetk.dto;

import org.wetk.entity.Absence;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class AbsenceDTO extends AbstractDTO<Absence> {

	private Long id;

	private boolean late;

	private String exuse;


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	@Override
	public Absence toEntity(Absence entity) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
