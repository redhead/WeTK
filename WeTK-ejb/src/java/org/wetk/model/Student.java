/*
 */
package org.wetk.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


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

	private int ordinal;

	@ManyToOne
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
