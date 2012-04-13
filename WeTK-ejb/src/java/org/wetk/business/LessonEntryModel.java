/*
 */
package org.wetk.business;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.wetk.business.local.ILessonEntry;
import org.wetk.entity.AbstractEntity;
import org.wetk.entity.ClassEntity;
import org.wetk.entity.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonEntryModel extends AbstractModel/** @FIXME <LessonEntry, LessonEntryDTO> */
		implements ILessonEntry {

	@Override
	public List getAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}


	@Override
	public LessonEntry find(Long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}


	@Override
	public org.wetk.entity.LessonEntry getLessonEntryFor(ClassEntity clazz, Date date, int lessonHour) {
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

}
