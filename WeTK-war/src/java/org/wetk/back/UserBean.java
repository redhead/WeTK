/*
 */
package org.wetk.back;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "user")
@RequestScoped
public class UserBean {

	public String logout() {
		HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sess.invalidate();
		return "index";
	}

}
