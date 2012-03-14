/*
 */
package org.wetk.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Entity
@DiscriminatorValue(value = "student")
public class Student extends Person {

	private static final long serialVersionUID = 1L;

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
