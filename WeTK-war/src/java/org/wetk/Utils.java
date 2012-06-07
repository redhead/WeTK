/*
 */
package org.wetk;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class Utils {

	public static String getString(String key) {
		return getString(key, new Object[0]);
	}


	public static String getString(String key, Object... obj) {
		String text;
		try {
			Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle("org.wetk.translation", locale);

			text = bundle.getString(key);

			if(obj != null && obj.length > 0) {
				return MessageFormat.format(text, obj);
			}
		} catch(Exception e) {
			text = "???" + key + "???";
		}
		return text;
	}


	public static ErrorType getErrorType(EJBException e) {
		Throwable ex = e.getCausedByException();
		while(ex.getCause() != null && ex.getCause() != ex) {
			ex = ex.getCause();
		}
		if(ex instanceof SQLException) {
			ErrorType type = getSQLErroType((SQLException) ex);
			if(type != null) return type;
		}
		throw e;
	}


	private static ErrorType getSQLErroType(SQLException ex) {
		int errorCode = ex.getErrorCode();
		switch(errorCode) {
			case 1062:
				return ErrorType.DUPLICATE;
		}
		return null;
	}


	public static void addMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(msg));
	}


	public static boolean validatePositive(Object value) {
		int number;
		if(value instanceof String) {
			try {
				number = Integer.parseInt((String) value);
			} catch(NumberFormatException e) {
				return false;
			}
		} else {
			number = (Integer) value;
		}
		if(number < 1) {
			return false;
		}
		return true;
	}

}
