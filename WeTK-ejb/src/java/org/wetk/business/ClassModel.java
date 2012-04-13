/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
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
		return getEntityManager().createNamedQuery(ClassEntity.GET_ALL_SUBJECTS).getResultList();
	}


	@Override
	public void save(ClassDTO dto, Long teacherId) {
		ClassEntity clazz = dtoToEntity(dto);

		Teacher teacher = getReference(Teacher.class, teacherId);
		clazz.setTeacher(teacher);

		saveEntity(clazz);
	}

}
