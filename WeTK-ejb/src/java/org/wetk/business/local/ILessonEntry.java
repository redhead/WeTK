/*
 */

package org.wetk.business.local;

import java.util.Date;
import javax.ejb.Local;
import org.wetk.model.ClassEntity;
import org.wetk.model.LessonEntry;

/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILessonEntry extends IAbstractBean {

	public LessonEntry getLessonEntryFor(ClassEntity clazz, Date date, int lessonHour);
	
}
