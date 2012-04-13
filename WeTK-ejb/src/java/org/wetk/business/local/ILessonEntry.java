/*
 */
package org.wetk.business.local;

import java.util.Date;
import javax.ejb.Local;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILessonEntry extends IAbstractModel<LessonEntry> {

	public LessonEntry getLessonEntryFor(ClassEntity clazz, Date date, int lessonHour);


	void save(LessonEntryDTO dto);

}
