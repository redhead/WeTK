/*
 */
package org.wetk.business;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import org.wetk.business.local.ISubject;
import org.wetk.dto.SubjectDTO;
import org.wetk.entity.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
@RolesAllowed("teacher")
public class SubjectModel extends AbstractModel<Subject, SubjectDTO> implements ISubject {

	@Override
	public List<Subject> getAll() {
		return getEntityManager().createNamedQuery(Subject.GET_ALL_SUBJECTS).getResultList();
	}


	@Override
	@RolesAllowed("admin")
	public void save(SubjectDTO dto) {
		Subject entity = dtoToEntity(dto);
		saveEntity(entity);
	}

}
