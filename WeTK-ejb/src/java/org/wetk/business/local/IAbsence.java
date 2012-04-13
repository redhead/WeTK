/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.AbsenceDTO;
import org.wetk.entity.Absence;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IAbsence extends IAbstractModel<Absence> {

	public void save(AbsenceDTO dto, Long studentId, Long lessonEntryId);

}
