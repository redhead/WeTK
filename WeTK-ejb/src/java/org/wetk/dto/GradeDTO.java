/*
 */
package org.wetk.dto;

import org.wetk.entity.Grade;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class GradeDTO extends AbstractDTO<Grade> {

	private Long id;

	private double grade;

	private String comment;

	private Long subjectId;


	public GradeDTO(Grade entity) {
		if(entity == null) return;
		
		id = entity.getId();
		comment = entity.getComment();
		grade = entity.getGrade();

		if(entity.getSubject() != null) {
			subjectId = entity.getSubject().getId();
		}
	}


	@Override
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public double getGrade() {
		return grade;
	}


	public void setGrade(double grade) {
		this.grade = grade;
	}


	public Long getSubjectId() {
		return subjectId;
	}


	@Override
	public Grade toEntity(Grade entity) {
		entity.setId(id);
		entity.setComment(comment);
		entity.setGrade(grade);
		return entity;
	}

}
