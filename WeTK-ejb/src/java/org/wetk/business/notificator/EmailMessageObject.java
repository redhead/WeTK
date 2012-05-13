/*
 */
package org.wetk.business.notificator;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class EmailMessageObject implements Serializable {

	private String email;

	private Long classId;

	private Date dateOfWeek;


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}


	public Date getDateOfWeek() {
		return dateOfWeek;
	}


	public void setDateOfWeek(Date dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

}
