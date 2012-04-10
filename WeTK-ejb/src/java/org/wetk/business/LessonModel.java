/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.ILesson;
import org.wetk.dto.LessonDTO;
import org.wetk.model.ClassEntity;
import org.wetk.model.Lesson;
import org.wetk.model.SubjectAssignment;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonModel extends AbstractModel implements ILesson {

	@Override
	public List<Lesson> getAllLessons() {
		return getEntityManager().createNamedQuery(Lesson.GET_ALL_LESSONS).getResultList();
	}


	@Override
	public void save(LessonDTO dto, Long classId, Long assignmentId) {
		ClassEntity clazz = getEntityManager().getReference(ClassEntity.class, classId);
		SubjectAssignment assignment = getEntityManager().getReference(SubjectAssignment.class, assignmentId);

		Lesson entity = dtoToEntity(dto);
		entity.setClazz(clazz);
		entity.setAssignment(assignment);

		saveEntity(entity);
	}


	@Override
	public Lesson find(Long id) {
		return getEntityManager().find(Lesson.class, id);
	}


	@Override
	public void delete(Long id) {
		Lesson entity = find(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	private Lesson dtoToEntity(LessonDTO dto) {
		if(dto.getId() != null) {
			Lesson lesson = find(dto.getId());
			if(lesson != null) {
				return dto.toEntity(lesson);
			}
		}
		return dto.toEntity(new Lesson());
	}

}
