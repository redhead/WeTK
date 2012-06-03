/*
 */
package org.wetk.back;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.wetk.business.local.ILessonEntry;
import org.wetk.dto.LessonEntryDTO;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "entries")
@SessionScoped
public class LessonEntryBean implements Serializable {

	private static final String PAGE_ID = "pretty:lessonEntry";

	@EJB
	private ILessonEntry entryModel;

	@ManagedProperty(value = "#{user}")
	private UserBean userBean;

	private LessonEntryDTO lessonEntry;

	private Long assignmentId;

	private Date date = new Date();

	private int lessonHour = 1;

	private Long selectedClassId;

	private transient boolean editting = false;


	public void findEntry() {
		LessonEntryDTO entry = entryModel.getLessonEntryFor(selectedClassId, date, lessonHour);
		setup(entry);
	}


	public String saveEntry() {
		Long signerId = (lessonEntry.getId() == null ? userBean.getTeacher().getId() : null);
		entryModel.save(lessonEntry, selectedClassId, date, lessonHour, assignmentId, signerId);
		editting = false;
		findEntry();
		return PAGE_ID;
	}


	public String deleteEntry() {
		if(lessonEntry != null && lessonEntry.getId() != null) {
			entryModel.delete(lessonEntry.getId());
			lessonEntry = null;
			editting = false;
		}
		return PAGE_ID;
	}


	public String editEntry() {
		editting = true;
		return PAGE_ID;
	}


	public String cancel() {
		if(lessonEntry == null || lessonEntry.getId() == null) {
			lessonEntry = null;
		}
		editting = false;
		return PAGE_ID;
	}


	public String prevEntry() {
		LessonEntryDTO entry = entryModel.findPreviousTo(date, lessonHour, userBean.getTeacher());
		setup(entry);

		return PAGE_ID;
	}


	public String nextEntry() {
		LessonEntryDTO entry = entryModel.findNextTo(date, lessonHour, userBean.getTeacher());
		setup(entry);

		return PAGE_ID;
	}


	public String selectLesson(long classId, int day, int hour) {
		selectedClassId = classId;
		lessonHour = hour;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, day);
		date = cal.getTime();
		
		findEntry();
		
		return PAGE_ID;
	}


	private void setup(LessonEntryDTO entry) {
		lessonEntry = entry;

		selectedClassId = entry.getClassId();
		date = entry.getDate();
		lessonHour = entry.getLessonHour();
		assignmentId = entry.getAssignmentId();

		editting = (entry.getId() == null ? true : false);
	}


	public boolean isSearchDisabled() {
		if(lessonEntry == null) return false;
		if(lessonEntry.getId() == null) return true;
		return editting;
	}


	public LessonEntryDTO getLessonEntry() {
		return lessonEntry;
	}


	public void setLessonEntry(LessonEntryDTO lessonEntry) {
		this.lessonEntry = lessonEntry;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getLessonHour() {
		return lessonHour;
	}


	public void setLessonHour(int lessonHour) {
		this.lessonHour = lessonHour;
	}


	public Long getSelectedClassId() {
		return selectedClassId;
	}


	public void setSelectedClassId(Long selectedClassId) {
		this.selectedClassId = selectedClassId;
	}


	public Long getAssignmentId() {
		return assignmentId;
	}


	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}


	public boolean isEditting() {
		return editting;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
