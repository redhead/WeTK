/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.ITeacher;
import org.wetk.dto.TeacherDTO;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "teachers")
@RequestScoped
public class TeachersBean {

	@EJB
	private ITeacher model;

	private TeacherDTO teacher = new TeacherDTO();

	private String password;


	public TeacherDTO getTeacher() {
		return teacher;
	}


	public String edit(Long id) throws Exception {
		Teacher t = model.find(id);
		if(t == null) {
			throw new Exception("Teacher not found");
		}
		teacher = new TeacherDTO(t);
		return null;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveTeacher() {
		model.save(teacher, password);
		return "success";
	}


	public List<TeacherDTO> getTeachers() {
		List<Teacher> teachers = model.getAllTeachers();
		List<TeacherDTO> dtos = new ArrayList<TeacherDTO>();
		for(Teacher t : teachers) {
			dtos.add(new TeacherDTO(t));
		}
		return dtos;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
