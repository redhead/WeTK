/*
 */
package org.wetk.dto;

import org.wetk.model.ClassEntity;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class TeacherDTO {

	private Long id;

	private String username;

	private String firstName;

	private String lastName;

	private String titleBefore;

	private String titleAfter;

	private String email;

	private boolean admin;


	public TeacherDTO() {
	}


	public TeacherDTO(Teacher teacher) {
		if(teacher == null) return;
		id = teacher.getId();
		username = teacher.getUsername();
		firstName = teacher.getFirstName();
		lastName = teacher.getLastName();
		titleBefore = teacher.getTitleBefore();
		titleAfter = teacher.getTitleAfter();
		email = teacher.getEmail();
		admin = teacher.isAdmin();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
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


	public String getTitleAfter() {
		return titleAfter;
	}


	public void setTitleAfter(String titleAfter) {
		this.titleAfter = titleAfter;
	}


	public String getTitleBefore() {
		return titleBefore;
	}


	public void setTitleBefore(String titleBefore) {
		this.titleBefore = titleBefore;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFullName() {
		String full = "";

		if(titleBefore != null && !titleBefore.trim().isEmpty()) {
			full += titleBefore + " ";
		}

		full += firstName;
		full += " ";
		full += lastName;

		if(titleAfter != null && !titleAfter.trim().isEmpty()) {
			full += " " + titleAfter;
		}

		return full;
	}


	public Teacher toEntity(Teacher t) {
		t.setUsername(getUsername());
		t.setFirstName(getFirstName());
		t.setLastName(getLastName());
		t.setEmail(getEmail());
		t.setTitleAfter(getTitleAfter());
		t.setTitleBefore(getTitleBefore());
		t.setAdmin(isAdmin());
		return t;
	}

}
