package org.wetk.back;

/*
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.wetk.business.local.ILesson;
import org.wetk.dto.TimeTable;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "timetable")
@RequestScoped
public class TimeTableBean implements Serializable {

	@EJB
	private ILesson lessonModel;

	@ManagedProperty(value = "#{user}")
	private UserBean userBean;

	private TimeTable table;


	@PostConstruct
	public void init() {
		table = lessonModel.getTimeTableFor(userBean.getTeacher().getId());
	}


	public TimeTable getTimeTable() {
		return table;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
