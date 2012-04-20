/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import org.wetk.Utils;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "days")
@ApplicationScoped
public class DaysBean {

	public List<SelectItem> getSelectItems() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		items.add(new SelectItem(Calendar.MONDAY, Utils.getString("day_mon")));
		items.add(new SelectItem(Calendar.TUESDAY, Utils.getString("day_tue")));
		items.add(new SelectItem(Calendar.WEDNESDAY, Utils.getString("day_wed")));
		items.add(new SelectItem(Calendar.THURSDAY, Utils.getString("day_thu")));
		items.add(new SelectItem(Calendar.FRIDAY, Utils.getString("day_fri")));

		return items;
	}


	public String getDayTitle(int day) {
		switch(day) {
			case Calendar.MONDAY:
				return Utils.getString("day_mon");
			case Calendar.TUESDAY:
				return Utils.getString("day_tue");
			case Calendar.WEDNESDAY:
				return Utils.getString("day_wed");
			case Calendar.THURSDAY:
				return Utils.getString("day_thu");
			case Calendar.FRIDAY:
				return Utils.getString("day_fri");
		}
		return "";
	}


	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}

}
