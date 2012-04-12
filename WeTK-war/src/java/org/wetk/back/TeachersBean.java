/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.wetk.ErrorType;
import org.wetk.Utils;
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


	public TeachersBean() {
		teacher.setEmail("@");
	}


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
		try {
			model.save(teacher, password);
			return "success";
		} catch(EJBException e) {
			if(Utils.getErrorType(e) == ErrorType.DUPLICATE) {
				String msg = Utils.getString("error_userExists", teacher.getUsername());
				Utils.addMessage("form-errors", msg);
			}
		}
		return null;
	}


	public List<TeacherDTO> getTeachers() {
		List<Teacher> teachers = model.getAllTeachers();
		List<TeacherDTO> dtos = new ArrayList<TeacherDTO>();
		for(Teacher t : teachers) {
			dtos.add(new TeacherDTO(t));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<Teacher> teachers = model.getAllTeachers();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(Teacher t : teachers) {
			String fullName = new TeacherDTO(t).getFullName();
			items.add(new SelectItem(t.getId(), fullName));
		}
		return items;
	}


	public List<SelectItem> getClasslessTeachersSelectItems() {
		List<Teacher> teachers = model.getAllTeachers();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(Teacher t : teachers) {
			if(t.getClazz() != null) continue;
			String fullName = new TeacherDTO(t).getFullName();
			items.add(new SelectItem(t.getId(), fullName));
		}
		return items;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
