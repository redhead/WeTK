/*
 */
package org.wetk.back;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.IAbsence;
import org.wetk.business.local.IClass;
import org.wetk.dto.AbsenceDTO;
import org.wetk.dto.StudentAbsenceDTO;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "classAdmin")
@RequestScoped
public class ClassAdminBean {

	@ManagedProperty("#{param.selectedId}")
	private Long selectedId;

	@EJB
	private IClass classModel;

	@EJB
	private IAbsence absenceModel;

	@ManagedProperty(value = "#{user}")
	private UserBean userBean;

	private List<StudentAbsenceDTO> students;

	private List<AbsenceDTO> absences;


	@PostConstruct
	public void init() {
		if(userBean.getHasClass()) {
			Long classId = userBean.getTeacher().getClazz().getId();
			students = classModel.getStudentsFor(classId);
			if(selectedId != null) {
				absences = absenceModel.getFor(selectedId);
			}
		}
	}


	public String saveExcuse(Long id) {
		System.out.println("FUCK " + id);
		for(AbsenceDTO a : absences) {
			System.out.println(a.getId());
			if(id.equals(a.getId())) {
				System.out.println("YES");
				absenceModel.saveExcuse(a);
				break;
			}
		}
		return "pretty:classWithParam";
	}


	public String deleteAbsence(Long id) {
		absenceModel.delete(id);
		return "pretty:classWithParam";
	}


	public List<StudentAbsenceDTO> getStudents() {
		return students;
	}


	public List<AbsenceDTO> getAbsences() {
		return absences;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}


	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}


	public Long getSelectedId() {
		return selectedId;
	}

}
