/*
 */
package org.wetk.business.local;

import java.util.List;
import javax.ejb.Local;
import org.wetk.dto.GradeDTO;
import org.wetk.dto.NoteDTO;
import org.wetk.entity.Grade;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface IGrade extends IAbstractModel<Grade> {

	public List<NoteDTO> getAllFor(Long studentId);


	public void create(GradeDTO grade, Long studentId, Long subjectId);


	public void edit(GradeDTO grade);

}
