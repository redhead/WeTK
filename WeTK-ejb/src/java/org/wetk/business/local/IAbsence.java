/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.AbsenceDTO;
import org.wetk.entity.Absence;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IAbsence extends IAbstractModel<Absence> {

	public void saveExcuse(AbsenceDTO a);


	public List<AbsenceDTO> getFor(long studentId);

}
