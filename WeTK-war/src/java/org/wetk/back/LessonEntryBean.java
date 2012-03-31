/*
 */
package org.wetk.back;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.wetk.model.Absence;
import org.wetk.model.ClassEntity;
import org.wetk.model.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "entries")
@SessionScoped
public class LessonEntryBean implements Serializable {

//	@EJB
//	private LessonEntryModel model;
	private ClassEntity clazz;

	private Date date = new Date();

	private int hour = 1;


	public LessonEntry getLessonEntry() {
		/** @FIXME odstranit staticky data */
		LessonEntry entry = new LessonEntry();
		entry.setAbsences(new ArrayList<Absence>());
		entry.setDate(date);
		entry.setLessonHour(1);
		entry.setTopic("Irregular verbs");
		entry.setLessonNumber(48);
		return entry;
	}

}
