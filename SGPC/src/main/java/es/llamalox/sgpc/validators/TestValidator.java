package es.llamalox.sgpc.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.llamalox.sgpc.model.tests.Test;

@Component
public class TestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Test.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Test test = (Test) target;
		if (test.getFechaFin().before(test.getFechaInicio())
				|| test.getFechaFin().equals(test.getFechaInicio())) {
			errors.rejectValue("fechaFin", "test.fechafin.minimo");
		}
	}
}
