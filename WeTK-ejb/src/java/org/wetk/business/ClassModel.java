/*
 */
package org.wetk.business;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.IClass;
import org.wetk.dto.ClassDTO;
import org.wetk.dto.StudentAbsenceDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
@RolesAllowed("teacher")
public class ClassModel extends AbstractModel<ClassEntity, ClassDTO> implements IClass {

	@Override
	public List<ClassEntity> getAll() {
		return getEntityManager().createNamedQuery(ClassEntity.GET_ALL_CLASSES).getResultList();
	}


	@Override
	@RolesAllowed("admin")
	public void save(ClassDTO dto, Long teacherId) {
		ClassEntity clazz = dtoToEntity(dto);

		Teacher teacher = getReference(Teacher.class, teacherId);
		clazz.setTeacher(teacher);

		saveEntity(clazz);
	}


	@Override
	public List<ClassEntity> getAllFor(Teacher teacher) {
		if(teacher == null) return new ArrayList<ClassEntity>();

		Query query = getEntityManager().createNamedQuery(ClassEntity.GET_ALL_FOR_TEACHER);
		query.setParameter("teacher", teacher);

		List<ClassEntity> list = query.getResultList();

		if(teacher.getClazz() != null) {
			ClassEntity tClass = teacher.getClazz();
			for(ClassEntity clazz : list) {
				if(clazz.equals(tClass)) {
					return list;
				}
			}
			list.add(0, tClass);
		}

		return list;
	}


	@Override
	public List<ClassEntity> getAllExcept(List<ClassEntity> except) {
		if(!except.isEmpty()) {
			Query query = getEntityManager().createNamedQuery(ClassEntity.GET_ALL_EXCEPT);
			query.setParameter("classes", except);
			return query.getResultList();
		} else {
			Query query = getEntityManager().createNamedQuery(ClassEntity.GET_ALL_CLASSES);
			return query.getResultList();
		}
	}


	@Override
	public List<StudentAbsenceDTO> getStudentsFor(long classId) {
		ClassEntity clazz = getReference(classId);
		Query q = getEntityManager().createQuery("SELECT NEW org.wetk.dto.StudentAbsenceDTO(s, "
				+ "SUM(CASE WHEN a.exuse IS NULL THEN 1 ELSE 0 END)) "
				+ "FROM Student AS s "
				+ "LEFT JOIN s.absences a "
				+ "WHERE s.clazz = :class "
				+ "GROUP BY s "
				+ "ORDER BY s.ordinal");
		q.setParameter("class", clazz);
		return q.getResultList();
	}

}
