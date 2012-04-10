/*
 */
package org.wetk;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class Utils {

	public static String message(String key) {
		String text;
		try {
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle("org.wetk.translation", locale);
			
			text = bundle.getString(key);
		} catch(Exception e) {
			text = "???" + key + "???";
		}
		return text;
	}

}
