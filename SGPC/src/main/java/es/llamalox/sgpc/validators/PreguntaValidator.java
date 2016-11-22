package es.llamalox.sgpc.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;

@Component
public class PreguntaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Pregunta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Pregunta pregunta = (Pregunta) target;
		int suma = 0;
		List<Respuesta> respuestas = pregunta.getRespuestas();
		if (respuestas == null || respuestas.size() < 2) {
			errors.rejectValue("respuestas", "pregunta.respuesta.minimo");
		} else {
			for (Respuesta respuesta : pregunta.getRespuestas()) {
				suma += respuesta.getCorrecta();
			}
			if (suma != 1) {
				errors.rejectValue("respuestas",
						"pregunta.respuesta.acertadas.invalidas");
			}
		}
	}
}
