/*
 */
package org.wetk.model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String titleBefore;

	private String titleAfter;

	private boolean admin;

	@OneToMany(mappedBy = "teacher")
	private List<SubjectAssignment> assignments;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTitleBefore() {
		return titleBefore;
	}


	public void setTitleBefore(String titleBefore) {
		this.titleBefore = titleBefore;
	}


	public String getTitleAfter() {
		return titleAfter;
	}


	public void setTitleAfter(String titleAfter) {
		this.titleAfter = titleAfter;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
