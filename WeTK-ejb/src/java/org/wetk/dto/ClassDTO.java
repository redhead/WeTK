/*
 */
package org.wetk.dto;

import org.wetk.model.ClassEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class ClassDTO {

	private Long id;

	private String title;


	public ClassDTO() {
	}


	public ClassDTO(ClassEntity clazz) {
		if(clazz == null) return;
		id = clazz.getId();
		title = clazz.getTitle();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public ClassEntity toEntity(ClassEntity clazz) {
		clazz.setId(id);
		clazz.setTitle(title);
		return clazz;
	}

}
