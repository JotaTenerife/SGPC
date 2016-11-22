package es.llamalox.sgpc.model.tests;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import es.llamalox.sgpc.model.usuarios.Usuario;

/**
 * http://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 * association-with-extra-columns-in-join-table-example
 */
@Embeddable
public class IntentoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Test test;
	private Pregunta pregunta;

	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
