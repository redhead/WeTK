/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.ISubject;
import org.wetk.dto.SubjectDTO;
import org.wetk.model.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "subjects")
@RequestScoped
public class SubjectsBean {

	@EJB
	private ISubject model;

	private SubjectDTO subject = new SubjectDTO();


	public SubjectDTO getSubject() {
		return subject;
	}


	public String edit(Long id) throws Exception {
		Subject t = model.find(id);
		if(t == null) {
			throw new Exception("Subject not found");
		}
		subject = new SubjectDTO(t);
		return null;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveSubject() {
		model.save(subject);
		return "success";
	}


	public List<SubjectDTO> getSubjects() {
		List<Subject> subjects = model.getAllSubjects();
		List<SubjectDTO> dtos = new ArrayList<SubjectDTO>();
		for(Subject s : subjects) {
			dtos.add(new SubjectDTO(s));
		}
		return dtos;
	}

}
