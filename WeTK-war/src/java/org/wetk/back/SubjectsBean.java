/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.business.local.ISubject;
import org.wetk.dto.SubjectDTO;
import org.wetk.entity.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "subjects")
@RequestScoped
public class SubjectsBean {

	@EJB
	private ISubject subjectModel;

	private SubjectDTO subject = new SubjectDTO();


	public SubjectDTO getSubject() {
		return subject;
	}


	public String edit(Long id) throws Exception {
		Subject t = subjectModel.find(id);
		if(t == null) {
			throw new Exception("Subject not found");
		}
		subject = new SubjectDTO(t);
		return null;
	}


	public String delete(Long id) throws Exception {
		subjectModel.delete(id);
		return null;
	}


	public String saveSubject() {
		subjectModel.save(subject);
		return "success";
	}


	public List<SubjectDTO> getSubjects() {
		List<Subject> subjects = subjectModel.getAll();
		List<SubjectDTO> dtos = new ArrayList<SubjectDTO>();
		for(Subject s : subjects) {
			dtos.add(new SubjectDTO(s));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<Subject> subjects = subjectModel.getAll();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(Subject s : subjects) {
			items.add(new SelectItem(s.getId(), s.getTitle()));
		}
		return items;
	}

}
