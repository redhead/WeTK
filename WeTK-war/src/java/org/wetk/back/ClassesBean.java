/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.ErrorType;
import org.wetk.Utils;
import org.wetk.business.local.IClass;
import org.wetk.dto.ClassDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "classes")
@RequestScoped
public class ClassesBean {

	@EJB
	private IClass classModel;

	@ManagedProperty(value = "#{user}")
	private UserBean userBean;

	private ClassDTO clazz = new ClassDTO();

	private Long teacherId = null;


	public ClassDTO getClassDTO() {
		return clazz;
	}


	public String edit(Long id) throws Exception {
		ClassEntity t = classModel.find(id);
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
		classModel.delete(id);
		return null;
	}


	public String saveClass() {
		try {
			classModel.save(clazz, teacherId);
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
		List<ClassEntity> classes = classModel.getAll();
		List<ClassDTO> dtos = new ArrayList<ClassDTO>();
		for(ClassEntity c : classes) {
			dtos.add(new ClassDTO(c));
		}
		return dtos;
	}


	public List<SelectItem> getSelectItems() {
		List<ClassEntity> classes = classModel.getAll();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for(ClassEntity c : classes) {
			items.add(new SelectItem(c.getId(), c.getTitle()));
		}
		return items;
	}


	public List<SelectItem> getTeacherClassSelectItems() {
		Teacher teacher = userBean.getTeacher();
		List<ClassEntity> classes = classModel.getAllFor(teacher);
		List<ClassEntity> other = classModel.getAllExcept(classes);

		List<SelectItem> items = new ArrayList<SelectItem>();
		for(ClassEntity c : classes) {
			items.add(new SelectItem(c.getId(), c.getTitle()));
		}
		if(!other.isEmpty()) {
			items.add(new SelectItem("", "-- ostatní --", null, true));
			for(ClassEntity c : other) {
				items.add(new SelectItem(c.getId(), c.getTitle()));
			}
		}
		return items;
	}


	public Long getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}


	public void setUserBean(UserBean user) {
		this.userBean = user;
	}

}
