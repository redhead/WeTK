/*
 */
package org.wetk.back;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.wetk.business.local.ILessonEntry;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.entity.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "entries")
@SessionScoped
public class LessonEntryBean implements Serializable {

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
		LessonEntry entry = entryModel.getLessonEntryFor(selectedClassId, date, lessonHour);
		setup(entry);
		editting = (lessonEntry.getId() == null ? true : false);
	}


	public String saveEntry() {
		entryModel.save(lessonEntry, selectedClassId, date, lessonHour, assignmentId);
		editting = false;
		findEntry();
		return "pretty:lessonEntry";
	}


	public String editEntry() {
		editting = true;
		return "pretty:lessonEntry";
	}


	public String cancel() {
		if(lessonEntry == null || lessonEntry.getId() == null) {
			lessonEntry = null;
		}
		editting = false;
		return "pretty:lessonEntry";
	}


	public String prevEntry() {
		LessonEntry entry = entryModel.findPreviousTo(date, lessonHour, userBean.getTeacher());

		if(entry != null) {
			setup(entry);
		}
		return "pretty:lessonEntry";
	}


	public String nextEntry() {
		LessonEntry entry = entryModel.findNextTo(date, lessonHour, userBean.getTeacher());

		if(entry != null) {
			setup(entry);
		}
		return "pretty:lessonEntry";
	}


	private void setup(LessonEntry entry) {
		lessonEntry = new LessonEntryDTO(entry);

		selectedClassId = lessonEntry.getClassId();
		date = lessonEntry.getDate();
		lessonHour = lessonEntry.getLessonHour();
		assignmentId = lessonEntry.getAssignmentId();
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
