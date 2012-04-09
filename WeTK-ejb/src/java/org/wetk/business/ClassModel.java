/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.IClass;
import org.wetk.dto.ClassDTO;
import org.wetk.model.ClassEntity;
import org.wetk.model.Teacher;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class ClassModel extends AbstractModel implements IClass {

	@Override
	public List<ClassEntity> getAllClasses() {
		return getEntityManager().createNamedQuery(ClassEntity.GET_ALL_SUBJECTS).getResultList();
	}


	@Override
	public void save(ClassDTO dto, Long teacherId) {
		ClassEntity clazz = dtoToEntity(dto);

		Teacher teacher = getEntityManager().getReference(Teacher.class, teacherId);
		clazz.setTeacher(teacher);

		saveEntity(clazz);
	}


	@Override
	public ClassEntity find(Long id) {
		return getEntityManager().find(ClassEntity.class, id);
	}


	@Override
	public void delete(Long id) {
		ClassEntity entity = find(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	private ClassEntity dtoToEntity(ClassDTO dto) {
		if(dto.getId() != null) {
			ClassEntity clazz = find(dto.getId());
			if(clazz != null) {
				return dto.toEntity(clazz);
			}
		}
		return dto.toEntity(new ClassEntity());
	}

}
