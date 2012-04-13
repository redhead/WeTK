/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.LessonDTO;
import org.wetk.entity.Lesson;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILesson extends IAbstractAdminModel<Lesson> {

	public void save(LessonDTO lesson, Long classId, Long assignmentId);

}
