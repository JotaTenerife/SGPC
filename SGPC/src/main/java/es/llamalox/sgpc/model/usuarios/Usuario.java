package es.llamalox.sgpc.model.usuarios;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import es.llamalox.sgpc.model.Accesible;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.validators.NifNie;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable, Accesible {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO_SIZE = 7;
	private static final int PHONE_SIZE = 9;
	private static final int PHONE_FRACTION = 0;
	private static final int PHONE_INTEGER = 12;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSequence")
	@SequenceGenerator(name = "usuarioSequence", sequenceName = "USUARIO_SEQ")
	@Column(name = "USUARIO_ID", nullable = false)
	private Integer id;

	@NotEmpty
	@Size(min = CODIGO_SIZE, max = CODIGO_SIZE, message = "el tamaño tiene que ser de 7")
	@Pattern(regexp = "[A-Z0-9]*", message = "debe contener solo letras mayúsculas y números")
	@Column(name = "CODIGO", length = CODIGO_SIZE, unique = true, nullable = false)
	private String codigo;

	@NotEmpty
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@NotEmpty
	@NifNie
	@Column(name = "NIF_NIE", nullable = false)
	private String nifNie;

	@NotEmpty
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@NotEmpty
	@Column(name = "APELLIDO1", nullable = false)
	private String apellido1;

	@Column(name = "APELLIDO2", nullable = true)
	private String apellido2;

	@NotEmpty
	@Email
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Size(min = PHONE_SIZE, max = PHONE_SIZE, message = "el tamaño tiene que ser de 9")
	@Digits(fraction = PHONE_FRACTION, integer = PHONE_INTEGER, message = "formato incorrecto < 900000000 >")
	@Column(name = "TELEFONO", nullable = true)
	private String telefono;

	@Column(name = "FECHA_ALTA", nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date fechaAlta;

	@Column(name = "FECHA_BAJA", nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date fechaBaja;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
	private Rol rol;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryKey.usuario")
	private Set<Intento> intentos;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNifNie() {
		return nifNie;
	}

	public void setNifNie(String nifNie) {
		this.nifNie = nifNie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAlta() {
		return fechaAlta == null ? null : (Date) fechaAlta.clone();
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta == null ? null : (Date) fechaAlta.clone();
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Intento> getIntentos() {
		return intentos;
	}

	public void setIntentos(Set<Intento> intentos) {
		this.intentos = intentos;
	}

	public Set<Test> getTests() {
		Set<Test> tests = new HashSet<Test>();
		for (Intento intento : getIntentos()) {
			tests.add(intento.getTest());
		}
		return tests;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", codigo=" + codigo + ", nifNie="
				+ nifNie + ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + "]";
	}

}