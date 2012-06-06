package org.wetk.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jaroslav Medek
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		int atPosition = email.indexOf("@");
		int dotPosition = email.lastIndexOf(".");
		if (atPosition == -1 || dotPosition == -1 || atPosition > dotPosition) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Please enter a valid email");
			message.setSummary("Email not valid");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
