/*
 */
package org.wetk.dto;

import org.wetk.entity.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class SubjectDTO extends AbstractDTO<Subject> {

	private Long id;

	private String title;

	private String code;


	public SubjectDTO() {
	}


	public SubjectDTO(Subject subject) {
		if(subject == null) return;
		id = subject.getId();
		title = subject.getTitle();
		code = subject.getCode();
	}


	@Override
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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public Subject toEntity(Subject subject) {
		subject.setId(id);
		subject.setTitle(title);
		subject.setCode(code);
		return subject;
	}

}
