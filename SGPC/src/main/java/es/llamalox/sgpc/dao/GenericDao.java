package es.llamalox.sgpc.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {

	T getByKey(PK key);

	T getByCodigo(String codigo);

	T save(T entity);

	T update(String codigo, T entity);

	T update(T entity);

	boolean delete(T entity);

	boolean deleteByCodigo(String codigo);

	boolean reactivateByCodigo(String codigo);

	List<T> findAll();

}
