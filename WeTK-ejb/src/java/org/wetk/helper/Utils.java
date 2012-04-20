/*
 */
package org.wetk.helper;

import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class Utils {

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

}
