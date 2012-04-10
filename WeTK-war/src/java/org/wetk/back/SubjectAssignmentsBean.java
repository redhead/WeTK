/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.business.local.ISubjectAssignment;
import org.wetk.dto.SubjectAssignmentDTO;
import org.wetk.model.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "assignments")
@RequestScoped
public class SubjectAssignmentsBean {

	@EJB
	private ISubjectAssignment model;

	private SubjectAssignmentDTO assignment = new SubjectAssignmentDTO();

	private Long teacherId;

	private Long subjectId;


	public SubjectAssignmentDTO getAssignment() {
		return assignment;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveAssignment() {
		model.save(teacherId, subjectId);
		return "success";
	}


	public List<SubjectAssignmentDTO> getAssignments() {
		List<SubjectAssignment> assigns = model.getAllAssignments();
		List<SubjectAssignmentDTO> dtos = new ArrayList<SubjectAssignmentDTO>();
		for(SubjectAssignment a : assigns) {
			dtos.add(new SubjectAssignmentDTO(a));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<SubjectAssignment> assigns = model.getAllAssignments();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(SubjectAssignment a : assigns) {
			String label = a.getSubject().getTitle() + " - " + a.getTeacher().getLastName() + ", " + a.getTeacher().getFirstName();
			items.add(new SelectItem(a.getId(), label));
		}
		return items;
	}


	public Long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}
