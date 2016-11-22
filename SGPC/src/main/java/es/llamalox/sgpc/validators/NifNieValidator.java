package es.llamalox.sgpc.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NifNieValidator implements ConstraintValidator<NifNie, String> {

	static final Logger LOGGER = LoggerFactory.getLogger(NifNieValidator.class);
	private static final int MODULE23 = 23;
	private Pattern mask = Pattern.compile("[XYZ0-9][0-9]{7,7}[A-Z]");

	@Override
	public void initialize(NifNie constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null || value.isEmpty()) {
			return false;
		}

		final Matcher matcher = mask.matcher(value);

		if (!matcher.matches()) {
			return false;
		}

		String nie = value;

		switch (value.charAt(0)) {
		case 'X':
			nie = nie.replaceFirst("X", "0");
			break;
		case 'Y':
			nie = nie.replaceFirst("Y", "1");
			break;
		case 'Z':
			nie = nie.replaceFirst("Z", "2");
			break;
		default:
			break;
		}

		final String dni = nie.substring(0, 8);
		final String control = nie.substring(8, 9);
		final String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
		final int position = Integer.parseInt(dni) % MODULE23;

		final String controlCalculated = letters.substring(position,
				position + 1);

		if (!control.equalsIgnoreCase(controlCalculated)) {
			return false;
		}
		return true;
	}
}
