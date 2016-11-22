package es.llamalox.sgpc.model.usuarios;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import es.llamalox.sgpc.model.Accesible;

@Entity
@Table(name = "ROLES")
public class Rol implements Serializable, Accesible {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO_LENGHT = 20;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolSequence")
	@SequenceGenerator(name = "rolSequence", sequenceName = "ROL_SEQ")
	@Column(name = "ROL_ID", nullable = false)
	private Integer id;

	@Pattern(regexp = "[A-Za-z0-9_-]*", message = "Solo puede contener letras, números y los caracteres _-")
	@Column(name = "CODIGO", length = CODIGO_LENGHT, unique = true, nullable = false)
	private String codigo;

	@Column(name = "DESCRIPCION", nullable = true)
	private String descripcion;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Column(name = "FECHA_BAJA", nullable = true)
	private Date fechaBaja;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ROLES_PERMISOS", joinColumns = { @JoinColumn(name = "ROL_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERMISO_ID") })
	private Set<Permiso> permisos;

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
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Date getFechaBaja() {
		return fechaBaja == null ? null : (Date) fechaBaja.clone();
	}

	@Override
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja == null ? null : (Date) fechaBaja.clone();
	}

	@Override
	public boolean isActivo() {
		if (getFechaBaja() == null) {
			return true;
		}
		return false;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", codigo=" + codigo + ", descripcion="
				+ descripcion + ", fechaBaja=" + fechaBaja + "]";
	}

}
