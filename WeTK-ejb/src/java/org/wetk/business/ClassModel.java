/*
 */
package org.wetk.business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.IClass;
import org.wetk.dto.ClassDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class ClassModel extends AbstractModel<ClassEntity, ClassDTO> implements IClass {

	@Override
	public List<ClassEntity> getAll() {
		return getEntityManager().createNamedQuery(ClassEntity.GET_ALL_CLASSES).getResultList();
	}


	@Override
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

}
