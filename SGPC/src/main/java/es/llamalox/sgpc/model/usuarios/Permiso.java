package es.llamalox.sgpc.model.usuarios;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import es.llamalox.sgpc.model.Accesible;

@Entity
@Table(name = "PERMISOS")
public class Permiso implements Serializable, Accesible {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO_LENGHT = 20;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permisoSequence")
	@SequenceGenerator(name = "permisoSequence", sequenceName = "PERMISO_SEQ")
	@Column(name = "PERMISO_ID", nullable = false)
	private Integer id;

	@Pattern(regexp = "[A-Za-z0-9_-]*", message = "Solo puede contener letras, números y los caracteres _-")
	@Column(name = "CODIGO", length = CODIGO_LENGHT, unique = true, nullable = false)
	private String codigo;

	@Column(name = "descripcion", nullable = true)
	private String descripcion;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permisos", cascade = CascadeType.ALL)
	private Set<Rol> roles;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(String type) {
		this.codigo = type;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public Date getFechaBaja() {
		return null;
	}

	@Override
	public void setFechaBaja(Date fechaBaja) {

	}

	@Override
	public boolean isActivo() {
		return true;
	}

	@Override
	public String toString() {
		return "Permiso [id=" + id + ", codigo=" + codigo + ", descripcion="
				+ descripcion + "]";
	}

}
