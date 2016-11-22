package es.llamalox.sgpc.model.tests;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import es.llamalox.sgpc.model.Accesible;

@Entity
@Table(name = "RESPUESTAS")
public class Respuesta implements Serializable, Accesible {

	private static final long serialVersionUID = 1L;
	private static final int RESPUESTA_CORRECTA = 0;
	private static final int RESPUESTA_INCORRECTA = 1;
	private static final int CODIGO_LENGHT = 40;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respuestaSequence")
	@SequenceGenerator(name = "respuestaSequence", sequenceName = "RESPUESTA_SEQ")
	@Column(name = "RESPUESTA_ID", nullable = false)
	private Integer id;

	@NotEmpty
	@Pattern(regexp = "[A-Za-z0-9_-]*", message = "Solo puede contener letras, números y los caracteres _-")
	@Column(name = "CODIGO", length = CODIGO_LENGHT, unique = true, nullable = false)
	private String codigo;

	@Column(name = "TEXTO", nullable = true)
	private String texto;

	@NotNull
	@Min(RESPUESTA_CORRECTA)
	@Max(RESPUESTA_INCORRECTA)
	@Column(name = "CORRECTA", nullable = false)
	private Integer correcta;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "PREGUNTA_ID")
	private Pregunta pregunta;

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

	public Integer getCorrecta() {
		return correcta;
	}

	public void setCorrecta(Integer correcta) {
		this.correcta = correcta;
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

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", codigo=" + codigo + ", texto="
				+ texto + ", correcta=" + correcta + "]";
	}

}