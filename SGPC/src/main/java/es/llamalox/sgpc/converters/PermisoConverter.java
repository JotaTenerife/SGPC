package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.service.usuarios.PermisoService;

@Component
public class PermisoConverter implements Converter<String, Permiso> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(PermisoConverter.class);

	@Autowired
	private PermisoService service;

	@Override
	public Permiso convert(String element) {
		Integer id = Integer.parseInt(element);
		Permiso permiso = service.getByKey(id);
		LOGGER.info("Permiso : {}", permiso.toString());
		return permiso;
	}

}
