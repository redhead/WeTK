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
import org.wetk.entity.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "assignments")
@RequestScoped
public class SubjectAssignmentsBean {

	@EJB
	private ISubjectAssignment assignmentModel;

	private SubjectAssignmentDTO assignment = new SubjectAssignmentDTO();

	private Long teacherId;

	private Long subjectId;


	public SubjectAssignmentDTO getAssignment() {
		return assignment;
	}


	public String delete(Long id) throws Exception {
		assignmentModel.delete(id);
		return null;
	}


	public String saveAssignment() {
		assignmentModel.save(teacherId, subjectId);
		return "success";
	}


	public List<SubjectAssignmentDTO> getAssignments() {
		List<SubjectAssignment> assigns = assignmentModel.getAll();
		List<SubjectAssignmentDTO> dtos = new ArrayList<SubjectAssignmentDTO>();
		for(SubjectAssignment a : assigns) {
			dtos.add(new SubjectAssignmentDTO(a));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<SubjectAssignment> assigns = assignmentModel.getAll();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(SubjectAssignment a : assigns) {
			items.add(createSelectItem(a));
		}
		return items;
	}


	public List<SelectItem> getSelectItemsFor(Long teacherId, Long classId) {
		List<SubjectAssignment> assigns = assignmentModel.getAllFor(teacherId, classId);
		List<SubjectAssignment> other = assignmentModel.getAllExcept(assigns, classId);

		List<SelectItem> items = new ArrayList<SelectItem>();
		for(SubjectAssignment a : assigns) {
			String label = a.getSubject().getTitle() + " - " + a.getTeacher().getLastName() + ", " + a.getTeacher().getFirstName();
			items.add(new SelectItem(a.getId(), label));
		}
		
		if(!other.isEmpty()) {
			items.add(new SelectItem("", "-- suplování --", null, true));
			for(SubjectAssignment a : other) {
				items.add(createSelectItem(a));
			}
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


	private SelectItem createSelectItem(SubjectAssignment a) {
		String label = a.getSubject().getTitle() + " - " + a.getTeacher().getLastName() + ", " + a.getTeacher().getFirstName();
		return new SelectItem(a.getId(), label);
	}

}
