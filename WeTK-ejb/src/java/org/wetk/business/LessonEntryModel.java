/*
 */
package org.wetk.business;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.wetk.business.local.ILessonEntry;
import org.wetk.model.AbstractEntity;
import org.wetk.model.ClassEntity;
import org.wetk.model.LessonEntry;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Stateless
public class LessonEntryModel extends AbstractModel implements ILessonEntry {

	@Override
	public org.wetk.model.LessonEntry getLessonEntryFor(ClassEntity clazz, Date date, int lessonHour) {
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
	public void save(AbstractEntity ent) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
