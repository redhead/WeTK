/*
 */
package org.wetk.entity;

import java.io.Serializable;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
abstract public class AbstractEntity implements Serializable {

	abstract public Long getId();


	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			AbstractEntity ent = (AbstractEntity) obj;
			return (ent.getId().equals(this.getId()));
		}
		return false;
	}


	@Override
	public int hashCode() {
		int hash = (int) (7 * getId());
		return hash;
	}


	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		long id = getId();
		return "org.wetk.model." + className + "[ id=" + id + " ]";
	}

}
