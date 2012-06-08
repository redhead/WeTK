/*
 */
package org.wetk.back;

import java.io.Serializable;
import java.security.Principal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.wetk.business.local.ITeacher;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

	private static final String ADMIN_ROLE = "admin";

	@EJB
	private ITeacher teacherModel;

	private Teacher loggedInTeacher;


	private Principal getUserPrincipal() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	}


	public boolean getIsLoggedIn() {
		return (getUserPrincipal() != null);
	}


	public String logout() {
		HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sess.invalidate();

		loggedInTeacher = null;

		return "pretty:timetable";
	}


	public boolean getIsAdmin() {
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(ADMIN_ROLE);
	}


	public synchronized Teacher getTeacher() {
		if(loggedInTeacher == null) {
			Principal p = getUserPrincipal();

			if(p == null) return null;

			String username = p.getName();

			loggedInTeacher = teacherModel.findByUsername(username);
			return loggedInTeacher;
		}
		return loggedInTeacher;
	}


	public boolean getHasClass() {
		Teacher t = getTeacher();
		if(t == null) return false;

		return (t.getClazz() != null);
	}

}
