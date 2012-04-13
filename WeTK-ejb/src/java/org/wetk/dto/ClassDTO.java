/*
 */
package org.wetk.dto;

import org.wetk.entity.ClassEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class ClassDTO extends AbstractDTO<ClassEntity> {

	private Long id;

	private String title;

	private Long teacherId;

	private String teacherFullName;


	public ClassDTO() {
	}


	public ClassDTO(ClassEntity clazz) {
		if(clazz == null) return;
		id = clazz.getId();
		title = clazz.getTitle();
		if(clazz.getTeacher() != null) {
			teacherId = clazz.getTeacher().getId();
			teacherFullName = new TeacherDTO(clazz.getTeacher()).getFullName();
		}
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


	public String getTeacherFullName() {
		return teacherFullName;
	}


	public void setTeacherFullName(String teacherFullName) {
		this.teacherFullName = teacherFullName;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}


	@Override
	public ClassEntity toEntity(ClassEntity clazz) {
		clazz.setId(id);
		clazz.setTitle(title);
		return clazz;
	}

}
