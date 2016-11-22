package es.llamalox.sgpc.dao.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.model.usuarios.Rol;

@Repository
public class PermisoDaoImpl extends GenericDaoImpl<Integer, Permiso> implements
		PermisoDao {

	@Autowired
	private RolDao rolDao;

	@Override
	public boolean reactivateByCodigo(String codigo) {
		return true;
	}

	/**
	 * Sincroniza la relación entre roles y permisos.
	 */
	@Override
	public Permiso update(String codigo, Permiso entity) {
		try {
			Permiso original = getByCodigo(codigo);
			original = (Permiso) getSession().merge(entity);
			List<Rol> roles = rolDao.findAll();
			for (Rol rol : roles) {
				if (rol.getPermisos().contains(original)) {
					if (!original.getRoles().contains(rol)) {
						rol.getPermisos().remove(original);
					}
				} else {
					if (original.getRoles().contains(rol)) {
						rol.getPermisos().add(original);
					}
				}
			}
			return original;
		} catch (Exception e) {
			return null;
		}
	}
}