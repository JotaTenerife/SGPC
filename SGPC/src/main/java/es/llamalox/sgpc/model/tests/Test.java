package es.llamalox.sgpc.model.tests;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import es.llamalox.sgpc.model.Accesible;
import es.llamalox.sgpc.model.usuarios.Usuario;

@Entity
@Table(name = "TESTS")
public class Test implements Serializable, Accesible {

	static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
	private static final long serialVersionUID = 1L;
	private static final int OPORTUNIDADES_MIN = 1;
	private static final int CODIGO_LENGHT = 20;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testSequence")
	@SequenceGenerator(name = "testSequence", sequenceName = "TEST_SEQ")
	@Column(name = "TEST_ID", nullable = false)
	private Integer id;

	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9_-]*", message = "Solo puede contener letras, números y los caracteres _-")
	@Column(name = "CODIGO", length = CODIGO_LENGHT, unique = true, nullable = false)
	private String codigo;

	@NotEmpty
	@Column(name = "NOMBRE", unique = true, nullable = false)
	private String nombre;

	@NotEmpty
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@NotNull
	@Min(OPORTUNIDADES_MIN)
	@Column(name = "OPORTUNIDADES", nullable = true)
	private Integer oportunidades;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "FECHA_INICIO", nullable = false)
	private Date fechaInicio;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "FECHA_FIN", nullable = false)
	private Date fechaFin;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "FECHA_BAJA", nullable = true)
	private Date fechaBaja;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TESTS_PREGUNTAS", joinColumns = { @JoinColumn(name = "TEST_ID") }, inverseJoinColumns = { @JoinColumn(name = "PREGUNTA_ID") })
	private Set<Pregunta> preguntas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryKey.test")
	private Set<Intento> intentos;

	@Transient
	private Set<Usuario> usuarios;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOportunidades() {
		return oportunidades;
	}

	public void setOportunidades(Integer oportunidades) {
		this.oportunidades = oportunidades;
	}

	public Date getFechaInicio() {
		return fechaInicio == null ? null : (Date) fechaInicio.clone();
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio == null ? null : (Date) fechaInicio
				.clone();
	}

	public Date getFechaFin() {
		return fechaFin == null ? null : (Date) fechaFin.clone();
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin == null ? null : (Date) fechaFin.clone();
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

	public boolean isEnFecha() {
		Date ahora = new Date();
		if (fechaInicio.before(ahora) && fechaFin.after(ahora)) {
			return true;
		}
		return false;
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public Set<Intento> getIntentos() {
		return intentos;
	}

	public void setIntentos(Set<Intento> intentos) {
		this.intentos = intentos;
	}

	public Set<Usuario> getUsuarios() {
		if (usuarios == null) {
			setUsuarios();
		}
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	private void setUsuarios() {
		if (intentos != null) {
			Set<Usuario> usuariosCalculados = new HashSet<Usuario>();
			for (Intento intento : intentos) {
				usuariosCalculados.add(intento.getUsuario());
			}
			this.usuarios = usuariosCalculados;
		} else {
			this.usuarios = new HashSet<Usuario>();
		}

	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", oportunidades="
				+ oportunidades + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", fechaBaja=" + fechaBaja
				+ ", preguntas=" + preguntas + ", usuarios=" + usuarios + "]";
	}

}