/*
 */
package org.wetk.model;

import java.util.List;
import javax.persistence.*;
import org.wetk.helper.PasswordHashProvider;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Teacher.GET_ALL_TEACHERS, query = "SELECT t FROM Teacher t ORDER BY username"),
	@NamedQuery(name = Teacher.GET_BY_USERNAME, query = "SELECT t FROM Teacher t WHERE username = :username")
})
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_TEACHERS = "Teacher.getAllTeachers";

	public static final String GET_BY_USERNAME = "Teacher.getByUsername";

	@Column(nullable = false, unique = true)
	private String username;

	private String password;

	private String titleBefore;

	private String titleAfter;

	private boolean admin = false;

	@OneToMany(mappedBy = "teacher")
	private List<SubjectAssignment> assignments;

	@OneToOne(mappedBy = "teacher")
	private ClassEntity clazz;


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
		this.password = PasswordHashProvider.hash(password);
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


	public List<SubjectAssignment> getAssignments() {
		return assignments;
	}


	public void setAssignments(List<SubjectAssignment> assignments) {
		this.assignments = assignments;
	}


	public ClassEntity getClazz() {
		return clazz;
	}


	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

}
