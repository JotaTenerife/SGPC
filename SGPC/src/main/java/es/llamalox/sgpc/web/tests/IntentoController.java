package es.llamalox.sgpc.web.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.tests.IntentoService;
import es.llamalox.sgpc.service.tests.PreguntaService;
import es.llamalox.sgpc.service.tests.TestService;
import es.llamalox.sgpc.service.usuarios.RolService;
import es.llamalox.sgpc.service.usuarios.UsuarioService;
import es.llamalox.sgpc.web.BaseController;
import es.llamalox.sgpc.web.GenericController;

@Controller
@RequestMapping("/tests")
@SessionAttributes("usuario, preguntas, roles, loggedinuser, basepath, model")
public class IntentoController implements GenericController {

	static final Logger LOGGER = LoggerFactory
			.getLogger(IntentoController.class);

	private static final String MODEL = "Usuario";
	private static final String BASE = "/tests";
	private static final String MESSAGE_START = "El test ";

	@Autowired
	private UsuarioService service;

	@Autowired
	private BaseController controller;

	@Autowired
	private RolService rolService;

	@Autowired
	private TestService testService;

	@Autowired
	private PreguntaService preguntaService;

	@Autowired
	private IntentoService intentoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		Usuario objeto = service.getByCodigo(controller.getCurrentUserCode());
		model.addAttribute("intentoService", intentoService);
		model.addAttribute("codigoUsuario", controller.getCurrentUserCode());
		model.addAttribute(OBJETO, objeto);
		return MODEL + "Inicio" + LIST;
	}

	@RequestMapping(value = { PATH }, method = RequestMethod.GET)
	public String view(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Test objeto = testService.getByCodigo(codigo);
		if (objeto == null) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
		intentoService.limpiaIntentos(objeto,
				service.getByCodigo(controller.getCurrentUserCode()));
		Set<Pregunta> preguntas = objeto.getPreguntas();
		List<Pregunta> list = new ArrayList<Pregunta>(preguntas);
		Collections.sort(list, new Comparator<Pregunta>() {
			@Override
			public int compare(Pregunta p1, Pregunta p2) {
				return p1.getId() - p2.getId();
			}
		});
		int numPreguntas = objeto.getPreguntas().size();
		int numIntento = 0;
		if (objeto.getIntentos().iterator().hasNext()) {
			numIntento = objeto.getIntentos().iterator().next().getIntentos();
		}
		model.addAttribute(
				"aprobado",
				intentoService.isAprobado(objeto.getCodigo(),
						controller.getCurrentUserCode()));
		model.addAttribute("primeraPregunta", list.get(0).getCodigo());
		model.addAttribute("numPreguntas", numPreguntas);
		model.addAttribute("numIntento", numIntento);
		model.addAttribute(OBJETO, objeto);
		return MODEL + "Test" + "Hacer";
	}

	@RequestMapping(value = { "/{test}/{pregunta}" }, method = RequestMethod.GET)
	public String verPregunta(@PathVariable("test") String test,
			@PathVariable("pregunta") String pregunta, ModelMap model,
			RedirectAttributes attributes) {
		Intento objeto = intentoService.getIntento(test, pregunta,
				controller.getCurrentUserCode());
		if (objeto == null) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + pregunta
					+ " del test " + test + " no existe");
			return REDIRECT + BASE;
		}
		if (objeto.getIntentos() >= objeto.getTest().getOportunidades()) {
			attributes.addFlashAttribute(ERROR,
					"Ha superado el número de intentos para este test");
			return REDIRECT + BASE;
		}
		Pregunta preguntaActual = preguntaService.getByCodigo(pregunta);
		model.addAttribute("respuestas", preguntaActual.getRespuestas());
		model.addAttribute(OBJETO, objeto);
		return "IntentoPregunta" + FORM;
	}

	@RequestMapping(value = { "/{test}/{pregunta}" }, method = RequestMethod.POST)
	public String guardarPregunta(@RequestParam Respuesta respuesta,
			@PathVariable("test") String test,
			@PathVariable("pregunta") String pregunta, ModelMap model,
			RedirectAttributes attributes) {
		try {
			Test testActual = testService.getByCodigo(test);
			Pregunta preguntaActual = preguntaService.getByCodigo(pregunta);
			Usuario usuario = service.getByCodigo(controller
					.getCurrentUserCode());

			// Crea los datos del nuevo intento on los datos suministrados y lo
			// actualiza
			Intento intento = new Intento();
			intento.setTest(testService.getByCodigo(test));
			intento.setPregunta(preguntaActual);
			intento.setUsuario(usuario);
			intento.setAcierto(respuesta.getCorrecta());
			intento.setIntentos(0); // Será sobreescrito
			intentoService.update(intento);

			// Busca la pregunta siguiente del test o termina

			// Ordena la lista de preguntas por ID
			Set<Pregunta> preguntas = testActual.getPreguntas();
			List<Pregunta> list = new ArrayList<Pregunta>(preguntas);
			Collections.sort(list, new Comparator<Pregunta>() {
				@Override
				public int compare(Pregunta p1, Pregunta p2) {
					return p1.getId() - p2.getId();
				}
			});

			int indice = 0;
			for (Pregunta p : list) {
				if (p.getId().equals(preguntaActual.getId())) {
					break;
				}
				indice++;
			}
			LOGGER.info("Indice : {}", indice);
			try {
				Pregunta preguntaSiguiente = list.get(indice + 1);
				return "redirect:" + BASE + "/" + testActual.getCodigo() + "/"
						+ preguntaSiguiente.getCodigo();
			} catch (IndexOutOfBoundsException e) {
				return "redirect:" + BASE;
			}

		} catch (Exception e) {
			Intento objeto = intentoService.getIntento(test, pregunta,
					controller.getCurrentUserCode());
			model.addAttribute(EDIT, true);
			model.addAttribute(OBJETO, objeto);
			model.addAttribute(ERROR, e.getMessage());
			return "IntentoPregunta" + FORM;
		}

	}

	@ModelAttribute("roles")
	public List<Rol> initializeProfiles() {
		return rolService.findAll();
	}

	@ModelAttribute("loggedinuser")
	public String getLoggedInUser() {
		return controller.getCurrentUser();
	}

	@ModelAttribute("basepath")
	public String getPath() {
		return IntentoController.BASE;
	}

	@ModelAttribute("model")
	public String getModel() {
		return MODEL;
	}
}