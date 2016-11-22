package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.service.usuarios.RolService;

@Component
public class RolConverter implements Converter<String, Rol> {

	static final Logger LOGGER = LoggerFactory.getLogger(RolConverter.class);

	@Autowired
	private RolService service;

	@Override
	public Rol convert(String element) {
		Integer id = Integer.parseInt(element);
		Rol rol = service.getByKey(id);
		LOGGER.info("Rol : {}", rol);
		return rol;
	}

}