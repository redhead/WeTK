/*
 */
package org.wetk.back;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.wetk.Utils;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
@ManagedBean(name = "days")
@RequestScoped
public class DaysBean {

	public List<SelectItem> getSelectItems() {
		List<SelectItem> items = new ArrayList<SelectItem>();

		items.add(new SelectItem(Calendar.MONDAY, Utils.message("day_mon")));
		items.add(new SelectItem(Calendar.TUESDAY, Utils.message("day_tue")));
		items.add(new SelectItem(Calendar.WEDNESDAY, Utils.message("day_wed")));
		items.add(new SelectItem(Calendar.THURSDAY, Utils.message("day_thu")));
		items.add(new SelectItem(Calendar.FRIDAY, Utils.message("day_fri")));

		return items;
	}
	
}
