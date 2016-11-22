package es.llamalox.sgpc.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<PK extends Serializable, T> {

	T getByKey(PK key);

	T getByCodigo(String codigo);

	T save(T entity);

	T update(String codigo, T entity);

	boolean delete(T entity);

	boolean deleteByCodigo(String codigo);

	boolean reactivateByCodigo(String codigo);

	List<T> findAll();

}
