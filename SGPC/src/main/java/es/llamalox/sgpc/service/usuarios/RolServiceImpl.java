package es.llamalox.sgpc.service.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class RolServiceImpl extends GenericServiceImpl<Integer, Rol> implements RolService {

	@Autowired
	public RolServiceImpl(GenericDao<Integer, Rol> genericDao) {
		super(genericDao);
	}

}