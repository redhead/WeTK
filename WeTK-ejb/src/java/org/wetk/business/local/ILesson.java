/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.LessonDTO;
import org.wetk.entity.Lesson;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILesson extends IAbstractAdminModel<Lesson> {

	public void save(LessonDTO lesson, Long classId, Long assignmentId);


	public Lesson getLessonFor(Long classId, int day, int lessonHour);


	public Lesson findPrevTo(int day, int lessonHour, Teacher teacher);


	public Lesson findNextTo(int day, int lessonHour, Teacher teacher);

}
