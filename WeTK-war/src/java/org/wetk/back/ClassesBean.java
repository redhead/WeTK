/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.ErrorType;
import org.wetk.Utils;
import org.wetk.business.local.IClass;
import org.wetk.dto.ClassDTO;
import org.wetk.model.ClassEntity;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "classes")
@RequestScoped
public class ClassesBean {

	@EJB
	private IClass model;

	private ClassDTO clazz = new ClassDTO();

	private Long teacherId = null;


	public ClassDTO getClassDTO() {
		return clazz;
	}


	public String edit(Long id) throws Exception {
		ClassEntity t = model.find(id);
		if(t == null) {
			throw new Exception("Class not found");
		}
		if(t.getTeacher() != null) {
			teacherId = t.getTeacher().getId();
		}
		clazz = new ClassDTO(t);
		return null;
	}


	public String delete(Long id) throws Exception {
		model.delete(id);
		return null;
	}


	public String saveClass() {
		try {
			model.save(clazz, teacherId);
			return "success";
		} catch(EJBException e) {
			if(Utils.getErrorType(e) == ErrorType.DUPLICATE) {
				String msg = Utils.getString("error_classExists", clazz.getTitle());
				Utils.addMessage("form-errors", msg);
			}
		}
		return null;
	}


	public List<ClassDTO> getClasses() {
		List<ClassEntity> classes = model.getAllClasses();
		List<ClassDTO> dtos = new ArrayList<ClassDTO>();
		for(ClassEntity c : classes) {
			dtos.add(new ClassDTO(c));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<ClassEntity> classes = model.getAllClasses();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(ClassEntity c : classes) {
			items.add(new SelectItem(c.getId(), c.getTitle()));
		}
		return items;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}
