package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.usuarios.UsuarioService;

public class UsuarioConverter implements Converter<String, Usuario> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(UsuarioConverter.class);

	@Autowired
	private UsuarioService service;

	@Override
	public Usuario convert(String element) {
		Integer id = Integer.parseInt(element);
		Usuario usuario = service.getByKey(id);
		LOGGER.info("Usuario : {}", usuario.toString());
		return usuario;
	}

}
