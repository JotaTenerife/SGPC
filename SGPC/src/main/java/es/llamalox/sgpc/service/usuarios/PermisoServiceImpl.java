package es.llamalox.sgpc.service.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class PermisoServiceImpl extends GenericServiceImpl<Integer, Permiso> implements PermisoService {

	@Autowired
	public PermisoServiceImpl(GenericDao<Integer, Permiso> genericDao) {
		super(genericDao);
	}

}
