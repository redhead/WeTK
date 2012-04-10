/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import org.wetk.business.local.ISubject;
import org.wetk.dto.SubjectDTO;
import org.wetk.model.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class SubjectModel extends AbstractModel implements ISubject {

	@Override
	public List<Subject> getAllSubjects() {
		return getEntityManager().createNamedQuery(Subject.GET_ALL_SUBJECTS).getResultList();
	}


	@Override
	public void save(SubjectDTO dto) {
		Subject entity = dtoToEntity(dto);
		saveEntity(entity);
	}


	@Override
	public Subject find(Long id) {
		return getEntityManager().find(Subject.class, id);
	}


	@Override
	public void delete(Long id) {
		Subject entity = find(id);
		if(entity != null) {
			getEntityManager().remove(entity);
		}
	}


	private Subject dtoToEntity(SubjectDTO dto) {
		if(dto.getId() != null) {
			Subject subject = find(dto.getId());
			if(subject != null) {
				return dto.toEntity(subject);
			}
		}
		return dto.toEntity(new Subject());
	}

}
