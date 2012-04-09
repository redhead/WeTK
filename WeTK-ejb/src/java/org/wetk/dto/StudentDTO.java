/*
 */
package org.wetk.dto;

import org.wetk.model.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class StudentDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private int ordinal;


	public StudentDTO() {
	}


	public StudentDTO(Student clazz) {
		if(clazz == null) return;
		id = clazz.getId();
		firstName = clazz.getFirstName();
		lastName = clazz.getLastName();
		email = clazz.getEmail();
		ordinal = clazz.getOrdinal();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getOrdinal() {
		return ordinal;
	}


	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}


	public String getFullName() {
		return firstName + " " + lastName;
	}


	public Student toEntity(Student student) {
		student.setId(id);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setOrdinal(ordinal);
		return student;
	}

}
