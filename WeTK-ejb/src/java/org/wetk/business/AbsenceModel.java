/*
 */
package org.wetk.business;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import org.wetk.business.local.IAbsence;
import org.wetk.dto.AbsenceDTO;
import org.wetk.entity.Absence;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
@RolesAllowed("teacher")
public class AbsenceModel extends AbstractModel<Absence, AbsenceDTO> implements IAbsence {

	@Override
	public List<AbsenceDTO> getFor(long studentId) {
		Student s = find(Student.class, studentId);
		List<Absence> absences = s.getAbsences();
		List<AbsenceDTO> list = new ArrayList<AbsenceDTO>();
		for(Absence a : absences) {
			list.add(new AbsenceDTO(a));
		}
		return list;
	}


	@Override
	public void saveExcuse(AbsenceDTO a) {
		String excuse = a.getExcuse();
		if(excuse.trim().isEmpty()) {
			excuse = null;
		}
		Absence absence = getReference(a.getId());
		absence.setExuse(excuse);
		saveEntity(absence);
	}

}
