package es.llamalox.sgpc.dao.usuarios;

import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.usuarios.Rol;

@Repository
public class RolDaoImpl extends GenericDaoImpl<Integer, Rol> implements RolDao {

}