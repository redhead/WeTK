/*
 */
package org.wetk.model;

import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Student.GET_ALL_STUDENTS, query = "SELECT s FROM Student s ORDER BY s.lastName, s.firstName")
})
public class Student extends Person {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_STUDENTS = "Student.getAllStudents";

	@Column(nullable = false)
	private int ordinal;

	@ManyToOne(optional = false)
	private ClassEntity clazz;


	public int getOrdinal() {
		return ordinal;
	}


	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}


	public ClassEntity getClazz() {
		return clazz;
	}


	public void setClazz(ClassEntity clazz) {
		this.clazz = clazz;
	}

}
