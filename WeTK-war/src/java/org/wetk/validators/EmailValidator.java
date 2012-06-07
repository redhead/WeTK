package org.wetk.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

	private static final String EMAIL_REGEXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern mask;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		mask = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = mask.matcher(email);

		if (!matcher.matches()) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Prosím, vložte správný email");
            message.setSummary("Email není vpořádku");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
	}
}
