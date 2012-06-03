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
			if(ent.getId() != null && this.getId() != null) {
				return (ent.getId().equals(this.getId()));
			}
			return false;
		}
		return false;
	}


	@Override
	public int hashCode() {
		long id = (getId() == null ? 0 : getId());
		int hash = (int) (7 * id);
		return hash;
	}


	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		long id = getId();
		return "org.wetk.model." + className + "[ id=" + id + " ]";
	}

}
