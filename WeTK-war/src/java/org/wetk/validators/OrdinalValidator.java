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
@FacesValidator("ordinalValidator")
public class OrdinalValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		int number = (Integer) value;
		if (number < 1) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Prosím, vložte správné číslo pořadí");
			message.setSummary("Pořadové číslo není vpořádku");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
