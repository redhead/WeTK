/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.LessonDTO;
import org.wetk.model.Lesson;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ILesson {

	public List<Lesson> getAllLessons();


	public void save(LessonDTO lesson, Long classId, Long assignmentId);


	public Lesson find(Long id);


	public void delete(Long id);

}
