package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.service.tests.RespuestaService;

@Component
public class RespuestaConverter implements Converter<String, Respuesta> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(RespuestaConverter.class);

	@Autowired
	private RespuestaService service;

	@Override
	public Respuesta convert(String element) {
		Integer id = Integer.parseInt(element);
		Respuesta respuesta = service.getByKey(id);
		LOGGER.info("Respuesta : {}", respuesta.toString());
		return respuesta;
	}

}
