/*
 */
package org.wetk.dto;

import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class StudentDTO extends AbstractDTO<Student> {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private int ordinal;

	private Long classId;

	private String classTitle;


	public StudentDTO() {
	}


	public StudentDTO(Student student) {
		if(student == null) return;

		id = student.getId();
		firstName = student.getFirstName();
		lastName = student.getLastName();
		email = student.getEmail();
		ordinal = student.getOrdinal();

		if(student.getClazz() != null) {
			classId = student.getClazz().getId();
			classTitle = student.getClazz().getTitle();
		}
	}


	@Override
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


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long classId) {
		this.classId = classId;
	}


	public String getClassTitle() {
		return classTitle;
	}


	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}


	@Override
	public Student toEntity(Student student) {
		student.setId(id);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setOrdinal(ordinal);
		return student;
	}

}
