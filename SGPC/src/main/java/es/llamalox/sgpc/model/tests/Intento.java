package es.llamalox.sgpc.model.tests;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.llamalox.sgpc.model.usuarios.Usuario;

/**
 * http://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 * association-with-extra-columns-in-join-table-example
 */
@Entity
@Table(name = "INTENTOS")
@AssociationOverrides({
		@AssociationOverride(name = "primaryKey.usuario", joinColumns = @JoinColumn(name = "USUARIO_ID")),
		@AssociationOverride(name = "primaryKey.test", joinColumns = @JoinColumn(name = "TEST_ID")),
		@AssociationOverride(name = "primaryKey.pregunta", joinColumns = @JoinColumn(name = "PREGUNTA_ID")) })
public class Intento implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int ACIERTO_FALSO = 0;
	private static final int ACIERTO_VERDADERO = 1;

	private IntentoId primaryKey = new IntentoId();

	@NotNull
	@Min(ACIERTO_FALSO)
	@Max(ACIERTO_VERDADERO)
	@Column(name = "ACIERTO", nullable = false)
	private Integer acierto = ACIERTO_FALSO;

	@NotNull
	@Column(name = "INTENTOS", nullable = false)
	private Integer intentos = 0;

	@Transient
	private Respuesta respuesta;

	@EmbeddedId
	public IntentoId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(IntentoId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public Usuario getUsuario() {
		return getPrimaryKey().getUsuario();
	}

	public void setUsuario(Usuario usuario) {
		getPrimaryKey().setUsuario(usuario);
	}

	@Transient
	public Test getTest() {
		return getPrimaryKey().getTest();
	}

	public void setTest(Test test) {
		getPrimaryKey().setTest(test);
	}

	@Transient
	public Pregunta getPregunta() {
		return getPrimaryKey().getPregunta();
	}

	public void setPregunta(Pregunta pregunta) {
		getPrimaryKey().setPregunta(pregunta);
	}

	public Integer getAcierto() {
		return acierto;
	}

	public void setAcierto(Integer acierto) {
		this.acierto = acierto;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "Intento [ usuario=" + getUsuario().getCodigo() + ", test="
				+ getTest().getCodigo() + ", pregunta="
				+ getPregunta().getCodigo() + ", acierto=" + acierto
				+ ", intentos=" + intentos + "]";
	}

}
