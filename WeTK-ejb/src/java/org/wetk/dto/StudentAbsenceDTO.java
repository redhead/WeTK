/*
 */
package org.wetk.dto;

import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class StudentAbsenceDTO {

	private long id;

	private String fullName;

	private int absenceCount;


	public StudentAbsenceDTO() {
	}


	public StudentAbsenceDTO(Student student, Number absenceCount) {
		this.id = student.getId();
		this.fullName = student.getFirstName() + " " + student.getLastName();
		this.absenceCount = absenceCount.intValue();
	}


	public long getId() {
		return id;
	}


	public String getFullName() {
		return fullName;
	}


	public int getAbsenceCount() {
		return absenceCount;
	}

}
