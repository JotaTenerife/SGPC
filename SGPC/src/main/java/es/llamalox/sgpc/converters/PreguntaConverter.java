package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.service.tests.PreguntaService;

@Component
public class PreguntaConverter implements Converter<String, Pregunta> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(PreguntaConverter.class);

	@Autowired
	private PreguntaService service;

	@Override
	public Pregunta convert(String element) {
		Integer id = Integer.parseInt(element);
		Pregunta test = service.getByKey(id);
		LOGGER.info("Pregunta : {}", test);
		return test;
	}

}
