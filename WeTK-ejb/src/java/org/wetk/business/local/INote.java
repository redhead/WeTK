/*
 */
package org.wetk.business.local;

import java.util.List;
import org.wetk.entity.Note;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public interface INote extends IAbstractModel<Note> {

	public List<Note> getAllFor(Long studentId);


	void save(Long lessonEntryId, Long studentId);

}
