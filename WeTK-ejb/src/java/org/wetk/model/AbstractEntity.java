/*
 */
package org.wetk.model;

import java.io.Serializable;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
abstract class AbstractEntity implements Serializable {

	abstract public Long getId();


	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		long id = getId();
		return "org.wetk.model." + className + "[ id=" + id + " ]";
	}

}
