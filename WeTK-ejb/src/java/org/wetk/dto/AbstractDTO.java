/*
 */
package org.wetk.dto;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
abstract public class AbstractDTO<T> {

	abstract public Long getId();


	abstract public T toEntity(T entity);

}
