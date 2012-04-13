/*
 */
package org.wetk.back;

import java.security.Principal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.wetk.business.local.ITeacher;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "user")
@RequestScoped
public class UserBean {

	public static final String ADMIN_ROLE = "admin";

	@EJB
	private ITeacher teacherModel;


	public boolean getIsLoggedIn() {
		Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		return (p != null);
	}


	public String logout() {
		HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sess.invalidate();
		return "pretty:home";
	}


	public boolean getIsAdmin() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(ADMIN_ROLE);
	}


	public boolean getHasClass() {
		Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		String username = p.getName();
		Teacher t = teacherModel.findByUsername(username);
		return (t.getClazz() != null);
	}

}
