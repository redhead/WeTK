/*
 */
package org.wetk.back;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.wetk.business.notificator.ClassTeacherNotificationLocal;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "notify")
@RequestScoped
public class NotifyBean implements Serializable {

	@EJB
	private ClassTeacherNotificationLocal notificator;


	public String notifyTeachers() {
		notificator.notifyEndOfWeek(null);
		return "pretty:";
	}

}
