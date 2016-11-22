package es.llamalox.sgpc.service.usuarios;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.model.usuarios.Usuario;

public class DetalleUsuarioService implements UserDetailsService {

	static final Logger LOGGER = LoggerFactory
			.getLogger(DetalleUsuarioService.class);

	@Autowired
	private UsuarioService service;

	/**
	 * Busca el usuario en la base de datos y lo gestiona como user de Spring
	 * Security
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public UserDetails loadUserByUsername(String codigo)
			throws UsernameNotFoundException {
		Usuario usuario = service.getByCodigo(codigo);
		LOGGER.info("Usuario : {}", usuario);
		if (usuario == null) {
			LOGGER.info("Usuario no encontrado");
			throw new UsernameNotFoundException(
					"Nombre de usuario no encontrado");
		}
		return new org.springframework.security.core.userdetails.User(
				usuario.getCodigo(), usuario.getPassword(), usuario.isActivo(),
				true, true, true,
				getGrantedAuthorities(getPrivileges(usuario.getRol())));
	}

	/**
	 * Si el rol está activo asigna como privilegio los permisos asociados.
	 * 
	 * @param rol
	 *            rol al que pertenece el usuario
	 * @return Colección de privilegios
	 */
	private Collection<String> getPrivileges(Rol rol) {
		Collection<String> privileges = new ArrayList<String>();
		Collection<Permiso> collection = new ArrayList<Permiso>();
		if (rol.isActivo()) {
			collection.addAll(rol.getPermisos());
		}
		for (Permiso item : collection) {
			privileges.add(item.getCodigo());
		}
		return privileges;
	}

	/**
	 * Como queremos asignar los permisos a los módulos al completo los tratamos
	 * como si fueran roles de Spring Security.
	 * 
	 * @param privileges
	 *            Colección de permisos asociados al rol
	 * @return Autorizaciones gestionadas por Spring Security
	 */
	private Collection<GrantedAuthority> getGrantedAuthorities(
			Collection<String> privileges) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + privilege));
		}
		LOGGER.info("authorities : {}", authorities);
		return authorities;
	}

}