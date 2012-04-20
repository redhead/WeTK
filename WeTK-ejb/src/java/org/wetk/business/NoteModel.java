/*
 */
package org.wetk.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.wetk.business.local.INote;
import org.wetk.dto.NoteDTO;
import org.wetk.entity.Note;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class NoteModel extends AbstractModel<Note, NoteDTO> implements INote {

	@Override
	public List<Note> getAllFor(Long studentId) {
		Student student = getReference(Student.class, studentId);

		Query query = getEntityManager().createNamedQuery(Note.GET_ALL_FOR_STUDENT);
		query.setParameter("student", student);

		return query.getResultList();
	}


	@Override
	public void save(Long lessonEntryId, Long studentId) {
		/** @FIXME */
		throw new UnsupportedOperationException();
	}

}
