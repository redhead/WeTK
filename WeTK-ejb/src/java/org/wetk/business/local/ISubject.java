/*
 */
package org.wetk.business.local;

import javax.ejb.Local;
import org.wetk.dto.SubjectDTO;
import org.wetk.entity.Subject;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ISubject extends IAbstractModel<Subject> {

	public void save(SubjectDTO subject);

}
