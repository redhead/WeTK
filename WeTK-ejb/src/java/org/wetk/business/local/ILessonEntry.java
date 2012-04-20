/*
 */
package org.wetk.business.local;

import java.util.Date;
import javax.ejb.Local;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.entity.LessonEntry;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILessonEntry extends IAbstractModel<LessonEntry> {

	public LessonEntry getLessonEntryFor(Long classId, Date date, int lessonHour);


	public void save(LessonEntryDTO lessonEntry, Long classId, Date date, int lessonHour, Long assignmentId);


	public LessonEntry findPreviousTo(Date date, int lessonHour, Teacher teacher);


	public LessonEntry findNextTo(Date date, int lessonHour, Teacher teacher);

}
