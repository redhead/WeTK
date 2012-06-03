/*
 */
package org.wetk.entity;

import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Student.GET_ALL_STUDENTS, query = "SELECT s FROM Student s ORDER BY s.lastName, s.firstName")
})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ordinal", "clazz_id" }))
public class Student extends Person {

	private static final long serialVersionUID = 1L;

	public static final String GET_ALL_STUDENTS = "Student.getAllStudents";

	@Column(nullable = false)
	private int ordinal;

	@ManyToOne(optional = false)
	private ClassEntity clazz;

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Absence> absences;


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


	public List<Absence> getAbsences() {
		return absences;
	}


	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

}
