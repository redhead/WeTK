/*
 */
package org.wetk.business;

import org.wetk.business.local.IAbsence;
import org.wetk.dto.AbsenceDTO;
import org.wetk.entity.Absence;
import org.wetk.entity.LessonEntry;
import org.wetk.entity.Student;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class AbsenceModel extends AbstractModel<Absence, AbsenceDTO> implements IAbsence {

	@Override
	public void save(AbsenceDTO dto, Long studentId, Long lessonEntryId) {
		Absence absence = dtoToEntity(dto);

		Student student = find(Student.class, studentId);
		LessonEntry entry = find(LessonEntry.class, lessonEntryId);

		absence.setStudent(student);
		absence.setLessonEntry(entry);

		saveEntity(absence);
	}

}
