/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.IStudent;
import org.wetk.dto.StudentDTO;
import org.wetk.model.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "students")
@RequestScoped
public class StudentsBean {

	@EJB
	private IStudent model;

	private StudentDTO student = new StudentDTO();

	private Long classId = null;


	public StudentsBean() {
		student.setOrdinal(1);
		student.setEmail("@");
	}


	public StudentDTO getStudent() {
		return student;
	}


	public String edit(Long id) throws Exception {
		Student s = model.find(id);
		if(s == null) {
			throw new Exception("Student not found");
		}
		student = new StudentDTO(s);
		classId = student.getClassId();
		return null;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveStudent() {
		model.save(student, classId);
		return "success";
	}


	public List<StudentDTO> getStudents() {
		List<Student> students = model.getAllStudents();
		List<StudentDTO> dtos = new ArrayList<StudentDTO>();
		for(Student s : students) {
			dtos.add(new StudentDTO(s));
		}
		return dtos;
	}


	public Long getClassId() {
		return classId;
	}


	public void setClassId(Long clazz) {
		this.classId = clazz;
	}

}
