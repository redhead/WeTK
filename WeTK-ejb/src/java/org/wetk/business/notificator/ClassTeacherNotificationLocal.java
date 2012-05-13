/*
 */
package org.wetk.business.notificator;

import javax.ejb.Local;
import javax.ejb.Timer;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@Local
public interface ClassTeacherNotificationLocal {

	void notifyEndOfWeek(Timer timer);

}
