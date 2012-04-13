/*
 */
package org.wetk.business;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.wetk.business.local.ILessonEntry;
import org.wetk.dto.LessonEntryDTO;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonEntryModel extends AbstractModel<LessonEntry, LessonEntryDTO> implements ILessonEntry {

	@Override
	public LessonEntry getLessonEntryFor(ClassEntity clazz, Date date, int lessonHour) {
		Query query = getEntityManager().createNamedQuery(LessonEntry.GET_BY_CLASS_DATE_HOUR);
		query.setParameter("class", clazz);
		query.setParameter("date", date);
		query.setParameter("lessonHour", lessonHour);
		try {
			return (LessonEntry) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}


	@Override
	public void save(LessonEntryDTO dto) {
		/** @TODO */
	}

}
