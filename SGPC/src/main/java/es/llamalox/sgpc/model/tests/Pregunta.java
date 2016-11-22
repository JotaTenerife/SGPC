package es.llamalox.sgpc.model.tests;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import es.llamalox.sgpc.model.Accesible;

@Entity
@Table(name = "PREGUNTAS")
public class Pregunta implements Serializable, Accesible {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO_LENGHT = 20;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preguntaSequence")
	@SequenceGenerator(name = "preguntaSequence", sequenceName = "PREGUNTA_SEQ")
	@Column(name = "PREGUNTA_ID", nullable = false)
	private Integer id;

	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9_-]*", message = "Solo puede contener letras, números y los caracteres _-")
	@Column(name = "CODIGO", length = CODIGO_LENGHT, unique = true, nullable = false)
	private String codigo;

	@NotEmpty
	@Column(name = "TEXTO", unique = true, nullable = false)
	private String texto;

	@Column(name = "IMAGEN", nullable = true)
	private String imagen;

	@Column(name = "FECHA_BAJA", nullable = true)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date fechaBaja;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pregunta")
	private List<Respuesta> respuestas;

	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryKey.pregunta")
	// private Set<Intento> intentos;

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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	@Override
	public Date getFechaBaja() {
		return fechaBaja == null ? null : (Date) fechaBaja.clone();
	}

	@Override
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja == null ? null : (Date) fechaBaja.clone();
	}

	// public Set<Intento> getIntentos() {
	// return intentos;
	// }
	//
	// public void setIntentos(Set<Intento> intentos) {
	// this.intentos = intentos;
	// }

	@Override
	public boolean isActivo() {
		if (getFechaBaja() == null) {
			return true;
		}
		return false;
	}

	// public boolean isAsignado() {
	// if (getIntentos() == null || getIntentos().size() == 0) {
	// return false;
	// }
	// return true;
	// }

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", codigo=" + codigo + ", texto=" + texto
				+ ", imagen=" + imagen + ", fechaBaja=" + fechaBaja + "]";
	}
}